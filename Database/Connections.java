import java.sql.*;
import java.util.List;

import java.util.ArrayList;

//import java.util.Map;
//import java.util.HashMap;
//import java.util.Set;
//import java.util.HashSet;
public class Connections extends ConnectionsAbstract {


    public Connections() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        this.connection = connection;

    }

    public boolean createDB(String url) {
       try {
            this.connection = DriverManager.getConnection(url);
            Statement statement = this.connection.createStatement();
            int Result = statement.executeUpdate("CREATE DATABASE mydata");
            return true;

         } 
        catch(SQLException se) {
            System.out.println("Could not create database");
            return false;
        } 
    }
    @Override
    public boolean connectDB(String url, String username, String password) {
        try {
            this.connection = DriverManager.getConnection(url, username, password);
            return true;

         } 
        catch(SQLException se) {
            System.out.println("Couldn't connect");
            return false;
        }
    }
    @Override
    public boolean disconnectDB() {
        // Implement this method!
        if(this.connection != null) {
            try {
                this.connection.close();
                return true;
            } catch (SQLException se) {
                return false;
            }
        }
        return true;
    }


    // public static void main(String[] args) throws ClassNotFoundException {
    public static void main(String[] args) {
         // You can put testing code in here. It will not affect our autotester.
        try {
            // "jdbc:postgresql://localhost/csc343h-viscadan?currentSchema=parlgov";
            String url = "jdbc:mysql://localhost/?user=root&password=password";
            String username = "root";
            String password = "password";
            Connections conn = new Connections();
            boolean result = conn.createDB(url);
            boolean res = conn.connectDB(url, username, password);
            System.out.println("Connect?: " + result);

        // Test election sequence
        //System.out.println("Test 1:");
        //ElectionCabinetResult a = a2.electionSequence("Canada");
        //for(int i = 0; i < a.elections.size(); ++i) {
        //    System.out.println("Election: " + a.elections.get(i) + " Cabinet: " + a.cabinets.get(i));
        //}

       
        //System.out.println("Disconnection");
        //System.out.println(a2.disconnectDB());

        }
        catch(ClassNotFoundException err){
            System.out.println("Class not found exception");
        }
    }
}
