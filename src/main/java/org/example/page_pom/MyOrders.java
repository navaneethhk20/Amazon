package org.example.page_pom;
import org.example.base.CommonToAllPages;
import org.example.utils.PropertiesReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyOrders extends CommonToAllPages {
    
    WebDriver driver;

    public  MyOrders(WebDriver driver){
        this.driver =driver;
    }

    private By expandbutton = By.xpath("//button[@aria-label='Expand Account and Lists']");
    private By myAccount= By.xpath("//a[@aria-controls=\"nav-flyout-accountList\"] ");
    private By yourAccountSelector= By.xpath("//h1[normalize-space()='Your Account']");
    private By yourOrdersSelector = By.xpath("//h2[normalize-space()='Your Orders']");
    private By yourAddressesSelector = By.xpath("//h2[normalize-space()='Your Addresses']");
    private By addAddressButton= By.xpath("//div[text()='Add address']");
    private By plussignButton= By.xpath("//div[@id=\"ya-myab-plus-address-icon\"]");
    private By enterName= By.xpath("//input[@id=\"address-ui-widgets-enterAddressFullName\"]");
    private By enterPhonenumber= By.xpath("//input[@id=\"address-ui-widgets-enterAddressPhoneNumber\"]");
    private By postalcode=By.xpath("//input[@id=\"address-ui-widgets-enterAddressPostalCode\"]");
    private By enterAdressLine1 =By.xpath("//input[@id=\"address-ui-widgets-enterAddressLine1\"]");
    private By enteradressline2= By.xpath("//input[@id=\"address-ui-widgets-enterAddressLine2\"]");
    private By landmark= By.xpath("//input[@id=\"address-ui-widgets-landmark\"]");
    private By addresscity= By.xpath("//input[@id=\"address-ui-widgets-enterAddressCity\"]");
    private By statedropdown= By.xpath("//span[@id=\"address-ui-widgets-enterAddressStateOrRegion\"]");
    private By dropdownofmystate =By.xpath("//span[text()=\"KARNATAKA\"]");
    private By submit= By.xpath("//span[text()=\"Add address\"]");

    public void addANewAddress(){
        visiblityofElement(expandbutton).click();
        visiblityofElement(myAccount).click();
        presenceOfElement(yourAddressesSelector).click();
        visiblityofElement(addAddressButton).click();
        visiblityofElement(enterName).sendKeys(PropertiesReader.readKey("name"));
        enterInput(enterPhonenumber,PropertiesReader.readKey("number"));
        enterInput(postalcode,PropertiesReader.readKey("postnum"));
        enterInput(enterAdressLine1,PropertiesReader.readKey("address1"));
        enterInput(enteradressline2,PropertiesReader.readKey("address2"));
        enterInput(landmark,PropertiesReader.readKey("landmark"));
        enterInput(addresscity,PropertiesReader.readKey("cityname"));
        clickElement(statedropdown);
        visiblityofElement(dropdownofmystate).click();
        clickElement(submit);
    }


}
