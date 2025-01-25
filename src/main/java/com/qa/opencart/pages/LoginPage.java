package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By emailID=By.id("input-email");
	private By password=By.id("input-password");
	private By loginBtn=By.xpath("//input[@value='Login']");
	private By forgotPwdLink=By.linkText("Forgotten Password");
	private By register=By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}

	
	public String getLoginPageTitle() {
		String title=driver.getTitle();
		System.out.println("Login page Title :"+title);
		return title;
	}
	
	public String getLoginPageURL() {
		String url=driver.getCurrentUrl();
		System.out.println("Login page URL :"+url);
		return url;
	}
	
	public boolean isForgotPwdLinkExist() {
		return driver.findElement(forgotPwdLink).isDisplayed();
	}
	
	public AccountsPage doLogin(String un,String pwd) {
		driver.findElement(emailID).sendKeys(un);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
		return new AccountsPage(driver);
	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(register);
		return new RegisterPage(driver);
	}
	
	
	
	
	
	
}
