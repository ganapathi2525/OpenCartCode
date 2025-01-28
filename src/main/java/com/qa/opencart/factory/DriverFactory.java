package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exception.FrameworkException;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		optionsManager = new OptionsManager(prop);

		String browserName = prop.getProperty("browser").toLowerCase().trim();

		System.out.println("browser name is : " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			// driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		} else if (browserName.trim().equalsIgnoreCase("firefox")) {
			// driver=new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		} else if (browserName.trim().equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

		} else if (browserName.trim().equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			System.out.println("please pass the right browser name :" + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		// driver.manage().window().fullscreen();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {
		// mvn clean install -Denv="qa"
		// mvn clean install
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		System.out.println("Running test cases on Env: " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is passed....Running tests on QA env...");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					System.out.println("....Wrong env is passed....No need to run the test cases....");
					throw new FrameworkException("WRONG ENV IS PASSED...");
				// break;
				}

			}
		} catch (FileNotFoundException e) {

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;

	}

	
//	public Properties initProp() {
//		// mvn clean install -Denv="qa"
//		// mvn clean install
//		prop = new Properties();
//		FileInputStream ip = null;
//		String envName = System.getProperty("env");
//		System.out.println("Running test cases on Env: " + envName);
//
//		try {
//			if (envName.equals("qa")) {
//				System.out.println("no env is passed....Running tests on QA env...");
//				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
//			}
//			else if(envName.equals("stage")){
//				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
//
//			}
//			else if(envName.equals("dev")){
//				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
//
//			}
//			else if(envName.equals("prod")){
//				ip = new FileInputStream("./src/test/resources/config/config.properties");
//
//			}
//			else {
//				System.out.println("....Wrong env is passed....No need to run the test cases....");
//				throw new FrameworkException("WRONG ENV IS PASSED...");
//				
//			}
//		} catch (FileNotFoundException e) {
//
//		}
//
//		try {
//			prop.load(ip);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		return prop;
//
//	}
//
	public static String getScreenshot() {
		File scrFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtil.copyFile(scrFile, destination);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;

	}

}
