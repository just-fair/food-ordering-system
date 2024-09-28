

import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;





public class PnlTransactions extends JPanel {
    protected ConnectDB connection;
    protected DefaultTableModel tableModel;
   
    public PnlTransactions(ConnectDB connection) {
        initComponents();
        this.setSize(714, 609);
        
        
        this.connection=connection;
        this.btnView.setEnabled(false);
        this.btnDelete.setEnabled(false);
        this.tableModel = (DefaultTableModel) tblTransactions.getModel();
        
        //design sa header ng table
        this.tblTransactions.getTableHeader().setFont(new Font("Sogoe UI", Font.BOLD, 14));  
        this.tblTransactions.getTableHeader().setBackground(new Color(0xEADBC8));
        this.tblTransactions.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        this.tblTransactions.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
         this.tblTransactions.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
           
                public void valueChanged(ListSelectionEvent e) {
                    //pag may nakaselect na row enable si view at delete btn 
                    if(tblTransactions.getSelectedRow()!=-1){
                       btnView.setEnabled(true);
                       btnDelete.setEnabled(true);


                    }else{
                        btnView.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }


                    // Check kung marami nakaselect na row
                    if (tblTransactions.getSelectedRowCount() > 1) {
                       //nililimit sa isa lang yung pagselect ng rows
                        int[] selectedRows = tblTransactions.getSelectedRows();
                        tblTransactions.getSelectionModel().setSelectionInterval(selectedRows[0], selectedRows[0]);
                    }
                }
        });
         
         
        
        this.displayTbl();
        
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransactions = new javax.swing.JTable();
        btnView = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 250, 246));
        setLayout(null);

        jScrollPane1.setBackground(new java.awt.Color(218, 192, 163));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 250, 246)));

        tblTransactions.setAutoCreateRowSorter(true);
        tblTransactions.setBackground(new java.awt.Color(254, 250, 246));
        tblTransactions.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblTransactions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice ID", "Date", "Cashier", "Total", "Time", "Order ID", "Change", "Cash"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTransactions.setFillsViewportHeight(true);
        tblTransactions.setGridColor(new java.awt.Color(254, 250, 246));
        tblTransactions.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblTransactions.setRowHeight(25);
        tblTransactions.setSelectionBackground(new java.awt.Color(16, 44, 87));
        tblTransactions.setSelectionForeground(new java.awt.Color(254, 250, 246));
        jScrollPane1.setViewportView(tblTransactions);
        if (tblTransactions.getColumnModel().getColumnCount() > 0) {
            tblTransactions.getColumnModel().getColumn(0).setResizable(false);
            tblTransactions.getColumnModel().getColumn(0).setPreferredWidth(15);
            tblTransactions.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            });
            tblTransactions.getColumnModel().getColumn(1).setResizable(false);
            tblTransactions.getColumnModel().getColumn(2).setResizable(false);
            tblTransactions.getColumnModel().getColumn(3).setResizable(false);
            tblTransactions.getColumnModel().getColumn(3).setPreferredWidth(8);
            tblTransactions.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            });
            tblTransactions.getColumnModel().getColumn(4).setResizable(false);
            tblTransactions.getColumnModel().getColumn(5).setResizable(false);
            tblTransactions.getColumnModel().getColumn(5).setPreferredWidth(7);
            tblTransactions.getColumnModel().getColumn(5).setCellRenderer(new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            });
            tblTransactions.getColumnModel().getColumn(6).setResizable(false);
            tblTransactions.getColumnModel().getColumn(6).setPreferredWidth(8);
            tblTransactions.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            });
            tblTransactions.getColumnModel().getColumn(7).setResizable(false);
            tblTransactions.getColumnModel().getColumn(7).setPreferredWidth(8);
            tblTransactions.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            });
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(12, 60, 690, 300);

        btnView.setBackground(new java.awt.Color(218, 192, 163));
        btnView.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-view-60.png"))); // NOI18N
        btnView.setText("View");
        btnView.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 44, 87), 2, true));
        btnView.setIconTextGap(15);
        btnView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewActionPerformed(evt);
            }
        });
        add(btnView);
        btnView.setBounds(180, 460, 150, 70);

        btnDelete.setBackground(new java.awt.Color(218, 192, 163));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-delete-50.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 44, 87), 2, true));
        btnDelete.setIconTextGap(8);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete);
        btnDelete.setBounds(400, 460, 150, 70);
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
       int selectedRow = tblTransactions.getSelectedRow();
       
       
                if(selectedRow!=-1){
                    String invcID=""+tblTransactions.getValueAt(selectedRow, 0);
                    String date=""+tblTransactions.getValueAt(selectedRow, 1);
                    String cashier=""+tblTransactions.getValueAt(selectedRow, 2);
                    String total=""+tblTransactions.getValueAt(selectedRow, 3);
                    String time=""+tblTransactions.getValueAt(selectedRow, 4);
                    String orderID=""+tblTransactions.getValueAt(selectedRow, 5);
                    String change=""+tblTransactions.getValueAt(selectedRow, 6);
                    String cash=""+tblTransactions.getValueAt(selectedRow, 7);
                    ArrayList<Food> orders= new ArrayList<>();
                   
                    
                    
                   
                    
                    
                  try{
                      String command = "SELECT * FROM ORDERS WHERE ORDER_ID = ?";
                      PreparedStatement ps = connection.con.prepareStatement(command);
                      ps.setInt(1, Integer.parseInt(orderID));
                      ResultSet results = ps.executeQuery();
                      
                      if(results!=null){
                          while(results.next()){
                          
                          String name=results.getString("food_name");
                          int qnty = results.getInt("food_qnty");
                          int ttl = results.getInt("food_total");
                          
                          orders.add(new Food(name, qnty, ttl));
                        }
                      }
                      
                      results.close();
                      ps.close();
                      
                      
                      //show ng receipt
                      JFrame receipt = new JFrame();
                      receipt.setSize(422,650);
                      receipt.add(new Invoice(date, time, invcID, cashier, orders, total, cash, change));
                      System.out.println(date);
                      receipt.setVisible(true);
                      
                  }catch(Exception e){
                      e.printStackTrace();
                  }
                }
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int response = JOptionPane.showConfirmDialog(this,"Are you sure delete this item?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE );
        switch(response){
            case 0:
                int selectedRow = tblTransactions.getSelectedRow();

                        if(selectedRow!=-1){
                            String invc_id=""+tblTransactions.getValueAt(selectedRow, 0);
                            String date = ""+tblTransactions.getValueAt(selectedRow, 1);
                            String cashier= ""+tblTransactions.getValueAt(selectedRow, 2);
                            String total = ""+tblTransactions.getValueAt(selectedRow, 3);
                            String time = ""+tblTransactions.getValueAt(selectedRow, 4);
                            String orderID = ""+tblTransactions.getValueAt(selectedRow, 5);
                            String change = ""+tblTransactions.getValueAt(selectedRow, 6);
                            String cash = ""+tblTransactions.getValueAt(selectedRow, 7);

                            try{
                                
                               //add sa archive bago idelete
                              String command = "INSERT INTO ARCINVOICE (invc_id, date,"
                                + " cashier, total, time, order_id, change, cash) VALUES (?,?,?,?,?,?,?,?)";
                              
                                PreparedStatement ps = connection.con.prepareStatement(command);
                                ps.setInt(1, Integer.parseInt(invc_id));
                                ps.setString(2, date);
                                ps.setString(3, cashier);
                                ps.setInt(4, Integer.parseInt(total));
                                ps.setString (5, time);
                                ps.setInt(6, Integer.parseInt(orderID));
                                ps.setInt(7, Integer.parseInt(change));
                                ps.setInt(8, Integer.parseInt(cash));
                                ps.executeUpdate();
                                
                                
                             //delete
                              ps = connection.con.prepareStatement("DELETE FROM invoice WHERE invc_id=?");
                              ps.setInt(1, Integer.parseInt(invc_id));
                              ps.executeUpdate();
                              ps.close();

                              this.displayTbl();
                          }catch(Exception e){
                              System.out.print(e.getMessage());
                          }


                        }
                        break;
                        
            case 1:
                break;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed
    
    
    
    public void displayTbl(){
        //tanggalin laman ng table
        if(tableModel.getRowCount()>0){
            for(int i =tableModel.getRowCount()-1; i>=0; i--){
                tableModel.removeRow(i);
            }
        }
        
        
        
        String command="SELECT * FROM INVOICE";
        ResultSet results = connection.getResult(command);
        
        
        try{
             
             
             while(results.next()){
                String InvcID = results.getString("invc_id");
                String date = results.getString("date");
                String cashier= results.getString("cashier");
                String total = results.getString("total");
                String time = results.getString("time");
                String orderID = results.getString("order_id");
                String change = results.getString("change");
                String cash = results.getString("cash");
                
                //lagay sa isang array mga values then add sa row
                String newRow[]={InvcID, date, cashier, total, time,orderID, change, cash };
                
                
                tableModel.addRow(newRow);
              
            }
             
            results.close();
             
             
         }catch(Exception e){
         System.out.println(e.getMessage());
         }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnView;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransactions;
    // End of variables declaration//GEN-END:variables
    
}
