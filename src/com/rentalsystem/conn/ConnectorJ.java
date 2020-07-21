/**
 * <h>Connertor J</h>
 * <pr>This class is designed to manage connection between JAVA and DB</pr>
 * <pre>
 * @author  WooChan Park
 * @ver 1.0
 * @since 2019-05-27
 * </pre>
 */
package com.rentalsystem.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectorJ {

    public static Connection con = null;
    private static String server = "127.0.0.1"; // MySQL server address
    private static String database = "car_rental_system"; // MySQL DATABASE name
    private static String user_name = "root"; //  MySQL server ID
    private static String password = "1234"; // MySQL server Password

    public static boolean loadDriver() {
        /**
         * To load JDBC Driver
         * @param nothing
         * @return boolean This parameter notice whether loading JDBC Driver has failed or not.
         * @exception ClassNotFoundException
         */
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return true;
        }catch (ClassNotFoundException e){
            System.err.println("!!<jdbc error> driver load error" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public static boolean connectToServer(){
        /**
         * To connect to Server
         * @param nothing
         * @return boolean This parameter notice whether connecting to server has succeeded or not.
         * @exception SQLException
         */
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_rental_system?serverTimezone=UTC&useSSL=false", "root", "admin");
            System.out.println("Connecting to server completed.");
            return true;
        }catch(SQLException e){
            System.err.println("con �삤瑜�:" + e.getMessage());
            return false;
        }
    }
    public static boolean disconnectFromServer(){
        /**
         * To disconnect to Server
         * @param return
         * @return boolean This parameter notice wheter disconnecting from server has succeeded or not.
         * @exception SQLException
         */
        try{
            if(con != null) {
                con.close();
                System.out.println("Disconnected from server.");
            }
            return true;
        }catch(SQLException e){
            return false;
        }
    }



}

