package hospitalmanagment;
//package jdbc;

import java.sql.*;

public class Databaseconn {
    
    Connection myconn = null;
    Statement query = null;
//    ResultSet rs = null;
    
    
    
    public Databaseconn()
    {
        myconn = null;
        query = null;

        String dburl = "jdbc:mysql://localhost:3306/hms";
        String user = "root";
        String pass = "";
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            myconn = DriverManager.getConnection(dburl,user,pass);
            query = myconn.createStatement();
        }
        catch(Exception e)  
        {
            System.out.println(e);
        }
    }
    
}
