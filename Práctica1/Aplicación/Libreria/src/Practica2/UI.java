/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practica2;


import Practica2.Element.FactTable;
import Practica2.Element.Table;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Wauricio
 */
public class UI extends javax.swing.JFrame {
    private DefaultTableModel scheme;
    private DefaultTableModel items;
    private Connection Conn;
    private String dbName=null;
    private String dbTable=null;
    private String idTable=null;
    private String lastSelected="";
    private int action=-1;
    private Type type=null;
    private HashMap<String,Table> dim;
    private HashMap<String,FactTable> factbl;
    

    /**
     * Creates new form UI
     */
    public UI() {
        scheme=new DefaultTableModel();
        items=new DefaultTableModel();
        try{
            Conn=Manager.getDBConnection();
        }catch(Exception e){
            System.out.println("Error al Realizar la Conexión con el Driver");
            System.exit(1);
        }
        initComponents();
        jTable1.setModel(scheme);
        jTable2.setModel(items);
        dim=new HashMap<>();
        factbl=new HashMap<>();
        setItems(0);
        
    }
    
    private void setItems(int i){
        jComboBox1.removeAllItems();
        switch(i){
            case 0:
                ArrayList<String> names = Manager.getDatabases(Conn);
                for(String name:names)
                    jComboBox1.addItem(name);
               
                break;
            case 1:
                if(dim.size()==0){
                    JOptionPane.showMessageDialog(null,"Error:no Hay Dimensiones Creadas", 
                            "Error",JOptionPane.ERROR_MESSAGE);
                }else{
                    
                    for (Map.Entry<String, Table> entry : dim.entrySet()) {
                        jComboBox1.addItem(entry.getKey());
                    }
                    action=3;
                            
                    /*
                    for (Table t : dim){
                        jComboBox1.addItem(t.getName());
                    }*/
                }
                
                break;
                
            case 2:
                if(factbl.size()==0){
                      JOptionPane.showMessageDialog(null,"Error:no Hay Fact Tables Creadas", 
                            "Error",JOptionPane.ERROR_MESSAGE);
                }else{
                     for (Map.Entry<String,FactTable> entry : factbl.entrySet()) {
                        jComboBox1.addItem(entry.getKey());
                    }
                    // action=4;
                }
                
                
                break;
                
                
            
        }
        
        
        
        
        

    }
    
    
    
    private void doAction() {
        try{
        switch(action){
            case 0:  //tables are showed
                Conn=Manager.getDBConnection(dbName);
                Manager.setTablesDesc(Conn, scheme);
                action=1;
                break;
            case 1: //Attr are showed
                dbTable=scheme.getValueAt(jTable1.getSelectedRow(),0).toString();
                System.out.println("Table :"+dbTable);
                action=2;
                idTable=Manager.setTableDec(Conn, dbTable,dbName,scheme);
                jLabel2.setText("Create Dimension ");
                items.setColumnIdentifiers(new Object[]{"DATA BASE","TABLE_NAME","NAME","TYPE","ID_TABLE"});
                break;
            
            case 2://Add Item
                String name = scheme.getValueAt(jTable1.getSelectedRow(), 0).toString();
                String type =scheme.getValueAt(jTable1.getSelectedRow(), 1).toString();
                items.addRow(new Object[]{dbName,dbTable,name,type,idTable});
                
                 break;
            
            case 3: //Add Dimmetion in Facttable
                    jLabel2.setText("Create Fact Table");
                    items.setColumnIdentifiers(new Object[]{"DIMENSION'S NAME","ID_DIMESION","ID_TYPE"});
                    String nameDim =jComboBox1.getSelectedItem().toString();
                    Table t =this.dim.get(nameDim);
                    items.addRow(new Object[]{nameDim,t.getId(),t.getTypeId()});
                
                break;
            
        
        }
        }catch(SQLException e){
            System.out.println("Ocurrio Un Problema en la Consulta");
            e.printStackTrace();
        }catch(Exception ex){
            System.out.println("Ocurrio Un Problema en la Conexión");
            ex.printStackTrace();
        }
    }
    
