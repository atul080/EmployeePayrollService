/*
 * @Author Atul Srivastav
 * EmployeePayrollFileIOService Class to perform all the FILE IO operations.
 * @Date- 03/02/22
 * */
package com.employeeservice;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class EmployeePayrollFileIOService {
    EmployeePayrollFileIOService() {
    }
    //getting file name
    public static String PAYROLL_FILE_NAME = "payroll-file.txt";
    /*
     * writeData method to write employee data to File.
     * @param List that contains employee data to be wrigtten.
     * @return void.
     * */
    public void writeData(List<EmployeePayrollData> employeePayrollList) {
        StringBuffer empBuffer = new StringBuffer();
        employeePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(PAYROLL_FILE_NAME), empBuffer.toString().getBytes());
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    /*
     * countEntries method to get count of employees from File.
     * @return long COUNT of employees.
     * */
    public long countEntries() {
        try {
            return Files.lines(new File(PAYROLL_FILE_NAME).toPath()).count();
        } catch (IOException e) {
            return -1;
        }
    }
    /*
     * printData method to print employee data by reading File.
     * @return void.
     * */
    public void printData() {
        try {
            Files.lines(new File(PAYROLL_FILE_NAME).toPath()).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
