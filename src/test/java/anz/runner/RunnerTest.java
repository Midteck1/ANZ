package anz.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features= {"src/test/resources/anz/Details.feature"},
		glue= {"anz.teststeps"},
		plugin= {"html:target/cucumber-reports.html","rerun:rerun/failed_scenarios.txt"},
		//tags= "@Details",
		
		monochrome=false,
		dryRun=false
		)


public class RunnerTest extends AbstractTestNGCucumberTests{

	
}
