package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void doLogin() {
		accountsPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	

	@DataProvider
	public Object[][] getProductImagesTestData() {
		return new Object[][]{
			{"Macbook","MacBook Pro",4},
			{"Macbook","MacBook Air",4},
			{"samsung","Samsung SyncMaster 941BW",1},

		};
	}
	
	@Test(dataProvider = "getProductImagesTestData")
	public void productImagesCountTest(String searchKey,String productName,int imagesCount) {
		searchPage=accountsPage.performSearch(searchKey);
		productInfoPage=searchPage.selectProduct(productName);
		int actualCount=productInfoPage.getProductImagesCount();
		Assert.assertEquals(actualCount, imagesCount);
	}
	
	@Test
	public void productInfoTest() {
		searchPage=accountsPage.performSearch("Macbook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		Map<String,String>productInfo=productInfoPage.getProductInfo();
		softAssert.assertEquals(productInfo.get("Brand"), "Apple");
		softAssert.assertEquals(productInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfo.get("Reward Points"), "800");
		softAssert.assertEquals(productInfo.get("productprice"), "$2,000.00");
		
		softAssert.assertAll();
	}
	
	@Test
	public void addToCartTest() {
		searchPage=accountsPage.performSearch("Macbook");
		productInfoPage=searchPage.selectProduct("MacBook Pro");
		productInfoPage.enterQuantity(2);
		String actSuccessMesg=productInfoPage.addProductToCart();
		//Success: You have added MacBook Pro to your shopping cart!
		softAssert.assertTrue(actSuccessMesg.contains("Success"));
		softAssert.assertTrue(actSuccessMesg.contains("MacBook Pro"));
		softAssert.assertEquals(actSuccessMesg, "Success: You have added MacBook Pro to your shopping cart!");

		softAssert.assertAll();

		
	}
	
	
	
	

}
