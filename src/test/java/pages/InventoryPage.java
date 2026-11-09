package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

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


    //INVENTÁRIO

    public void abrirProduto(String nome) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and text()='" + nome + "']")).click();
        wait.until(ExpectedConditions.urlContains("inventory-item"));
    }

    //FILTRO

    public void ordenarPor(String opcao) {
        Select filtro = new Select(driver.findElement(By.cssSelector("[data-test='product-sort-container']")));
        filtro.selectByVisibleText(opcao);
    }

    public List<String> lerNomesDosProdutos() {
        List<String> nomes = new ArrayList<>();
        for (WebElement e : driver.findElements(By.cssSelector("[data-test='inventory-item-name']"))) {
            nomes.add(e.getText());
        }
        return nomes;
    }

    public List<Double> lerPrecosDosProdutos() {
        List<Double> precos = new ArrayList<>();
        for (WebElement e : driver.findElements(By.cssSelector("[data-test='inventory-item-price']"))) {
            precos.add(Double.parseDouble(e.getText().replace("$", "")));
        }
        return precos;
    }



}