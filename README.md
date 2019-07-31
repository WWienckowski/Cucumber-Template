
# Cucumber / Selenium Template

## Contents

1. [Directory](#directory)
2. [Running a Test](#running-a-test)
3. [Generating a Cluecumber Report](#generating-a-cluecumber-report)

## Directory

### src/test/resources

* **features** - where the '.feature' or gherkin files go

### src/test/java

* **com.template**
  * DriverManager.java - launches the desired browser with as it's configured in this file
  * Helpers.java - general utilities
  * PageObjectManager.java - holds all page objects, which abstract the minute actions used in the step definitions
  * RunnerTestCase.java - cucumber configuration options

* **com.template.page_objects** - where page objects are located

* **com.template.stepdefs** - where step definitions and step hooks are located

### target - where reports are generated

* **basic-html-report** - generates a simple report named "index.html"

* **cluecumber-report** - generates a nicer looking report named "index.html"

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

## Generating a Cluecumber Report

  When the test runs it will generate a basic html report and the results will be shown in the terminal, however the stylized cluecumber report will not generate unless you run the tests with:

  ```mvn verify```

  You can use the same expressions with this command to run specific tests, such as:

  ```mvn verify -Dcucumber.options="--tags '@tagName'"```

  In this way, you can run the tests and generate a cluecumber report.
  
  If you just want to generate a cluecumber report from the existing cucumber.json file without running additional tests enter:

  ```mvn cluecumber-report:reporting```
