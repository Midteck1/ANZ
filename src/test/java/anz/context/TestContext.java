package anz.context;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import anz.manager.PageObjectManager;
import anz.reports.ExtentManager;


public class TestContext {
	
	ExtentReports report;
	ExtentTest test;
	
	private PageObjectManager pageObjectManager;
	
	
	public TestContext() {
		System.out.println("Constructor TestContext");
		report=ExtentManager.getReports();
		pageObjectManager=new PageObjectManager();
		
	}
	
	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}
	public void createScenario(String scenarioName) {
		test=report.createTest(scenarioName);
		this.pageObjectManager.getWebDriverManager().init(test);
	}
	
	public void endScenario() {
		report.flush();
	}
	public void log(String msg) {
		this.pageObjectManager.getWebDriverManager().log(msg);
	}
	
}
