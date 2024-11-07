package org.f5games.aiden_game.assets.ascii;

public class Forest {
    public static void ascii(String[] args) {

        String color1 = "\033[96m";
        String color7 = "\033[93m";
        String reset = "\033[0m";

        System.out.println();
        System.out.println(color1 + "         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println(color7 + "                            Nivel 1: Bosque Encantado");
        System.out.println(color1 + "         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.print("                _-_                   _-_                  _-_\n" + //
                "             /~~   ~~\\             /~~   ~~\\            /~~   ~~\\ \n" + //
                "          /~~         ~~\\.      /~~         ~~\\.     /~~         ~~\\\n" + //
                "         {               }.    {               }.   {               }\n" + //
                "          \\  _-     -_  /       \\  _-     -_  /      \\  _-     -_  /\n" + //
                "            ~  \\\\ //  ~.          ~  \\\\ //  ~.         ~  \\\\ //  ~\n" + //
                "         _- -   | | _- _.      _- -   | | _- _.     _- -   | | _- _ \n" + //
                "           _ -  | |   -_.        _ -  | |   -_.       _ -  | |   -_\n" + //
                "               // \\\\                 // \\\\                // \\\\ ");
        System.out.println();
        System.out.println("         - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
    }
}
