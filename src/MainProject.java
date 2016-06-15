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
public class MainProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, InterruptedException {
        WebDriver driver = new ChromeDriver();
        PrintWriter printer = new PrintWriter("PasswordUser.csv");
        Scanner Andrew = new Scanner(System.in);
        String[] random;
        String[] WNames;
        String[] WLastN;
        String[] EmailAd;

        random = new String[210];
        random[0] = "A";
        random[1] = "B";
        random[2] = "C";
        random[3] = "D";
        random[4] = "E";
        random[5] = "F";
        random[6] = "G";
        random[7] = "H";
        random[8] = "I";
        random[9] = "J";
        random[10] = "K";
        random[11] = "L";
        random[12] = "M";
        random[13] = "N";
        random[14] = "O";
        random[15] = "P";
        random[16] = "Q";
        random[17] = "R";
        random[18] = "S";
        random[19] = "T";
        random[20] = "U";
        random[21] = "V";
        random[22] = "W";
        random[23] = "X";
        random[24] = "Y";
        random[25] = "Z";
        random[26] = "a";
        random[27] = "b";
        random[28] = "c";
        random[29] = "d";
        random[30] = "e";
        random[31] = "f";
        random[32] = "g";
        random[33] = "h";
        random[34] = "i";
        random[35] = "j";
        random[36] = "k";
        random[37] = "l";
        random[38] = "m";
        random[39] = "n";
        random[40] = "o";
        random[41] = "p";
        random[42] = "q";
        random[43] = "r";
        random[44] = "s";
        random[45] = "t";
        random[46] = "u";
        random[47] = "v";
        random[48] = "w";
        random[49] = "x";
        random[50] = "y";
        random[51] = "z";
        random[52] = "0";
        random[53] = "0";
        random[54] = "1";
        random[55] = "2";
        random[56] = "3";
        random[57] = "4";
        random[58] = "5";
        random[59] = "6";
        random[60] = "7";
        random[61] = "8";
        random[62] = "9";

        String[] CPassword;
        int x = 1;
        int z = 0;
        int y = 0;
        Random A1 = new Random();
        WNames = new String[51];
        WNames[0] = "Aaliyah";
        WNames[1] = "Abbey";
        WNames[2] = "Abby";
        WNames[3] = "Adele";
        WNames[4] = "Bailey";
        WNames[5] = "Bay";
        WNames[6] = "Beatrice";
        WNames[7] = "Beccy";
        WNames[9] = "Cadence";
        WNames[11] = "Cailyn";
        WNames[12] = "Caitlyn";
        WNames[13] = "Calista";
        WNames[14] = "Dacia";
        WNames[15] = "Daina";
        WNames[16] = "Dale";
        WNames[17] = "Danette";
        WNames[18] = "Elisabeth";
        WNames[19] = "Elle";
        WNames[20] = "Elsa";
        WNames[21] = "Electra";
        WNames[22] = "Francoise";
        WNames[23] = "Flo";
        WNames[24] = "Fernanda";
        WNames[25] = "Fay";
        WNames[26] = "Fallon";
        WNames[27] = "Gabby";
        WNames[28] = "Gabrielle";
        WNames[29] = "Georgia";
        WNames[30] = "Gia";
        WNames[31] = "Grace";
        WNames[32] = "Hailee";
        WNames[27] = "Haley";
        WNames[28] = "Holly";
        WNames[29] = "Hermione";
        WNames[30] = "Hope";
        WNames[31] = "Indigo";
        WNames[32] = "Iris";
        WNames[33] = "Isabelle";
        WNames[34] = "Izzy";
        WNames[35] = "Ivy";
        WNames[36] = "Jade";
        WNames[37] = "Jayne";
        WNames[38] = "Jan";
        WNames[39] = "Jamiya";
        WNames[40] = "Jenna";
        WNames[41] = "Kathleen";
        WNames[42] = "Katie";
        WNames[43] = "Kayden";
        WNames[44] = "Kay";
        WNames[45] = "Karla";
        WNames[46] = "Lela";
        WNames[47] = "Lianne";
        WNames[48] = "Lacy";
        WNames[49] = "Liara";
        WNames[50] = "Lena";

        WLastN = new String[51];
        WLastN[0] = "Smith";
        WLastN[1] = "Johnson";
        WLastN[2] = "Williams";
        WLastN[3] = "Brown";
        WLastN[4] = "Jones";
        WLastN[5] = "Miller";
        WLastN[6] = "Davis";
        WLastN[7] = "Garcia";
        WLastN[8] = "Rodriguez";
        WLastN[9] = "Wilson";
        WLastN[10] = "Martinez";
        WLastN[11] = "Anderson";
        WLastN[12] = "Taylor";
        WLastN[13] = "Thomas";
        WLastN[14] = "Hernandez";
        WLastN[15] = "Moore";
        WLastN[16] = "Martin";
        WLastN[17] = "Jackson";
        WLastN[18] = "Thompson";
        WLastN[19] = "White";
        WLastN[20] = "Lopez";
        WLastN[21] = "Lee";
        WLastN[22] = "Gonzalez";
        WLastN[23] = "Harris";
        WLastN[24] = "Clark";
        WLastN[25] = "Lewis";
        WLastN[26] = "Robinson";
        WLastN[27] = "Walker";
        WLastN[28] = "Perez";
        WLastN[29] = "Hall";
        WLastN[30] = "Young";
        WLastN[31] = "Allen";
        WLastN[32] = "Sanchez";
        WLastN[33] = "Wright";
        WLastN[34] = "King";
        WLastN[35] = "Scott";
        WLastN[36] = "Green";
        WLastN[37] = "Baker";
        WLastN[38] = "Adams";
        WLastN[39] = "Nelson";
        WLastN[40] = "Hill";
        WLastN[41] = "Ramirez";
        WLastN[42] = "Campbell";
        WLastN[43] = "Mitchell";
        WLastN[44] = "Roberts";
        WLastN[45] = "Carter";
        WLastN[46] = "Phillips";
        WLastN[47] = "Evans";
        WLastN[48] = "Turner";
        WLastN[49] = "Torres";
        WLastN[50] = "Parker";

