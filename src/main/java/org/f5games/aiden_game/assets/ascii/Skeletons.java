package org.f5games.aiden_game.assets.ascii;

public class Skeletons {
    public static void ascii(String[] args) {

        String color1 = "\033[96m"; // cian
        String color1b = "\033[1;96m"; // cian bold
        String color2 = "\033[35m"; // magenta
        String color3 = "\033[94m"; // azul
        String color4 = "\033[91m"; // rojo
        String color5 = "\033[92m"; // verde
        String color6 = "\033[97m"; // blanco
        String color7 = "\033[93m"; // amarillo
        String color10 = "\033[38;5;206;48;5;57m";
        String reset = "\033[0m"; // blanco

        System.out.println();
        System.out.println(color1 + "         - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(color7 + "                        Monstruo: Esqueletos");
        System.out.println(color1 + "         - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("               .-.            .-.             .-.     \n" + //
                "              (o.o)          (o.o)           (o.o)\n" + //
                "               |=|            |=|             |=|\n" + //
                "              __|__.         __|__           __|__\n" + //
                "            //.=|=.\\\\.     //.=|=.\\\\.      //.=|=.\\\\\n" + //
                "           // .=|=. \\\\.   // .=|=. \\\\.    // .=|=. \\\\\n" + //
                "           \\\\ .=|=. //.   \\\\ .=|=. //.    \\\\ .=|=. //\n" + //
                "            \\\\(_=_)//.     \\\\(_=_)//       \\\\(_=_)//\n" + //
                "             (:| |:).       (:| |:).        (:| |:)\n" + //
                "              || ||.         || ||.          || ||\n" + //
                "              () ().         () ().          () ()\n" + //
                "              || ||.         || ||.          || ||\n" + //
                "              || ||.         || ||.          || ||\n" + //
                "             ==' '==.       ==' '==.        ==' '==");
        System.out.println();
        System.out.println("         - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
    }
}