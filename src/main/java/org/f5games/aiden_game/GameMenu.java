package org.f5games.aiden_game;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.f5games.aiden_game.assets.ascii.Forest;
import org.f5games.aiden_game.assets.ascii.GameOver;
import org.f5games.aiden_game.assets.ascii.Skeletons;
import org.f5games.aiden_game.assets.ascii.Title;
import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.services.BackpackService;
import org.f5games.aiden_game.services.CharacterService;
import org.f5games.aiden_game.services.GameObjectService;
import org.springframework.stereotype.Component;

@Component
public class GameMenu {
    private int turnsUntilPowerAttackAvailable;
    private int turnsUntilShieldActive;
    private boolean isShieldActive = false;

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

    private final CharacterService characterService;
    private final BackpackService backpackService;
    private final GameObjectService objectService;

    // @Autowired
    public GameMenu(CharacterService characterService, BackpackService backpackService,
            GameObjectService objectService) {
        this.characterService = characterService;
        this.backpackService = backpackService;
        this.objectService = objectService;
    }

    public void start() {
        boolean startGame = false;
        Scanner scanner = new Scanner(System.in);

        Title.main(null);
        System.out.println(color10 + "           Bienvenido a: La Noche De Los Espiritus!            \n" + reset);
        System.out.println("""
                En un pequeño y remoto pueblo rodeado de densos bosques, cada año, en la noche de
                Halloween, las barreras entre el mundo de los vivos y los muertos se debilitan.

                Esta noche, el malvado hechicero Mortis ha decidido desatar a las criaturas más
                temidas de la oscuridad: esqueletos, fantasmas y vampiros. Su objetivo es apoderarse
                del alma del pueblo y sumergirlo en un eterno estado de terror.

                El pueblo, que en su día era alegre, se ha convertido en un lugar de miedo y
                desesperación. Sin embargo, en medio de la oscuridad, surge un héroe: ¡¡ Aiden !!

                Aiden es un joven valiente con un espíritu indomable. Con un legado de guerreros
                ancestrales, deberá enfrentarse a las criaturas de Mortis y restaurar la paz antes
                de que el reloj marque la medianoche........ \n
                """);

        do {
            System.out
                    .println(color3 + "- - - - - - - - - - - - " + reset + " INICIO:  ELIGE UNA OPCION " + color3
                            + " - - - - - - - - - - - -");
            System.out.println(color3 + "- -" + color7 + "              1. Empezar a Jugar   " + color4
                    + "   2. Salir del juego             " + color3 + "- -");
            System.out
                    .println(color3 + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - " + //
                            "" + reset);

            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    // Obtenemos los personajes desde el servicio
                    Character aiden = characterService.getCharacterById(1L);
                    Character skeleton = characterService.getCharacterById(2L);
                    List<Backpack> backpack = backpackService.getAll();
                    List<GameObject> object = objectService.getAll();

                    startGame = true; // Para salir del bucle

                    firstFight(aiden, skeleton, backpack, object);
                    break;

                case 2:
                    System.out.println(color4 + "Has salido del juego. ¡Hasta pronto!" + reset);
                    startGame = true; // Para salir del bucle
                    break;

                default:
                    System.out.println("Solo las opciones 1 y 2 están disponibles.. Intentelo de nuevo\n");
                    break;
            }
        } while (!startGame);

