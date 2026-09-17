package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ssLoginPage {
	
	public ssLoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "Email")
	private WebElement emailTF;
	
	@FindBy(id = "Password")
	private WebElement passwordTF;
	
	@FindBy(id = "Login")
	private WebElement loginButton;
	
	public WebElement getEmailtf() {
		return emailTF;
	}
	
	public WebElement getPasswordtf() {
		return passwordTF;
	}
	
	public WebElement getLoginButton() {
		return loginButton;
	}
}
