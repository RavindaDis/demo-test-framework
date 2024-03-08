# Demo Test Automation Framework
* [Description](#description)
* [Getting Started](#getting-started)
  * [Java](#java)
  * [Maven](#maven)
* [Executing Tests](#executing-tests)
  * [API Tests](#api-tests) 
  * [Web Tests](#web-tests)
    * [Browser Switching](#browser-switching)
    * [Executing Web Tests](#executing-web-tests)
* [Test Result Analysis](#test-result-analysis)
  * [Reporting](#reporting)
  * [Logging](#logging)
* [Tools and Technologies](#tools-and-technologies)
* [Project structure](#project-structure)
* [Feature Highlights](#feature-highlights)
* [Future Development](#future-development)

## Description
This repository contains comprehensive automation tests designed for the Demo application, encompassing both frontend and backend verification. It provides distinct E2E test suites tailored for frontend validation, as well as integration (API) test suites focused on backend functionality and holistic system verification. Users can leverage these suites to ensure robust testing coverage across all facets of the application.
## Getting Started
To commence work on the project, it's essential to have the following tools and technologies configured:

#### Java
- Recommended version: Java 13 (or higher)
- To download: https://www.oracle.com/java/technologies/downloads/
- Installation guideline: https://docs.oracle.com/en/java/javase/18/install/overview-jdk-installation.html
- To check the current version installed:
```bash
java -version
```

#### Maven
- Recommended version: Maven 3.8 (or higher) 
- To download: https://maven.apache.org/download.cgi
- Installation guideline: https://maven.apache.org/install.html
- To check the current version installed:
```bash
mvn -version
```

## Executing Tests

## API Tests

- To execute **the API Regression Suite** using a command line interface, use the following maven goal from the main directory:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=./src/test/resources/api-test-suites/api_regression.xml
```
- Or to trigger a build via https://jenkins.demo.app/job/Demo-Test-Automation/job/demo-integration-tests/.

## Web Tests
### Browser Switching
To switch between Headless execution and GUI execution and to select the browser for GUI execution, set attributes as following in the *./src/test/resources/config.properties* file
##### Headless Browser execution
- **Headless**=**true**
- 'Browser' attribute will be ignored for Headless execution.
##### Chrome Browser GUI execution
- **Headless**=**false**
- **Browser**=**CHROME**
##### EDGE Browser GUI execution
- **Headless**=**false**
- **Browser**=**EDGE**

### Executing Web Tests
- To execute **all the UI tests** in command line using the following maven goal from main directory:
```bash
mvn clean test -Dsurefire.suiteXmlFiles=./src/test/resources/web-test-suites/e2e_regression.xml
```
- Or to trigger a build via https://jenkins.demo.app/job/Demo-Test-Automation/job/demo-e2e-tests/.

## Test Result Analysis
### Reporting
- A report will be created after each execution in: './test-output/' folder or the Allure Report tab if executed via Jenkins

### Logging
- Log files will be created after each execution with time stamp in: './test-output/Test Execution Logs' folder

## Tools and Technologies
- Web tests: Java, Selenium WebDriver, TestNG, Maven
- API tests: Java, Rest-Assured, Hamcrest Matchers, TestNG, Maven
- Reporting: Allure Reporter
- Logging: SLF4J, Logback, TestNG

## Project Structure
The project uses both main and test levels.
### main
In the project main is broken into 4 packages the resources file
#### api
- ApiBase class has to be extended for each api test class. This abstract class consists with request building and sending common methods.
#### web
- WebBase class has to be extended for each web test class. This class consists with the headless switch, browser setup and switching methods, browser closing methods, page object instanciation.
- pages and panels packages consists with the Page Object Model (POM).
#### listeners
- This package consists with the listener class which is used for logging. TestNG listeners are used here.
#### utils
- This package consists with the utility classes where constants are stored, property files are read, common supporting methods are placed etc.
#### resources
- jsonSchemaFiles directory consists with the schema files which are used for validating the responses.
- also this directory consists with the lockback xml file which configures logging.
### test
test consists with 2 main packages and resources.
#### api
- Consists with the API test classes and their helper classes
#### web
- Consists with all Web E2E test classes and their helper classes
#### resources
- api-test-suites directory includes all the testNG runner files for API tests
- web-test-suites directory includes all the testNG runner files for Web tests
- config.properties file which consists with the property values
### test-output
- Created after a test execution
- Consists of automatic test reports and logs
## Feature Highlights
- Toggle for on/off headless execution for the front-end tests
- Page Object Model (POM) design pattern
- Cross-browser support for Chrome and Edge
- JSON schema validation for APIs
- Executing tests in groups
- Report creation after each execution
- Execution log creation with the help of test listeners after each execution
- Changing attributes from property file level
## Future Development
- Utilizing Serialization and Deserialization techniques to effectively test complex APIs and ensure robust functionality.
- Leveraging APIs for streamlined test data creation and cleanup processes, resulting in improved execution efficiency and easier maintenance.
- Enhancing CI/CD scripts by incorporating parameterization and post-action functionalities for greater flexibility and automation.
- Expanding browser support to include Firefox and Safari, as well as implementing compatibility with various Headless browsers to cater to diverse testing environments.
- Introducing visual testing methodologies with tools like Applitools to detect and address UI discrepancies effectively.
- Integrating automated accessibility testing using tools such as AXE to ensure compliance with accessibility standards and enhance user experience.
- Enabling test execution across different devices such as mobile phones and tablets to ensure comprehensive coverage and compatibility.
- Implementing automated test results analysis leveraging machine learning capabilities provided by tools like Report Portal for in-depth insights and trend analysis.
- Incorporating retry mechanisms for failed test scripts to improve test robustness and reliability in dynamic environments.
- Integrating test automation executions with test management systems to facilitate seamless tracking, monitoring, and reporting for enhanced visibility and control.
