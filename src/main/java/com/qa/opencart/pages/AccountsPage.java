 package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;

	
	private By search=By.name("search");
	private By acctsHeaders=By.xpath("//div[@id='content']//h2");
	private By download=By.linkText("Downloads");
	private By searchClick=By.xpath("//div[@id='search']//button[@type='button']");
	
	public AccountsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public String getAccountsPageTitle() {
		String title=driver.getTitle();
		System.out.println("Accounts page Title :"+title);
		return title;
	}
	
	public String getAccountsPageURL() {
		String url=driver.getCurrentUrl();
		System.out.println("Accounts page URL :"+url);
		return url;
	}
	
	public boolean isSearchExist() {
		return driver.findElement(search).isDisplayed();
	}
	
	public int acctHeadersCount() {
		int count=driver.findElements(acctsHeaders).size();
		return count;
	}
	
	public boolean isDownloadsExist() {
		return driver.findElement(download).isDisplayed();
	}
	
	public SearchPage performSearch(String productKey) {
		driver.findElement(search).clear();
		driver.findElement(search).sendKeys(productKey);
		driver.findElement(searchClick).click();
		return new SearchPage(driver);
	}
	


}
