package ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class welcomePageShoppersStack {
	
	public welcomePageShoppersStack(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(id = "loginBtn")
	private WebElement LoginLink;
	
	public WebElement getLoginLink() {
		
		return LoginLink;
	}
}
