package com.employeeservice;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EmployeePayrollService {
    public static Connection con=DBConnectionClass.getConnection();
    public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO}

    private List<EmployeePayrollData> employeePayrollList;
    public EmployeePayrollService()
    {

    };
    public EmployeePayrollService(List<EmployeePayrollData> employeePayrollList) {
        this.employeePayrollList = employeePayrollList;
    }
    public static void main(String[] args) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollList);
        Scanner consoleInputReader = new Scanner(System.in);
        employeePayrollService.readEmployeePayrollData(consoleInputReader);
        employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
    }


    private void readEmployeePayrollData(Scanner consoleInputReader) {
        System.out.println("Enter Employee ID: ");
        int id = consoleInputReader.nextInt();
        System.out.println("Enter Employee Name: ");
        String name = consoleInputReader.next();
        System.out.println("Enter Employee Salary: ");
        double salary = consoleInputReader.nextDouble();
        employeePayrollList.add(new EmployeePayrollData(id, name, salary));
    }

    public void writeEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.CONSOLE_IO))
            System.out.println("\nWriting Employee Payroll Roaster to Console\n" + employeePayrollList);
        else {
            if (ioService.equals(IOService.FILE_IO)) {
                new EmployeePayrollFileIOService().writeData(employeePayrollList);
            }
        }
    }

    public long countEntries(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return -2;
    }

    public void printData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            new EmployeePayrollFileIOService().printData();
        }
    }
    public long readEmployeePayrollData(IOService ioService) {
        if (ioService.equals(IOService.FILE_IO)) {
            return new EmployeePayrollFileIOService().countEntries();
        }
        return -2;
    }

    public static List<EmployeePayrollData> getEmployeeData(IOService ioService)
    {
        List<EmployeePayrollData> emp = null;
        if(ioService.equals(IOService.DB_IO))
            emp= PayrollOperation.readData(con);
        return emp;
    }

    public static EmployeePayrollData updateEmployeeData(IOService ioService)
    {
        EmployeePayrollData e=null;
        if(ioService.equals(IOService.DB_IO))
            e=PayrollOperation.updateData(con);
        return e;
    }

    public static EmployeePayrollData getDataByName(IOService ioService,String name)
    {
        EmployeePayrollData e=null;
        if(ioService.equals(IOService.DB_IO))
            e= PayrollOperation.readDataByName(con,name);
        return e;
    }


    public static List<EmployeePayrollData> getEmployeesJoinedByDate(IOService ioService,String st,String en)
    {
        Date start = Date.valueOf(st);
        Date end = Date.valueOf(en);
        List<EmployeePayrollData> e = null;
        if(ioService.equals(IOService.DB_IO))
            e= PayrollOperation.getEmployeesJoinedInGivenRange(con,start,end);
        return e;
    }

    public static Map<String,Double> getSumOfSalByGender(IOService ioService)
    {
        Map<String,Double> sum=null;
        if(ioService.equals(IOService.DB_IO))
            sum=PayrollOperation.sumOfSal(con);
        return sum;
    }
    public static Map<String,Double> getAvgOfSalByGender(IOService ioService)
    {
        Map<String,Double> avg=null;
        if(ioService.equals(IOService.DB_IO))
            avg=PayrollOperation.avgOfSal(con);
        return avg;
    }
    public static Map<String,Double> getMinOfSalByGender(IOService ioService)
    {
        Map<String,Double> minSal=null;
        if(ioService.equals(IOService.DB_IO))
            minSal=PayrollOperation.minOfSal(con);
        return minSal;
    }

    public static Map<String,Double> getMaxOfSalByGender(IOService ioService)
    {
        Map<String,Double> maxSal= null;
        if(ioService.equals(IOService.DB_IO))
            maxSal=PayrollOperation.maxOfSal(con);
        return maxSal;
    }
    public static Map<String,Double> getCountOfEmpByGender(IOService ioService)
    {
        Map<String,Double> empCount= null;
        if(ioService.equals(IOService.DB_IO))
            empCount=PayrollOperation.countOfEmp(con);
        return empCount;
    }
}
