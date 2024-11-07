package org.f5games.aiden_game.assets.ascii;

public class Graveyard {
    public static void main(String[] args) {

        String color2 = "\033[35m"; // magenta
        String color7 = "\033[93m"; // amarillo
        String reset = "\033[0m"; // blanco

        System.out.println();
        System.out.println(color2 + "         - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
        System.out.println(color7 + "                            Nivel 2: Cementerio");
        System.out.println(color2 + "         - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("               ,-=-.       ______     _.           ,-=-. \n" + //
                "              /  +  \\     />----->  _|1|_.        /  +  \\ \n" + //
                "              | ~~~ |    // -/- /  |_ H _|.       | ~~~ |\n" + //
                "              |R.I.P|   //  /  /     |S|.         |R.I.P|\n" + //
                "         \\vV,,|_____|V,//_____/VvV,v,|_|/,,vhjwv/,|_____|V\\vV,,");
        System.out.println();
        System.out.println("         - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
    }
}
