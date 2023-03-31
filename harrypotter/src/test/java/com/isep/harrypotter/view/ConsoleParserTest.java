package com.isep.harrypotter.view;

import com.isep.harrypotter.model.characters.Wizard;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

//Not working because waiting for user input
class ConsoleParserTest {

    private ConsoleParser consoleParser;

   @Test
    void getInt_validInput() {
        String input = "42\n";
        inputStream(input);
        int result = consoleParser.getInt("");
        assertEquals(42, result);
        assertNotEquals(43, result);
    }

    @Test
    void getInt_invalidInput() {
        String input = "invalid\n42\n";
        inputStream(input);
        int result = consoleParser.getInt("Please enter a number");
        assertEquals(42, result);
    }

    @Test
    void getString_validInput() {
        String input = "hello\n";
        inputStream(input);
        String result = consoleParser.getString(new Wizard());
        assertEquals("hello", result);
    }

    private void inputStream(String input){
        InputStream stream = new ByteArrayInputStream(input.getBytes());
        this.consoleParser = new ConsoleParser(stream);
    }
}
