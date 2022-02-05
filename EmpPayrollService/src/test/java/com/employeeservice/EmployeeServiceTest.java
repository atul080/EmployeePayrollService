/*
 * @Author Atul Srivastav
 * Employeeervice Test class.
 * @Date- 05/02/22
 * */
package com.employeeservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static com.employeeservice.EmployeePayrollService.IOService.DB_IO;
import static com.employeeservice.EmployeePayrollService.IOService.FILE_IO;

public class EmployeeServiceTest {
    @Test
    public void givenEmployeesWhenWrittenToFileShouldMatchEmployeeEntries() {
        EmployeePayrollData[] arrayOfEmps = {
                new EmployeePayrollData(1, "3eff Bezos", 100000.0),
                new EmployeePayrollData(2, "Bill Gates", 200000.0),
                new EmployeePayrollData(3, "Mark Zuckerberg", 300000.0)};
        EmployeePayrollService employeePayrollService =
                new EmployeePayrollService(Arrays.asList(arrayOfEmps));
        employeePayrollService.writeEmployeePayrollData(FILE_IO);
        employeePayrollService.printData(FILE_IO);
        long entries = employeePayrollService.countEntries(FILE_IO);
        Assertions.assertEquals(3, entries);
    }

    @Test
    public void givenFile0nReadingFromFileShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
        long entries = employeePayrollService.readEmployeePayrollData(FILE_IO);
        Assertions.assertEquals(3, entries);
    }

    @Test
    public void resultCount_shouldEqual_toActualDataSize()
    {
        List<EmployeePayrollData> data= EmployeePayrollService.getEmployeeData(DB_IO);
        Assertions.assertEquals(3,data.size());
    }
    @Test
    public void updatedValue_shouldMatch_toActualDBReult()
    {
      EmployeePayrollData e= EmployeePayrollService.updateEmployeeData(DB_IO);
      Assertions.assertEquals(300000,e.getBasicPay());
    }
    @Test
    public void whenGivingName_shouldRetiveDataFromDB_andMatchToExpected()
    {
        EmployeePayrollData e= EmployeePayrollService.getDataByName(DB_IO,"Bill");
        Assertions.assertEquals("Bill",e.getName());
        Assertions.assertEquals(1,e.getId());
        Assertions.assertEquals(1000000,e.getBasicPay());
    }

    @Test
    public void whenGivingStartEndDate_shouldRetiveDataFromDB_andMatchToExpected()
    {
        List<EmployeePayrollData> data= EmployeePayrollService.getEmployeesJoinedByDate(DB_IO,"2018-01-01","2019-12-01");
        Assertions.assertEquals(2,data.size());
    }
	  @Test
    public void shouldRetiveSalaryByGender_andMatchToExpected()
    {
        Map<String,Double> empSum= EmployeePayrollService.getSumOfSalByGender(DB_IO);
        Assertions.assertEquals(1000000,empSum.get("M"));
        Assertions.assertEquals(3300000,empSum.get("F"));

        Map<String,Double> empAvg= EmployeePayrollService.getAvgOfSalByGender(DB_IO);
        Assertions.assertEquals(1000000,empAvg.get("M"));
        Assertions.assertEquals(1650000,empAvg.get("F"));

        Map<String,Double> empMinSal= EmployeePayrollService.getMinOfSalByGender(DB_IO);
        Assertions.assertEquals(1000000,empMinSal.get("M"));
        Assertions.assertEquals(300000,empMinSal.get("F"));

        Map<String,Double> empMaxSal= EmployeePayrollService.getMaxOfSalByGender(DB_IO);
        Assertions.assertEquals(1000000,empMaxSal.get("M"));
        Assertions.assertEquals(3000000,empMaxSal.get("F"));

        Map<String,Double> empCount= EmployeePayrollService.getCountOfEmpByGender(DB_IO);
        Assertions.assertEquals(1,empCount.get("M"));
        Assertions.assertEquals(2,empCount.get("F"));

    }

}
