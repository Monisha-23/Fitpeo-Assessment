# FitPeo Automation Assessment

## Overview

This is an automation framework built with **Selenium WebDriver** to test web applications. The project uses **Cucumber** for behavior-driven development (BDD), simplifying the creation of test scenarios using Gherkin syntax. The framework is designed to provide a maintainable and scalable approach to web application testing.

## Technologies Used

- **Selenium WebDriver**: Automates browser interactions for functional testing.
- **Cucumber**: Implements behavior-driven testing, allowing tests to be written in natural language (Gherkin).
- **JUnit**: Manages the execution of test cases and assertions.
- **Maven**: Handles project dependency management and the build lifecycle.
- **WebDriverWait**: Ensures elements are interactable before performing actions by applying explicit waits.
- **Java**: The programming language used to implement test scripts.
- **EdgeDriver**: WebDriver for automating tests on the Microsoft Edge browser.

## Prerequisites

Ensure the following are installed and configured on your system before running the tests:

- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE.
- **Java**: JDK 8 or higher.
- **Maven**: For project dependency management.
- **Microsoft Edge WebDriver**: Ensure the appropriate version of EdgeDriver is available. The WebDriver is already included under `src/main/resources/drivers/` in the project.

## Setup Instructions

### 1. Clone the Repository
Clone the project repository to your local machine:
```bash
git clone https://github.com/yourusername/selenium-automation-project.git
cd selenium-automation-project
```

## Setup

1. **Clone the Repository**:
   ```bash
   https://github.com/Monisha-23/Fitpeo-Assessment.git
   ```
   
## Project Structure 
    selenium-automation-project/
    ├── src/
    │   ├── main/
    │   │   └── resources/
    │   │       ├── driver/
    │   │       │   └── msedgedriver.exe  (WebDriver executable)
    │   │       └── features/
    │   │           └── main.feature  (Cucumber feature file defining test scenarios)
    │   ├── java/
    │   │   └── org/
    │   │       └── example/
    │   │           ├── stepDefinitions/
    │   │           │   └── Main.java  (Step definitions for the feature file)
    │   │           └── runner/
    │   │               └── MainRunner.java  (Test runner class with setup/teardown hooks)
    │   └── test/
    │
    └── pom.xml  (Maven build file for dependency management and project configuration)


## Running the Tests
1. **Run the Project**
   To run the entire test suite, navigate to the MainRunner class located in runner package under src/main/java/org/example/. Right-click on the MainRunner.java file and run it as a JUnit test. This will execute all scenarios defined in the main.feature file.


2. **Run particular Scenario**
   Open the main.feature file located in the features directory under src/main/resources/.
   Select and execute a particular scenario or feature within the file by running it directly in your IDE or through the command line.

## Access Reports
   After running the tests through MainRunner, a target folder will be generated in the project root directory. Inside this folder, you will find the cucumber-report.html file, which contains detailed execution results. You can open this file in any web browser to review the test execution summary, including passed/failed scenarios, execution time, and other relevant data.




