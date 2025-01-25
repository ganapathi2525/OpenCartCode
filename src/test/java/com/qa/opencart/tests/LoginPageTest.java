package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginPageTitleTest() {
		String actualTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, "Account Login");
	}
	
	@Test
	public void loginPageURLTest() {
		String actualURL=loginPage.getLoginPageURL();
		Assert.assertTrue(actualURL.contains("route=account/login"));
	}
	
	
	@Test
	public void forgotpwdLinkExistTest() {
		boolean flag=loginPage.isForgotPwdLinkExist();
		Assert.assertTrue(flag);
	}
	
	@Test(priority=1)
	public void doLoginTest() {
		accountsPage=loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
		Assert.assertTrue(accountsPage.isSearchExist());
		
	}

}
