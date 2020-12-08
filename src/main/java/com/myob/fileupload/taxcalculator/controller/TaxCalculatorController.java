package com.myob.fileupload.taxcalculator.controller;

import com.myob.fileupload.taxcalculator.model.MonthlyIncome;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.text.DecimalFormat;


/*
This class will calculate monthly income, monthly income tax and net income based on Gross Income.
Name and Gross Income are validated.
 */

@RestController
@Validated
public class TaxCalculatorController {

    @GetMapping(value = "/GenerateMonthlyPayslip/{name}/{income}")
    @ResponseBody
    public MonthlyIncome  calculateGrossSalary(@Pattern(regexp = "[^0-9]*") @PathVariable String name, @Min(0) @PathVariable String income)
    {
        DecimalFormat df = new DecimalFormat("####0.00");
        double grossIncome = 0;
        try
        {
            grossIncome = Double.parseDouble(income);
        }
        catch(NumberFormatException ex)
        {
            ResponseEntity.badRequest();
        }
        MonthlyIncome monthlyIncome = new MonthlyIncome();
        double monthlyIncomeTax = calculateTotalTax(grossIncome);
        double netMonthlyIncome = getMonthlyIncome(grossIncome) - monthlyIncomeTax;

        monthlyIncome.setName(name);
        monthlyIncome.setGrossMonthlyIncome(df.format(getMonthlyIncome(grossIncome)));
        monthlyIncome.setMonthlyIncomeTax(df.format(monthlyIncomeTax));
        monthlyIncome.setNetMonthlyIncome(df.format(netMonthlyIncome));

        return monthlyIncome;
    }
    private double getMonthlyIncome(double grossIncome)
    {
        return  grossIncome/12;
    }
    private double calculateTotalTax(double grossIncome)
    {
        double netMonthlyTax;
        if (grossIncome >= 0 && grossIncome <= 20000)
        {
            netMonthlyTax = 0;
        }
        else if (grossIncome >= 20001 && grossIncome <= 40000)
        {
            netMonthlyTax = ((grossIncome - 20000)*0.1)/12;
        }
        else if(grossIncome >= 40001 && grossIncome <= 80000)
        {
            netMonthlyTax = ((40000 - 20000)*0.1 + (grossIncome - 40000)*0.2)/12;
        }
        else if(grossIncome >= 80001 && grossIncome <= 180000)
        {
            netMonthlyTax =  ((40000 - 20000)*0.1 + (80000 - 40000)*0.2 + (grossIncome - 80000)*0.3)/12;

        }
        else
        {
            netMonthlyTax = ((40000 - 20000)*0.1 + (80000 - 40000)*0.2 + (180000 - 80000)*0.3 + (grossIncome - 180000)*0.4)/12;
        }
        return netMonthlyTax;
    }

}
