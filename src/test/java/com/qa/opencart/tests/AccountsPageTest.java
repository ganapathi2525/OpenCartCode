package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void doLogin() {
		accountsPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
	}
	
	@Test
	public void accountsPageTitleTest() {
		String actualTitle=accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitle, "My Account");
	}
	
	@Test
	public void accountsPageURLTest() {
		String actualURL=accountsPage.getAccountsPageURL();
		Assert.assertTrue(actualURL.contains("route=account/account"));
	}
	
	@Test
	public void searchBtnExist() {
		boolean flag=accountsPage.isSearchExist();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void downloadLinkExist() {
		boolean flag=accountsPage.isDownloadsExist();
		Assert.assertTrue(flag);
	}
	
	@Test
	public void headersCountTest() {
		int count=accountsPage.acctHeadersCount();
		Assert.assertEquals(count, 4);
	}
	
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][]{
			{"Macbook"},
			{"samsung"},
			{"iMac"},
			{"Apple"}

		};
	}
	
	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		searchPage=accountsPage.performSearch(searchKey);
		Assert.assertTrue(searchPage.getSearchProductsCount()>0);
		
	}
	
	@DataProvider
	public Object[][] getProductTestData() {
		return new Object[][]{
			{"Macbook","MacBook Pro"},
			{"Macbook","MacBook Air"},
			{"samsung","Samsung SyncMaster 941BW"},

		};
	}
	
	@Test(dataProvider = "getProductTestData")
	public void searchProductTest(String searchKey,String productName) {
		searchPage=accountsPage.performSearch(searchKey);
		if(searchPage.getSearchProductsCount()>0) {
			productInfoPage=searchPage.selectProduct(productName);
			String actualProductHeader=productInfoPage.getProductHeaderValue();
			Assert.assertEquals(actualProductHeader, productName);
		}

		
	}
	
	
	

	
}
