/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import DB_Product.Item;
import DB_Product.RelationDB;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import libreria.DataBase;

/**
 *
 * @author Wauricio
 */
public class AddItem_UI extends javax.swing.JPanel {
    private Item itm;
    private final DefaultTableModel model;
    private String idItem;
    public AddItem_UI() {
        initComponents();
        model=new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Descripción", "Valor"});
        jTable1.setModel( model);
    }
    
    
    
    
    
    
    public String getIdItem(){
       return  model.getValueAt(0, 1).toString();
    }

    public void initProduc(Item i){
        itm=i;
        try{
            Utilities.setMetaData(model,i.getTableName(), itm.getdb().getConnection());//Información Tabla
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void initUpdate(Item i,String id){
        itm=i;
        jButton1.setEnabled(false);
         try{
            
           Utilities.setInformationByIDitem(model,itm.getByID(id));
          model.addTableModelListener(new TableModelListener(){
              @Override
              public void tableChanged(TableModelEvent tme) {
                   /* System.out.println(model.getValueAt(tme.getFirstRow(),tme.getColumn()-1));
                    System.out.println(model.getValueAt(tme.getFirstRow(),tme.getColumn()));*/
                   String value=model.getValueAt(tme.getFirstRow(),tme.getColumn()).toString(); 
                   try{      
                         String id = model.getValueAt(0,1).toString();
                         
                      System.out.println(itm.UpdateInfo(value
                              , model.getValueAt(tme.getFirstRow(),tme.getColumn()-1).toString(), id));

                      DataBase.doSQLInsert(itm.getdb().getConnection(), itm.UpdateInfo(value
                              , model.getValueAt(tme.getFirstRow(),tme.getColumn()-1).toString(), id));
                      itm.getdb().getConnection().commit();
                    }catch(SQLException e){
                        JOptionPane.showMessageDialog(null,
                        "Error :Ocurio Un poblema Al Actualizar Registro",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                       
                        e.printStackTrace();
                    }
              }
          });
          
          for (RelationDB tmp:itm.getRelations()){
                Add_Multi mul=new Add_Multi(tmp,id,itm);
                mul.setVisible(true);
                jPanel1.add(mul);
            }
            
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
    
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Caracteristica", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Añadir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));
        jScrollPane3.setViewportView(jPanel1);

        jButton2.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(386, 386, 386)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Map<String,String> values = Utilities.getTableInformation(model);
       try{
       //itm.getdb().getConnection().setAutoCommit(false);
       itm.insertData(itm.getTableName(),values);
       idItem=getIdItem();
       for (RelationDB tmp:itm.getRelations()){
                Add_Multi mul=new Add_Multi(tmp,idItem,itm);
                mul.setVisible(true);
                jPanel1.add(mul);
            }
       this.repaint();
//jPanel1.setEnabled(false);
       }catch(SQLException e){
           e.printStackTrace();
       }
       
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
