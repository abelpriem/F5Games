package org.f5games.aiden_game.assets.ascii;

public class Graveyard {
    public static void ascii(String[] args) {

        String azul = "\033[1;36m";
        String reset = "\033[0m";

        System.out.println();
        System.out.println(azul + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
        System.out.println("                   Nivel 2: Cementerio");
        System.out.println(azul + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("      ,-=-.       ______     _.           ,-=-. \n" + //
                "     /  +  \\     />----->  _|1|_.        /  +  \\ \n" + //
                "     | ~~~ |    // -/- /  |_ H _|.       | ~~~ |\n" + //
                "     |R.I.P|   //  /  /     |S|.         |R.I.P|\n" + //
                "\\vV,,|_____|V,//_____/VvV,v,|_|/,,vhjwv/,|_____|V\\vV,,");
        System.out.println();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
    }
}
