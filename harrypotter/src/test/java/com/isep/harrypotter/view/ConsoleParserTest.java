package com.isep.harrypotter.view;

import com.isep.harrypotter.model.characters.Wizard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

//Not working because waiting for user input
class ConsoleParserTest {

    private ConsoleParser consoleParser;

    @BeforeEach
    void setUp() {
        consoleParser = new ConsoleParser();
    }

    @Test
    void getInt_validInput() {
        String input = "42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int result = consoleParser.getInt("");
        assertEquals(42, result);
    }

    @Test
    void getInt_invalidInput() {
        String input = "invalid\n42\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int result = consoleParser.getInt("Please enter a number");
        assertEquals(42, result);
    }

    @Test
    void getString_validInput() {
        String input = "hello\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String result = consoleParser.getString(new Wizard());
        assertEquals("hello", result);
    }

    @Test
    void getString_invalidInput() {
        String input = "42\nhello\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        String result = consoleParser.getString(new Wizard());
        assertEquals("hello", result);
    }
}
