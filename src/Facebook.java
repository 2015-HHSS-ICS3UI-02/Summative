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
public class Facebook {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        String A = "https://www.facebook.com/r.php?next&locale=en_US&display=page";
        Scanner scanner = new Scanner(new File("PasswordUser.csv"));
        PrintWriter printer = new PrintWriter("FacebookUser.csv");
        int x = 0;
        while (x < 10) {
            String Fname = scanner.nextLine();
            String Lname = scanner.nextLine();
            String Email = scanner.nextLine();

            driver.get(A);
            WebElement FFname = driver.findElement(By.xpath("//*[@id=\"u_0_1\"]"));
            WebElement FLname = driver.findElement(By.xpath("//*[@id=\"u_0_3\"]"));
            WebElement FEmail = driver.findElement(By.xpath("//*[@id=\"u_0_6\"]"));
            WebElement FEmail2 = driver.findElement(By.xpath("//*[@id=\"u_0_9\"]"));
            WebElement password = driver.findElement(By.xpath("//*[@id=\"u_0_b\"]"));
            FFname.sendKeys(Fname);
            FLname.sendKeys(Lname);
            FEmail.sendKeys(Email + "@gmail.com");
            FEmail2.sendKeys(Email + "@gmail.com");
            password.sendKeys("340602242");
            x++;
            printer.println(Email + "@gmail.com");



            driver.close();

        }
    }
}
