package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
	
	public SignupPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	
	@FindBy(id = "First Name")
	private WebElement firstNametf;
	
	@FindBy(id = "Last Name")
	private WebElement lastNametf;
	
	@FindBy(id = "Phone Number")
	private WebElement phoneNumbertf;
	
	@FindBy(id = "Email Address")
	private WebElement emailAddresstf;
	
	@FindBy(id = "Password")
	private WebElement passwordtf;
	
	@FindBy(id = "Confirm Password")
	private WebElement confirmpasswordtf;
	
	public WebElement firstName() {
		return firstNametf;
	}
	
	public WebElement lastName() {
		return lastNametf;
	}
	
	public WebElement phoneNumber() {
		return phoneNumbertf;
	}
	
	public WebElement emailAddress() {
		return emailAddresstf;
	}
	
	public WebElement passwordTF() {
		return passwordtf;
	}
	
	public WebElement confirmPasswordTf() {
		return confirmpasswordtf;
	}
	
}
