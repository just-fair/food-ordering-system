/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Neil
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JLabel;
import com.google.gson.Gson;

public class Invoice extends javax.swing.JPanel {

    protected ConnectDB connection;
    protected LocalDate currentDate = LocalDate.now();
    protected String date = currentDate.toString();
    protected LocalTime currentTime = LocalTime.now();
    protected String time = currentTime.toString();
    protected int payment = 0;
    protected int total = 0;
    protected int change = 0;
    protected String name = "";
    protected int invcID = 1;
    protected int orderID=0;
    protected ArrayList <Food> orders = null;
    protected String ordersArray = "";
    
    
    //CONSTRUCTOR
    //gamit pag mag view ng receipt sa admin
    public Invoice(String date, String time, String invcID,
            String cashier, ArrayList<Food> orders, String total,
            String cash, String change ){
        
        initComponents();
        this.lblDate.setText(date);
        this.lblTime.setText(time);
        this.lblTransNum.setText("INVC"+invcID);
        this.lblCashier.setText(cashier);
        this.lblTotal.setText(total);
        this.lblCash.setText(cash);
        this.lblChange.setText(change);
        
        //display ng mga pagkain at presyo sa receipt
        for(Food order:orders){
            String pagkain=order.foodName+" x"+order.pcs;
            String presyo=""+order.total;
            
            JLabel item = new JLabel();
            item.setText(pagkain);
            this.pnlOrders.add(item);
            
            JLabel price = new JLabel();
            price.setText(presyo);            
            this.pnlPrice.add(price);
        }
    
    }
    
    
    //CONSTRUCTOR
    //gamit sa dashboard during payment
    public Invoice(ConnectDB connection, int payment, int total, String name, ArrayList<Food> orders) {
        initComponents();
        this.payment=payment;
        this.total=total;
        this.change = payment-total;
        this.name = name;
        this.orders = orders;
        Gson gson = new Gson();
        this.ordersArray= gson.toJson(orders);
        
        //display ng mga pagkain at presyo sa receipt
        for(Food order: orders){
            
            String item = order.foodName+" x"+ order.pcs;
            int price = order.total;
             
            JLabel lblItem = new JLabel();
            lblItem.setText(item);
            
            JLabel lblPrice=new JLabel();
            lblPrice.setText(""+price);
            
            this.pnlOrders.add(lblItem);
            this.pnlPrice.add(lblPrice);

        }
        
        
        //Saving ng Receipt/Invoice sa database
        try{
            //GENERARE INVOICE ID
      
            ResultSet results = connection.getResult("SELECT INVC_ID FROM INVOICE ORDER BY INVC_ID"); //kunin lahat ng invoiceID sa INVOICE table
            
            if (results != null){
                while (results.next()){
                    int invcIdSaTable = results.getInt("invc_ID");
                    
                    if(invcIdSaTable==invcID) invcID+=1; //pag may same id sa table increment
                    
                    if(invcIdSaTable>=invcID) invcID=results.getInt("invc_ID")+1; //mataas value set invcID sa mas mataas na value and plus 1
                }
            }else invcID = 1; //pag walang laman yung table 1 na yung generated na invoice id
            
            
            
            results  = connection.getResult("SELECT INVC_ID FROM ARCINVOICE ORDER BY INVC_ID"); //kunin lahat ng invoiceID sa archive ng invoice
            
            if(results!=null){
                //int invcIdSaTable = results.getInt("invc_ID");
                while (results.next()){
                    if(results.getInt("invc_ID")==invcID) invcID+=1; //pag may same id sa table increment
                }
            }

            
            results.close();
            
            
            
            
            //GENERATE NG ORDER ID
            
            //kunin lahat ng value ng order_id sa orders table
            ResultSet resulta = connection.getResult("SELECT ORDER_ID FROM ORDERS ORDER BY ORDER_ID"); 
            
            if(resulta == null) orderID=1;
            else{
                //kukunin last value ng order_ID
                while (resulta.next()){
                    if(resulta.getInt("order_id")>orderID) orderID=resulta.getInt("order_id");
                }
            }
            resulta.close();
            
            
            
            //insert bagong row sa Invoice table
            String command = "INSERT INTO INVOICE (invc_id, date,"
                    + " cashier, total, time, order_id, change, cash) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.con.prepareStatement(command);
            ps.setInt(1, invcID);
            ps.setString(2, date);
            ps.setString(3, name);
            ps.setInt(4, total);
            ps.setString (5, time);
            ps.setInt(6, orderID+1);
            ps.setInt(7, change);
            ps.setInt(8, payment);
            ps.executeUpdate();
            
            //insert bagong row sa Orders table
            //yung ArrayList na naglalaman ng mga orders ni user ay isesave sa ORDERS Table
            String command2 = "INSERT INTO ORDERS (order_id, food_name, food_qnty, food_total) VALUES(?, ?, ?, ?)";
            ps = connection.con.prepareStatement(command2);
            
            //kadaloop mag dadagdag ng row sa Orders table
            for(Food order:orders){
                ps.setInt(1, orderID+1);
                ps.setString(2, order.foodName);
                ps.setInt(3, order.pcs);
                ps.setInt(4, order.total);
                ps.executeUpdate();
            }
           
            ps.close();
            
            
            
            
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        
        this.lblDate.setText(date);
        this.lblTime.setText(time);
        this.lblTransNum.setText("INVC"+invcID);
        this.lblCash.setText(""+payment);
        this.lblTotal.setText(""+total);
        this.lblChange.setText(""+change);
        this.lblCashier.setText(name);
        
        
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblOrders = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        lblTransNum = new javax.swing.JLabel();
        lblCashier = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblCash = new javax.swing.JLabel();
        lblChange = new javax.swing.JLabel();
        pnlOrders = new javax.swing.JPanel();
        pnlPrice = new javax.swing.JPanel();

        lblOrders.setText("jLabel13");

        jLabel17.setText("jLabel17");

        jLabel1.setText("Date:");

        jLabel2.setText("Time:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("FastFood");

        jLabel4.setText("University Heights Sarmiento");

        jLabel5.setText("09123456789");

        jLabel6.setText("--------------------------------------------------------------------------------------------------------");

        jLabel7.setText("-------------------------------------------------------------------------------------------------------");

        jLabel8.setText("Transaction Number: ");

        jLabel9.setText("Cashier:");

        jLabel10.setText("-------------------------------------------------------------------------------------------------------");

        jLabel11.setText("Price");

        jLabel12.setText("Item/s");

        jLabel13.setText("-------------------------------------------------------------------------------------------------------");

        jLabel14.setText("Total:");

        jLabel15.setText("Change:");

        jLabel16.setText("Cash:");

        lblDate.setText("asda");

        lblTime.setText("jLabel17");

        lblTransNum.setText("jLabel17");

        lblCashier.setText("asd");

        lblTotal.setText("jLabel17");

        lblCash.setText("jLabel17");

        lblChange.setText("jLabel17");

        pnlOrders.setLayout(new java.awt.GridLayout(9, 0));

        pnlPrice.setLayout(new java.awt.GridLayout(9, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(lblTransNum, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(lblCashier, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260)
                        .addComponent(lblTotal))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260)
                        .addComponent(lblCash))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(240, 240, 240)
                        .addComponent(lblChange))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(6, 6, 6)
                .addComponent(jLabel4)
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))))
                .addGap(10, 10, 10)
                .addComponent(jLabel10)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(lblTransNum))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(lblCashier))
                .addGap(6, 6, 6)
                .addComponent(jLabel13)
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel11))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlOrders, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(pnlPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel7)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(lblTotal))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(lblCash))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(lblChange))
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblCash;
    private javax.swing.JLabel lblCashier;
    private javax.swing.JLabel lblChange;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblOrders;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTransNum;
    private javax.swing.JPanel pnlOrders;
    private javax.swing.JPanel pnlPrice;
    // End of variables declaration//GEN-END:variables
}
