# Movie Web Application Automation Suite 🎬

## 📋 Introduction

This is a **personal automation testing project** built as part of my *learning-by-doing* journey in Automation Testing.

Instead of learning only from theory, this project was created to practice real-world QA workflows, apply automation tools in a practical context, and continuously improve my testing skills through hands-on experience.

The goal of this project is to:

- Apply automation concepts in a real project  
- Practice designing a maintainable test framework  
- Simulate real-world QA scenarios  
- Learn and improve through implementation  

The automation suite validates core features of a movie streaming web application, including authentication, search, filtering, and pagination.

This project reflects my growth mindset and commitment to becoming a professional Automation Tester.

---

## 🚀 Project Overview

This automation suite validates core business flows of a movie streaming platform, focusing on reliability, scalability, and maintainability.

### Key Functional Areas Covered

- User Authentication (Login, Register, Forgot Password)  
- Movie Search & Filtering  
- Pagination  
- UI Validation  
- API Testing for Movie Data  

The framework is built to simulate **real QA project structure** used in professional environments.

---

## 🧠 Testing Approach

### ✔️ Automation Strategy

- Focus on **critical user journeys**
- Validate both **positive & negative scenarios**
- Combine **UI and API testing**
- Use data-driven testing where applicable

### ✔️ Design Principles

- Page Object Model (POM)
- Separation of concerns
- Reusable components
- Centralized configuration
- Clean logging & reporting

---

## 🛠 Tech Stack

- **Language:** Java 23  
- **UI Automation:** Selenium WebDriver  
- **Test Framework:** TestNG  
- **API Testing:** REST Assured  
- **Build Tool:** Maven  
- **Reporting:** Allure Report  
- **Logging:** Log4j2 + SLF4J  
- **Data Handling:** OpenCSV  

---

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
```

---

## 📊 Reporting & Debugging

- Allure Report integration  
- Automatic screenshot capture on failures  
- Structured logging for easier debugging  
- Clear test result visualization  

---

## 📝 Test Coverage Status

| Feature | Coverage |
|--------|---------|
| Login | ✅ |
| Register | ✅ |
| Search | ✅ |
| Forgot Password | ✅ |
| Movie Filter | ✅ |
| Pagination | ✅ |
| API Testing | 🚧 In Progress |

---

## 💡 What This Project Demonstrates

- Ability to design a maintainable automation framework  
- Understanding of real QA workflows  
- Knowledge of UI & API automation  
- Clean coding practices  
- Scalable project structure  

---

## 📌 Future Improvements

- CI/CD integration (GitHub Actions/Jenkins)  
- Cross-browser execution  
- Dockerized test execution  
- Test data management enhancement

---

## 👨‍💻 Author

**Tran Dang Duy**  
Automation Testing Learner | Aspiring QA Engineer  

This project is part of my personal portfolio to demonstrate my practical skills and learning progress in test automation.
