package com.juaracoding.namapeserta;

import com.juaracoding.namapeserta.drivers.DriverSingleton;
import com.juaracoding.pages.CheckoutPage;
import com.juaracoding.pages.InventoryPage;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class InventoryTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        DriverSingleton.getInstance();
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    // --- 1. FITUR LOGIN ---
    @Test(priority = 1)
    public void testLoginNegative() {
        loginPage.loginProcess("wrong_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface: Username and password do not match"));
    }

    @Test(priority = 2)
    public void testLoginPositive() {
        loginPage.loginProcess("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    // --- 2. FITUR ADD TO CART (Sesuai gambar Test Case 3) ---
    @Test(priority = 3)
    public void testAddToCartPositive() {
        inventoryPage.clickAddToCart();

        // Verifikasi Expected Result: angka 1 di keranjang & tombol berubah jadi
        // "Remove"
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "1");
        Assert.assertEquals(inventoryPage.getButtonText(), "Remove");
    }

    // --- 3. FITUR CHECKOUT ---
    @Test(priority = 4)
    public void testCheckoutNegative() {
        inventoryPage.goToCart();
        checkoutPage.clickCheckout();

        // Mengosongkan form data untuk memicu error
        checkoutPage.inputCheckoutInfo("", "", "");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Error: First Name is required"));
    }

    @Test(priority = 5)
    public void testCheckoutPositive() {
        // Melengkapi data valid
        checkoutPage.inputCheckoutInfo("Juara", "Coding", "12345");
        checkoutPage.clickFinish();

        Assert.assertEquals(checkoutPage.getCompleteHeaderText(), "Thank you for your order!");
    }

    @AfterClass
    public void tearDown() {
        DriverSingleton.quitDriver();
    }
}
