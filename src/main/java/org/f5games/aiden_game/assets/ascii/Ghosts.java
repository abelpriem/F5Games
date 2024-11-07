package org.f5games.aiden_game.assets.ascii;

public class Ghosts {
    public static void ascii(String[] args) {

        String color2 = "\033[35m";
        String color7 = "\033[93m";
        String reset = "\033[0m";

        System.out.println();
        System.out.println(color2 + "         - - - - - - - - - - -");
        System.out.println(color7 + "             Jefe: Fantasma");
        System.out.println(color2 + "         - - - - - - - - - - -");
        System.out.print("              .-----.\n" + //
                "            .' -   - '.\n" + //
                "           /  .-. .-.  \\\n" + //
                "           |  | | | |  |\n" + //
                "            \\ \\o/ \\o/ /\n" + //
                "           _/    ^    \\_\n" + //
                "          | \\  '---'  / |\n" + //
                "          / /`--. .--`\\ \\\n" + //
                "         / /'---` `---'\\ \\\n" + //
                "         '.__.       .__.'\n" + //
                "             `|     |`\n" + //
                "              |     \\\n" + //
                "              \\      '--.\n" + //
                "               '.        `\\\n" + //
                "                 `'---.   |\n" + //
                "            jgs     ,__) /\n" + //
                "                     `..'");
        System.out.println();
        System.out.println("         - - - - - - - - - - -" + reset);
    }
}