    private void doCreate(int act){
        switch(act){
            case 2: // Dimesion Created
                String name = JOptionPane.showInputDialog(this, "Nombre de Dimensión?:");
                String id = JOptionPane.showInputDialog(this, "Nombre ID?:");
                String type = JOptionPane.showInputDialog(this, "Tipo ID?:");
                
                if(name!=null && id!=null && type!= null ){
                    Table dim = new Table (name,id,type);
                    int row =jTable2.getRowCount();
                    HashMap<String,String[]>attr=new HashMap<>();
                    for(int i=0;i<row;i++){
                                
				/*System.out.println("Nombre :" +jTable2.getValueAt(i,2)
                                +" Type :"+jTable2.getValueAt(i,3) + " Referencia: "+jTable2.getValueAt(i,0)+"."+jTable2.getValueAt(i,1)
                                );*/
                                attr.put(jTable2.getValueAt(i,2).toString(),
                                        new String[]{jTable2.getValueAt(i,3).toString(),
                                            jTable2.getValueAt(i,0)+"."+jTable2.getValueAt(i,1),jTable2.getValueAt(i,4).toString()});
                    }
                    dim.setAttr(attr);
                    this.dim.put(dim.getName(),dim);
                     Util.removeData(items);
                     JOptionPane.showMessageDialog(null,"OK:Dimesión '"+name+"'  Creada De forma Exitosa!", 
                            "OK",JOptionPane.OK_OPTION);
                    
                }else{
                     JOptionPane.showMessageDialog(null,"Error:Ingresa el Nombre de la Dimesión e ID ", 
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
                
            case 3: //Fact Table Created
                String nameFact = JOptionPane.showInputDialog(this, "Nombre de Fact Table ?:");
                
                if(nameFact!=null ){
                    FactTable ft= new FactTable(nameFact);
                   HashMap<String,Table> dim=new HashMap<>();
                    int row =jTable2.getRowCount();
                    for(int i=0;i<row;i++){
                                
                                dim.put(jTable2.getValueAt(i,0).toString(),this.dim.get(jTable2.getValueAt(i,0).toString()));
                    
                    }
                    ft.setDimensions(dim);
                    this.factbl.put(ft.getName(),ft);
                     Util.removeData(items);
                     JOptionPane.showMessageDialog(null,"OK:Fact Table'"+nameFact+"'  Creada De forma Exitosa!", 
                            "OK",JOptionPane.WARNING_MESSAGE);
                    
                }else{
                     JOptionPane.showMessageDialog(null,"Error:Ingresa el Nombre de la Fact Table  ", 
                            "Error",JOptionPane.ERROR_MESSAGE);
                }
                
                
                break;
        }
    }
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Data Base", "Name", "Type"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Item");

        jLabel2.setText("Selected Item");

        jButton1.setText("Remove");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Type");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data Bases", "Dimentions", "Fact Tables" }));
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });

        jButton2.setText("Create");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(132, 132, 132)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        dbName=evt.getItem().toString();
        System.out.println("Database Selected:"+dbName);
        
        if(!lastSelected.equals(dbName)){
            switch(jComboBox2.getSelectedIndex()){
                case 0: //database selected                     
                    action=0;
                    doAction(); 
                    break;
                case 1:  // dimension selected
                    Manager.setDimensions(dim.get(jComboBox1.getSelectedItem().toString()), scheme, jLabel2);
                    System.out.println("Mostrar Dimesion");
                    break;
                case 2://Fact tables selected
                   System.out.println("fact TAble :" +factbl.get(jComboBox1.getSelectedItem().toString()).getName());
                  Manager.setFactTables(factbl.get(jComboBox1.getSelectedItem().toString()), scheme, jLabel2);
                    
                    break;
            }
        }
        lastSelected=dbName;
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        doAction();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       int row = jTable2.getSelectedRow();
       if(row>-1){
           items.removeRow(row);
       }else{
           JOptionPane.showMessageDialog(null,"Warning :No selecciono un Elemento", "Warning",JOptionPane.WARNING_MESSAGE);
            
       }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
       if(jComboBox2.getSelectedIndex()<0){
           JOptionPane.showMessageDialog(null,"Error:Secciona un Elementoo para Mostrar",
                   "Error",JOptionPane.ERROR);
       }else{
           setItems(jComboBox2.getSelectedIndex());
       }
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        doCreate(action);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
