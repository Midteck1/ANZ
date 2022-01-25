package anz.manager;

import anz.pages.ANZLandingPage;

public class PageObjectManager {
	
	ANZLandingPage homepage;
	
	WebDriverManager app;
	
	public PageObjectManager() {
		this.app=new WebDriverManager();
	}

	
	public ANZLandingPage getHomePage() {
		if(homepage==null) {
			System.out.println("Landing page inint");
			homepage=new ANZLandingPage(app);
		}
		return homepage;
	}
		

	public WebDriverManager getWebDriverManager() {
		
		return app;
		// TODO Auto-generated method stub
		
	}
	
	
}
