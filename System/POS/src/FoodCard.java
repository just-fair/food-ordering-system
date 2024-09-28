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
import javax.swing.*;
import javax.swing.border.Border;
import java.net.URL;

public class FoodCard extends JPanel  {
    
    protected String imageFileName="Images/";
    protected String foodName="";
    protected int foodPrice=0;
    
    //CONSTRUCTOR
    public FoodCard(String iFileName, String fName, int fPrice){ 
        this.imageFileName=imageFileName+iFileName;
        this.foodName=fName;
        this.foodPrice=fPrice;
        
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(0xB0A695));
        
        //ayaw gumana ng directory nalang ilalagay kaya need ng getResource method
        URL imageUrl = getClass().getClassLoader().getResource(this.imageFileName);

        ImageIcon image = new ImageIcon(imageUrl);
        Image resizedImage = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        
        Border border = BorderFactory.createLineBorder(new Color(0x776B5D), 3);
        
        
        JLabel food = new JLabel();
        JLabel price=new JLabel();
        
        food.setText(foodName+" ₱"+this.foodPrice);
        food.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        food.setHorizontalTextPosition(JLabel.CENTER);
        food.setVerticalTextPosition(JLabel.BOTTOM);
        food.setIcon(new ImageIcon(resizedImage));
        food.setHorizontalAlignment(JLabel.CENTER);
        food.setVerticalAlignment(JLabel.CENTER);
        
        
        
        this.add(food);
        this.setBorder(border);
        this.setSize(250, 500);
        this.setVisible(true);
        
    }
    
}
