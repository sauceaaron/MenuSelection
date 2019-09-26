import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Comparator;
import java.util.List;

public class MenuSelectionTest
{
	WebDriver driver;

	@Test
	public void orderTheLobster()
	{
		driver.findElement(By.id("lobster"));
	}

	@Test
	public void orderTheLowestPricedMenuItem()
	{
		List<WebElement> elements = driver.findElements(By.cssSelector("#menu > .entree > button"));
		WebElement leastExpensive = elements.stream().sorted(sortByPrice).findFirst().get();
		leastExpensive.click();
	}

	@Test
	public void orderTheHighestPricedMenuItem()
	{
		List<WebElement> elements = driver.findElements(By.cssSelector("#menu > .entree > button"));
		WebElement mostExpensive = elements.stream().sorted(sortByPrice.reversed()).findFirst().get();
		mostExpensive.click();
	}

	@Test
	public void orderTheFirstMenuItem()
	{
		WebElement firstItem = driver.findElement(By.cssSelector("#menu > .entree:first-of-type > button"));
		firstItem.click();
	}

	@Test
	public void orderTheLastMenuItem()
	{
		driver.findElement(By.cssSelector("#menu > .entree:last-of-type > button"));
	}

	@Before
	public void setup()
	{
		driver = new ChromeDriver();
		driver.get("http://localhost:9080/menu.html");
	}

	@After
	public void teardown() throws InterruptedException
	{
		Thread.sleep(5*1000);
		driver.quit();
	}

	Comparator<WebElement> sortByPrice = new Comparator<WebElement>() {
		@Override
		public int compare(WebElement a, WebElement b)
		{
			System.out.println("comparing " + a.getText() + " to " + b.getText());

			return getPrice(a).compareTo(getPrice(b));
		}
	};

	public Double getPrice(WebElement item)
	{
		return Double.valueOf(item.getText().split("\\$")[1].trim());
	}
}