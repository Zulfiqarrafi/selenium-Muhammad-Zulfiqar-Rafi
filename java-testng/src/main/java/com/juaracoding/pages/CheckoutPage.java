package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "first-name")
    private WebElement firstNameField;

    @FindBy(id = "last-name")
    private WebElement lastNameField;

    @FindBy(id = "postal-code")
    private WebElement postalCodeField;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    public void inputCheckoutInfo(String firstName, String lastName, String postalCode) {
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
        continueButton.click();
    }

    public void clickFinish() {
        finishButton.click();
    }

    public String getCompleteHeaderText() {
        return completeHeader.getText();
    }

    public String getErrorMessage() {
        return errorMessage.getText();
    }
}
