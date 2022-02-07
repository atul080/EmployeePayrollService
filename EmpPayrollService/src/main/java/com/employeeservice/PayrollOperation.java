package com.employeeservice;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayrollOperation {
    public static Statement stmt;
    public static PreparedStatement pstmt;
    public static ResultSet rs;
    public static List<EmployeePayrollData> data= new ArrayList<EmployeePayrollData>();
    public static EmployeePayrollData e;
    public static List<EmployeePayrollData> readData(Connection con)
    {

        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select * from employee_payroll");
            while(rs.next()) {
                e=new EmployeePayrollData(rs.getInt(1), rs.getString(2), rs.getDouble(6));
                data.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static EmployeePayrollData updateData(Connection con)
    {
        boolean chk=false;
        try {
            pstmt =con.prepareStatement("update employee_payroll set basic_pay=? where name=?");
            pstmt.setDouble(1, 300000);
            pstmt.setString(2,"Terisa");
            int a=pstmt.executeUpdate();
            rs=con.createStatement().executeQuery("select * from employee_payroll where name='Terisa'");
            while(rs.next()) {
                e = new EmployeePayrollData(rs.getInt(1), rs.getString(2), rs.getDouble(6));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return e;
    }
    public static EmployeePayrollData readDataByName(Connection con,String name)
    {
        try {
            pstmt=con.prepareStatement("select * from employee_payroll where name=?");
            pstmt.setString(1,name);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                e=new EmployeePayrollData(rs.getInt(1), rs.getString(2), rs.getDouble(6));
            }
        } catch (SQLException e) {
            System.out.println("Invalid name. Please enter correct name.");
        }
        return e;
    }
    public static List<EmployeePayrollData> getEmployeesJoinedInGivenRange(Connection con,Date start,Date end)
    {
        List<EmployeePayrollData> data= new ArrayList<EmployeePayrollData>();
        try {
            pstmt=con.prepareStatement("select * from employee_payroll where start between ? and ?");
            pstmt.setDate(1,start);
            pstmt.setDate(2,end);
            rs=pstmt.executeQuery();
            while(rs.next()) {
                e=new EmployeePayrollData(rs.getInt(1), rs.getString(2), rs.getDouble(6),rs.getDate(11));
                data.add(e);
            }
        } catch (SQLException e) {
            System.out.println("Invalid name. Please enter correct name.");
        }
        return data;
    }


    public static Map<String, Double> sumOfSal(Connection con) {
        Map<String,Double> empSal=new HashMap<>();
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select gender,sum(basic_pay) from employee_payroll group by gender");
            while(rs.next()) {
                empSal.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empSal;
    }

    public static Map<String, Double> avgOfSal(Connection con) {
        Map<String,Double> empSal=new HashMap<>();
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select gender,avg(basic_pay) from employee_payroll group by gender");
            while(rs.next()) {
                empSal.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empSal;
    }

    public static Map<String, Double> minOfSal(Connection con) {
        Map<String,Double> empSal=new HashMap<>();
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select gender,min(basic_pay) from employee_payroll group by gender");
            while(rs.next()) {
                empSal.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empSal;
    }

    public static Map<String, Double> maxOfSal(Connection con) {
        Map<String,Double> empMaxSal=new HashMap<>();
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select gender,max(basic_pay) from employee_payroll group by gender");
            while(rs.next()) {
                empMaxSal.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empMaxSal;
    }

    public static Map<String, Double> countOfEmp(Connection con) {
        Map<String,Double> empCount=new HashMap<>();
        try {
            stmt=con.createStatement();
            rs=stmt.executeQuery("select gender,count(id) from employee_payroll group by gender");
            while(rs.next()) {
                empCount.put(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empCount;
    }
}
