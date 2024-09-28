/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.*;
        
public class Order extends javax.swing.JPanel {
    
    protected String foodName="";
    protected int price=0;
    protected int pcs=1;
    protected int total=0;
    protected int index=0;
    protected ArrayList<Food> orders;
    protected JLabel lblTotal;
    protected JButton btnPay;
    
    //CONSTRUCTOR
    public Order(String foodImage, String foodName, int foodPrice, int pcs, ArrayList orders, JLabel lblTotal, JButton btnPay) {
        initComponents();
        
        this.pcs=pcs;
        this.foodName=foodName;
        this.orders=orders;
        this.price=foodPrice;
        this.total=pcs*price;
        this.lblTotal=lblTotal;
        this.btnPay=btnPay;
        
        getIndex();
        
        //ayaw gumana ng directory nalang
        URL imageUrl = getClass().getClassLoader().getResource(foodImage);
        ImageIcon image = new ImageIcon(imageUrl);
        Image resizedImage = image.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        
        lblImage.setIcon(new ImageIcon(resizedImage));
        lblFoodName.setText(foodName);
        lblPcs.setText(""+pcs);
        lblPrice.setText(""+total);
        
        
        
        
        this.setVisible(true);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        lblFoodName = new javax.swing.JLabel();
        btnMinus = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lblPrice = new javax.swing.JLabel();
        lblPcs = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setBackground(new java.awt.Color(235, 227, 213));

        lblImage.setText("Image");

        lblFoodName.setText("FoodName");

        btnMinus.setText("-");
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        btnAdd.setText("+");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lblPrice.setText("0");

        lblPcs.setText("0");

        btnDelete.setBackground(new java.awt.Color(235, 227, 213));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        btnDelete.setBorder(null);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lblFoodName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMinus)
                .addGap(18, 18, 18)
                .addComponent(lblPcs, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAdd)
                .addGap(18, 18, 18)
                .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(lblPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPcs)
                    .addComponent(btnMinus)
                    .addComponent(lblFoodName))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    //MINUS BUTTON
    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        if(pcs==1) return;
        else pcs--;
        
        this.total=price*pcs;
        this.lblPcs.setText(""+pcs);
        this.lblPrice.setText(""+total);
        
        getIndex();
        
        orders.get(index).setTotal(total);
        orders.get(index).setPcs(pcs);
        
       
        int tempTotal=0;
        
        for(Food order : orders){
            tempTotal+=order.total;
        }
        
        lblTotal.setText(""+tempTotal);
      
        
    }//GEN-LAST:event_btnMinusActionPerformed

    //DELETE BUTTON
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        int response = JOptionPane.showConfirmDialog(null,"Remove order?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE );
                        
                        //response =0 if nag yes, response ==1 naman pag no
                        switch(response){
                            case 0:
                                Container parent = this.getParent();
        
                            if(parent!=null){
                                parent.remove(this);
                                parent.revalidate();
                                parent.repaint();   
                            }

                            for(Food order:orders){
                                if(order.foodName==this.foodName){
                                    orders.remove(order);
                                    if(orders.size()==0) this.btnPay.setEnabled(false);
                                    else this.btnPay.setEnabled(true);
                                    break;
                                }
                            }
                            
                            
                            int tempTotal=0;        
                            for(Food order : orders){
                                tempTotal+=order.total;
                            }

                            lblTotal.setText(""+tempTotal);
                                
                                break;
                                
                            case 1:
                                break;
                                
                        }
        
        
        
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    
    //ADD BUTTON
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        pcs++;
        this.total=price*pcs;
        this.lblPcs.setText(""+pcs);
        this.lblPrice.setText(""+total);
        
        getIndex();
       
        //update display total price saka pcs sa order panel
        orders.get(index).setTotal(total);
        orders.get(index).setPcs(pcs);
        
        //compute ng total then display
        int tempTotal=0;
        for(Food order : orders){
            tempTotal+=order.total;
        }
        
        lblTotal.setText(""+tempTotal);
    }//GEN-LAST:event_btnAddActionPerformed

    
    public void getIndex(){
        //kunin yung Index then iset
        for(Food order: orders){
            if(order.foodName==this.foodName){
                index=orders.indexOf(order);
                break;
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnMinus;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel lblFoodName;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblPcs;
    private javax.swing.JLabel lblPrice;
    // End of variables declaration//GEN-END:variables
}
