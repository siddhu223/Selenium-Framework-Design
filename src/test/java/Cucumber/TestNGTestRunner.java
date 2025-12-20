package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Cucumber", glue = "siddheshselenium.stepDefinations", monochrome = true, plugin = {
		"html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}

// glue= used so that it can link feature file with step defination
// monochrome is used because the result will be in encoded form so we can not
// understand so monochrome will translate it
// Plugin is used to generate report it requires target value pair
// so target is i want html report and value is path to store so in target/cucumber
// cucumber will not able to scan testng assertion, anotation/libraries so extends AbstractTestNGCucumberTests is used
// it will use only in  testng loader only not for junit
// In jUNIT it is inbuild
