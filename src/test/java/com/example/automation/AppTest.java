package com.example.automation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void testGetGreeting() {
        App app = new App();
        String expected = App.GREETING_MESSAGE;
        String actual = app.getGreeting();
        assertEquals(expected, actual, "Greeting should match the default message.");
    }

    @Test
    void testWelcomeMessage() {
        App app = new App();
        String expected = "Hello, Anant!";
        String actual = app.welcomeMessage("Anant");
        assertEquals(expected, actual, "Welcome message should greet the user correctly.");
    }

    @Test
    void testWelcomeMessageWithEmptyName() {
        App app = new App();
        String expected = "Hello, Guest!";
        String actual = app.welcomeMessage("");
        assertEquals(expected, actual, "Welcome message should handle empty names correctly.");
    }

    @Test
    void testWelcomeMessageWithNullName() {
        App app = new App();
        String expected = "Hello, Guest!";
        String actual = app.welcomeMessage(null);
        assertEquals(expected, actual, "Welcome message should handle null names correctly.");
    }
}