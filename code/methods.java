import java.sql.*;
import java.util.*;
import java.io.*;
import javax.sql.*;
import java.lang.*;
import java.text.*;


public class methods {
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static Scanner sc = new Scanner(System.in);
    static int storeID = 1;
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/hmkachha";

    public static void main(String[] args) {
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            String user = "hmkachha";
            String passwd = "harsh123";
            conn = DriverManager.getConnection(jdbcURL, user, passwd);
        }catch(Throwable oops){
            oops.printStackTrace();
        }

        System.out.println("Enter Store Information");
        
        System.out.print("Store Address: ");
        String storeAddr = sc.next();
        System.out.println();

        System.out.print("Store Phone: ");
        String storePhone = sc.next();
        System.out.println();

        try{
            stmt = conn.createStatement();
            System.out.println("INSERT INTO Store(storeAddress, Phone_No) VALUES (\'" + storeAddr + "\',\'" + storePhone + "\')");
            //stmt.executeUpdate("INSERT INTO Store(storeAddress, Phone_No) VALUES (\'" + storeAddr + "\',\'" + storePhone + "\')");
            
            // stmt = conn.preparestatement("INSERT into Store(storeAddress, Phone_No)" + " VALUES(?,?)");
            // stmt.setString(1, storeAddr);
            // stmt.setString(2, storePhone);  
            // stmt.executeUpdate();
            stmt.close();
            conn.close();
        }catch(Throwable oops){
            oops.printStackTrace();
        }


    }
    
}
