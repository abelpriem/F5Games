package org.f5games.aiden_game.assets.ascii;

public class GameOver {
        public static void ascii(String[] args) {

                String color1 = "\033[96m";
                String color1b = "\033[1;96m";
                String color2 = "\033[35m";
                String color3 = "\033[94m";
                String color4 = "\033[91m";
                String color5 = "\033[92m";
                String color6 = "\033[97m";
                String color7 = "\033[93m";
                String color10 = "\033[38;5;206;48;5;57m";
                String reset = "\033[0m";

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
