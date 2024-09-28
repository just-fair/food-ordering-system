/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectDB {
    private String host="jdbc:derby://localhost:1527/POS";
    private String username="database";
    private String password="password";
    protected Connection con;
    
    
    //CONSTRUCTOR
    ConnectDB(){
        
        try{
            con = DriverManager.getConnection(host, username, password);
            System.out.println("Connected to the database");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    //METHOD
    //Need ng String sql command as parameter
    //Magrereturn ng ResultSet
    public ResultSet getResult(String command){
        
        ResultSet result;
                
        try{
            Statement statement = con.createStatement();
            result = statement.executeQuery(command);
            return result;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    
    
}
