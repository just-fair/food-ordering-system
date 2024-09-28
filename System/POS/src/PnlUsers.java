
import java.awt.Color;
import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;



public class PnlUsers extends javax.swing.JPanel {

    protected ConnectDB connection=null;
    protected DefaultTableModel tblModel;
    
    public PnlUsers(ConnectDB connection) {
        initComponents();
        this.setSize(714, 609);
        
        this.connection=connection;
        this.tblModel=(DefaultTableModel) tblUsers.getModel();
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSave.setEnabled(false);
        btnCancel.setEnabled(false);
        
        //design sa column
        this.tblUsers.getTableHeader().setFont(new Font("Sogoe UI", Font.BOLD, 16));  
        this.tblUsers.getTableHeader().setBackground(new Color(0xEADBC8));
        this.tblUsers.getTableHeader().setBorder(BorderFactory.createEmptyBorder());
        
        this.tblUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tblUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
           
                public void valueChanged(ListSelectionEvent e) {
                    //pag may nakaselect na row disable si add button
                    if(tblUsers.getSelectedRow()!=-1){
                       btnAdd.setEnabled(false);
                       btnEdit.setEnabled(true);
                       btnDelete.setEnabled(true);
                       btnCancel.setEnabled(true);
                       txtName.setEnabled(false);
                       txtPassword.setEnabled(false);


                    }else{
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }


                    // Check kung marami nakaselect na row
                    if (tblUsers.getSelectedRowCount() > 1) {
                       //nililimit sa isa lang yung pagselect ng rows
                        int[] selectedRows = tblUsers.getSelectedRows();
                        tblUsers.getSelectionModel().setSelectionInterval(selectedRows[0], selectedRows[0]);
                    }
                }
        });
        
        this.displayUsersTbl();
      
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
        tblUsers = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblUserId = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        chckIsAdmin = new javax.swing.JCheckBox();
        btnSave = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 250, 246));
        setLayout(null);

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 250, 246)));

        tblUsers.setAutoCreateRowSorter(true);
        tblUsers.setBackground(new java.awt.Color(254, 250, 246));
        tblUsers.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tblUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "User ID", "Name", "Admin", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsers.setFillsViewportHeight(true);
        tblUsers.setGridColor(new java.awt.Color(254, 250, 246));
        tblUsers.setIntercellSpacing(new java.awt.Dimension(5, 5));
        tblUsers.setRowHeight(25);
        tblUsers.setSelectionBackground(new java.awt.Color(16, 44, 87));
        tblUsers.setSelectionForeground(new java.awt.Color(254, 250, 246));
        tblUsers.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblUsers);
        if (tblUsers.getColumnModel().getColumnCount() > 0) {
            tblUsers.getColumnModel().getColumn(0).setResizable(false);
            tblUsers.getColumnModel().getColumn(0).setPreferredWidth(3);
            tblUsers.getColumnModel().getColumn(0).setCellRenderer(new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            });
            tblUsers.getColumnModel().getColumn(1).setResizable(false);
            tblUsers.getColumnModel().getColumn(2).setResizable(false);
            tblUsers.getColumnModel().getColumn(2).setPreferredWidth(15);
            tblUsers.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
                {
                    setHorizontalAlignment(SwingConstants.CENTER);
                }
            });
            tblUsers.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(20, 40, 670, 301);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("User ID:");
        add(jLabel1);
        jLabel1.setBounds(230, 380, 49, 20);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Full Name:");
        add(jLabel2);
        jLabel2.setBounds(220, 420, 64, 20);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Password:");
        add(jLabel3);
        jLabel3.setBounds(220, 460, 80, 20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Is Admin?");
        add(jLabel4);
        jLabel4.setBounds(220, 500, 70, 20);

        lblUserId.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblUserId.setText("Auto generated");
        add(lblUserId);
        lblUserId.setBounds(320, 380, 110, 16);

        btnAdd.setBackground(new java.awt.Color(218, 192, 163));
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 44, 87), 2, true));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        add(btnAdd);
        btnAdd.setBounds(170, 550, 90, 40);

        btnEdit.setBackground(new java.awt.Color(218, 192, 163));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 44, 87), 2, true));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        add(btnEdit);
        btnEdit.setBounds(280, 550, 90, 40);

        btnDelete.setBackground(new java.awt.Color(218, 192, 163));
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 44, 87), 2, true));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        add(btnDelete);
        btnDelete.setBounds(390, 550, 90, 40);

        txtName.setBackground(new java.awt.Color(234, 219, 200));
        txtName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 44, 87), 2));
        add(txtName);
        txtName.setBounds(320, 420, 130, 20);

        txtPassword.setBackground(new java.awt.Color(234, 219, 200));
        txtPassword.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        txtPassword.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 44, 87), 2));
        add(txtPassword);
        txtPassword.setBounds(320, 460, 130, 20);

        chckIsAdmin.setBackground(new java.awt.Color(234, 219, 200));
        chckIsAdmin.setForeground(new java.awt.Color(16, 44, 87));
        add(chckIsAdmin);
        chckIsAdmin.setBounds(320, 490, 21, 25);

        btnSave.setBackground(new java.awt.Color(218, 192, 163));
        btnSave.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 44, 87), 2, true));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        add(btnSave);
        btnSave.setBounds(570, 440, 90, 24);

        btnCancel.setBackground(new java.awt.Color(218, 192, 163));
        btnCancel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(16, 44, 87), 2, true));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        add(btnCancel);
        btnCancel.setBounds(570, 490, 90, 24);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        int userId=Integer.parseInt((String)tblModel.getValueAt(tblModel.getRowCount()-1, 0))+1;
        String name=this.txtName.getText();
        String password = this.txtPassword.getText();
        boolean isAdmin = this.chckIsAdmin.isSelected();
        
        if(name.length()==0 || password.length()==0){
            JOptionPane.showMessageDialog(this, "Please fill up all fields");
            return;
        }
        
        

        
        try{
            ResultSet results  = connection.getResult("SELECT USER_ID FROM ARCUSERS ORDER BY USER_ID");
            
            if(results!=null){
                //int invcIdSaTable = results.getInt("invc_ID");
                while (results.next()){
                    if(results.getInt("user_ID")==userId) userId+=1; //pag may same id sa table increment
                }
            }
            
            results.close();
            
            String command="INSERT INTO USERS (user_id, name, admin, password) VALUES(?,?,?,?)";
            PreparedStatement ps = connection.con.prepareStatement(command);
            ps.setInt(1, userId);
            ps.setString(2, name);
            ps.setBoolean(3, isAdmin);
            ps.setString(4, password);
            ps.executeUpdate();
            
            this.displayUsersTbl();
            this.revalidate();
            this.repaint();
            
            this.lblUserId.setText("");
            this.txtName.setText("");
            this.txtPassword.setText("");
            this.chckIsAdmin.setSelected(false);
            
            JOptionPane.showMessageDialog(this, "Added Sucessfully");
            
            ps.close();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        btnEdit.setEnabled(false);
        btnSave.setEnabled(true);
        txtName.setEnabled(true);
        txtPassword.setEnabled(true);
        btnDelete.setEnabled(false);
        
       int selectedRow = tblUsers.getSelectedRow();
       
       if(selectedRow!=-1){
           String uId=""+tblUsers.getValueAt(selectedRow, 0);
           String name=""+tblUsers.getValueAt(selectedRow, 1);
           String isAdmin=""+ tblUsers.getValueAt(selectedRow, 2);
           String password=""+tblUsers.getValueAt(selectedRow, 3);
           
           this.lblUserId.setText(uId);
           this.txtName.setText(name);
           this.chckIsAdmin.setSelected(Boolean.parseBoolean(isAdmin));
           this.txtPassword.setText(password);
                 
       }
        
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        this.btnSave.setEnabled(false);
        
        int uId=Integer.parseInt(lblUserId.getText());
        String name=txtName.getText();
        String password=txtPassword.getText();
        boolean isAdmin = chckIsAdmin.isSelected();
        

        try{
        String command="UPDATE USERS SET name=?, admin=?, password=? WHERE user_id=?";
        PreparedStatement ps= connection.con.prepareStatement(command);
        
        ps.setString(1, name);
        ps.setBoolean(2, isAdmin);
        ps.setString(3, password);
        ps.setInt(4, uId);
        
        ps.executeUpdate();
        
        this.displayUsersTbl();
        
        ps.close();
        }catch(Exception e){
            System.out.println();
        }
        
        this.lblUserId.setText("");
        this.txtName.setText("");
        this.txtPassword.setText("");
        this.chckIsAdmin.setSelected(false);
        
        JOptionPane.showMessageDialog(null, "Updated Successfully");
        
        this.displayUsersTbl();
        
        this.btnAdd.setEnabled(true);
        this.btnCancel.setEnabled(false);
        this.lblUserId.setText("Auto generated");
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        
        
        int response = JOptionPane.showConfirmDialog(this,"Are you sure delete this item?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE );
        switch(response){
            case 0:
                int selectedRow = tblUsers.getSelectedRow();
       
                if(selectedRow!=-1){
                    String uID=""+tblUsers.getValueAt(selectedRow, 0);
                    String name=""+tblUsers.getValueAt(selectedRow, 1);
                    String isAdmin=""+tblUsers.getValueAt(selectedRow, 2);
                    String password=""+tblUsers.getValueAt(selectedRow, 3);
                    
                    try{
                        //lagay sa archive bago delete
                        String command="INSERT INTO ARCUSERS (user_id, name, admin, password) VALUES(?,?,?,?)";
                        PreparedStatement ps = connection.con.prepareStatement(command);
                        ps.setInt(1, Integer.parseInt(uID));
                        ps.setString(2, name);
                        ps.setBoolean(3, Boolean.parseBoolean(isAdmin));
                        ps.setString(4, password);

                        ps.executeUpdate();
                        
                        
                        //delete sa user table
                        ps = connection.con.prepareStatement("DELETE FROM USERS WHERE user_id=?");
                        ps.setInt(1, Integer.parseInt(uID));
                        ps.executeUpdate();
                        
                        JOptionPane.showMessageDialog(this, "Item Successfully Deleted");
                        
                        this.displayUsersTbl(); 
                        this.btnEdit.setEnabled(false);
                        this.btnDelete.setEnabled(false);
                        this.btnAdd.setEnabled(true);
                        this.txtName.setEnabled(true);
                        this.txtPassword.setEnabled(true);
                        
                        ps.close();
                        
                        
                    }catch(Exception e){      
                        System.out.println(e.getMessage());
                    }
                }
                
                
                break;
                
            case 1:
                
                break;
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        tblUsers.clearSelection();
        this.btnEdit.setEnabled(false);
        this.btnDelete.setEnabled(false);
        this.btnSave.setEnabled(false);
        this.lblUserId.setText("Auto generated");
        this.txtName.setText("");
        this.txtPassword.setText("");
        this.chckIsAdmin.setSelected(false);
        this.txtName.setEnabled(true);
        this.txtPassword.setEnabled(true);
        this.btnAdd.setEnabled(true);
    }//GEN-LAST:event_btnCancelActionPerformed
    
    public void displayUsersTbl(){
        //tanggalin laman ng table
        if(tblModel.getRowCount()>0){
            for(int i =tblModel.getRowCount()-1; i>=0; i--){
                tblModel.removeRow(i);
            }
        }
        
        
        String command="SELECT * FROM USERS ORDER BY USER_ID";
        ResultSet results=connection.getResult(command);
        
        
        try{
            while(results.next()){
                String uID = results.getString("user_id");
                String name = results.getString("name");
                boolean isAdmin= results.getBoolean("admin");
                String password = results.getString("password");
                
                String newRow[]={uID, name, ""+isAdmin, password};
                
                tblModel.addRow(newRow);
                
            }
            
            results.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chckIsAdmin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUserId;
    private javax.swing.JTable tblUsers;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPassword;
    // End of variables declaration//GEN-END:variables
}
