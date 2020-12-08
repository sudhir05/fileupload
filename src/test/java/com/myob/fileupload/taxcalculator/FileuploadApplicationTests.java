package com.myob.fileupload.taxcalculator;

import com.myob.fileupload.taxcalculator.controller.TaxCalculatorController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(controllers = TaxCalculatorController.class)//FileuploadApplicationTests
public class FileuploadApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testMyMonthlyIncome() throws Exception
	{
		final double grossIncome = 60000;
		final String employeeName = "Daivik Dan Brown";

		this.mockMvc.perform(MockMvcRequestBuilders.get("/GenerateMonthlyPayslip/{name}/{income}", employeeName, grossIncome))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.grossMonthlyIncome").value("5000.00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.monthlyIncomeTax").value("500.00"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.netMonthlyIncome").value("4500.00"));
	}
	@Test
	public void shouldReturn404WhenIncompleteUrlEntered() throws Exception
	{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/GenerateMonthlyPayslip"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	@Test
	public void shouldReturn400WhenBadRequestIsPassed() throws Exception
	{
		final String grossIncome = "NonNumericValue";
		final String employeeName = "Daivik Dan Brown";

		this.mockMvc.perform(MockMvcRequestBuilders.get("/GenerateMonthlyPayslip/{name}/{grossIncome}", employeeName, grossIncome))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	@Test
	public void shouldReturnErrorWhenNegativeGrossSalaryIsEntered() throws Exception
	{
		final double grossIncome = -1;
		final String employeeName = "Daivik Dan Brown";

		this.mockMvc.perform(MockMvcRequestBuilders.get("/GenerateMonthlyPayslip/{name}/{grossIncome}", employeeName, grossIncome))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
}