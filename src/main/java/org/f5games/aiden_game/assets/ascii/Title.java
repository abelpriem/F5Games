package org.f5games.aiden_game.assets.ascii;

public class Title {

    public static void ascii(String[] args) {

        String color5 = "\033[92m"; // verde
        String reset = "\033[0m"; // blanco

        System.out.println();
        System.out.println(color5 + "         - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("           ________    _____      _____   ___________\n" + //
                "          /  _____/   /  _  \\    /     \\  \\_   _____/\n" + //
                "         /   \\  ___  /  /_\\  \\  /  \\ /  \\  |    __)_ \n" + //
                "         \\    \\_\\  \\/    |    \\/    Y    \\ |        \\\n" + //
                "          \\______  /\\____|__  /\\____|__  //_______  /\n" + //
                "                 \\/         \\/         \\/         \\/ ");
        System.out.println();
        System.out.println(color5 + "         - - - - - - - - - - - - - - - - - - - - - - -" + reset);
        System.out.println();
    }
}
