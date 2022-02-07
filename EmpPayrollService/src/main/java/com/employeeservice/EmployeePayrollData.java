package com.employeeservice;

import java.util.Date;

public class EmployeePayrollData {
    public int id;
    public String name;
    public double basicPay;
    public Date date;

    public EmployeePayrollData(int id,String name,double basicPay)
    {
        this.id=id;
        this.name=name;
        this.basicPay=basicPay;
    }

    public EmployeePayrollData(int id,String name,double basicPay,Date date)
    {
        this.id=id;
        this.name=name;
        this.basicPay=basicPay;
        this.date=date;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasicPay() {
        return basicPay;
    }


    @Override
    public String toString(){
        return "id = "+id+" name = "+name+" salary = "+basicPay;
    }


    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass() )
            return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return id == that.id &&
                Double.compare(that.basicPay, basicPay) == 0 &&
                name.equals(that.name);
    }

}
