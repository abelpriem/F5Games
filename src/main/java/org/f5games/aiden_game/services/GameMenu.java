package org.f5games.aiden_game.services;

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
        System.out.println("Bienvenido al combate de Aiden!");
        System.out.println("1. Iniciar combate");
        System.out.println("2. Salir");

        int choice = scanner.nextInt();
        if (choice == 1) {
            // Obtener personajes (Aiden y un monstruo aleatorio) desde el servicio
            Character aiden = characterService.getCharacterByName("Aiden");
            Character monster = characterService.getRandomMonster();
            fight(aiden, monster);
        } else {
            System.out.println("Has salido del juego.");
        }
    }

    private void fight(Character aiden, Character monster) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Combate iniciado!");

        while (aiden.getHealth() > 0 && monster.getHealth() > 0) {
            displayStatus(aiden, monster);

            // Turno de Aiden
            System.out.println("Es tu turno. Elige una acción:");
            System.out.println("1. Atacar");
            System.out.println("2. Usar Objeto");
            int action = scanner.nextInt();

            if (action == 1) {
                attack(aiden, monster);
            } else if (action == 2) {
                // Aquí podrías añadir lógica para usar un objeto del Backpack
                System.out.println("Usar Objeto - Esta funcionalidad está en desarrollo.");
            }

            // Turno del monstruo si sigue vivo
            if (monster.getHealth() > 0) {
                monsterAttack(aiden, monster);
            }
        }

        if (aiden.getHealth() <= 0) {
            System.out.println("Aiden ha sido derrotado!");
        } else if (monster.getHealth() <= 0) {
            System.out.println("Has derrotado al monstruo!");
        }
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