/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Product;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Wauricio
 */
public class RelationDB {
    private String table,idNameMatch;
    private Map<String , String > values;
    private String name;
    
     
    public RelationDB(String name,String table,Map<String , String > values ,String idNameMatch){
        this.table=table;
        this.values=values;
        this.name=name;
        this.idNameMatch=idNameMatch;
    }

    public String getIdNameMatch() {
        return idNameMatch;
    }

    
    
    public String getTable() {
        return table;
    }

    public Map<String, String> getValues() {
        return values;
    }   

    public String getName() {
        return name;
    }
    public String getidName() {
        return idNameMatch;
    }
    
    /*
    public String insertRelationData(String valitem,String valitem2 ) {//idMatch1=idProducto
       return "insert into "+table+"("+this.idNameMatch+","+this.idNameMatchItem+")"+ " values("+valitem+","+valitem2+")";
        
    }
    public String deleteRelationData(String valitem,String valitem2) {
         return "delete from "+table+" where "+this.idNameMatch+"="+valitem+"  and "+ this.idNameMatchItem +"="+valitem2;
        
    }
    
    public String UpdateInfo(){
        return "Update"
    }*/
}
