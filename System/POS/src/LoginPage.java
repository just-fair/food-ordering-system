/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginPage extends JFrame implements ActionListener{
    
    //para madisplay sa gitna ng screen yung login
    protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int centerX= (int)(screenSize.getWidth()-500)/2;
    protected int centerY= (int)(screenSize.getHeight()-500)/2;
            
    
    protected JTextField userID;
    protected JPasswordField password;
    protected JButton btnLogin;
    protected JLabel message=new JLabel("Wrong user ID or password");
    protected JLabel message2= new JLabel("Both Fields must have a value");
    protected JLabel message3 = new JLabel("You are not an admin");
    protected ConnectDB connection;
    
    
    //CONSTRUCTOR
    public LoginPage(ConnectDB connection){
        
        
        this.connection=connection;
        this.setBounds(centerX, centerY, 500, 500);
        this.setLayout(null);
        //this.getContentPane().setBackground(Color.RED);
        this.setTitle("Food Ordering System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        JLabel usernameLabel = new JLabel("User ID:");
        usernameLabel.setBounds(55, 90, 80, 30);
        
        userID = new JTextField();
        userID.setBounds(140, 90, 250, 30);
        
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(55, 150, 80, 30);
        
        password = new JPasswordField();
        password.setBounds(140, 150, 250, 30);
        
        
        btnLogin = new JButton("Login");
        btnLogin.setBounds(200, 250, 130, 50);
        btnLogin.addActionListener(this);
        
        message.setVisible(false);
        message.setBounds(180, 380, 200, 30);
        message.setForeground(Color.red);
        
        message2.setVisible(false);
        message2.setBounds(180, 400, 200, 30);
        message2.setForeground(Color.red);
        
        message3.setVisible(false);
        message3.setBounds(200, 420, 200, 30);
        message3.setForeground(Color.red);
        
        
        
        this.add(usernameLabel);
        this.add(userID);
        this.add(passwordLabel);
        this.add(password);
        this.add(btnLogin);
        this.add(message);
        this.add(message2);
        this.add(message3);
        
        
        this.setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent e){
        //function for Login Button
        if(e.getSource()==btnLogin){
            
            //check kung yung may laman yung mga textfield
            if((userID.getText().equals("")) || (password.getPassword().length==0)){
                message2.setVisible(true);
                message.setVisible(false);
              
                userID.setText("");
                password.setText(null);
                return;
            }
            
            //Get data from Users table in databse
            String command = "SELECT * FROM USERS";
            ResultSet results = connection.getResult(command);
            
            try{
                //loop bawat row
                while(results.next()){
                    int uID=results.getInt("user_id");
                    String pWord=results.getString("password");
                    String name = results.getString("name");
                    boolean isAdmin=results.getBoolean("admin");
                    
                    if(Integer.parseInt(userID.getText())==uID){ //check kung yung inputed na userID is nageexist sa database
                        message.setVisible(false);
                        if(new String(password.getPassword()).equals(pWord)){// check kung tugma yung user ID sa password
                            this.setVisible(false);
                            if(isAdmin){//kapag admin adminDashboard magpapakita
                                AdminDashboard adminDashBoard = new AdminDashboard(connection, name, this);
                                break;
                            }else{//pag di amin yun dashboard para sa mga pagkain yung ididisplay
                                Dashboard dashBoard = new Dashboard(connection, name, this);
                            break;
                            }
   
                        }else{//show ng message kung di tugma yung password sa userID
                            message.setVisible(true);
                            message2.setVisible(false);
                        }
                    }else{//show message kung wala yung inputed userID sa database
                        message.setVisible(true);
                        message2.setVisible(false);
                    }
                    
                    
                }
                
                userID.setText("");
                password.setText(null);
                results.close();
                
                
            }catch(SQLException err){
                System.out.println(err.getMessage());
            }
        
       
        }

        
    }
    
}
