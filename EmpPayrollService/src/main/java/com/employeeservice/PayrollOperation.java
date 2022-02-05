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
    /*
     * updateData method to update employee_service data in employee_payroll db.
     * @param Connection object
     * @return EmployeePayrollData object which contains updated data.
     * updating basic_pay for TERISA using update query.
     * */
    public static EmployeePayrollData updateData(Connection con)
    {
        boolean chk=false;
        try {
            //using prepared statement and passing user's name and salary to update.
            pstmt =con.prepareStatement("update employee_payroll set basic_pay=? where name=?");
            pstmt.setDouble(1, 300000);
            pstmt.setString(2,"Terisa");
            int a=pstmt.executeUpdate();
            //getting the updated record from db
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
    /*
     * readDataByName method to get data from employee_service table by giving particular name.
     * @param Connection object and name
     * @return EmployeePayrollData object which contains data for given user.
     * Getting data for given user.
     * */
    public static EmployeePayrollData readDataByName(Connection con,String name)
    {
        try {
            //elect query to get data by passing name para using prepared statement
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
    /*
     * getEmployeesJoinedInGivenRange method to get data from employee_service table who joined in between given date range.
     * @param Connection object, start and end date.
     * @return EmployeePayrollData List which contains data of user who joined between given date range.
     * */
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
	 /*
     * sumOfSal method to get sum of salary of each gender type.
     * @param Connection obj 
     * @return MAP<String,Double> Gender,Sum of Salary.
     * */
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
	/*
     * avgOfSal method to get avg of salary of each gender type.
     * @param Connection obj 
     * @return MAP<String,Double> Gender,Avg of Salary.
     * */
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
	/*
     * minOfSal method to get min of salary of each gender type.
     * @param Connection obj 
     * @return MAP<String,Double> Gender,min of Salary.
     * */
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
	/*
     * getMaxOfSalByGender method to get max of salary of each gender type.
     * @param Connection obj 
     * @return MAP<String,Double> Gender,Max of Salary.
     * */
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
	/*
     * getCountOfEmpByGender method to get count of emp of each gender type.
     * @param Connection obj 
     * @return MAP<String,Double> Gender,count of emp.
     * */
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
