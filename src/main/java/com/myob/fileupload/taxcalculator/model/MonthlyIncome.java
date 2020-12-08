package com.myob.fileupload.taxcalculator.model;

public class MonthlyIncome {
    private String name;
    private String grossMonthlyIncome;
    private String monthlyIncomeTax;
    private String netMonthlyIncome;

    public void setName(String name) {
        this.name = name;
    }

    public void setGrossMonthlyIncome(String grossMonthlyIncome) {
        this.grossMonthlyIncome = grossMonthlyIncome;
    }

    public void setMonthlyIncomeTax(String monthlyIncomeTax) {
        this.monthlyIncomeTax = monthlyIncomeTax;
    }

    public void setNetMonthlyIncome(String netMonthlyIncome) {
        this.netMonthlyIncome = netMonthlyIncome;
    }

    public String getName() {
        return name;
    }

    public String getGrossMonthlyIncome() {
        return grossMonthlyIncome;
    }

    public String getMonthlyIncomeTax() {
        return monthlyIncomeTax;
    }

    public String getNetMonthlyIncome() {
        return netMonthlyIncome;
    }
}

