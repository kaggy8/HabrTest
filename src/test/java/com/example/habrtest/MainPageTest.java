package com.example.habrtest;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void habrFreelanceTest() {
        WebElement documentations = driver.findElement(By.xpath("//a[contains(text(), 'Документы')]"));
        documentations.click();

        WebElement link = driver.findElement(By.xpath("//a[contains(text(), 'Как Хабр взаимодействует с госорганами и другими заявителями')]"));
        link.click();

        List<WebElement> habrFreelance = driver.findElements(By.xpath("//*[contains(text(), 'Хабр Фриланс')]"));

        assertFalse(habrFreelance.isEmpty(), "habrFreelance не найден.");
    }

    @Test
    public void paginationArrowRightTest() {
        WebElement sciFi  = driver.findElement(By.xpath("//*[contains(text(), 'Научпоп')]"));
        sciFi.click();

        WebElement habs  = driver.findElement(By.xpath("//*[contains(text(), 'Все хабы')]"));
        habs.click();

        WebElement paginationNextElement = driver.findElement(By.cssSelector("#pagination-next-page > svg"));

        assertTrue(paginationNextElement.isDisplayed(), "Элемент не найден");
    }
}
