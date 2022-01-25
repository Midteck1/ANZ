package anz.pages;
import java.util.List;
import anz.manager.WebDriverManager;
import anz.testsetps.data.TestDetails;




public class ANZLandingPage {
	WebDriverManager app;
	
	public ANZLandingPage(WebDriverManager app) {
		this.app=app;
	}
	public void load(String browser)   {
		
		app.log("Trying to load home page");
		app.openBrowser(browser);
		app.navigate("url");
		app.waitForSec(3000);
		
	}	
	public void EnterDetails(List<TestDetails> jobGroups,int jobs) {
		
		for(int i=0;i<jobs;i++) {
			app.click("SingleButton_xp");
			app.selectElement("TitleList_xp",jobGroups.get(i).NoDependents);
			app.click("HomeToLiveInButton_xp");
			app.typeEnter("AnnualInComeTxt_xp", jobGroups.get(i).AnnualIncome);
			app.type("AnnualOtherIncome_xp", jobGroups.get(i).AnnualOtherIncome);
			app.type("MonthlyExp_xp", jobGroups.get(i).LivingExp);
			app.type("CurrentHomeLoan_xp", jobGroups.get(i).CurrentHLRepa);
			app.type("OtherLoan_xp", jobGroups.get(i).OtherLP);
			app.type("OtherMonthly_xp", jobGroups.get(i).OtherComm);
			app.type("TotalCC_xp", jobGroups.get(i).TotalCC);
			app.click("SubmmitButton_xp");
			app.waitForSec(3000);
			app.verifyText("EstimationText_xp", "$479,000");
			//app.verifyText("EstimationText_xp", "$482,000");
			//$482,000
		
		}

	}
	public void clickStatOver() {
		app.click("StartOverButton_xp");
		
		app.verifyValue("MonthlyExp_xp", "0");
		
	}
	public void enteringLeavingExp() {
		app.type("MonthlyExp_xp", "1");
		app.waitForSec(2000);
		app.scrollDown();
		app.click("SubmmitButton_xp");
		
	}
	public void verifyMessage(String expectedMessage) {
		app.verifyText1("ErrorMessage_xp", expectedMessage);
	}
	
}
