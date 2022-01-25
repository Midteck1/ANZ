package anz.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	
	//enum group named constant,to define our own data type
	//enumarated data type
	public enum DriverType {
	    CHROME,
	    FIREFOX,
	    SAFARI,
	    IE;
	}	

    //chrome driver supplier
    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
        System.setProperty("webdriver.chrome.driver", "Driver/chromedriver 5");
        return new ChromeDriver();
    };

    //firefox driver supplier
    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        System.setProperty("webdriver.gecko.driver", "/Users/username/Downloads/geckodriver");
        return new FirefoxDriver();
    };
    private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();
    //add more suppliers here

    //add all the drivers into a map
    static{
        driverMap.put(DriverType.CHROME, chromeDriverSupplier);
        driverMap.put(DriverType.FIREFOX, firefoxDriverSupplier);
    }

    //return a new driver from the map
    public static final WebDriver getDriver(DriverType type){
        return driverMap.get(type).get();
    }
    
    
    
}


