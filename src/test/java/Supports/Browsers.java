package Supports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.How;

public  class Browsers {

        public  static WebDriver driver;
        public static WebDriver getDriver()
        {
                return driver;
        }

        public static void open(String browser)
        {

                switch (browser)
                {
                        case "chrome":
                        {
                                System.setProperty("webdriver.chrome.driver", "src/test/java/Browsers/chromedriver.exe");
                                driver = new ChromeDriver();
                                driver.manage().window().maximize();
                                break;
                        }

                        case "firefox":
                        {
                                System.setProperty("webdriver.gecko.driver", "src/test/java/Browsers/geckodriver.exe");
                                driver = new FirefoxDriver();
                                driver.manage().window().maximize();
                                break;
                        }
                        default:
                                System.out.println("wrong browser");
                }
        }
        public static void visit(String url)
        {
                driver.get(url);
        }
        public static void click(How how, String locator)
        {
                driver.findElement(how.buildBy(locator)).click();
        }
        public static WebElement getElement(How how, String locator)
        {
               return driver.findElement(how.buildBy(locator));
        }
}
