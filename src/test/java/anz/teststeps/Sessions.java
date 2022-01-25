package anz.teststeps;

import java.net.MalformedURLException;

import anz.context.TestContext;
import anz.pages.ANZLandingPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

public class Sessions {

	public TestContext context;
	public ANZLandingPage landingpage;
	
	public Sessions(TestContext context) {
		//System.out.println("Sessons Test Context..");
		this.context=context;
		this.landingpage=this.context.getPageObjectManager().getHomePage();
		
	}
	@Before
	public void before(io.cucumber.java.Scenario scenario) {
		context.createScenario(scenario.getName());
		context.log("Starting Scenarios  "+scenario.getName());
	}
	@After
	public void after(io.cucumber.java.Scenario scenario) {
		context.log("Ending Scenarios "+ scenario.getName());
		context.endScenario();
		context.getPageObjectManager().getWebDriverManager().quit();
	}
	@Given("^I am navigation to ANZ site")
	public void doLogin()   {
		context.log("I am navigating to ANZ site");

		landingpage.load("Chrome");
		

	}
	
	
}
