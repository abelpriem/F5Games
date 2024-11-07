package org.f5games.aiden_game.assets.ascii;

public class GameOver {
        public static void ascii(String[] args) {

                String color7 = "\033[93m"; // amarillo
                String reset = "\033[0m"; // blanco

                System.out.println();
                System.out
                                .println(color7 + "          - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
                System.out.print(
                                "            ________    _____      _____   ___________ ________   ____   _________________________ \n"
                                                + //
                                                "           /  _____/   /  _  \\    /     \\  \\_   _____/ \\_____  \\  \\   \\ /   /\\_   _____/\\______   \\\n"
                                                + //
                                                "          /   \\  ___  /  /_\\  \\  /  \\ /  \\  |    __)_   /   |   \\  \\   Y   /  |    __)_  |       _/\n"
                                                + //
                                                "          \\    \\_\\  \\/    |    \\/    Y    \\ |        \\ /    |    \\  \\     /   |        \\ |    |   \\\n"
                                                + //
                                                "           \\______  /\\____|__  /\\____|__  //_______  / \\_______  /   \\___/   /_______  / |____|_  /\n"
                                                + //
                                                "                  \\/         \\/         \\/         \\/          \\/                    \\/         \\/ ");
                System.out.println();
                System.out
                                .println("          - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"
                                                + reset);
                System.out.println();
        }
}