        int password = 340602242;
        int Cpassword = 340602242;
        

        EmailAd = new String[100];
        while (y < 100) {
            int a1 = A1.nextInt(62);
            int a2 = A1.nextInt(62);
            int a3 = A1.nextInt(62);
            int a4 = A1.nextInt(62);
            int a5 = A1.nextInt(62);
            int a6 = A1.nextInt(62);
            int a7 = A1.nextInt(62);
            int a8 = A1.nextInt(62);
            String Email = random[a1] + random[a2] + random[a3] + random[a4] + random[a5] + random[a6] + random[a7] + random[a8];
            EmailAd[y] = Email;
            y++;
        }

        String A = "https://accounts.google.com/SignUp?service=mail&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ltmpl=default";

        while (x < 10) {

            int WNN = A1.nextInt(50);
            int WLN = A1.nextInt(50);
            int BMS = A1.nextInt(12) + 1;
            int day = A1.nextInt(31) + 1;
            int year = A1.nextInt(1999 - 1986) + 1986;
            int genderC = A1.nextInt(3) + 1;
            driver.get(A);
            WebElement name = driver.findElement(By.id("FirstName"));
            name.sendKeys("" + WNames[WNN]);
            WebElement Lname = driver.findElement(By.id("LastName"));
            Lname.sendKeys("" + WLastN[WLN]);
            WebElement GmailAddress = driver.findElement(By.id("GmailAddress"));
            GmailAddress.sendKeys("" + EmailAd[z]);
            WebElement passwordformelement = driver.findElement(By.id("Passwd"));
            WebElement confirmpasswordformelement = driver.findElement(By.id("PasswdAgain"));
            passwordformelement.sendKeys("" + password);
            confirmpasswordformelement.sendKeys("" + Cpassword);
            WebElement Birthday = driver.findElement(By.xpath("//*[@id=\"BirthMonth\"]/div[1]"));
            Birthday.click();

            if (BMS == 1) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":1\"]/div"));
                BM.click();
            } else if (BMS == 2) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":2\"]/div"));
                BM.click();
            } else if (BMS == 3) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":3\"]/div"));
                BM.click();
            } else if (BMS == 4) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":4\"]/div"));
                BM.click();
            } else if (BMS == 5) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":5\"]/div"));
                BM.click();
            } else if (BMS == 6) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":6\"]/div"));
                BM.click();
            } else if (BMS == 7) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":7\"]/div"));
                BM.click();
            } else if (BMS == 8) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":8\"]/div"));
                BM.click();
            } else if (BMS == 9) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":9\"]/div"));
                BM.click();
            } else if (BMS == 10) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":a\"]/div"));
                BM.click();
            } else if (BMS == 11) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":b\"]/div"));
                BM.click();
            } else if (BMS == 12) {
                WebElement BM = driver.findElement(By.xpath("//*[@id=\":c\"]/div"));
                BM.click();
            }

            WebElement BD = driver.findElement(By.xpath("//*[@id=\"BirthDay\"]"));
            BD.sendKeys("" + day);
            WebElement BM = driver.findElement(By.xpath("//*[@id=\"BirthYear\"]"));
            BM.sendKeys("" + year);
            WebElement GenderButton = driver.findElement(By.xpath("//*[@id=\"Gender\"]/div[1]"));
            GenderButton.click();
            if (genderC == 1) {
                WebElement GenderButton1 = driver.findElement(By.xpath("//*[@id=\":e\"]/div"));
                GenderButton1.click();
            } else if (genderC == 2) {
                WebElement GenderButton2 = driver.findElement(By.xpath("//*[@id=\":f\"]/div"));
                GenderButton2.click();
            } else if (genderC == 3) {
                WebElement GenderButton3 = driver.findElement(By.xpath("//*[@id=\":g\"]/div"));
                GenderButton3.click();
            }
            WebElement VC = driver.findElement(By.xpath("//*[@id=\"TermsOfService\"]"));
            VC.click();
            WebElement VFFP = driver.findElement(By.xpath("//*[@id=\"recaptcha_response_field\"]"));
            VFFP.click();
            System.out.println("Code?");
            String Code = Andrew.nextLine();
            VFFP.sendKeys("" + Code);
            printer.println(WNames[WNN]);
            printer.println(WLastN[WLN]);
            printer.println(EmailAd[z]);
            printer.println(BMS);
            printer.println(day);
            printer.println(year);
            printer.flush();
            WebElement NextStep = driver.findElement(By.xpath("//*[@id=\"submitbutton\"]"));
            NextStep.click();
            Thread.sleep(1000);
            
            

            x++;
            z++;

        }
        driver.close();

    }
}
