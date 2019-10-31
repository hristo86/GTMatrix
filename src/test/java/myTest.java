import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class myTest {
    String header = "/html/body/div[1]/header/div";
    String elementMail = "//*[@id=\"li-email\"]";
    String elementPass = "//*[@id=\"li-password\"]";
    String elementLogIn = "//*[@id=\"user-nav-login\"]/a";
    String elementLogIn2 = "//*[@id=\"menu-site-nav\"]/div[2]/div[1]/form/div[4]/button";
    String url = "/html/body/div[1]/main/article/form/div[1]/div[1]/div/input";
    String elementAnalysis = "/html/body/div[1]/main/article/form/div[3]/a";
    String getUrlIn = "//*[@id=\"af-region\"]";
    String analyzeButton = "/html/body/div[1]/main/article/form/div[1]/div[2]/button";
    String reTest = "body > div.page-wrapper > main > article > div.report-head > div.report-details";

    @Test

    public void startWebDriver() throws InterruptedException {

        String[] websites = {
              "https://www.taxback.com/en/",
                "https://www.taxback.com/en/contact-us",
               "https://www.taxback.com/en/canada",
               "https://www.taxback.com/en/australia/",
              "https://www.taxback.com/en/ireland/",
               "https://taxback.com/en/usa/",
               "https://taxback.com/en/uk/"
        };

        int[] destinationNumber = {2,3,1,4,6,7};

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://gtmetrix.com/");
        driver.findElement(By.xpath(this.elementLogIn)).click();
        this.logIn(driver);
        driver.findElement(By.xpath(elementLogIn2)).click();


        for ( String website : websites) {
            for(int dNum : destinationNumber) {
                System.out.println(website + " : " + dNum);
                new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath(this.url)));
                WebElement url = driver.findElement(By.xpath(this.url));
                url.sendKeys(website);
                this.TestTaxBackEn(driver, dNum);
                this.concolResult(driver);
                System.out.println("1");
                driver.get("https://gtmetrix.com/");

            }
            //this.concolResult(driver);
            System.out.println("2");
        }
        driver.close();
    }


    public void logIn(WebDriver driver) {
        WebElement mail = driver.findElement(By.xpath(this.elementMail));
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath(this.elementMail)));
        mail.sendKeys("hrganev@taxback.com");
        WebElement pass = driver.findElement(By.xpath(this.elementPass));
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.xpath(this.elementPass)));
        pass.sendKeys("T@xB@ck2017");
        System.out.println("Login successful");
    }

    public void TestTaxBackEn(WebDriver driver, int destinationNumber) {
        WebElement analis = driver.findElement(By.xpath(this.elementAnalysis));
        analis.click();

        // WebElement urlIn = driver.findElement(By.xpath(this.getUrlIn));
        //urlIn.click();

        Select dropdown = new Select(driver.findElement(By.xpath(this.getUrlIn)));
        dropdown.selectByValue(Integer.toString(destinationNumber));
        WebElement btn = driver.findElement(By.xpath(this.analyzeButton));
        btn.click();
        new WebDriverWait(driver, 600).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".report-page-detail-value")));

    }
    public void concolResult(WebDriver driver) {
        List<WebElement> reportResults = driver.findElements(By.cssSelector(".report-page-detail-value"));
        System.out.println("Onload Time - " + reportResults.get(0).getText());
        System.out.println("Total Page Size - " + reportResults.get(1).getText());
        System.out.println("Requests - " + reportResults.get(2).getText());
        new WebDriverWait(driver, 100).until(ExpectedConditions.elementToBeClickable(By.cssSelector(this.reTest)));
    }

}


