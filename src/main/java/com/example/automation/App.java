package com.example.automation;

import java.util.logging.Logger;

public class App {
  private static final Logger LOG = Logger.getLogger(App.class.getName());
  public static final String GREETING_MESSAGE = "Hello World!";

  public static void main(String[] args) {
    LOG.info(GREETING_MESSAGE);

    if (args.length > 0 && "test".equals(args[0])) {
      runTests();
    }
  }

  /**
   * Method to run tests for verifying the greeting message.
   */
  public static void runTests() {
    String expectedOutput = GREETING_MESSAGE;
    String actualOutput = getGreeting();

    if (!expectedOutput.equals(actualOutput)) {
      throw new AssertionError("Test failed: Output mismatch!");
    } else {
      LOG.info("Test passed: Output matches.");
    }
  }

  /**
   * Method to get the greeting message.
   * 
   * @return the greeting message.
   */
  public static String getGreeting() {
    return GREETING_MESSAGE;
  }

  /**
   * Method to generate a personalized welcome message.
   * 
   * @param name the name of the person to greet.
   * @return a welcome message.
   */
  public String welcomeMessage(String name) {
    if (name != null && !name.isEmpty()) 
    {
      return "Hello, " + name + "!";
    } 
    else 
    {
      return "Hello, Guest!";
    }
  }
}
