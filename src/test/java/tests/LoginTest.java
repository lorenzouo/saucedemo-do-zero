package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    // PREPARAÇÃO

    @BeforeEach
    void setup() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new", "--window-size=1920,1080");
        }
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @AfterEach
    void teardown(){
        driver.quit();
    }


    // ABRIR O SITE

    @Test
    void deveAbrirOSite() {
        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        System.out.println("Título: " + driver.getTitle());
    }


    // LOGIN CONCLUÍDO (usuário e senha corretos)

    @Test
    void usuarioCorretoSenhaCorreta(){

        loginPage.inserirUsuario("standard_user");
        loginPage.inserirSenha("secret_sauce");
        loginPage.clicarBotaoLogin("login-button");

        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        System.out.println("Título: " + driver.getTitle());

    }


    // LOGIN COM FALHA (aparece mensagem de erro e continua na tela de login)

    @Test
    void usuarioIncorretoSenhaIncorreta(){

        loginPage.inserirUsuario("bolinho");
        loginPage.inserirSenha("coxinha");
        loginPage.clicarBotaoLogin("login-button");

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

    @Test
    void usuarioCorretoSenhaIncorreta(){

        loginPage.inserirUsuario("standard_user");
        loginPage.inserirSenha("coxinha");
        loginPage.clicarBotaoLogin("login-button");

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

    @Test
    void usuarioIncorretoSenhaCorreta(){
        loginPage.inserirUsuario("coxinha");
        loginPage.inserirSenha("secret_sauce");
        loginPage.clicarBotaoLogin("login-button");

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

}