import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class OldTest {
    @Test
    public void testTextBox() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com");

        WebElement elementsSection = driver.findElement(By.xpath("//h5[text()='Elements']"));
        elementsSection.click();

        WebElement textBoxSection = driver.findElement(By.xpath("//span[text()='Text Box']"));
        textBoxSection.click();

        driver.findElement(By.id("userName")).sendKeys("Nick");
        driver.findElement(By.id("userEmail")).sendKeys("nick@gmail.com");
        driver.findElement(By.id("currentAddress")).sendKeys("325 Main St");
        driver.findElement(By.id("permanentAddress")).sendKeys("576 Main St");

        driver.findElement(By.cssSelector("#submit")).click();

        String outputName = driver.findElement(By.id("name")).getText();
        String outputEmail = driver.findElement(By.id("email")).getText();
        String outputCurrentAddress = driver.findElement(By.xpath("//p[@id='currentAddress']")).getText();
        String outputPermanentAddress = driver.findElement(By.xpath("//p[@id='permanentAddress']")).getText();

        Assert.assertEquals(outputName, "Name:Nick");
        Assert.assertEquals(outputEmail, "Email:nick@gmail.com");
        Assert.assertTrue(outputCurrentAddress.contains("Current Address :325 Main St"));
        Assert.assertTrue(outputPermanentAddress.contains("Permananet Address :576 Main St"));

        driver.quit();
    }

    @Test
    public void testButton() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/test/radio.html");

        WebElement option1 = driver.findElement(By.xpath("//input[@value='Option 1']"));
        option1.click();
        Assert.assertTrue(option1.isSelected());

        WebElement option2 = driver.findElement(By.xpath("//input[@value='Option 2']"));
        option2.click();
        Assert.assertTrue(option2.isSelected());

        WebElement option3 = driver.findElement(By.xpath("//input[@value='Option 3']"));
        option3.click();
        Assert.assertTrue(option3.isSelected());

        WebElement checkbox1 = driver.findElement(By.xpath("//input[@value='checkbox1']"));
        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected());

        WebElement checkbox2 = driver.findElement(By.xpath("//input[@value='checkbox2']"));
        checkbox2.click();
        Assert.assertTrue(checkbox2.isSelected());

        WebElement checkbox3 = driver.findElement(By.xpath("//input[@value='checkbox3']"));
        checkbox3.click();
        Assert.assertTrue(checkbox3.isSelected());

        driver.quit();
    }

    @Test
    public void testLoginPage() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector(".fa-sign-in")).click();

        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);

        WebElement successMessage = driver.findElement(By.cssSelector(".flash.success"));
        Assert.assertTrue(successMessage.isDisplayed());
        Assert.assertTrue(successMessage.getText().contains("You logged into a secure area!"));

        driver.quit();
    }

    @Test
    public void testDropdown() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdown = driver.findElement(By.id("dropdown"));
        dropdown.findElement(By.xpath("//option[text()='Option 1']")).click();

        String selectedOption = dropdown.getAttribute("value");
        Assert.assertEquals(selectedOption, "1");

        driver.quit();
    }

    @Test
    public void testAddRemoveElements() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addButton.click();

        WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
        Assert.assertTrue(deleteButton.isDisplayed());

        deleteButton.click();
        Assert.assertTrue(driver.findElements(By.xpath("//button[text()='Delete']")).isEmpty());

        driver.quit();
    }

    @Test
    public void testJavaScriptAlerts() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        WebElement alertButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
        alertButton.click();

        driver.switchTo().alert().accept();

        String expectedMessage = "You successfully clicked an alert";
        String actualMessage = driver.findElement(By.cssSelector("#result")).getText();
        Assert.assertEquals(actualMessage, expectedMessage);

        driver.quit();
    }

    @Test
    public void testCheckBoxes() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        WebElement checkbox1 = driver.findElement(By.xpath("//input[1]"));
        WebElement checkbox2 = driver.findElement(By.xpath("//input[2]"));

        Assert.assertFalse(checkbox1.isSelected());
        Assert.assertTrue(checkbox2.isSelected());

        checkbox1.click();
        Assert.assertTrue(checkbox1.isSelected());
        Assert.assertTrue(checkbox2.isSelected());

        driver.quit();
    }

    @Test
    public void testEnableTextField() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();
        WebElement textField = driver.findElement(By.xpath("//input[@type='text']"));

        Assert.assertFalse(textField.isEnabled(), "Text field should be enabled.");
        Assert.assertEquals(textField.getAttribute("value"), "", "Text field should be empty.");

        driver.quit();
    }

    @Test
    public void testLoginAddProductToCart() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.cssSelector("#user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.xpath("//button[contains(text(), 'Add to cart')]")).click();
        WebElement cartBadge = driver.findElement(By.className("shopping_cart_badge"));

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
        Assert.assertEquals(cartBadge.getText(), "1");

        driver.quit();
    }

    @Test
    public void testFlightBooking() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://blazedemo.com");
        driver.findElement(By.name("fromPort")).sendKeys("Paris");
        driver.findElement(By.name("toPort")).sendKeys("New York");
        driver.findElement(By.xpath("//input[@value='Find Flights']")).click();

        String expectedTitle = "Flights from Paris to New York:";
        Assert.assertTrue(driver.getPageSource().contains(expectedTitle), "Title not found on the page!");

        driver.findElement(By.xpath("//input[@value='Choose This Flight']")).click();

        String expectedDetailTitle = "Your flight from Paris to London.";
        Assert.assertFalse(driver.getPageSource().contains(expectedDetailTitle), "Flight details page not found!");

        driver.quit();
    }

    @Test
    public void testSearchWiki() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://en.wikipedia.org/wiki/Main_Page");

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.findElement(By.xpath("//*[@id='p-search']/a/span[1]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.findElement(By.xpath("//*[@id='searchform']/div/div/div[1]/input")).sendKeys("Selenium");

        driver.findElement(By.cssSelector("#searchform > div > button")).click();

        WebElement artickleTitle = driver.findElement(By.xpath("//*[@id='firstHeading']/span[text()='Selenium']"));
        Assert.assertEquals(artickleTitle.getText(), ("Selenium"));
        driver.quit();
    }

    @Test
    public void testToolsQA() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://demoqa.com/text-box");

        String title = driver.getTitle();
        Assert.assertEquals(title, "DEMOQA");

        WebElement primaryImage = driver.findElement(By.xpath("//img[@src = '/images/Toolsqa.jpg']"));
        Assert.assertTrue(primaryImage.isDisplayed());
        driver.quit();
    }

    @Test
    public void testPlaceholderFill() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");

        WebElement textBoxUser = driver.findElement(By.name("user-name"));
        textBoxUser.sendKeys("standard_user");
        WebElement textBoxPass = driver.findElement(By.id("password"));
        textBoxPass.sendKeys("secret_sauce");
        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");

        driver.quit();
    }
}



