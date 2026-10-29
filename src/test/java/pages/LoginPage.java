package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    // CAMPOS DE LOGIN

    public void inserirUsuario(String usuario) {
        driver.findElement(By.id("user-name")).sendKeys(usuario);
    }

    public void inserirSenha(String senha) {
        driver.findElement(By.id("password")).sendKeys(senha);
    }


    // BOTÃO DE LOGIN

    public void clicarBotaoLogin(String botao){
        driver.findElement(By.id("login-button")).click();
    }
}