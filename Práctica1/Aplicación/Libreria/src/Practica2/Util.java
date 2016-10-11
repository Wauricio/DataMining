/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wauricio
 */
public class Util {
   public static void setDataOnTable(ResultSet rs, DefaultTableModel modelo) throws SQLException
    {
        setColumns(rs.getMetaData(), modelo);
        removeData(modelo);
        addData(rs, modelo);
    }
    
    
    public static void setColumns(ResultSetMetaData rsmd,final DefaultTableModel model) throws  SQLException {
       int numCol = rsmd.getColumnCount();
       Object[] eti = new Object[numCol];
        for(int i=0;i<numCol;i++)
            eti[i]=rsmd.getColumnLabel(i+1);
        model.setColumnIdentifiers(eti);
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
   
}
