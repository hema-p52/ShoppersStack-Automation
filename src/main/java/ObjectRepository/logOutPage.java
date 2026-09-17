package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class logOutPage {
	
	public logOutPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath = "//button[@aria-label='Account settings' and .//*[@data-testid='SettingsIcon']]")
	private WebElement settingBt;
	
	@FindBy(xpath = "//li[@role='menuitem' and normalize-space(.)='Logout']")
	private WebElement logoutBt;
	
	public WebElement accountSettings() {
		return settingBt;
	}
	
	public WebElement logoutbT() {
		return logoutBt;
	}

	
}
