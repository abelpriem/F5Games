package org.f5games.aiden_game.assets.ascii;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MortiTest {

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
        Morti.ascii(null);

        String output = outContent.toString();

        assertTrue(output.contains("Jefe Final: Morti"));
        assertTrue(output.contains(".            )        )"));
        assertTrue(output.contains("( \\   | /  ___   ( \\/     ___   \\ | ( ("));
        assertTrue(output.contains("\\.)  |/  /   \\__      __/   \\   \\|  )"));
    }
}
