package org.f5games.aiden_game.assets.text;

import java.util.Scanner;

public class MenuAiden {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar el menú con atributos actualizados
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("- -             Salud: 100PT   Fuerza: 15PT    Puntos: 0PT              - -");
        System.out.println("- -                                                                     - -");
        System.out.println("- - MENU: 1. Atacar || 2. Ataque Potente || 3. Ver Mochila || 4. Escudo - -");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

        String option = scanner.next();

        switch (option) {
            case "1":
                System.out.println("Has atacado!");
                break;
            case "2":
                System.out.println("Has realizado un ataque potente!");
                break;
            case "3":
                System.out.println("Mostrando mochila...");
                break;
            case "4":
                System.out.println("Has activado el escudo!");
                break;
            default:
                System.out.println("Elige una opción del menú");
                break;
        }

        scanner.close();
    }
}
