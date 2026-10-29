package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {

    WebDriver driver;
    LoginPage loginpage;

    // PREPARAÇÃO

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        LoginPage loginPage = new LoginPage(driver);
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

        loginpage.inserirUsuario("standard_user");
        loginpage.inserirSenha("secret_sauce");
        loginpage.clicarBotaoLogin("login-button");

        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());

        System.out.println("Título: " + driver.getTitle());

    }


    // LOGIN COM FALHA (aparece mensagem de erro e continua na tela de login)

    @Test
    void usuarioIncorretoSenhaIncorreta(){

        loginpage.inserirUsuario("bolinho");
        loginpage.inserirSenha("coxinha");
        loginpage.clicarBotaoLogin("login-button");

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

    @Test
    void usuarioCorretoSenhaIncorreta(){

        loginpage.inserirUsuario("standard_user");
        loginpage.inserirSenha("coxinha");
        loginpage.clicarBotaoLogin("login-button");

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

    @Test
    void usuarioIncorretoSenhaCorreta(){
        loginpage.inserirUsuario("coxinha");
        loginpage.inserirSenha("secret_sauce");
        loginpage.clicarBotaoLogin("login-button");

        String mensagem = driver.findElement(By.cssSelector("[data-test='error']")).getText();
        System.out.println("Título: " + driver.getTitle());
        System.out.println("Mensagem : " + mensagem);

        assertEquals("https://www.saucedemo.com/", driver.getCurrentUrl());
        assertEquals("Epic sadface: Username and password do not match any user in this service", mensagem);
    }

}