        scanner.close(); // Cerrar scanner al final del método
    }

    private void firstFight(Character aiden, Character skeleton, List<Backpack> backpack, List<GameObject> object) {
        Scanner scanner = new Scanner(System.in);
        int numEsqueletos = 3;
        int countSkeleton = 1;
        boolean turn = false; // Inicia con el turno del esqueleto | Aiden -> True

        Forest.main(null);
        System.out.println();
        System.out.println("Algo se mueve en la oscuridad de la noche...");
        System.out.println("Se escuchan sonidos emitidos por seres extraños...");
        Skeletons.main(null);
        System.out.println("\n \n               ¡Combate iniciado!");
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
                    System.out.println(color5 + "Es tu turno. Elige una acción:" + reset);
                    System.out.println("1. Atacar | 2. Ataque Potente | 3. Usar Objeto | 4. Escudo");
                    int action = scanner.nextInt();

                    switch (action) {
                        case 1:
                            useAttack(aiden, skeleton);
                            validAction = true;
                            break;
                        case 2:
                            boolean resultPowerfullAttack = powerfullAttack(aiden, skeleton);

                            if (resultPowerfullAttack) {
                                validAction = true;
                            } else {
                                System.out.println("Hay que esperar 3 turnos para volver a utilizarlo...");
                            }

                            break;
                        case 3:
                            boolean resultBackpack = displayBackpack(backpack);

                            if (resultBackpack) {
                                validAction = true;
                            }

                            break;
                        case 4:
                            if (useShield()) {
                                validAction = true;
                            }

                            break;
                        default:
                            System.out.println(color10 + "    ¡ Elige una opción válida ! \n    " + reset);
                    }
                }
            } else {
                // Turno del esqueleto
                System.out.println(color4 + "Es el turno del Esqueleto! | Habiliad: RAPIDEZ | Nº: Esqueleto: " + reset
                        + countSkeleton);
                monsterAttack(aiden, skeleton);
            }

            // Alternar el turno
            turn = !turn;

            // Verificar si el esqueleto fue derrotado
            if (skeleton.getHealth() <= 0 && numEsqueletos > 1) {
                numEsqueletos--;
                countSkeleton++;

                skeleton.setHealth(30); // Restablecer salud del próximo esqueleto
                System.out.println(color10 + "Aiden ha derrotado a un esqueleto!!" + reset);

                GameObject objectToAdd = findObject(object, backpack);
            } else if (skeleton.getHealth() <= 0 && numEsqueletos == 1) {
                numEsqueletos--;

                System.out.println(color10 + "       Has derrotado al último esqueleto!       " + reset);
            }
        }

        // Resultado final del combate
        if (aiden.getHealth() <= 0) {
            System.out.println();
            System.out.println();
            System.out.println("Aiden ha sido derrotado!");
            GameOver.main(null);
        } else {
            System.out.println();
            System.out.println(color5 + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("- -         VICTORIA: Aiden ha derrotado a todos los esqueletos         - -");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
            System.out.println();
            System.out.println();
        }

        scanner.close();
    }

    private void displayStatus(Character aiden, Character monster) {
        System.out.println();
        System.out.println(color2 + "------- ESTADO DEL COMBATE -------");
        System.out.printf("Aiden - Salud: %d, Fuerza: %d\n", aiden.getHealth(), aiden.getStrength());
        System.out.printf("%s - Salud: %d, Fuerza: %d\n", monster.getName(), monster.getHealth(),
                monster.getStrength());
        System.out.println("--------·-------------------------" + reset);
        System.out.println();
    }

    private void useAttack(Character attacker, Character target) {
        System.out.println();
        System.out.printf("%s ataca a %s\n", attacker.getName(), target.getName());
        target.setHealth(target.getHealth() - attacker.getStrength());
    }

    private boolean powerfullAttack(Character attacker, Character target) {
        if (turnsUntilPowerAttackAvailable > 0) {
            System.out.println("Hay que esperar 3 turnos para volver a utilizarlo...");
            turnsUntilPowerAttackAvailable--;
            return false;
        }

        turnsUntilPowerAttackAvailable = 3;

        System.out.println();
        System.out.printf("%s usa ataque poderoso contra %s\n", attacker.getName(), target.getName());
        target.setHealth(target.getHealth() - (attacker.getStrength() + 10));

        return true;
    }

    private boolean displayBackpack(List<Backpack> backpack) {
        if (backpack.isEmpty()) {
            System.out.println();
            System.out.println("La mochila está vacía");
            System.out.println("Introduce otra acción a ejecutar");
            System.out.println();
            return false;
        }

        return true;
    }

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

    public GameObject findObject(List<GameObject> objects, List<Backpack> backpack) {
        // Filtra los objetos no especiales (special == false)
        List<GameObject> nonSpecialObjects = objects.stream()
                .filter(obj -> !obj.getSpecial()) // Accedemos al atributo special
                .collect(Collectors.toList());

        // Selecciona un objeto aleatorio de los no especiales y retorna el objeto
        if (!nonSpecialObjects.isEmpty()) {
            Random random = new Random();

            GameObject objectFinded = nonSpecialObjects.get(random.nextInt(nonSpecialObjects.size()));
            System.out.println("Enhorabuena! El Esqueleto te ha dropeado: " + objectFinded.getName());

            return objectFinded;
        }

        return null; // Retorna null si no hay objetos no especiales
    }

    // public void addObject(GameObject objectToAdd, Backpack backpack) {
    // }
}
