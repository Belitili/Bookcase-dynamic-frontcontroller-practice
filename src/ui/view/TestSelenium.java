package ui.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSelenium {
	
	WebDriver driver;
	String url;
	
	String testTitle = "TestTitle";;
	String testAuthor = "TestAuthor";
	String testNrOfPages = "200";
	String testIsbn = "111-11-111-1111-1";
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "WebContent\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		url = "http://localhost:8080/1TX4_r0298778_VerdonckMaria_Boekenkast/";
	}
	
	@Test
	public void test_add_book_form_filled_in_correct() {
		this.makeTestBook();
		
		driver.get(url + "BookController?action=READ");
		Collection<WebElement> tds = driver.findElements(By.tagName("td"));
		
		assertTrue(this.collectionContains(tds, testTitle));
		assertTrue(this.collectionContains(tds, testAuthor));
		assertTrue(this.collectionContains(tds, testNrOfPages));
		assertTrue(this.collectionContains(tds, testIsbn));
		
		this.removeTestBook();
	}
	
	@Test
	public void test_add_book_form_leave_title_and_author_blank() {
		driver.get(url + "boekenFormulier.jsp");
		this.fillInField(driver.findElement(By.id("title")), "");
		this.fillInField(driver.findElement(By.id("author")), "");
		this.fillInField(driver.findElement(By.id("nrpages")), testNrOfPages);
		this.fillInField(driver.findElement(By.id("isbn")), testIsbn);
		driver.findElement(By.id("addBook")).click();
		
		Collection<WebElement> errors = driver.findElements(By.className("error"));
		
		assertTrue(errors.size()==2);
		assertTrue(this.collectionContains(errors, "The title for your book cannot be empty"));
		assertTrue(this.collectionContains(errors, "The author for your book cannot be empty"));
		assertEquals(driver.findElement(By.id("nrpages")).getAttribute("value"), testNrOfPages);
		assertEquals(driver.findElement(By.id("isbn")).getAttribute("value"), testIsbn);
	}
	
	@Test
	public void test_delete_book_works() {
		this.makeTestBook();
		driver.get(url + "BookController?action=READ");
		
		Collection<WebElement> tds = driver.findElements(By.tagName("td"));
		assertTrue(this.collectionContains(tds, testIsbn));
		
		this.removeTestBook();
		
		driver.get(url + "BookController?action=READ");
		Collection<WebElement> tds2 = driver.findElements(By.tagName("td"));
		assertFalse(this.collectionContains(tds2, testIsbn));
	}
	
	private void makeTestBook() {
		driver.get(url + "boekenFormulier.jsp");
		this.fillInField(driver.findElement(By.id("title")), testTitle);
		this.fillInField(driver.findElement(By.id("author")), testAuthor);
		this.fillInField(driver.findElement(By.id("nrpages")), testNrOfPages);
		this.fillInField(driver.findElement(By.id("isbn")), testIsbn);
		driver.findElement(By.id("addBook")).click();
	}
	
	private void removeTestBook() {
		driver.get(url + "BookController?action=READ");
		String delid = "delete" + testIsbn;
		driver.findElement(By.id(delid)).click();
		driver.findElement(By.id("verwijderJa")).click();
	}
	
	private void fillInField(WebElement field, String keys) {
		field.clear();
		field.sendKeys(keys);
	}
	
	private boolean collectionContains(Collection<WebElement> collec, String text) {
		for (WebElement el: collec) {
			if (el.getText().contains(text)) {
				return true;
			}
		}
		return false;
	}
	
	@After
	public void shutDown() {
		driver.quit();
	}

}
