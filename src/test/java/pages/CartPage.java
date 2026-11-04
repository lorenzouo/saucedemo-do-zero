package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String lerNomeDoItem() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("[data-test='inventory-item-name']"))).getText();
    }

    public void voltarParaTelaInventario(){
        driver.findElement(By.id("continue-shopping")).click();
    }

    public void clicarCheckout(){
        driver.findElement(By.id("checkout")).click();
    }
}