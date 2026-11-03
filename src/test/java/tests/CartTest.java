package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest {

    WebDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    // PREPARAÇÃO

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);

        driver.get("https://www.saucedemo.com");
        loginPage.inserirUsuario("standard_user");
        loginPage.inserirSenha("secret_sauce");
        loginPage.clicarBotaoLogin("login-button");
        inventoryPage.acessarCarrinhoDeCompras("shopping_cart_link");

    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void voltarParaTelaInventario(){
        cartPage.voltarParaTelaInventario();
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

    }




















}