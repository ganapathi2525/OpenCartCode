package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By productHeader = By.tagName("h1");
	private By productImages = By.xpath("//ul[@class='thumbnails']//img");
	private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By qunatity=By.id("input-quantity");
	private By addToCartBtn=By.xpath("//button[text()='Add to Cart']");
	private By cartSuccessMessg=By.cssSelector("div.alert.alert-success");
	
	private Map<String, String> metaMap;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public String getProductHeaderValue() {
		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("product header: " + productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsVisible(productImages, 10).size();
		System.out.println("Product images count " + imagesCount);
		return imagesCount;
	}
	
	public void enterQuantity(int qty) {
		eleUtil.doSendKeys(qunatity, String.valueOf(qty));	
	}
	
	public String addProductToCart() {
		eleUtil.doClick(addToCartBtn);
		String successMesg=eleUtil.waitForElementVisible(cartSuccessMessg, 10).getText();
		successMesg=successMesg.substring(0,successMesg.length()-1).replace("\n","");
		System.out.println(successMesg);
		return successMesg;
	}
	
	
	
	
	

	//header
	public Map<String, String> getProductInfo() {
		//metaMap = new HashMap<String, String>(); // not maintanining the order
		metaMap = new LinkedHashMap<String, String>(); //maintain the order
	//	metaMap = new TreeMap<String, String>();// sorted based on alphabatical order--order based

		metaMap.put("productHeader", getProductHeaderValue());	
		getProductMetaData();
		getProductPriceData();
		System.out.println(metaMap);
		return metaMap;
	}

	//meta data
	private void getProductMetaData() {
		List<WebElement> metalList = eleUtil.waitForElementsVisible(productMetaData, 10);

		for (WebElement e : metalList) {
			String meta = e.getText();
			String metaInfo[] = meta.split(":");
			String key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			metaMap.put(key, value);
		}

	}
	
	//price data
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTax = priceList.get(1).getText();
		String exTaxVal = exTax.split(":")[1].trim();

		metaMap.put("productprice", price);
		metaMap.put("exTax", exTaxVal);
		
	}
	
	
	

}
