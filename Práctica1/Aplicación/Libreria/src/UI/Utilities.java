/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import libreria.DataBase;

/**
 *
 * @author Wauricio
 */
public class Utilities {
    
    
    /*Set Information in table*/
     public static void setDataOnTable(ResultSet rs, DefaultTableModel modelo) throws SQLException
    {
        setColumns(rs.getMetaData(), modelo);
        removeData(modelo);
        addData(rs, modelo);
    }
    
    
    public static void setColumns(ResultSetMetaData rsmd,final DefaultTableModel modelo) throws  SQLException {
       int numCol = rsmd.getColumnCount();
       Object[] eti = new Object[numCol];
        for(int i=0;i<numCol;i++)
            eti[i]=rsmd.getColumnLabel(i+1);
        modelo.setColumnIdentifiers(eti);
    }
    public static void addData(ResultSet rs, final DefaultTableModel modelo)
    {
            int RowNum = 0;
            try
            {
                while (rs.next())
                {
                    Object[] data = new Object[modelo.getColumnCount()];
                    for (int i = 0; i < modelo.getColumnCount(); i++)
                        data[i] = rs.getObject(i + 1);
                    modelo.addRow(data);
                    RowNum++;
                }
                rs.close();
            } catch (Exception e)
            {
                e.printStackTrace();
            }
    }    
    
    
    public static void removeData(final DefaultTableModel modelo)
    {
       while (modelo.getRowCount() > 0)
           modelo.removeRow(0);
    }   
   
    /*Update Information*/
    
    
    public static void setInformationByIDitem(final DefaultTableModel model , ResultSet rs)throws SQLException{
        ResultSetMetaData rsmd = rs.getMetaData();
        removeData(model);
        int numCol=rsmd.getColumnCount();
        if(rs.next()){
             for(int i=0; i<numCol;i++){
                     Object[] data = new Object[2];
                     data[0]=rsmd.getColumnLabel(i+1);
                     data[1]=rs.getObject(i+1);
                     model.addRow(data);
                     
             }
        }
    }
    
    public static void setMetaData(final DefaultTableModel model , String table , Connection c ) throws SQLException{
        String [] mtdt = DataBase.getColumns(c, table);
        for (String s : mtdt){
            model.addRow(new Object[]{s.toString(),""});
        }
    }
    
    public static Map<String,String> getTableInformation(final DefaultTableModel model  ){
        Map<String,String> map=new HashMap<String ,String>();
           for(int i=0;i<model.getRowCount();i++){
               map.put(model.getValueAt(i, 0).toString(),model.getValueAt(i, 1).toString() );
        }
          return map;
    }
    
    public static String valuesSql(int i){
        
        if(i==1){
            return "(?)";
        }
        else {
          String vl="?";
          while(i-->1){
              vl=vl+",?";
          }
          return "("+vl+")";        
        }
        
    }
    
    public static String setColumnNames(Map<String,String> columsName){
        String name="";
        int i=columsName.size();
         for (Map.Entry<String, String> entry : columsName.entrySet()){
             if(--i==0)
             {
                 name+=entry.getKey();
             }
             else
             {
                name+=entry.getKey()+",";
             }
             
         }
         return "("+name+")";
    }
    
    public static void setDataType(final PreparedStatement stmt,String type,String value,int i )throws SQLException{
        System.out.print(type);
        switch(type.toLowerCase()){
            case "int":
                stmt.setInt(i, Integer.parseInt(value));
                break;
            case "int4":
                stmt.setInt(i, Integer.parseInt(value));
                break;
            case "numeric":
                stmt.setFloat(i, Float.parseFloat(value));
                break;
                
            default:
                 stmt.setString(i, value);
                break;
        }
    }
    
  

    
    
}
