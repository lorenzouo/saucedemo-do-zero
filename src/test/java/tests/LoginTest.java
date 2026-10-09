package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
    }

    @AfterEach
    void teardown(){
        driver.quit();
    }


    @Test
    void deveAbrirOSite() {
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        System.out.println("Título: " + driver.getTitle());
    }

    @Test
    void usuarioIncorretoSenhaIncorreta(){
        driver.findElement(By.id("user-name")).sendKeys("bolinho");
        driver.findElement(By.id("password")).sendKeys("coxinha");
        driver.findElement(By.id("login-button")).click();

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

    @Test
    void usuarioCorretoSenhaIncorreta(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("coxinha");
        driver.findElement(By.id("login-button")).click();

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

    @Test
    void usuarioIncorretoSenhaCorreta(){
        driver.findElement(By.id("user-name")).sendKeys("coxinha");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

    @Test
    void usuarioCorretoSenhaCorreta(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        System.out.println("Título: " + driver.getTitle());

    }

}