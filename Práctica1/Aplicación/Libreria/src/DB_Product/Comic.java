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
public class Comic implements Item {
    final private DataBase db;
    final private String idTableName="idComic";
    final private  String driver="com.mysql.jdbc.Driver" , url= "jdbc:mysql://localhost:3306/",dbname="comics",dbUser="root",dbPwd="root"; 
    final private String[] type_sales={"Fecha","Empleado","IDVenta"} ;
    final private String[] type_search={"Titulo","Costo","Fecha","Autor","Tema", "Saga"} ;
    
    
    
    public Comic()throws Exception{
      db=new DataBase(url,dbname,driver,dbUser,dbPwd);
      
    }

    @Override
    public ResultSet getAll() throws SQLException {
            return  db.getConnection().createStatement().executeQuery("select * from "+getTableName());
        }

    
    
    @Override
    public ResultSet searchbyType(String value, String type) throws SQLException {
        String qry;
        switch(type.toLowerCase()){
            case "titulo":
                qry="select * from "+getTableName() +" where titulo like'%"+value.toLowerCase()+"%'";
                break;
            case "costo":
                qry= "select * from "+ getTableName() + " where costo BETWEEN "+value.substring(0,value.indexOf("-"))+" and "+value.substring(value.indexOf("-")+1,value.length()) + " order by costo";
                break;
            case "fecha":
                qry="select * from "+getTableName()+ " where fechaPublicacion like '%"+value.toLowerCase()+"%'";
                break;
            case "autor":
                qry ="select c.* ,CONCAT (a.nombre ,' ', a.apellidos) as autor  from "+ getTableName()+" c , autor a , comicautor ca "
                        + "where c.idComic=ca.idComic and a.idAutor=ca.idAutor and a.nombre like '%"+value.toLowerCase() +
                        "%' or a.apellidos like '%"+value.toLowerCase()+"%'";
                break;
            case "tema":
                qry="select c.* from "+getTableName()+ " c , tema t ,comictema ct where c.idComic=ct.idComic "
                        + "and ct.idTema=t.idTema and t.nombre like '%"+value.toLowerCase()+"%'";
                break;
            case "saga":
                qry="select c.* from "+getTableName()+" c , saga s where c.idSaga=s.idSaga and s.nombre like '%"+value.toLowerCase()+"%'";
                
                    break;
            default :
                qry="select * from "+getTableName();
                break;   
        }
        System.out.println(qry);
        return DataBase.executeSQL(db.getConnection(), qry); 
    }

    @Override
    public ResultSet getAllSales() throws SQLException {
    return  db.getConnection().createStatement().executeQuery("select s.* , from venta");

    }

    @Override
    public ResultSet getSalesByType(String Value, String Type) throws SQLException {
       String qry="select s.idVenta , s.monto , s.fecha , c.titulo , cv.cantidad , e.nombre as Empleado from venta s , comic c , comicventa cv , empleado e " +
"where s.idVenta=cv.idVenta and c.idComic=cv.idComic and e.idEmpleado=s.idEmpleado ";
       switch(Type.toLowerCase()){
           case "fecha":
               qry+="and s.fecha like '%"+Value.toLowerCase()+"%'";
               break;
           case "idventa":
                qry+="and s.idVenta="+Value;
               break;
           case "empleado":
               qry+= " and e.nombre like '%"+Value.toLowerCase()+"%' or e.apellidoP like '%"+Value.toLowerCase()+"%' or e.apellidoM like '%"+Value.toLowerCase()+"%'";
               break;
           default:
               break;  
       }
       qry+= " order by s.idVenta ";
       return DataBase.executeSQL(db.getConnection(),qry);
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
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       return db.getConnection().createStatement().executeQuery("select * from comic where idcomic="+id);
    }

    @Override
    public DataBase getdb() {
      return db;
    }

    @Override
    public void  insertData(String table, Map<String, String> values) throws  SQLException  {
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
        return "comic";
    }

    @Override
    public ArrayList<RelationDB> getRelations()  {
       ArrayList<RelationDB> rl = new ArrayList<RelationDB>();
        try{
        Map<String , String > themes = new HashMap<String,String>();
        ResultSet rst=db.getConnection().createStatement().executeQuery("select * from tema");
         
        while(rst.next()){
            themes.put(rst.getString("nombre"),rst.getString("idTema"));
            
        }
        rl.add(new RelationDB("TEMA","comictema",themes,"idTema"));/**/
        Map<String , String > author = new HashMap<String,String>();
        rst=db.getConnection().createStatement().executeQuery("select * from autor");
        while(rst.next()){
            author.put(rst.getString("nombre")+rst.getString("apellidos")  ,rst.getString("idAutor"));
        }
        rl.add(new RelationDB("AUTOR","comicautor",author,"idAutor"));/**/
        /* Map<String , String > saga = new HashMap<String,String>();
        rst=db.getConnection().createStatement().executeQuery("select * from saga");
        while(rst.next()){
            saga.put(rst.getString("nombre")+rst.getString("editorial"),rst.getString("idSaga"));
        }
        rl.add(new RelationDB("SAGA","comicSaga",saga));/**/
        return rl;
       }catch(SQLException  e){
           e.printStackTrace();
           return null;
       }
        
        
    }

    @Override
    public String getNameID_DB() {
        return "idComic";
    }

    @Override
    public String insertRelationData(String valitem, String valitem2 ,String idNameMatch , String table ) {
         return "insert into "+table+"("+idNameMatch+","+idTableName+")"+ " values("+valitem+","+valitem2+")";
    }

    @Override
    public String deleteRelationData(String valitem, String valitem2 , String idNameMatch , String table) {
         return "delete from "+table+" where "+idNameMatch+"="+valitem+"  and "+ idTableName +"="+valitem2;
    }

    @Override
    public String UpdateInfo(String value , String columnName ,String id) {
        return "update "+getTableName()+" set "+columnName+"= '"+value+"' where "+getNameID_DB()+"="+id;
    }

    @Override
    public ResultSet getAllMatch() throws SQLException {
       return  db.getConnection().createStatement().executeQuery("select c.idComic as ID ,  c.titulo as TITULO ,c.Costo as COSTO , c.existencias as EJEMPLARES , t.nombre as GENEROTEMA from Comic c , saga s , tema t , comictema ct  where c.idSaga=s.idSaga and ct.idcomic=c.idcomic and t.idtema=ct.idtema");
   }

    @Override
    public HashMap<String, String> getToSale(String id)throws SQLException  {
        HashMap<String , String> data = new HashMap<String,String>();
        ResultSet rst = DataBase.executeSQL(db.getConnection(),"select * from "+getTableName()+" where "+getNameID_DB()+"="+id);
       if(rst.next()){
        data.put("id",rst.getString(getNameID_DB()));
        data.put("name",rst.getString("titulo"));
        data.put("cost", rst.getString("costo"));   
           
       }
        return data;
    }



    
}
