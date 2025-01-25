package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class SearchPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchProductResults=By.xpath("//div[@class='caption']//a");

	public SearchPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);

	}
	
	public int getSearchProductsCount() {
		int count=eleUtil.waitForElementsVisible(searchProductResults, 10).size();
		System.out.println("products count"+count);
		return count;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		By productLocator=By.linkText(productName);
		eleUtil.waitForElementVisible(productLocator, 10).click();
		return new ProductInfoPage(driver);
	}

}
