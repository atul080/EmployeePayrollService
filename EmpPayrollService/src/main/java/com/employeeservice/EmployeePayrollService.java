/*
 * @Author Atul Srivastav
 * EmployeePayrollOperations Class to perform all the actions.
 * @Date- 05/02/22
 * */
package com.employeeservice;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeePayrollService {
    //object of DBConnection class
    public static Connection con=DBConnectionClass.getConnection();
    //declarations
    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}
    private List<EmployeePayrollData> employeePayrollList;
    public EmployeePayrollService()
    {

    };
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }
    /*
     * main method to do CONSOLE_IO operations
     *  */
    public static void main(String[] args) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }

    /*
     * readEmployeePayrollData method to read employee data from console.
     * @param Scanner obj
     * */
    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }
    /*
     * writeEmployeePayrollData method to write employee data to console.
     * @param IOService obj
     * */
    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
        else {
            if (ioService.equals(IOService.FILE_IO)) {
                new EmployeePayrollFileIOService().writeData(employeePayrollList);
            }
        }
    }
    /*
     * countEntries method to get employee data count from file.
     * @param IOService obj
     * @return long COUNT of employees
     * */
    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return -2;
    }
    /*
     * printData method to print employee data by reading from file.
     * @param IOService obj FILE_IO
     * */
    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().printData();
        }
    }
    /*
     * readEmployeePayrollData method to read employee data from file.
     * @param IOService obj FILE_IO
     * @return long
     * */
    public long readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return -2;
    }
    /*
     * getEmployeeData method to get employee data from DB.
     * @param IOService obj DB_IO
     * @return List of all the user data present in DB
     * */
    public static List<EmployeePayrollData> getEmployeeData(IOService ioService)
    {
        List<EmployeePayrollData> emp = null;
        //checking if it is DB_IO and calling readData method by passing connection object.
        if(ioService.equals(IOService.DB_IO))
            emp= PayrollOperation.readData(con);
        return emp;
    }
    /*
     * updateEmployeeData method to update employee data by giving new values..
     * @param IOService obj DB_IO
     * calls updateData method.
     * @return EmployeePayrollData object which contains updated values
     * */
    public static EmployeePayrollData updateEmployeeData(IOService ioService)
    {
        EmployeePayrollData e=null;
        //checking if it is DB_IO and calling updateData method by passing connection object.
        if(ioService.equals(IOService.DB_IO))
            e=PayrollOperation.updateData(con);
        return e;
    }
}
