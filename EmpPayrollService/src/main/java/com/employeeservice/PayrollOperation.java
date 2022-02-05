/*
* @Author Atul Srivastav
* EmployeePayrollOperations Class to perform all the actions.
* @Date- 05/02/22
* */
package com.employeeservice;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollOperation {
    //variable declarations and object creations
    public static Statement stmt;
    public static PreparedStatement pstmt;
    public static ResultSet rs;
    public static List<EmployeePayrollData> data= new ArrayList<EmployeePayrollData>();
    public static EmployeePayrollData e;

    /*
    * readData method to read employee_service data from employee_payroll db.
    * @param Connection object
    * @return List of all the data stored in employee payroll table.
    * */
    public static List<EmployeePayrollData> readData(Connection con)
    {
        try {
            //executing select query.
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from employee_payroll");
            //itterating over result set to add data to the List.
            while(rs.next()) {
                e=new EmployeePayrollData(rs.getInt(1), rs.getString(2), rs.getDouble(6));
                data.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}