package com.employeeservice;
import java.sql.*;
public class DBConnectionClass {
    public static Connection con;
    public static Connection getConnection()
    {
        try {
             //Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");
             con = DriverManager.getConnection("jdbc:mysql://localhost:3306/payroll_service", "root", "atul123");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }
}
