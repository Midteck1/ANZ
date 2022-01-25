package anz.teststeps;




import java.util.List;
import java.util.Map;

import anz.context.TestContext;
import anz.pages.ANZLandingPage;
import anz.testsetps.data.TestDetails;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;



public class DetailsEntry {

	public TestContext context;
	public ANZLandingPage landingpage;

	public DetailsEntry(TestContext context) {
		this.context=context;
		this.landingpage=this.context.getPageObjectManager().getHomePage();
	}
	@When("Enter following details")
	public void enter_following_details(List<TestDetails> jobGroups) {
		System.out.println("Total row "+jobGroups.size());
		landingpage.EnterDetails(jobGroups,jobGroups.size());
	}

	@Then("Click StartOver and clear the form")
	public void click_start_over() {
		landingpage.clickStatOver();
	}

	@Then("Enterning only for living expenses and click Work out how much I could borrow button")
	public void enterning_only_$_for_living_expenses() {
		landingpage.enteringLeavingExp();

	}


	@Then("verfiy the message")
	public void verfiy_the_message() {
		landingpage.verifyMessage("Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.");

	}

	@DataTableType
	public TestDetails entry(Map<String, String> entry) {
		System.out.println(entry.toString());
		return new TestDetails(entry.get("NoDependents"),
				entry.get("AnnualIncome"),
				entry.get("AnnualOtherIncome"),entry.get("LivingExp"),
				entry.get("CurrentHLRepa"),entry.get("OtherLP"),entry.get("OtherComm"),entry.get("TotalCC"));
	}
}





