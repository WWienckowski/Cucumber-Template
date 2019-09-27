
# Cucumber / Selenium Template

## Contents

1. [Directory](#directory)
2. [Running a Test](#running-a-test)
3. [Changing the Test Environment](#changing-the-test-environment)
4. [Generating a Cluecumber Report](#generating-a-cluecumber-report)

## Directory

### src/test/resources

* **features** - where the '.feature' or gherkin files go

### src/test/java

* **com.template**
  * DriverManager.java - launches the desired browser as it's configured in this file
  * Helpers.java - general utilities
  * PageObjectManager.java - holds all page objects, which abstract the minute actions used in the step definitions
  * RunnerTestCase.java - cucumber configuration options

* **com.template.page_objects** - where page object methods are located

* **com.template.stepdefs** - where step definitions and step hooks are located

### target - where reports are generated

* **basic-html-report** - generates a simple html report named "index.html"

* **testing-report YYYY-DD-MM HH:MM:SS z** - generates a styled, navigable report named "index.html"

[Back to Contents](#contents)

## Running a Test

  From the directory where the pom.xml file is located, type:
  
  ```mvn test```

  This will run all features and all scenarios. To run only specifically tagged features or scenarios type:

  ```mvn test -Dcucumber.options="--tags '@tagName'"```

  | Expression | Description |
  |------------|-------------|
  |```@tagName and not @otherTagName```| All scenarios tagged with ```@tagName```, but not ```@otherTagName``` |
  |```@tagName or @otherTagName```| All scenarios tagged with either ```@tagName``` or ```@otherTagName``` |
  |```@tagName and @otherTagName```| All scenarios tagged with both ```@tagName``` and ```@otherTagName```
  |```(@tagName or @otherTagName) and (not @thirdTagName)```| All scenarios tagged with either ```@tagName``` or ```@otherTagName```, ignoring any of those which are tagged with ```@thirdTagName```

  [Back to Contents](#contents)

## Changing the Test Environment

  The test environment can be changed when running the test from the command line like this:

  ```mvn test -Dlocation="environment"```

  | Environment | Description | Url |
  |-------------|-------------|------|
  |```-Dlocation="DEV"```| Dev environment | <http://pink-develop.s3-website.us-east-2.amazonaws.com/> |
  |```-Dlocation="QA"```| Qa environment | <http://pink-qa.s3-website-us-east-1.amazonaws.com/> |
  |```-Dlocation="LOCAL"```| Local environment | <http://localhost:4200/> |

  If the tests are run without specifiying the environment, then the environment will default to Dev.

  [Back to Contents](#contents)

## Generating a Cluecumber Report

  When the test runs it will generate a basic html report and the results will be shown in the terminal, however the stylized cluecumber report will not generate unless you run the tests with:

  ```mvn verify```

  You can use the same expressions with this command to run specific tests, such as:

  ```mvn verify -Dcucumber.options="--tags '@tagName'"```

  In this way, you can run the tests and generate a cluecumber report.
  
  If you just want to generate a cluecumber report from the existing cucumber.json file without running additional tests enter:

  ```mvn cluecumber-report:reporting```

  [Back to Contents](#contents)
