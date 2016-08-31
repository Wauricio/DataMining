/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wauricio
 */
public class DataBase {
    private Connection c;
   // private  String driver , url,db,dbUser,dbPwd;  
    
    public DataBase(String url,String db , String driver ,String user , String pass)throws Exception{
        c= getConnector(url,db,driver,user,pass);
        /* this.driver =driver;
         this.url = url;
         this.db=db;
         this.dbUser =user;
         this.dbPwd =pass;*/
    }
    
    
    
    public Connection getConnection() {
        return c;
    }

    
    
    public static Connection getConnector(String url,String db , String driver ,String user , String pass) throws Exception {
     Connection conn=null;  
        Class.forName(driver).newInstance();
        conn=DriverManager.getConnection(url+db,user,pass);
        conn.setAutoCommit(false);
     return conn;
     }
    
     public static ResultSet executeSQL (Connection conn, String query) throws SQLException {  
         return  conn.createStatement().executeQuery(query);
     }  
   
     public static void doSQLInsert (Connection conn, String query) throws SQLException {  
      conn.createStatement().executeUpdate(query);
     }
  
  
  
/*Descripción DB*/
    public static  ArrayList<String> getDBTables(Connection conn ) throws SQLException{
            ArrayList<String> meta=new ArrayList<String>();
            DatabaseMetaData dbmd = conn.getMetaData();
                //String types[] = {"TABLE","VIEW","SYSTEM TABLE","GLOBAL","TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM"};
                String[] types = {"TABLE"};
                ResultSet rs = dbmd.getTables(null, null, "%", types);
                while (rs.next()) 
                {
                    //System.out.println(rs.getString("TABLE_NAME"));
                    meta.add(rs.getString("TABLE_NAME").toLowerCase());
                }
           return meta;
    }
    
    
 
    
    public static String[] getColumns(Connection conn , String table) throws  SQLException {
         //Map<String,String> col=new HashMap<String,String>();
        ResultSetMetaData rsmd = conn.createStatement().executeQuery("select * from "+table).getMetaData();
        int numCol = rsmd.getColumnCount();
        String [] clm= new String[numCol];
        for(int i=0;i<numCol;i++)
            clm[i]=rsmd.getColumnName(i+1).toLowerCase();
        return clm;      
    }
    
     public static Map<String,String> getMetaDataColumns(Connection conn , String table) throws  SQLException {
         Map<String,String> col=new HashMap<String,String>();
        ResultSetMetaData rsmd = conn.createStatement().executeQuery("select * from "+table).getMetaData();
        int numCol = rsmd.getColumnCount();
        for(int i=0;i<numCol;i++)
            col.put(rsmd.getColumnName(i+1).toLowerCase(), rsmd.getColumnTypeName(i+1));
        return col;      
    }    
    

    
    /*
    public static boolean hasTable(Connection c ,String name) throws Exception{
        ArrayList<String> tables = getDBTables(c);
       for (String tem :tables)
       {
           if(name.equals(tem))
               return true;
       }
       return false;
    }
    
    public static boolean hasColumn(Connection c ,String table, String column) throws Exception{
         Map<String,String> col = getColumns(c,table);
         for (Map.Entry<String, String> temp :col.entrySet())
         {
             if(column.equals(temp.getKey().toString()))
                 return true;
         }  
         return false;   
    }
    */
    
    
    
    
   }
