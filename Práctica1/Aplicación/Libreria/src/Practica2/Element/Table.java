/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2.Element;
import java.util.HashMap;

/**
 *
 * @author Wauricio
 */
public class Table {
    private String name;
    //private String dbNAme;
    private String id;
    private String typeId;
    private HashMap<String,String[]> attr;

    public Table(String name, String id, String typeId) {
        this.name = name;
        this.id = id;
        this.typeId = typeId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }


    public String getName() {
        return name;
    }
/*
    public String getDbNAme() {
        return dbNAme;
    }*/

    public String getId() {
        return id;
    }


    public void setName(String name) {
        this.name = name;
    }
/*
    public void setDbNAme(String dbNAme) {
        this.dbNAme = dbNAme;
    }*/

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, String[]> getAttr() {
        return attr;
    }

    public void setAttr(HashMap<String, String[]> attr) {
        this.attr = attr;
    }




    
    public String toScript(){
        String scp="CREATE TABLE "+this.name+"(";
        
           return null;
    }
    
    
}
