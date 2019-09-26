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
		WebElement lobster = driver.findElement(By.id("lobster"));
		System.out.println(lobster.getText());
		lobster.click();
	}

	@Test
	public void orderTheLowestPricedMenuItem()
	{
		List<WebElement> elements = driver.findElements(By.cssSelector("#menu > .entree > button"));
		WebElement leastExpensive = elements.stream().sorted(sortByPrice).findFirst().get();
		System.out.println(leastExpensive.getText());
		leastExpensive.click();
	}

	@Test
	public void orderTheHighestPricedMenuItem()
	{
		List<WebElement> elements = driver.findElements(By.cssSelector("#menu > .entree > button"));
		WebElement mostExpensive = elements.stream().sorted(sortByPrice.reversed()).findFirst().get();
		System.out.println(mostExpensive.getText());
		mostExpensive.click();
	}

	@Test
	public void orderTheFirstMenuItem()
	{
		WebElement firstItem = driver.findElement(By.cssSelector("#menu > .entree:first-of-type > button"));
		System.out.println(firstItem.getText());
		firstItem.click();
	}

	@Test
	public void orderTheLastMenuItem()
	{
		WebElement lastItem = driver.findElement(By.cssSelector("#menu > .entree:last-of-type > button"));
		System.out.println(lastItem.getText());
		lastItem.click();
	}

	@Before
	public void setup()
	{
		driver = new ChromeDriver();
		driver.get("https://sauceaaron.github.io/MenuSelection/menu.html");
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