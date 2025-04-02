# MB_Automation

Features & Capabilities of the Framework --

1.Java – It uses Java programming language.
2.TestNG – It uses TestNG as a testing framework. You can learn more about TestNG from our – TestNG tutorial.
3.Maven-based – It will be maven-based. So all the dependencies will be in a POM file and the test suite can be triggered using maven commands.
4.Hybrid Framework – It is a hybrid framework with a combination of the modular and data-driven framework.
5.Page Object Model – The framework will use the Page Object Model design pattern in Selenium.
6.Screenshot on failure – The framework is capable to capture screenshots in case of failed tests.
7.Test data in Excel – The framework has a utility class that will read test data from an excel file.

TestBase.java inside Base Package
This is the base file that performs set-up and tear-down operations like –  browser configurations, implicit and explicit waits handling, cookies deletion, etc. Each test class must extend this class.


Page Classes inside Pages Package
The ‘pages’ package contains all the page classes. Each page class contains the web elements, CSS selector and actions that can be performed on those classes.


Test Classes inside Test Package
The ‘test’ package contains all the test classes. Each test class extends the TestBase.java class and contains the test scripts.


Util Package
In the util package, we can have all the utilities e.g. in this framework, I have an ExcelUtil file that reads data from an excel file and converts it into 2D array.


Logs folder
The logs folder contains all the log files generated while running the test scripts.


TestData folder
The testData folder contains the test data for the test scripts e.g. test data input is given here and used by our test scripts.


Pom.xml file
The pom.xml contains all the dependencies used in the project.


TestNG.xml
TestNG.xml file contains the testNG configurations using which we can run a particular method, group, or test class and at the same time configure the tests to run in parallel.


