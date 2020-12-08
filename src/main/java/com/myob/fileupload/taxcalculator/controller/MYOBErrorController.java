package com.myob.fileupload.taxcalculator.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/*
All the errors will be redirected to this class. This class will customize the response.
 */

@RestController
public class MYOBErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request)
    {
        String errorMsg = "Valid URL e.g.http://localhost:8080/GenerateMonthlyPayslip/Dan/60000";
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                errorMsg =  "This is not a valid URL. Valid URL e.g.http://localhost:8080/GenerateMonthlyPayslip/Dan/60000";
            } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                errorMsg =  "This is not a valid URL. Name should contain only Alphabet. Gross salary must be numeric and Non Negative.";
            }
        }
        return errorMsg;
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}