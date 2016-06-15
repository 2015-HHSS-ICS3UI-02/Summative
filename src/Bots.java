/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author maxia2242
 */
public class Bots {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        Scanner scanner = new Scanner(new File("PasswordUser.csv"));
        String A = "https://www.facebook.com/";
        int x = 0;
        while (x < 10) {
            driver.get(A);
            WebElement EmailorPhone = driver.findElement(By.xpath("//*[@id=\"email\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"pass\"]"));
            EmailorPhone.sendKeys("");
            password.sendKeys("");
            x++;
        }
    }
}
