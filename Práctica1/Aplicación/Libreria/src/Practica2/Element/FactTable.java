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
public class FactTable {
    private String name;
    private HashMap<String,Table> dimensions;
    private HashMap<String,String> attr;

    public FactTable(String name) {
        this.name = name;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Table> getDimensions() {
        return dimensions;
    }

    public void setDimensions(HashMap<String, Table> dimensions) {
        this.dimensions = dimensions;
    }

    public HashMap<String, String> getAttr() {
        return attr;
    }

    public void setAttr(HashMap<String, String> attr) {
        this.attr = attr;
    }
    
    
    
}
