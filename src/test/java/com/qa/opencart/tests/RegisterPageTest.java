package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest {
	
	@BeforeClass
	public void regPageSetUp() {
		registerPage=loginPage.navigateToRegisterPage();
	}
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData[][]=ExcelUtil.getTestData("register");
		return regData;
	}

	
	@Test(dataProvider = "getRegTestData")
	public void userRegTest(String firstName,String lastName,String email,
			String telephone,String password,String subscribe) {
		Assert.assertTrue(registerPage.registerUser(firstName, lastName, email,
				 telephone, password, subscribe));
	}
	

}
