# Movie App Automation Suite 

Automated Testing Project for the Movie Project website, built using **Selenium WebDriver** and **Java**, following the **Page Object Model (POM)** design pattern.

## Introduction

This project contains automated test scripts (UI & Functional) for a movie streaming web application. It covers critical features such as Authentication (Login/Register/Forgot Password), Movie Search, Movie Details, and Filtering mechanisms.

**Target Application:** `https://movie-project-front-end.vercel.app`

## Tech Stack

This project utilizes the following technologies and libraries (based on `pom.xml`):

* **Language:** Java 23
* **Core Framework:** Selenium WebDriver (4.40.0)
* **Test Runner:** TestNG (7.11.0)
* **Build Tool:** Maven
* **Reporting:** Allure Report (2.24.0)
* **Logging:** Log4j2 & SLF4J
* **API Testing:** REST Assured (5.4.0)
* **Data Handling:** OpenCSV (5.7.1)

## Project Structure

The project follows the **Page Object Model (POM)** pattern to enhance test maintenance and code reusability.

```text
src
├── main
│   └── java
│       ├── base        # BasePage and common configurations
│       ├── components  # Shared components (Header, Footer, etc.)
│       ├── driver      # WebDriver management (DriverManager)
│       ├── pages       # Page Classes (LoginPage, HomePage, etc.)
│       └── utils       # Utilities (ConfigReader, ScreenshotUtils, CSVUtils)
└── test
    └── java
        ├── api         # API Test classes
        ├── base        # BaseTest (Driver Setup/Teardown)
        ├── listeners   # TestNG Listeners (Logging, Screenshots on failure)
        └── tests       # Main Test Classes (LoginTest, SearchMovieTest, etc.)
    └── resources
        ├── config      # Configuration files (config.properties)
        ├── log4j2.xml  # Logging configuration
        └── ...         # Test Data (CSV, etc.)
