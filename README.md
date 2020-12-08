Assumptions
------------------
1. Code has been build and tested with Java 11.
2. Hit the URL in browser/postman or through curl. URL is http://localhost:8080/GenerateMonthlyPayslip/employeeName/annualIncome>.
3. Response can be seen in JSON.

How to Run
-------------
1. Unzip fileupload.zip.
2. Go to project directory i.e. fileupload.
3. If you have installed maven and maven path is correctly set. Run ``mvn test`` to see the test results.
4. To run the application through maven, Run `mvn spring-boot:run`. This will open `8080` port. 
   Hit url `http://localhost:8080/GenerateMonthlyPayslip/<employee name>/<annual income>`. 
5. If you have not installed maven, you can get the output through running jar. From the project folder
   run `java -jar target/fileuplaod-0.0.1-SNAPSHOT.jar`. This will open `8080` port. 
   Hit url `http://localhost:8080/GenerateMonthlyPayslip/<employee name>/<annual income>`. 
 
 Test cases
 ----------------
 There are 4 test cases written for unit testing and code coverage.
 1. Calculate and validate monthly income, monthly income tax and net monthly income.
 2. Return invalid URL if the URL passed is invalid or incorrect.
 3. Return Bad Input if the Income entered in Negative.
 4. Return Bad Input if the Income entered in Alphabets.
 5. Return Bad Input if the name entered in Numeric.
 
 Sample Input and Output
 --------------------
 1. Input: `http://localhost:8080/GenerateMonthlyPayslip/sudhir%20kumar/-23000`
    Output: This is not a valid URL. Name should contain only Alphabet. Gross salary must be numeric and Non Negative.
 2. Input: `http://http://localhost:8080/GenerateMonthlyPayslip/Sudhir/80001`
    Output: {"name":"Sudhir","grossMonthlyIncome":"6666.75","monthlyIncomeTax":"833.36","netMonthlyIncome":"5833.39"}
 3. Input: `http://localhost:8080/GenerateMonthlyPayslip/Sudhir/Myob`
    Output: This is not a valid URL. Name should contain only Alphabet. Gross salary must be numeric and Non Negative.
 4. Input: `http://localhost:8080/GenerateMonthlyPayslip/bad-stuff-blah%20blah`
    Output: This is not a valid URL. Valid URL e.g.http://localhost:8080/GenerateMonthlyPayslip/Dan/60000