package org.f5games.aiden_game.assets.ascii;

public class Castle {
    public static void main(String[] args) {

        String azul = "\033[1;36m";
        String reset = "\033[0m";

        System.out.println();
        System.out.println(azul + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("   /\\                                                        /\\\n" + //
                "  |  |                                                      |  |\n" + //
                " /----\\           "+reset+"       Castillo de Morti" + azul + "                 /----\\\n" + //
                "[______]        "+reset+"       Nivel 3: Nivel Final "+ azul + "              [______]\n" + //
                " |    |         _____                        _____         |    |\n" + //
                " |[]  |        [     ]                      [     ]        |  []|\n" + //
                " |    |       [_______][ ][ ][ ][][ ][ ][ ][_______]       |    |\n" + //
                " |    [ ][ ][ ]|     |  ,----------------,  |     |[ ][ ][ ]    |\n" + //
                " |             |     |/'    ____..____    '\\|     |             |\n" + //
                "  \\  []        |     |    /'    ||    '\\    |     |        []  /\n" + //
                "   |      []   |     |   |o     ||     o|   |     |  []       |\n" + //
                "   |           |  _  |   |     _||_     |   |  _  |           |\n" + //
                "   |   []      | (_) |   |    (_||_)    |   | (_) |       []  |\n" + //
                "   |           |     |   |     (||)     |   |     |           |\n" + //
                "   |           |     |   |      ||      |   |     |           |\n" + //
                " /''           |     |   |o     ||     o|   |     |           ''\\\n" + //
                "[_____________[_______]--'------''------'--[_______]_____________]");
        System.out.println();
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -"+ reset);
    }
}
