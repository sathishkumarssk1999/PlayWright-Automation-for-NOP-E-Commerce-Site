# Playwright Automation for NOP E-Commerce Site

This project aims to automate an e-commerce website - https://demo.nopcommerce.com/ using **Playwright with java**.
It follows a Data-Driven Framework and utilizes the Page Object Model approach.


## Prerequisites

Ensure that your system has the following software installed:

- Java (version 17 or above)
- Maven - (version 3.x)
- IntelliJ IDEA (community version)

## Add Dependencies in (POM.xml)

Add the following dependencies to your `pom.xml` file:


    <!-- Playwright -->
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.41.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Apache POI -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi</artifactId>
        <version>5.2.5</version>
    </dependency>

    <!-- Apache POI-OOXML -->
    <dependency>
        <groupId>org.apache.poi</groupId>
        <artifactId>poi-ooxml</artifactId>
        <version>5.2.5</version>
    </dependency>

    <!-- TestNG -->
    <dependency>
        <groupId>org.testng</groupId>
        <artifactId>testng</artifactId>
        <version>7.9.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Maven -->
    <dependency>
        <groupId>org.apache.maven</groupId>
        <artifactId>maven</artifactId>
        <version>3.10.1</version>
    </dependency>



## Getting Started

- Clone the repository from GitHub:
- git clone https://github.com/sathishkumarssk1999/PlayWright-Automation-for-NOP-E-Commerce-Site.git
- Open the project in your preferred IDE

## Note

- Please note that in Playwright Java, achieving Parallel testing is not supported
  Reference: https://playwright.dev/java/docs/multithreading
- Maximizing the window to full screen is not supported.
  Reference: https://github.com/microsoft/playwright/issues/1086
- Before each run old test result will get deleted automatically only **latest test result will be kept**
- Please be aware that the database on the https://demo.nopcommerce.com/ site is automatically reset every 15 to 30 minutes.

## Test Cases Covered

- `NewUserTest.java` - Registration process for 2 users 
- `LoginTest.java` -  Sign in process. Both positive and negative 
- `SearchTest.java` 
    -  Search process and retrieved data to excel
    -  Adding selected items to the cart and update the cart 
    -  One active session is there, opening same link in the same browser should close the previous one and opens newer session
    -  One active session is there, opening same link in different browser should not happen.

## Project Structure

The project follows standard Maven directory structure:

- `src/main/java/pages`: Homepage, LoginPage, RegisterPage, SearchPage
- `src/main/java/utils`: excelUtils
- `src/main/resources`: TestData.xls
- `src/test/java/Nopcommerce`: Base, LoginTest, NewUserTest, SearchTest
- `test-output`: screenshots
- `test-output`: TestOutput.xls
- `./`: README.md, TestNG.xml, pom.xml

## Running the Test Suite

- First of all, Make sure you have edit the `TestData.xls` workbook
- In the `TestData.xls` workbook, modify the email IDs for two users in the `register` sheet. 
- Now Run the `TestNG.xml` file, Test will execute in sequential manner
- Three tests will execute, consist of 9 TestData

## Test Output and Results

After running the TestNG suite, find the following artifacts in the `test-output` folder:

- Screenshots for each test data, saved in the specified path `(./test-output/screenshots/)`
- The search results are extracted and stored in an Excel sheet `TestOutput.xls` along with 
   relevant details corresponding to the search criteria.



