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
    }


}
