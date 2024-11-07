package org.f5games.aiden_game.assets.ascii;

public class Winner {
        public static void ascii(String[] args) {

                String color10 = "\033[38;5;206;48;5;57m";
                String reset = "\033[0m";

                System.out.println();
                System.out
                                .println(color10 + "          - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -          ");
                System.out.print(
                                "            __      __ .___  _______    _______   _____________________ ._.          \n"
                                                + //
                                                "           /  \\    /  \\|   | \\      \\   \\      \\  \\_   _____/\\______   \\| |          \n"
                                                + //
                                                "           \\   \\/\\/   /|   | /   |   \\  /   |   \\  |    __)_  |       _/| |          \n"
                                                + //
                                                "            \\        / |   |/    |    \\/    |    \\ |        \\ |    |   \\ \\|          \n"
                                                + //
                                                "             \\__/\\  /  |___|\\____|__  /\\____|__  //_______  / |____|_  / __          \n"
                                                + //
                                                "                  \\/                \\/         \\/         \\/         \\/  \\/          ");
                System.out.println();
                System.out.println(
                                "         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -           "
                                                + reset);
                System.out.println();
        }
}