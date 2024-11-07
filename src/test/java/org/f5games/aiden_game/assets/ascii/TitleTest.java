package org.f5games.aiden_game.assets.ascii;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.f5games.aiden_game.assets.ascii.Title;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TitleTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testAsciiArt() {
        Title.ascii(null);

        String output = outContent.toString();

        assertTrue(output.contains("________    _____      _____   ___________"));
        assertTrue(output.contains("/  _____/   /  _  \\    /     \\  \\_   _____/"));
        assertTrue(output.contains("\\    \\_\\  \\/    |    \\/    Y    \\ |        \\"));
        assertTrue(output.contains("\\______  /\\____|__  /\\____|__  //_______  /"));
        assertTrue(output.contains("\\/         \\/         \\/         \\/ "));
    }
}
