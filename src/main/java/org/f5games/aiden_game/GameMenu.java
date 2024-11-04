package org.f5games.aiden_game;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.f5games.aiden_game.assets.ascii.Forest;
import org.f5games.aiden_game.assets.ascii.GameOver;
import org.f5games.aiden_game.assets.ascii.Skeletons;
import org.f5games.aiden_game.assets.ascii.Title;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.services.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameMenu {
    private int turnsUntilPowerAttackAvailable;
    private int turnsUntilShieldActive;
    private boolean isShieldActive = false;

    private final CharacterService characterService;

    @Autowired
    public GameMenu(CharacterService characterService) {
        this.characterService = characterService;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        Title.main(null);
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

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("- -          INICIO: 1. Empezar a Jugar || 2. Salir del juego           - -");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        int choice = scanner.nextInt();

        if (choice == 1) {
            // Obtenemos los personajes desde el servicio
            Character aiden = characterService.getCharacterById(1L);
            Character skeleton = characterService.getCharacterById(2L);
            // Character ghost = characterService.getCharacterById(3L);
            // Character vampire = characterService.getCharacterById(4L);
            // Character mortis = characterService.getCharacterById(5L);
            firstFight(aiden, skeleton);
            // secondFight(aiden, ghost);
        } else {
            System.out.println("Has salido del juego. ¡Hasta pronto!");
        }

        scanner.close();
    }

    private void firstFight(Character aiden, Character skeleton) {
        Scanner scanner = new Scanner(System.in);
        int numEsqueletos = 3;
        int countSkeleton = 1;
        boolean turn = false; // Inicia con el turno del esqueleto | Aiden -> True

        Forest.main(null);
        System.out.println();
        System.out.println("Algo se mueve en la oscuridad de la noche...");
        System.out.println("Se escuchan sonidos emitidos por seres extraños...");
        Skeletons.main(null);
        System.out.println("¡Combate iniciado!");
        System.out.println();

        while (aiden.getHealth() > 0 && numEsqueletos > 0) {
            displayStatus(aiden, skeleton);

            try {
                TimeUnit.SECONDS.sleep(3); // Pausa de 3 segundos entre turnos
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupción inesperada en el combate");
            }

            if (turn) {
                boolean validAction = false;

                while (!validAction) {
                    // Turno de Aiden
                    System.out.println("Es tu turno. Elige una acción:");
                    System.out.println("1. Atacar | 2. Ataque Potente | 3. Usar Objeto | 4. Escudo");
                    int action = scanner.nextInt();

                    switch (action) {
                        case 1:
                            useAttack(aiden, skeleton);
                            validAction = true;
                            break;
                        case 2:
                            boolean result = powerfullAttack(aiden, skeleton);

                            if (result) {
                                validAction = true;
                            } else {
                                System.out.println("Hay que esperar 3 turnos para volver a utilizarlo...");
                            }

                            break;
                        case 3:
                            System.out.println("Usar Objeto - Esta funcionalidad está en desarrollo.");
                            validAction = true;
                            break;
                        case 4:
                            useShield();
                            validAction = true;
                            break;
                        default:
                            System.out.println("Elige una opción válida...");
                    }
                }
            } else {
                // Turno del esqueleto
                System.out.println("Es el turno del Esqueleto! | Habiliad: RAPIDEZ | Nº: Esqueleto: " + countSkeleton);
                monsterAttack(aiden, skeleton);
            }

            // Alternar el turno
            turn = !turn;

            // Verificar si el esqueleto fue derrotado
            if (skeleton.getHealth() <= 0 && numEsqueletos > 1) {
                numEsqueletos--;
                countSkeleton++;

                skeleton.setHealth(30); // Restablecer salud del próximo esqueleto
                System.out.println("Aiden ha derrotado a un esqueleto!!");
            } else if (skeleton.getHealth() <= 0 && numEsqueletos == 1) {
                numEsqueletos--;

                System.out.println("Has derrotado al último esqueleto!");
            }
        }

        // Resultado final del combate
        if (aiden.getHealth() <= 0) {
            System.out.println("Aiden ha sido derrotado!");
            GameOver.main(null);
        } else {
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("- -         VICTORIA: Aiden ha derrotado a todos los esqueletos         - -");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }

        scanner.close();
    }

    private void displayStatus(Character aiden, Character monster) {
        System.out.println();
        System.out.println("------- ESTADO DEL COMBATE -------");
        System.out.printf("Aiden - Salud: %d, Fuerza: %d\n", aiden.getHealth(), aiden.getStrength());
        System.out.printf("%s - Salud: %d, Fuerza: %d\n", monster.getName(), monster.getHealth(),
                monster.getStrength());
        System.out.println("----------------------------------");
        System.out.println();
    }

    private void useAttack(Character attacker, Character target) {
        System.out.println();
        System.out.printf("%s ataca a %s\n", attacker.getName(), target.getName());
        target.setHealth(target.getHealth() - attacker.getStrength());
    }

    private boolean powerfullAttack(Character attacker, Character target) {
        if (turnsUntilPowerAttackAvailable > 0) {
            turnsUntilPowerAttackAvailable--;
            return false;
        }

        turnsUntilPowerAttackAvailable = 3;

        System.out.println();
        System.out.printf("%s usa ataque poderoso contra %s\n", attacker.getName(), target.getName());
        target.setHealth(target.getHealth() - (attacker.getStrength() + 10));

        return true;
    }

    // private void displayBackpack(Backpack backpack) {
    // System.out.println(backpack.getGameObject());
    // }

    private boolean useShield() {
        if (isShieldActive) {
            System.out.println("El escudo ya está activo. Espera a que se desactive.");
            return false;
        }

        isShieldActive = true;
        turnsUntilShieldActive = 2;

        System.out.println();
        System.out.println("Aiden ha usado el Escudo!!");

        return true;
    }

    private void monsterAttack(Character aiden, Character monster) {
        int damage = monster.getStrength();

        if (isShieldActive) {
            damage -= 5;
            System.out.printf("%s ataca a Aiden, pero el escudo reduce el daño a %d!\n", monster.getName(), damage);
        } else {
            System.out.printf("%s ataca a Aiden!\n", monster.getName());
        }

        aiden.setHealth(aiden.getHealth() - Math.max(damage, 0));

        if (isShieldActive) {
            turnsUntilShieldActive--;

            if (turnsUntilShieldActive <= 0) {
                isShieldActive = false;
                System.out.println("El escudo de Aiden ha expirado.");
            }
        }
    }
}
