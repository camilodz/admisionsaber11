package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class PruebaProgamasIT {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("http://localhost:8080/AppWebAdmisionPruebasSaber11/faces/Vistas/programaofertado/List.xhtml");
    driver.findElement(By.xpath("//div[@id='j_idt22:j_idt23']/ul/li[2]/a/span")).click();
    driver.findElement(By.linkText("View")).click();
    driver.findElement(By.xpath("//button[@id='j_idt31:j_idt74']/span")).click();
    driver.findElement(By.id("form:j_idt31:j_idt37_input")).click();
    driver.findElement(By.id("form:j_idt31:j_idt37_input")).clear();
    driver.findElement(By.id("form:j_idt31:j_idt37_input")).sendKeys("55");
    driver.findElement(By.xpath("//span[@id='form:j_idt31:j_idt54']/a/span/span")).click();
    driver.findElement(By.linkText("Ponderados")).click();
    driver.findElement(By.id("form:j_idt31:j_idt72_input")).click();
    driver.findElement(By.id("form:j_idt31:j_idt72_input")).clear();
    driver.findElement(By.id("form:j_idt31:j_idt72_input")).sendKeys("50");
    driver.findElement(By.id("form:j_idt31:pruebaAdicional:j_idt88_input")).click();
    driver.findElement(By.id("form:j_idt31:pruebaAdicional:j_idt88_input")).clear();
    driver.findElement(By.id("form:j_idt31:pruebaAdicional:j_idt88_input")).sendKeys("50");
    driver.findElement(By.xpath("//table[@id='form:j_idt31:pruebaAd']/tbody/tr/td/div/div[2]/span")).click();
    driver.findElement(By.xpath("//button[@id='form:j_idt31:j_idt91']/span")).click();
  }

  @After
  public void tearDown() throws Exception {
    //driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
