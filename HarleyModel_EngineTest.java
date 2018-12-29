package com.cybertek.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class HarleyModel_EngineTest {
    WebDriver driver;
    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();

    }
    @BeforeMethod
    public void setUpmethod (){
        //Step 1: open a Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
        @Test(priority = 0)
    public void testHarleyYear () {
        //Step 2: go to the URL "https://houston.craigslist.org/"
        driver.get("https://houston.craigslist.org/");
        //Step 3: insert 20 s implicitly wait command in case you have a slow connection
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Step 4: Create a Web element to locate and click on the link "motorcycles" and click it on
        WebElement motorcyclesLink = ((ChromeDriver) driver).findElementByLinkText("motorcycles");
        motorcyclesLink.click();
        //Step 5: Create a Web element to locate and fill the "miles" text box by "150"
        WebElement mile = ((ChromeDriver) driver).findElementByName("search_distance");
        mile.sendKeys("150");
        //Step 6: Create a Web element to locate and fill the "ZIP" text box by "77498"
        WebElement zipCode = ((ChromeDriver) driver).findElementByName("postal");
        zipCode.sendKeys("77498");
        //Step 7:Create a Web element to locate and fill the "year" text box by "2005"
        WebElement minYear = driver.findElement(By.name("min_auto_year"));
        minYear.sendKeys("2005");
        //Step 8 : Create a Web element to locate and fill the "engine displacement" text box by "1000"
        WebElement minEngineSize = driver.findElement(By.name("min_engine_displacement_cc"));
        minEngineSize.sendKeys("1000"+Keys.ENTER);
        //Step 9: Create a Web element to locate and click to navigate into the page of first motorcycle
        WebElement firstPage = ((ChromeDriver) driver).findElementByPartialLinkText("A");
        firstPage.click();
        // We are in detail page now
        // Step 10: Locate the text including the year of the motorcycle
        WebElement yearTextBox = driver.findElement(By.xpath("/html/body/section/section/section/div[1]/p[1]/span/b"));
        // Step 11: Get the text including the year of the motorcycle and parse it into int
        String yearText= yearTextBox.getText();
//        System.out.println(yearText);
        String [] yearWords = yearText.split(" ");
//        System.out.println(yearWords [0]);
//        System.out.println(yearWords [1]);
//        System.out.println(yearWords [2]);
        String yearAsText= yearWords[0];
        // convert the year as text to int
        int year = Integer.valueOf(yearAsText);
        //Step 12: check if the year is greater or equal to 2005 by assertTrue
        Assert.assertTrue(year>=2005,"year must be smaller than 2005 but it was : "+yearAsText);
//        Assert.assertTrue(year>=3005);

    }
    @Test(priority = 1)
    public  void  testEngine ()
    {
        // Step 13: copy all steps from 2 to 9 for Engine size check
        //Step 2: go to the URL "https://houston.craigslist.org/"
        driver.get("https://houston.craigslist.org/");
        //Step 3: insert 20 s implicitly wait command in case you have a slow connection
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        //Step 4: Create a Web element to locate and click on the link "motorcycles" and click it on
        WebElement motorcyclesLink = ((ChromeDriver) driver).findElementByLinkText("motorcycles");
        motorcyclesLink.click();
        //Step 5: Create a Web element to locate and fill the "miles" text box by "150"
        WebElement mile = ((ChromeDriver) driver).findElementByName("search_distance");
        mile.sendKeys("150");
        //Step 6: Create a Web element to locate and fill the "ZIP" text box by "77498"
        WebElement zipCode = ((ChromeDriver) driver).findElementByName("postal");
        zipCode.sendKeys("77498");
        //Step 7:Create a Web element to locate and fill the "year" text box by "2005"
        WebElement minYear = driver.findElement(By.name("min_auto_year"));
        minYear.sendKeys("2005");
        //Step 8 : Create a Web element to locate and fill the "engine displacement" text box by "1000"
        WebElement minEngineSize = driver.findElement(By.name("min_engine_displacement_cc"));
        minEngineSize.sendKeys("1000"+Keys.ENTER);
        //Step 9: Create a Web element to locate and click to navigate into the page of first motorcycle
        WebElement firstPage = ((ChromeDriver) driver).findElementByPartialLinkText("A");
        firstPage.click();
        // We are in detail page now
        //Step 14: Locate the text including the engine displacement of the motorcycle
        WebElement engineBox = driver.findElement(By.xpath("//span[contains(text(),'displacement')]"));
        // Step 15: Get the text including the engine displacement of the motorcycle and parse it into int
        String engineText = engineBox.getText();
        String[] engineWords = engineText.split(":");
        String engineSize = engineWords[1].trim();
        int engine = Integer.valueOf(engineSize);
        //Step 16: check if the engine displacement is greater or equal to 1000 by assertTrue
        Assert.assertTrue(engine >= 1000, "engine size must be greater than 1000 cc");
    }

    @AfterMethod
    public  void afterMethod(){
//       driver.close();
    }
    @AfterClass
    public void afterClass (){
        System.out.println("end of test, thank you");
//        driver.quit();
   }




}
