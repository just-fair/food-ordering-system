/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */


public class Main {

    
    public static void main(String[] args) {
        ConnectDB connection = new ConnectDB();
        
        
        LoginPage loginPage = new LoginPage(connection);
       
    } 
}
