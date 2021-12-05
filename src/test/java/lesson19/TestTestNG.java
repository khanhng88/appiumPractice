package lesson19;

import org.testng.annotations.*;

public class TestTestNG {

    @BeforeClass
    public void runBeforeClass() {
        System.out.println("run before class");
    }

    @BeforeMethod
    public void beforeMethodTest() {
        System.out.println("before test method");
    }

    @Test
    public void loginWithCorrectCreds() {
        System.out.println("Correct creds");
    }

    @Test
    public void loginWithInCorrectCreds() {
        System.out.println("InCorrect creds");
    }

    @AfterMethod
    public void afterMethodTest() {
        System.out.println("After test method");
    }

    @AfterClass
    public void runAfterClass() {
        System.out.println("run after class");
    }
}
