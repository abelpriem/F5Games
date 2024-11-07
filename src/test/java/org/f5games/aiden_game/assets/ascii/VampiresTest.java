package org.f5games.aiden_game.assets.ascii;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.f5games.aiden_game.assets.ascii.Vampires;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class VampiresTest {

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
        Vampires.ascii(null);

        String output = outContent.toString();

        assertTrue(output.contains("Jefe: Vampiro"));
        assertTrue(output.contains("/######\\"));
        assertTrue(output.contains("\\##\\   |       \\|     |  /#####|"));
        assertTrue(output.contains("/################\\######/########\\"));
        assertTrue(output.contains("/###################\\#/##############\\"));
    }
}
