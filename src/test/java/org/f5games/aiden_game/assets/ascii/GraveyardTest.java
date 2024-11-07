package org.f5games.aiden_game.assets.ascii;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GraveyardTest {

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
        Graveyard.ascii(null);

        String output = outContent.toString();

        assertTrue(output.contains("Nivel 2: Cementerio"));
        assertTrue(output.contains(",-=-.       ______     _.           ,-=-."));
        assertTrue(output.contains("/  +  \\     />----->  _|1|_.        /  +  \\"));
        assertTrue(output.contains("| ~~~ |    // -/- /  |_ H _|.       | ~~~ |"));
        assertTrue(output.contains("|R.I.P|   //  /  /     |S|.         |R.I.P|"));
        assertTrue(output.contains("\\vV,,|_____|V,//_____/VvV,v,|_|/,,vhjwv/,|_____|V\\vV,,"));
    }
}
