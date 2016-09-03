/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Product;

import UI.Utilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import libreria.DataBase;



/**
 *
 * @author Wauricio
 */
public class Book implements Item{
    DataBase db;
    private final String idTableName="idlibro";
    private final  String driver="org.postgresql.Driver", url="jdbc:postgresql://localhost:5432/",dbname="Libros",dbUser="postgres",dbPwd="root"; 
    private final String[] type_sales={"IdVenta","Fecha","Empleado"} ;
    private final String[] type_search={"Nombre","Costo","Autor","Editorial","Genero"} ;
    
    public Book()throws Exception{
       db=new DataBase(url,dbname,driver,dbUser,dbPwd);
    }

    @Override
    public ResultSet getAll() throws SQLException {
        return DataBase.executeSQL(db.getConnection(),"select * from libro order by idlibro");
    }

    @Override
    public ResultSet searchbyType(String value, String type) throws SQLException {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String qry="select c.idlibro,c.nombre , c.existencias , c.costo , c.editorial ,g.nombre as genero from "
                +getTableName()+ " c , genero g  where c.idgenero=g.idgenero";
      switch(type.toLowerCase()){
            
            case "nombre":
                qry+=" and c.nombre like '%"+value.toUpperCase()+"%'";
                break;
            case "costo":
                qry+=" and c.costo BETWEEN "+value.substring(0,value.indexOf("-"))+" and "+value.substring(value.indexOf("-")+1,value.length()) +" order by c.costo";
                break;
            case "autor":
                    qry="select c.idlibro,c.nombre , c.existencias , c.costo , c.editorial ,g.nombre from "
                            +getTableName()+ " c , genero g , autor a , libroautor la where c.idgenero=g.idgenero and la.idlibro=c.idlibro"
                            + " and la.idautor=a.idautor and a.nombre like '%"+value+"%'" ;
                break;
            case "editorial":
                 qry+=" and c.editorial like '%"+ value.toUpperCase()+ "%'";
                break;
                
            case "genero":
                    qry+=" and g.nombre like '%"+value+"%'";
                break;
            
        }
      System.out.println("sales type");
      System.out.println(qry);
       return DataBase.executeSQL(db.getConnection(), qry);
        
    }

    @Override
    public ResultSet getAllSales() throws SQLException {
      return DataBase.executeSQL(db.getConnection(),"select v.idVenta , l.nombre as Titulo, v.Monto ,v.fecha , e.nombre as Empleado  from venta v , empleado e , libro l ,libroventa lv where v.idEmpleado=e.idEmpleado " +
       "and lv.idVenta=v.idVenta and lv.idLibro=l.idLibro order by v.idVenta");
              
    }

    @Override
    public ResultSet getSalesByType(String value ,String type) throws SQLException {
        String qry="select v.idVenta , l.nombre as Titulo, v.Monto ,v.fecha , e.nombre as Empleado  from venta v , empleado e , libro l ,libroventa lv where v.idEmpleado=e.idEmpleado " +
       "and lv.idVenta=v.idVenta and lv.idLibro=l.idLibro ";
        switch(type.toLowerCase()){
            case "idventa":
                qry+=" and v.idVenta="+value;
                break;
            case "fecha":
                qry+=" and v.fecha='"+value+"'";
                break;
            case "empleado":
                qry+=" and e.nombre like '%"+value.toUpperCase()+"%' ";
                break;
        }
        return DataBase.executeSQL(db.getConnection(), qry + " order by v.idVenta ");
    }


    @Override
    public void deleteProduc() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void UpdateProduct() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getSearch_Type() {
       return type_search;
    }

    @Override
    public String[] getSales_Type() {
       return type_sales;
    }

    @Override
    public ResultSet getByID(String id) throws SQLException {
        return DataBase.executeSQL(db.getConnection(),"select * from libro where idlibro="+id);
    }

    @Override
    public DataBase getdb() {
        return db;
    }

    @Override
    public void insertData(String table, Map<String, String> values) throws SQLException {
        int numVal=values.size() ;
        int i=1;
        db.getConnection().setAutoCommit(false);
        Map<String, String> mtData = DataBase.getMetaDataColumns(db.getConnection(), table);
        String qry="INSERT INTO "+table+" "+ Utilities.setColumnNames(values) + " VALUES"+Utilities.valuesSql(numVal);
          PreparedStatement statement= db.getConnection().prepareStatement(qry);
          for (Map.Entry<String, String> entry : values.entrySet()){
               Utilities.setDataType(statement,mtData.get(entry.getKey()),entry.getValue(), i);
                i++;
            }
           System.out.println(statement.toString());
           
           statement.execute();
         db.getConnection().commit();
    
    }

    @Override
    public void insertProduc() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTableName() {
        return "libro";
    }

    @Override
    public ArrayList<RelationDB> getRelations(){
       ArrayList<RelationDB> rl = new ArrayList<RelationDB>();
        try{
        Map<String , String > aut = new HashMap<String,String>();
        ResultSet rst=db.getConnection().createStatement().executeQuery("select * from autor");
         
        while(rst.next()){
            aut.put(rst.getString("nombre")+ " "+ rst.getString("apellidos"),rst.getString("idautor"));
            
        }
        rl.add(new RelationDB("AUTOR","libroautor",aut,"idautor"));
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        
       return rl;
    }

    @Override
    public String getNameID_DB() {
        return "idlibro";
    }

    @Override
    public String insertRelationData(String valitem, String valitem2, String idNameMatch , String table) {
         return "insert into "+table+"("+idNameMatch+","+idTableName+")"+ " values("+valitem+","+valitem2+")";
    }

    @Override
    public String deleteRelationData(String valitem, String valitem2, String idNameMatch, String table) {
         return "delete from "+table+" where "+idNameMatch+"="+valitem+"  and "+ idTableName +"="+valitem2;
    }

    @Override
    public String UpdateInfo(String value , String columnName ,String id) {
         return "update "+getTableName()+" set "+columnName+"= '"+value+"' where "+getNameID_DB()+"="+id;
    }

    @Override
    public ResultSet getAllMatch() throws SQLException {
       return DataBase.executeSQL(db.getConnection(),"select l.idlibro ,l.nombre , l.costo,l.existencias , g.nombre from libro l , genero g where l.idgenero=g.idgenero");
    }

    @Override
    public HashMap<String, String> getToSale(String id) throws SQLException {
        HashMap<String , String> data = new HashMap<String,String>();
        ResultSet rst = DataBase.executeSQL(db.getConnection(),"select * from "+getTableName()+" where "+getNameID_DB()+"="+id);
       if(rst.next()){
        data.put("id",rst.getString(getNameID_DB()));
        data.put("name",rst.getString("nombre"));
        data.put("cost", rst.getString("costo"));   
           
       }
        return data;
    
    }
    
    
}
