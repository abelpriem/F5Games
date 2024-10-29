package org.f5games.aiden_game.services;

import org.f5games.aiden_game.assets.ascii.Skeletons;
import org.f5games.aiden_game.models.Character;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GameMenu {

    private final CharacterService characterService;

    @Autowired
    public GameMenu(CharacterService characterService) {
        this.characterService = characterService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido a: La Noche De Los Espiritus!");
        System.out.println("""
                En un pequeño y remoto pueblo rodeado de densos bosques, cada año, en la noche de
            Halloween, las barreras entre el mundo de los vivos y los muertos se debilitan. Esta
            noche, el malvado hechicero Mortis ha decidido desatar a las criaturas más temidas de
            la oscuridad: esqueletos, fantasmas y vampiros. Su objetivo es apoderarse del alma del
            pueblo y sumergirlo en un eterno estado de terror.
        
            El pueblo, que en su día era alegre, se ha convertido en un lugar de miedo y
            desesperación. Sin embargo, en medio de la oscuridad, surge un héroe: Aiden, un joven
            valiente con un espíritu indomable. Con un legado de guerreros ancestrales, Aiden debe
            enfrentarse a las criaturas de Mortis y restaurar la paz antes de que el reloj marque la
            medianoche........
                """);
            
        System.out.println("1. Iniciar juego");
        System.out.println("2. Salir");

        int choice = scanner.nextInt();
        if (choice == 1) {
            // Obtener personajes (Aiden y un esqueleto) desde el servicio
            Character aiden = characterService.getCharacterByName("Aiden");
            Character skeleton = characterService.getSkeleton("Esqueleto");
            fight(aiden, skeleton);
        } else {
            System.out.println("Has salido del juego.");
        }
        scanner.close();
    }

    private void fight(Character aiden, Character skeleton) {
        Scanner scanner = new Scanner(System.in);
        int numEsqueletos = 3;
        System.out.println("Algo se mueve en la oscuridad de la noche...");
        System.out.println("Se esuchan sonidos emitidos por seres extraños...");
        Skeletons.SkeletonsASCII();
        System.out.println("Combate iniciado!");

        while (aiden.getHealth() > 0 && numEsqueletos > 0) {
            displayStatus(aiden, skeleton);

            // Turno de Aiden
            System.out.println("Es tu turno. Elige una acción:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Objeto");
            int action = scanner.nextInt();

            if (action == 1) {
                attack(aiden, skeleton);
            } else if (action == 2) {
                // Aquí podrías añadir lógica para usar un objeto del Backpack
                System.out.println("Usar Objeto - Esta funcionalidad está en desarrollo.");
            }

            // Turno del monstruo si sigue vivo
            if (skeleton.getHealth() > 0) {
                monsterAttack(aiden, skeleton);
            } else if (skeleton.getHealth() <= 0) {
                System.out.println("Aiden ha derrotado un esqueleto!!");
                skeleton.setHealth(30);
                numEsqueletos --;
            }
        }

        if (aiden.getHealth() <= 0) {
            System.out.println("Aiden ha sido derrotado!");
        } else if (skeleton.getHealth() <= 0) {
            System.out.println("Has derrotado al monstruo!");
        }
        scanner.close();
    }

    private void displayStatus(Character aiden, Character monster) {
        System.out.println("------- ESTADO DEL COMBATE -------");
        System.out.printf("Aiden - Salud: %d, Fuerza: %d\n", aiden.getHealth(), aiden.getStrength());
        System.out.printf("%s - Salud: %d, Fuerza: %d\n", monster.getName(), monster.getHealth(), monster.getStrength());
        System.out.println("----------------------------------");
    }

    private void attack(Character attacker, Character target) {
        System.out.printf("%s ataca a %s\n", attacker.getName(), target.getName());
        target.setHealth(target.getHealth() - attacker.getStrength());
    }

    private void monsterAttack(Character aiden, Character monster) {
        System.out.printf("%s ataca a Aiden!\n", monster.getName());
        aiden.setHealth(aiden.getHealth() - monster.getStrength());
    }
}