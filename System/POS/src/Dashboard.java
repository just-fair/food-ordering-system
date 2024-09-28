import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Dashboard extends javax.swing.JFrame {
  protected ArrayList<Food> orders = new ArrayList<>();
  protected ConnectDB connection=null;
  protected String name = "";
  protected JFrame frmLogin;
    
   
    //CONSTRUCTOR
    public Dashboard(ConnectDB connection, String name, JFrame frmLogin) {
        initComponents();
        
        this.setSize(1130, 705);
        
        this.connection=connection;
        this.name = name;
        this.frmLogin=frmLogin;
        
        this.pnlMenu.setLayout(new GridLayout(0, 3, 10, 10));
        this.pnlAllOrders.setLayout(new GridLayout(9, 1, 3, 3));
        
        
        //Get data sa Menu table
        String command="SELECT * FROM MENU ORDER BY FOOD_ID";
        ResultSet results = connection.getResult(command);
        
        
        //Gagawing FoodCard bawat value na nakuha sa database with functionality
        try{
            while(results.next()){
                String foodName=results.getString("food_name");
                int foodPrice=results.getInt("food_price");
                String foodImage=results.getString("food_pic");
                
                FoodCard foodCard = new FoodCard(foodImage, foodName, foodPrice);
                
                foodCard.addMouseListener(new MouseAdapter(){
                    //pagpinindot yung foodCard
                    public void mouseClicked(MouseEvent e){
                        int response = JOptionPane.showConfirmDialog(pnlMenu,"Add to orders?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE );
                        
                        //response =0 if nag yes, response ==1 naman pag no
                        switch(response){
                            case 0:
                                //check kung may same na pagkain sa orders
                                for(Food order : orders){
                                    //if meron add nalang ng pcs na property ng Food sa order
                                    if(order.foodName.equals(foodCard.foodName)){
                                        order.setPcs(order.pcs+1);
                                        order.total=order.foodPrice*order.pcs;
                                        displayOrders();
                                        return;
                                    }
                                }
                                
                                //pag wala pa sa orders, create ng Food object then add sa ArrayList
                                orders.add(new Food(foodCard.imageFileName, foodCard.foodName, foodCard.foodPrice));
                                displayOrders();//display ng mga orders sa dashboard
                                
                                break;
                                
                            case 1:
                                break;
                                
                        }
                        
                    }
                    
                    //palit ng kulay pag may mouse
                    public void mouseEntered(MouseEvent e){
                        foodCard.setBackground(new Color(0xEBE3D5));
                    }
                    
                    public void mouseExited(MouseEvent e){
                        foodCard.setBackground(new Color(0xB0A695));
                    }
                });
                
                this.pnlMenu.add(foodCard); //add sa panel menu yung foodCard
                this.pnlMenu.revalidate();//refresh
                this.pnlMenu.repaint();
                
            }
            results.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        
       
        this.setVisible(true);
    }
    
    
    
    //Display ng orders panel
    public void displayOrders(){
        this.pnlAllOrders.removeAll();//remove muna mga laman ng orders panel bago ilagay lahat
        
        //check kung may orders na, pag wala disable payment button
        //pag meron enable payment button
        if(orders.size()==0) this.btnPay.setEnabled(false); //pag wala pang orders disable payment button
        else this.btnPay.setEnabled(true);
        
        
        int total=0;
        
        //bawat laman ng ArrayList is gagawing Order na object
        for(Food order:orders){
            
            total+=order.total;
            Order inorder = new Order(order.foodImage, order.foodName,
                                       order.foodPrice, order.pcs, orders,
                                       lblTotal, this.btnPay);
            
            this.pnlAllOrders.add(inorder);
            this.pnlAllOrders.revalidate();
            this.pnlAllOrders.repaint();
       
        }
        
        this.lblTotal.setText(""+total);//display ng total
        this.pnlTotal.revalidate();//refresh
        this.pnlTotal.repaint();
    }
    
   

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPanel = new javax.swing.JScrollPane();
        pnlMenu = new javax.swing.JPanel();
        pnlOrders = new javax.swing.JPanel();
        pnlTotal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pnlAllOrders = new javax.swing.JPanel();
        btnPay = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(243, 238, 234));
        getContentPane().setLayout(null);

        jPanel4.setBackground(new java.awt.Color(119, 107, 93));

        lblTitle.setFont(new java.awt.Font("Segoe Script", 1, 36)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Path He Came ni Kim");

        jButton1.setBackground(new java.awt.Color(119, 107, 93));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8-back-48.png"))); // NOI18N
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(119, 107, 93)));
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243)
                .addComponent(lblTitle)
                .addContainerGap(365, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitle)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 0, 1120, 73);

        jScrollPanel.setForeground(new java.awt.Color(255, 204, 102));
        jScrollPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPanel.setAutoscrolls(true);

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 705, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
        );

        jScrollPanel.setViewportView(pnlMenu);

        getContentPane().add(jScrollPanel);
        jScrollPanel.setBounds(10, 89, 707, 570);

        pnlOrders.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        pnlTotal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        pnlTotal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("TOTAL:");

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTotal.setText("0");

        javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(pnlTotal);
        pnlTotal.setLayout(pnlTotalLayout);
        pnlTotalLayout.setHorizontalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlTotalLayout.setVerticalGroup(
            pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTotalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(176, 166, 149));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 0, 0), null));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ORDERS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(148, 148, 148))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlAllOrdersLayout = new javax.swing.GroupLayout(pnlAllOrders);
        pnlAllOrders.setLayout(pnlAllOrdersLayout);
        pnlAllOrdersLayout.setHorizontalGroup(
            pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlAllOrdersLayout.setVerticalGroup(
            pnlAllOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 468, Short.MAX_VALUE)
        );

        btnPay.setBackground(new java.awt.Color(176, 166, 149));
        btnPay.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPay.setText("Pay");
        btnPay.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(119, 107, 93), 2, true));
        btnPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlOrdersLayout = new javax.swing.GroupLayout(pnlOrders);
        pnlOrders.setLayout(pnlOrdersLayout);
        pnlOrdersLayout.setHorizontalGroup(
            pnlOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAllOrders, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlOrdersLayout.createSequentialGroup()
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPay, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addContainerGap())
        );
        pnlOrdersLayout.setVerticalGroup(
            pnlOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAllOrders, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(pnlOrders);
        pnlOrders.setBounds(719, 89, 390, 570);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPayActionPerformed
        int total = Integer.parseInt(lblTotal.getText());
        
        String sPayment = JOptionPane.showInputDialog("Total Payment: "+ lblTotal.getText());
        
        if(sPayment==null) return;
        
        int payment = Integer.parseInt(sPayment);
        
        //gawa ng receipt
        JFrame receipt = new JFrame();
        receipt.setSize(422,650);
        receipt.add(new Invoice(connection, payment, total, name, orders));
        receipt.setVisible(true);
        
        this.pnlAllOrders.removeAll();
        this.pnlAllOrders.revalidate();
        this.pnlAllOrders.repaint();
        this.lblTotal.setText("0");
        this.orders.clear();//burahin laman ng ArrayList
        
        
    }//GEN-LAST:event_btnPayActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        frmLogin.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPay;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPanel;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JPanel pnlAllOrders;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPanel pnlOrders;
    private javax.swing.JPanel pnlTotal;
    // End of variables declaration//GEN-END:variables
}
