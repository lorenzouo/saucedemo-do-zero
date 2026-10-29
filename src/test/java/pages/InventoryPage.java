package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InventoryPage {

    WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }


    // CARRINHO

    public void adicionarBackpackAoCarrrinho(String backpack) {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    public String lerTextoBotaoRemove() {
        return driver.findElement(By.id("remove-sauce-labs-backpack")).getText();
    }

    public void acessarCarrinhoDeCompras(String carrinho) {
        driver.findElement(By.className("shopping_cart_link")).click();
    }


    // MENU LATERAL

    public void clicarMenu() {
        driver.findElement(By.id("react-burger-menu-btn")).click();
    }

    public void clicarLogout(String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }

    public void clicarAbout(String id) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id(id))).click();
    }

    public void clicarFecharMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("react-burger-cross-btn"))).click();
    }

    public boolean menuEstaFechado() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("react-burger-cross-btn")));
    }


    //RODAPÉ

    public void clicarRedeSocial(String dataTest, String parteDaUrl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String abaOriginal = driver.getWindowHandle();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test='" + dataTest + "']"))).click();

        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String aba : driver.getWindowHandles()) {
            if (!aba.equals(abaOriginal)) {
                driver.switchTo().window(aba);
            }
        }

        wait.until(ExpectedConditions.urlContains(parteDaUrl));
    }
}