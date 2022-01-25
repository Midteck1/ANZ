package anz.manager;

import java.io.FileInputStream;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import anz.manager.DriverFactory.DriverType;
public class WebDriverManager {

	WebDriver driver;
	ExtentTest test;
	Properties prop;
	SoftAssert softAssertion;

	public WebDriverManager() {
		try {
			prop=new Properties();
			String path=System.getProperty("user.dir")+"/src/test/resources/project.properties";
			FileInputStream s;
			s = new FileInputStream(path);
			prop.load(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		softAssertion=new SoftAssert();
	}



	public void log(String msg) {
		test.log(Status.INFO, msg);
	}
	public void openBrowser(String browser) {
		driver=DriverFactory.getDriver(DriverType.CHROME);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}

	/*public void openBrowser(String browser) {
		log("Browser Opened ");
		if(browser.equals("Mozila")) {
			driver=new FirefoxDriver();
		}else if(browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver","Driver/chromedriver 5");
				driver=new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}*/


	public void waitForSec(int sec) {
		try {
			Thread.sleep(sec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void navigate(String url) {
		log("Navigating To "+ url);
		driver.get(getProperty(url));
	}
	public void click(String locatorKey) {
		findElement(locatorKey).click();
	}
	public void type(String locatorKey,String data) {
		findElement(locatorKey).sendKeys(data);
	}
	public void typeEnter(String locatorKey,String data) {
		waitForSec(2000);
		findElement(locatorKey).sendKeys(data,Keys.ENTER);
		waitForSec(500);
	}

	public void scrollDownPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

	}
	public void scrollDown() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");

	}
	
	public void selectElement(String locatorKey,String data) {
		Select sel=new Select(findElement(locatorKey));
		sel.selectByVisibleText(data);
	}
	public void selectElementByIndex(String locatorKey) {
		Select sel=new Select(findElement(locatorKey));
		sel.selectByIndex(1);
	}

	public void loginFailure(String msg,boolean stopExecution) {
		test.log(Status.FAIL, msg);
		softAssertion.fail(msg);
		if(stopExecution) 
			softAssertion.assertAll();

	}
	public WebElement findElement(String locatorKey) {
		By locator=getLocator(locatorKey);
		WebElement e =null;
		try {
			WebDriverWait wait=new WebDriverWait(driver, 120);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			e=driver.findElement(locator);
		}catch(Exception ec) {
			//report
			loginFailure("Object not found.."+locatorKey,true);
		}
		return e;
	}
	public By getLocator(String locatorKey) {
		if(locatorKey.endsWith("_id")) 
			return By.id(getProperty(locatorKey));
		else if(locatorKey.endsWith("_name")) 
			return By.name(getProperty(locatorKey));
		else if(locatorKey.endsWith("_xp"))
			return By.xpath(getProperty(locatorKey));
		else 
			return By.cssSelector(getProperty(locatorKey));

	}
		//******************************Validation ********************************

	public boolean verifyTitle(String expectedTitleKey) {
		String expectedTitle = getProperty(expectedTitleKey);
		String actualTitle=driver.getTitle();
		if(expectedTitle.equals(actualTitle))
			return true;
		else
			return false;
	}

	public boolean verifyValue(String locatorKey,String textToVerify) {
		String element=findElement(locatorKey).getAttribute("value");
		System.out.println(element);
		if(element.equalsIgnoreCase(textToVerify)) {
			test.log(Status.PASS, "Expected: " +textToVerify + "Actual match"+element);
			return true;
		}else {
			test.log(Status.FAIL, "Expected: " +textToVerify + "Actual Not match"+element);
			return false;
		}
	}
	public boolean  verifyText(String locatorKey,String textToVerify) {
		String element=findElement(locatorKey).getText();
		if(element.equalsIgnoreCase(textToVerify)) {
			test.log(Status.PASS, "Expected: " +textToVerify + "Actual match"+element);
			return true;
		}else {
			test.log(Status.FAIL, "Expected: " +textToVerify + "and Actual Not match"+element);
			return false;
		}
	}
	public boolean  verifyText1(String locatorKey,String textToVerify) {
		String element=findElement(locatorKey).getText();
		String element1=element.replaceAll("\\s*$", "").replaceAll("^\\s*", "");
		System.out.println(element1);
		System.out.println(textToVerify);
		if(element1.equalsIgnoreCase(textToVerify)) {
			test.log(Status.PASS, "Expected Title is "+element1+"Actual Title is  "+textToVerify );
			return true;
			
			
		}else {
			test.log(Status.FAIL, "Expected: " +textToVerify + "and Actual Not match"+element);
			return false;
		}
	}
	public boolean isElementPresent(String locatorKey) {
		By locator=getLocator(locatorKey);
		//	WebElement e =null;
		try {
			WebDriverWait wait=new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

			//e=driver.findElement(locator);
		}catch(Exception ec) {
			//report
			//loginFailure("Object not found.."+locatorKey,true);
			return false;
		}
		return true;
	}


	//******************************Utility Function***************************
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
	public void init(ExtentTest test) {
		this.test=test;
	}

	public void quit() {
		if(driver !=null)
			driver.quit();
		//	if(softAssertion!=null)
			//	softAssertion.assertAll();

	}

}
