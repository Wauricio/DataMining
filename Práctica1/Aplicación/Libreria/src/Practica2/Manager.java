/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
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
    protected static void setTableDec(final Connection c,String table,final DefaultTableModel model) throws SQLException{
        Util.removeData(model);
        ResultSetMetaData rsmd = c.createStatement().executeQuery("select * from "+table).getMetaData();
        String[]desc={"NAME","DATA TYPE"};
        model.setColumnIdentifiers(desc);
        int numCol = rsmd.getColumnCount();
        for(int i=1;i<=numCol;i++){
            model.addRow( new Object []{rsmd.getColumnName(i),rsmd.getColumnTypeName(i)});
        }
    }
    

    
    
    /*
    public static  ArrayList<String> getDBTables(final Connection conn ) throws SQLException{
            ArrayList<String> meta=new ArrayList<>();
                //String types[] = {"TABLE","VIEW","SYSTEM TABLE","GLOBAL","TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"};};
                ResultSet rs = conn.getMetaData().getTables(null, null, "%", new String[]{"TABLE"});
                while (rs.next())
                    meta.add(rs.getString("TABLE_NAME").toLowerCase());
           return meta;
    }*/
}
