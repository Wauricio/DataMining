/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JPanel;
import libreria.DataBase;

/**
 *
 * @author Wauricio
 */
public interface Item {
    /*characteristics*/
    public String getTableName();
    public ArrayList<RelationDB> getRelations();
    public String getNameID_DB();
    
    
    
    /*catalog*/
    public ResultSet getAllMatch() throws SQLException;
     public ResultSet getAll() throws SQLException;
    public ResultSet searchbyType(String value , String type)throws SQLException ;
    public String[] getSearch_Type();
    public ResultSet getByID(String id )throws SQLException;
    public DataBase  getdb();
    
    /*Sales*/
    public ResultSet getAllSales()throws SQLException;
    public ResultSet getSalesByType(String value,String Type)throws SQLException;
    public String[]  getSales_Type();
   
    /*Insert*/
    
    public void insertData(String table , Map<String,String> vales)throws SQLException;
    public void insertProduc()throws SQLException;
    public void deleteProduc()throws SQLException;
    public void UpdateProduct()throws SQLException;
    
    /*Relations*/
     public String insertRelationData(String valitem,String valitem2 ,String idNameMatch , String Table );
     public String deleteRelationData(String valitem,String valitem2 , String idNameMatch,String Table);
      public String UpdateInfo(String value , String columnName ,String id);
          
}
