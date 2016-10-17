/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

import Practica2.Element.FactTable;
import Practica2.Element.Table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wauricio
 */
public class Manager {
    
    static String driver = "com.mysql.jdbc.Driver";
    static String url = "jdbc:mysql://localhost:3306/";
    static String db="comics";
    static String dbUser = "root";
    static String dbPwd = "root";
    
    public static void main(String args[]){
        try{
            Connection c = getDBConnection(db);
            ResultSetMetaData r= executeSQL(c,"select * from comic ").getMetaData();
            int size =r.getColumnCount();
            for(int i=1;i<size;i++){
                System.out.println(r.getColumnName(i)+ "  : "+r.getColumnTypeName(i)); 
            }
            /*while(){
                
                System.out.println(r.getString("Database"));
            }*/
            
            
        }catch(Exception e){
            e.printStackTrace();
            
        }
        
        
        
        
        
    }
    
    
    
    public static Connection getDBConnection(String db ) throws Exception {   
        Class.forName(driver).newInstance();
       return  DriverManager.getConnection(url+db,dbUser,dbPwd);
    }
     public static Connection getDBConnection() throws Exception {   
        Class.forName(driver).newInstance();
       return  DriverManager.getConnection(url,dbUser,dbPwd);
    }
    public static ResultSet executeSQL (Connection conn, String query) throws SQLException {  
     return  conn.createStatement().executeQuery(query);
    }
    
    public static ArrayList<String> getDatabases(final Connection conn){
        ArrayList<String> dbs = new ArrayList<>();
         try{
                ResultSet r= executeSQL(conn,"show databases");
                    while(r.next())
                        dbs.add(r.getString("Database"));

            }catch(SQLException e){
                System.out.println("Ocurrio Un problema al Conectar con SGBD");
                System.exit(1);
            }
        return dbs;
    }
    
    protected static void setTablesDesc(final Connection c , final DefaultTableModel model ) throws SQLException{
        //String types[] = {"TABLE","VIEW","SYSTEM TABLE","GLOBAL","TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"};                
        Util.removeData(model);
        ResultSet r = c.getMetaData().getTables(null, null, "%",new String[]{"TABLE"});
        model.setColumnIdentifiers(new Object[]{"TABLE_NAME","DATABASE_NAME","TABLE TYPE"});
        while(r.next()){
            model.addRow(new Object[]{r.getString("TABLE_NAME"),r.getString("TABLE_CAT"),r.getString("TABLE_TYPE")});
        }
        
    }
    protected static String setTableDec(final Connection c,String table,String DB,final DefaultTableModel model) throws SQLException{
        Util.removeData(model);
        ResultSetMetaData rsmd = c.createStatement().executeQuery("select * from "+table).getMetaData();
        ResultSet r=c.createStatement().executeQuery("SELECT COLUMN_NAME, COLUMN_KEY "
                + " FROM INFORMATION_SCHEMA.COLUMNS  WHERE TABLE_SCHEMA = \""+DB+"\" "
                + "AND TABLE_NAME = \""+table+"\"  and COLUMN_KEY IN(\"PRI\", \"UNI\")");
        String idTable="";
        if(r.next()){
            idTable=r.getString("COLUMN_NAME");
        }
        String[]desc={"NAME","DATA TYPE"};
        model.setColumnIdentifiers(desc);
        int numCol = rsmd.getColumnCount();
        for(int i=1;i<=numCol;i++){
            model.addRow( new Object []{rsmd.getColumnName(i),rsmd.getColumnTypeName(i)});
        }
        return idTable;
    }
    
    protected static void setDimensions(Table dim , final DefaultTableModel model , final JLabel label ){
        Util.removeData(model);
        model.setColumnIdentifiers(new String[]{"NAME" ,"TYPE","REFERENCES","ID_TABLE"} );
        HashMap<String,String[]>temp = dim.getAttr();
        label.setText("NAME : "+dim.getName() + " ID :"+dim.getId()+" ID_TYPE:"+dim.getTypeId());
        for (Map.Entry<String, String[]> entry : temp.entrySet()) {
            String inf[]=entry.getValue();
            model.addRow(new Object[]{entry.getKey(),inf[0],inf[1],inf[2]});
        }
    }
    
    protected static void setFactTables(FactTable ft,final DefaultTableModel model, final JLabel label ){
        Util.removeData(model);
        model.setColumnIdentifiers(new String[]{"DIMENSION'S NAME" ,"ID_DIMESION"} );
        
        label.setText("NAME : "+ft.getName() + " SIZE :"+ft.getDimensions().size());
        for (Map.Entry<String, Table> entry : ft.getDimensions().entrySet()) {

            model.addRow(new Object[]{entry.getKey(),entry.getValue().getId()});
        }

        
    }
 
}
