
# Cucumber / Selenium Template

## Contents

1. [Directory](#directory)
2. [Choosing the browser with scenario names](#choosing-the-browser-with-scenario-names)
3. [Running a Test](#running-a-test)
4. [Generating a Cluecumber Report](#generating-a-cluecumber-report)

## Directory [^](#contents)

### src/test/resources

* **features** - where the '.feature' or gherkin files go

### src/test/java

* **com.template**
  * DriverManager.java - launches the desired browser as it's configured in this file
  * Helpers.java - general utilities
  * PageObjectManager.java - holds all page objects, which abstract the minute actions used in the step definitions
  * RunnerTestCase.java - cucumber configuration options

* **com.template.page_objects** - where page objects are located

* **com.template.stepdefs** - where step definitions and step hooks are located

### target - where reports are generated

* **basic-html-report** - generates a simple report named "index.html"

* **cluecumber-report** - generates a nicer looking report named "index.html"

## Choosing the browser with scenario names [^](#contents)
  
  By default, the tests will run in FireFox unless the scenario name contains the word "Chrome" or "Edge" (case sensitive), in which case the test will be run using the Google Chrome or Microsoft Edge respectively.

  Optionally, you can test the same scenario on different browsers by creating a scenario outline like this:

    Scenario Outline: Perform a test in <browser>
      Given ...
      When ...
      Then  ...
    Examples:
    | browser |
    | "Firefox" |
    | "Chrome" |
    | "Edge" |

  In this case the scenario will be tested three times, once in FireFox, once in Chrome, and once in Edge

## Running a Test [^](#contents)

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

## Generating a Cluecumber Report [^](#contents)

  When the test runs it will generate a basic html report and the results will be shown in the terminal, however the stylized cluecumber report will not generate unless you run the tests with:

  ```mvn verify```

  You can use the same expressions with this command to run specific tests, such as:

  ```mvn verify -Dcucumber.options="--tags '@tagName'"```

  In this way, you can run the tests and generate a cluecumber report.
  
  If you just want to generate a cluecumber report from the existing cucumber.json file without running additional tests enter:

  ```mvn cluecumber-report:reporting```
