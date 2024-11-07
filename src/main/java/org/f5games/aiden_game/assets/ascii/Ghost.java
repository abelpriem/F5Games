package org.f5games.aiden_game.assets.ascii;

public class Ghost {
    public static void ascii(String[] args) {

        String azul = "\033[1;36m";
        String reset = "\033[0m";

        System.out.println();
        System.out.println(azul + "- - - - - - - - - - -" + reset);
        System.out.println("    Monstruo: Fantasma");
        System.out.println(azul + "- - - - - - - - - - -");
        System.out.print("     .-----.\n" + //
                "   .' -   - '.\n" + //
                "  /  .-. .-.  \\\n" + //
                "  |  | | | |  |\n" + //
                "   \\ \\o/ \\o/ /\n" + //
                "  _/    ^    \\_\n" + //
                " | \\  '---'  / |\n" + //
                " / /`--. .--`\\ \\\n" + //
                "/ /'---` `---'\\ \\\n" + //
                "'.__.       .__.'\n" + //
                "    `|     |`\n" + //
                "     |     \\\n" + //
                "     \\      '--.\n" + //
                "      '.        `\\\n" + //
                "        `'---.   |\n" + //
                "   jgs     ,__) /\n" + //
                "            `..'");
        System.out.println();
        System.out.println("- - - - - - - - - - -" + reset);
    }
}