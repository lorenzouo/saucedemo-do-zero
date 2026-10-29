package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryTest {
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

    }

    @AfterEach
    void teardown(){
        driver.quit();
    }


    // CARRINHO

    @Test
    void botaoViraRemoveDepoisDeAdicionar() {
        inventoryPage.adicionarBackpackAoCarrrinho("add-to-cart-sauce-labs-backpack");
        assertEquals("Remove", inventoryPage.lerTextoBotaoRemove());
    }

    @Test
    void validarProdutoAdicionadoAoCarrinho(){
        inventoryPage.adicionarBackpackAoCarrrinho("add-to-cart-sauce-labs-backpack");
        inventoryPage.acessarCarrinhoDeCompras("shopping_cart_link");
        cartPage.lerNomeDoItem();
        assertEquals("Sauce Labs Backpack", cartPage.lerNomeDoItem());
    }


    // MENU LATERAL

    @Test
    void validarBotaoLogoutPeloMenu(){
        inventoryPage.clicarMenu();
        inventoryPage.clicarLogout("logout_sidebar_link");
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
    }

    @Test
    void validarBotaoAboutPeloMenu(){
        inventoryPage.clicarMenu();
        inventoryPage.clicarAbout("about_sidebar_link");
        assertEquals("https://saucelabs.com/", driver.getCurrentUrl());
    }

    @Test
    void validarBotaoXFechaMenu() {
        inventoryPage.clicarMenu();
        inventoryPage.clicarFecharMenu();
        assertTrue(inventoryPage.menuEstaFechado());
    }

    //RODAPÉ

    @Test
    void validarBotaoX() {
        inventoryPage.clicarRedeSocial("social-x", "x.com");
        assertTrue(driver.getCurrentUrl().contains("x.com"));
    }

    @Test
    void validarBotaoFacebook(){
        inventoryPage.clicarRedeSocial("social-facebook", "facebook.com");
        assertTrue(driver.getCurrentUrl().contains("facebook.com"));
    }

    @Test
    void validarBotaoLinkedin(){
        inventoryPage.clicarRedeSocial("social-linkedin", "linkedin.com");
        assertTrue(driver.getCurrentUrl().contains("linkedin.com"));
    }

}
