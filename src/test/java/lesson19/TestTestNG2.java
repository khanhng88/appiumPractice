package lesson19;

import org.testng.annotations.Test;

public class TestTestNG2 {

    @Test
    public void loginWithCorrectCreds() {
        System.out.println("Correct creds 2");
    }

    @Test
    public void loginWithInCorrectCreds() {
        System.out.println("InCorrect creds 2");
    }
}
