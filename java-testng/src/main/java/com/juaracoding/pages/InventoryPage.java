package com.juaracoding.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {

    // Tombol Add to Cart produk pertama (Sauce Labs Backpack)
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCartButton;

    @FindBy(id = "remove-sauce-labs-backpack")
    private WebElement removeButton;

    @FindBy(className = "shopping_cart_badge")
    private WebElement cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickAddToCart() {
        addToCartButton.click();
    }

    public String getCartBadgeText() {
        return cartBadge.getText();
    }

    public String getButtonText() {
        return removeButton.getText();
    }

    public void goToCart() {
        cartLink.click();
    }
    
    public boolean isCartBadgeDisplayed() {
        try {
            return cartBadge.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
