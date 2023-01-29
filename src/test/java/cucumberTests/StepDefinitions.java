package cucumberTests;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.*;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StepDefinitions {
    private static WebDriver driver;
    private int responseCode;
    private JSONArray jsonArray;


    @BeforeAll
    public static void chromeDriverInit() {
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void chromeDriverQuit() {
        driver.quit();
    }

    @Given("nothing")
    public void nothing() {
    }

    @When("the login credentials are correct")
    public void theLoginCredentialsAreCorrect() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("tomsmith");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("SuperSecretPassword!");
        passwordInput.submit();
    }

    @When("the login credentials are incorrect")
    public void theLoginCredentialsAreIncorrect() {
        driver.get("https://the-internet.herokuapp.com/login");
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("tomsmith");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("SuperSecretWrongPassword!");
        passwordInput.submit();
    }

    @Then("the login is successful")
    public void theLoginIsSuccessful() {
        assertEquals("https://the-internet.herokuapp.com/secure", driver.getCurrentUrl());
    }

    @Then("the login is rejected")
    public void theLoginIsRejected() {
        assertEquals("https://the-internet.herokuapp.com/login", driver.getCurrentUrl());
    }


    @When("HTTP {int} is navigated")
    public void httpIsNavigated(int arg0) throws IOException {
        URL url = new URL("https://the-internet.herokuapp.com/status_codes/" + arg0);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        responseCode = connection.getResponseCode();
    }

    @Then("the status code is {int}")
    public void theStatusCodeIs(int arg0) {
        assertEquals(arg0, responseCode);
    }

    @When("user page is opened")
    public void userPageIsOpened() throws IOException {
        URL url = new URL("https://jsonplaceholder.typicode.com/users");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        try (InputStream input = connection.getInputStream()) {
            InputStreamReader isr = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder json = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                json.append((char) c);
            }
            jsonArray = new JSONArray(json.toString());
        }
    }

    @Then("the response contains user with address")
    public void theResponseContainsUserWithAddress() {
        boolean hasNicholas = false;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            if (jsonObject.has("name")) {
                if (jsonObject.getString("name").equals("Nicholas Runolfsdottir V")) {
                    hasNicholas = true;
                    assertTrue(jsonObject.has("address"));
                    JSONObject jsonAddress = jsonObject.getJSONObject("address");
                    assertTrue(jsonAddress.has("street"));
                    assertEquals("Ellsworth Summit", jsonAddress.getString("street"));
                    assertTrue(jsonAddress.has("suite"));
                    assertEquals("Suite 729", jsonAddress.getString("suite"));
                    assertTrue(jsonAddress.has("city"));
                    assertEquals("Aliyaview", jsonAddress.getString("city"));
                    assertTrue(jsonAddress.has("zipcode"));
                    assertEquals("45169", jsonAddress.getString("zipcode"));
                    assertTrue(jsonAddress.has("geo"));
                    JSONObject jsonGeo = jsonAddress.getJSONObject("geo");
                    assertTrue(jsonGeo.has("lat"));
                    assertEquals("-14.3990", jsonGeo.getString("lat"));
                    assertTrue(jsonGeo.has("lng"));
                    assertEquals("-120.7677", jsonGeo.getString("lng"));
                }
            }
        }
        assertTrue(hasNicholas);
    }
}
