package org.f5games.aiden_game.assets.ascii;

public class Castle {
    public static void ascii(String[] args) {

        String color4 = "\033[91m"; // rojo
        String color7 = "\033[93m"; // amarillo
        String reset = "\033[0m"; // blanco

        System.out.println();
        System.out.println(color4 + "         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("            /\\                                                        /\\\n" + //
                "           |  |                                                      |  |\n" + //
                "          /----\\           "+color7+"       Castillo de Morti" + color4 + "                 /----\\\n" + //
                "         [______]        "+color7+"       Nivel 3: Nivel Final "+ color4 + "              [______]\n" + //
                "          |    |         _____                        _____         |    |\n" + //
                "          |[]  |        [     ]                      [     ]        |  []|\n" + //
                "          |    |       [_______][ ][ ][ ][][ ][ ][ ][_______]       |    |\n" + //
                "          |    [ ][ ][ ]|     |  ,----------------,  |     |[ ][ ][ ]    |\n" + //
                "          |             |     |/'    ____..____    '\\|     |             |\n" + //
                "           \\  []        |     |    /'    ||    '\\    |     |        []  /\n" + //
                "            |      []   |     |   |o     ||     o|   |     |  []       |\n" + //
                "            |           |  _  |   |     _||_     |   |  _  |           |\n" + //
                "            |   []      | (_) |   |    (_||_)    |   | (_) |       []  |\n" + //
                "            |           |     |   |     (||)     |   |     |           |\n" + //
                "            |           |     |   |      ||      |   |     |           |\n" + //
                "          /''           |     |   |o     ||     o|   |     |           ''\\\n" + //
                "         [_____________[_______]--'------''------'--[_______]_____________]");
        System.out.println();
        System.out.println("         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"+ reset);
    }
}
