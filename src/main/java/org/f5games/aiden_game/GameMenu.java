package org.f5games.aiden_game;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.f5games.aiden_game.assets.ascii.Castle;
import org.f5games.aiden_game.assets.ascii.Forest;
import org.f5games.aiden_game.assets.ascii.GameOver;
import org.f5games.aiden_game.assets.ascii.Ghost;
import org.f5games.aiden_game.assets.ascii.Graveyard;
import org.f5games.aiden_game.assets.ascii.Skeletons;
import org.f5games.aiden_game.assets.ascii.Title;
import org.f5games.aiden_game.assets.ascii.Vampire;
import org.f5games.aiden_game.controllers.BackpackController;
import org.f5games.aiden_game.controllers.CharacterController;
import org.f5games.aiden_game.controllers.GameObjectController;
import org.f5games.aiden_game.models.Aiden;
import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.models.Skeleton;
import org.springframework.http.ResponseEntity;
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

    Scanner scanner = new Scanner(System.in);

    private final CharacterController characterController;
    private final BackpackController backpackController;
    private final GameObjectController objectController;

    // @Autowired
    public GameMenu(CharacterController characterController,
            BackpackController backpackController, GameObjectController objectController) {
        this.characterController = characterController;
        this.backpackController = backpackController;
        this.objectController = objectController;
    }

    public void start() {
        boolean startGame = false;

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
                    List<Character> personajes = characterController.getCharacter();
                    Aiden aiden = (Aiden) personajes.get(0);
                    Skeleton skeleton = (Skeleton) personajes.get(1);
                    org.f5games.aiden_game.models.Ghost ghost = (org.f5games.aiden_game.models.Ghost) personajes
                            .get(2);
                    org.f5games.aiden_game.models.Vampire vampire = (org.f5games.aiden_game.models.Vampire) personajes
                            .get(3);
                    // Mortis mortis = (Mortis) personajes.get(4);

                    ResponseEntity<List<Backpack>> backpack = backpackController.retrieveObjects();
                    List<GameObject> object = objectController.getObjects();

                    startGame = true; // Para salir del bucle

                    firstFight(aiden, skeleton, backpack, object);
                    secondFight(aiden, ghost, backpack, object);
                    thirdFigth(aiden, vampire, backpack, object);

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
    }

    private void firstFight(Aiden aiden, Skeleton skeleton,
            ResponseEntity<List<Backpack>> backpack, List<GameObject> object) {
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
                                System.out.println();
                                System.out.println("Hay que esperar 3 turnos para volver a utilizarlo...");
                                System.out.println();
                            }

                            break;
                        case 3:
                            boolean resultBackpack = displayBackpack(aiden);

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

                    turnsUntilPowerAttackAvailable--;
                }
            } else {
                // Turno del esqueleto
                System.out.println(color4 + "¡Es el turno del Esqueleto! | Habiliad: RAPIDEZ | Nº: Esqueleto: " + reset
                        + countSkeleton);

                monsterAttack(skeleton, aiden);
            }

            // Alternar el turno
            turn = !turn;

            // Verificar si el esqueleto fue derrotado
            if (skeleton.getHealth() <= 0 && numEsqueletos > 1) {
                numEsqueletos--;
                countSkeleton++;

                skeleton.setHealth(30); // Restablecer salud del próximo esqueleto
                characterController.updateCharacter(skeleton);

                System.out.println(color10 + "¡¡Aiden ha derrotado a un esqueleto!!" + reset);

                findNoSpecialObject(object, backpack, skeleton);
            } else if (skeleton.getHealth() <= 0 && numEsqueletos == 1) {
                numEsqueletos--;

                System.out.println(color10 + "       ¡Has derrotado al último esqueleto!       " + reset);
            }
        }

        // Resultado final del combate
        if (aiden.getHealth() <= 0) {
            System.out.println();
            System.out.println();
            System.out.println("Aiden ha sido derrotado...");
            GameOver.main(null);
        } else {
            System.out.println();
            System.out.println(color5 + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("- -         VICTORIA: Aiden ha derrotado a todos los esqueletos         - -");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
            System.out.println();

            aiden.setStrength(aiden.getStrength() + 10);
            characterController.updateCharacter(aiden);

            findSpecialObject(object, backpack, skeleton);
        }
    }

    private void secondFight(Aiden aiden, org.f5games.aiden_game.models.Ghost ghost,
            ResponseEntity<List<Backpack>> backpack, List<GameObject> object) {
        boolean turn = true;

        Graveyard.main(null);
        System.out.println();
        System.out.println("La niebla, las tumbas y los aullidos te hacen tener escalofríos...");
        System.out.println("Bu... bu... BUUUUUUU!");
        System.out.println("¡Un ruido a tus espaldas te sobresalta!");
        Ghost.main(null);
        System.out.println("\n \n               ¡Combate iniciado!");
        System.out.println();

        while (aiden.getHealth() > 0 && ghost.getHealth() > 0) {
            displayStatus(aiden, ghost);

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
                            if (ghostInvisivility()) {
                                System.out.println();
                                System.out.println("¡El fantasma se ha vuelvo invisible y no puedes atacarle!");
                            } else {
                                useAttack(aiden, ghost);
                            }

                            validAction = true;
                            break;
                        case 2:
                            boolean resultPowerfullAttack = powerfullAttack(aiden, ghost);

                            if (resultPowerfullAttack) {
                                validAction = true;
                            }

                            break;
                        case 3:
                            boolean resultBackpack = displayBackpack(aiden);

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

                    turnsUntilPowerAttackAvailable--;
                }
            } else {
                // Turno del fantasma
                System.out.println(color4 + "¡Es el turno del Fantasma! | Habiliad: INVISIBILIDAD");
                monsterAttack(ghost, aiden);
            }

            // Alternar el turno
            turn = !turn;

            // Verificar si el esqueleto fue derrotado
            if (ghost.getHealth() <= 0) {
                System.out.println(color10 + "¡¡Aiden ha derrotado al fantasma!!" + reset);

                GameObject objectToAdd = findSpecialObject(object, backpack, ghost);
            }
        }

        // Resultado final del combate
        if (aiden.getHealth() <= 0) {
            System.out.println();
            System.out.println();
            System.out.println("Aiden ha sido derrotado...");
            GameOver.main(null);
        } else {
            System.out.println();
            System.out.println(color5 + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("- -               VICTORIA: Aiden ha derrotado al fantasma                   - -");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
            System.out.println();
            System.out.println();

            aiden.setStrength(aiden.getStrength() + 15);
            characterController.updateCharacter(aiden);

            findSpecialObject(object, backpack, ghost);
        }
    }

    private void thirdFigth(Aiden aiden, org.f5games.aiden_game.models.Vampire vampire,
            ResponseEntity<List<Backpack>> backpack, List<GameObject> object) {
        boolean turn = true;

        Castle.main(null);
        System.out.println();
        System.out.println(
                "Tras superar tantas adversidades, finalmente Aiden entra en el Castillo para derrotar al Monstruo Mortis");
        System.out.println(
                "Las paredes de piedra, las gárgolas de piedra, la gran alfombra roja y los candelabros le dejan sin aliento...");
        System.out.println("De pronto... ¡un aleteo muy fuerte le sorprende de golpe!");
        Vampire.main(null);
        System.out.println("\n \n               ¡Combate iniciado!");
        System.out.println();

        while (aiden.getHealth() > 0 && vampire.getHealth() > 0) {
            displayStatus(aiden, vampire);

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
                            useAttack(aiden, vampire);

                            validAction = true;
                            break;
                        case 2:
                            boolean resultPowerfullAttack = powerfullAttack(aiden, vampire);

                            if (resultPowerfullAttack) {
                                validAction = true;
                            }

                            break;
                        case 3:
                            boolean resultBackpack = displayBackpack(aiden);

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

                    turnsUntilPowerAttackAvailable--;
                }
            } else {
                // Turno del vampiro
                System.out.println(color4 + "¡Es el turno del Vampiro! | Habiliad: INVISIBILIDAD");
                monsterAttack(vampire, aiden);
                recoverHealth(vampire);
            }

            // Alternar el turno
            turn = !turn;

            // Verificar si el esqueleto fue derrotado
            if (vampire.getHealth() <= 0) {
                System.out.println(color10 + "¡¡Aiden ha derrotado al Vampiro!!" + reset);

                GameObject objectToAdd = findSpecialObject(object, backpack, vampire);
            }
        }

        // Resultado final del combate
        if (aiden.getHealth() <= 0) {
            System.out.println();
            System.out.println();
            System.out.println("Aiden ha sido derrotado...");
            GameOver.main(null);
        } else {
            System.out.println();
            System.out.println(color5 + "- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            System.out.println("- -               VICTORIA: Aiden ha derrotado al vampiro                   - -");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -" + reset);
            System.out.println();
            System.out.println();

            aiden.setStrength(aiden.getStrength() + 20);
            aiden.setHealth(aiden.getHealth() + (aiden.getHealth() / 2));
            characterController.updateCharacter(aiden);

            findSpecialObject(object, backpack, vampire);
        }
    }

    // SHOW STATUS
    private void displayStatus(Character aiden, Character monster) {
        System.out.println();
        System.out.println(color2 + "------- ESTADO DEL COMBATE -------");
        System.out.printf("Aiden - Salud: %d, Fuerza: %d\n", aiden.getHealth(), aiden.getStrength());
        System.out.printf("%s - Salud: %d, Fuerza: %d\n", monster.getName(), monster.getHealth(),
                monster.getStrength());
        System.out.println("--------·-------------------------" + reset);
        System.out.println();
    }

    // NORMAL ATTACK
    private void useAttack(Character attacker, Character target) {
        System.out.println();
        System.out.printf("%s ataca a %s\n", attacker.getName(), target.getName());

        target.setHealth(target.getHealth() - attacker.getStrength());
        characterController.updateCharacter(target);
    }

    // POWERFULL ATTACK
    private boolean powerfullAttack(Character attacker, Character target) {
        if (turnsUntilPowerAttackAvailable > 0) {
            return false;
        }

        turnsUntilPowerAttackAvailable = 3;

        System.out.println();
        System.out.printf("%s usa ataque poderoso contra %s\n", attacker.getName(), target.getName());

        target.setHealth(target.getHealth() - (attacker.getStrength() + 10));
        characterController.updateCharacter(target);

        return true;
    }

    // MONSTER ATTACK
    private void monsterAttack(Character monster, Character aiden) {
        int damage = monster.getStrength();

        if (isShieldActive) {
            damage -= 5;
            System.out.printf("%s ataca a Aiden, pero el escudo reduce el daño a %d!\n", monster.getName(), damage);

            aiden.setHealth(aiden.getHealth() - (monster.getStrength() - 5));
            characterController.updateCharacter(aiden);
        } else {
            System.out.printf("%s ataca a Aiden!\n", monster.getName());

            aiden.setHealth(aiden.getHealth() - monster.getStrength());
            characterController.updateCharacter(aiden);
        }

        if (isShieldActive) {
            turnsUntilShieldActive--;

            if (turnsUntilShieldActive <= 0) {
                isShieldActive = false;
                System.out.println("El escudo de Aiden ha expirado.");
            }
        }
    }

    // USE SHIELD
    private boolean useShield() {
        if (isShieldActive) {
            System.out.println();
            System.out.println("El escudo ya está activo. Espera a que se desactive.");
            System.out.println();

            return false;
        }

        isShieldActive = true;
        turnsUntilShieldActive = 2;

        System.out.println();
        System.out.println("Aiden ha usado el Escudo!!");

        return true;
    }

    // BACKPACK - SHOW LIST
    private boolean displayBackpack(Character aiden) {
        ResponseEntity<List<Backpack>> backpack = backpackController.retrieveObjects();
        List<Backpack> backpackContents = backpack.getBody();

        if (backpackContents.isEmpty()) {
            System.out.println();
            System.out.println("La mochila está vacía");
            System.out.println("Introduce otra acción a ejecutar");
            System.out.println();
            return false;
        }

        System.out.println();
        System.out.println("\nObjetos en la mochila:");
        for (int i = 0; i < backpackContents.size(); i++) {
            Backpack item = backpackContents.get(i);
            GameObject gameObject = item.getGameObject(); // Obtén el GameObject asociado

            if (gameObject != null) {
                System.out.printf("%d. %s\n", i + 1, gameObject.getName());
            } else {
                System.out.printf("%d. Objeto sin nombre\n", i + 1);
            }
        }

        System.out.println();
        System.out.println("Selecciona un objeto para usar (1-" + backpackContents.size() + "):");

        try {
            int choiceObject = scanner.nextInt();

            // Validación de rango de índice
            if (choiceObject < 1 || choiceObject > backpackContents.size()) {
                System.out.println(
                        "Selección no válida. Por favor, elige un número entre 1 y " + backpackContents.size());
            }

            Backpack chosenObject = backpackContents.get(choiceObject - 1);

            System.out.println();
            System.out.println("Elija una opción para ese objeto:");
            System.out.println("1- Usar | 2- Tirar | 3- Volver al menú");

            int choiseOption = scanner.nextInt();

            switch (choiseOption) {
                case 1:
                    useObject(chosenObject, aiden); // Llama al método `useObject` con la instancia `Backpack`
                    break;
                case 2:
                    backpackController.deleteObject(chosenObject.getId());

                    System.out.println();
                    System.out.println("¡Has tirado el objeto!");

                    break;
                case 3:
                    return false;
                default:
                    System.out.println("Opción no válida");
                    return false;
            }

        } catch (InputMismatchException e) {
            System.out.println("Solo puedes ingresar números :)");
            scanner.next();
        }

        return true;
    }

    // BACKPACK - FIND OBJECT
    public GameObject findNoSpecialObject(List<GameObject> objects, ResponseEntity<List<Backpack>> backpack,
            Character monster) {
        // Filtra los objetos no especiales (special == false)
        List<Backpack> backpackContents = backpack.getBody();
        List<GameObject> nonSpecialObjects = objects.stream()
                .filter(obj -> !obj.getSpecial())
                .collect(Collectors.toList());

        // Selecciona un objeto aleatorio de los no especiales
        if (!nonSpecialObjects.isEmpty()) {
            Random random = new Random();

            GameObject objectDroped = nonSpecialObjects.get(random.nextInt(nonSpecialObjects.size()));

            System.out.println();
            System.out.println("¡Enhorabuena! El " + monster.getName() + " te ha dropeado: " + objectDroped.getName());
            System.out.println();

            if (backpackContents.size() == 3) {
                System.out.println("La mochila está llena... ");
                System.out.println("OPCIONES: 1- Reemplazar objeto | 2- No recoger objeto");

                int option = scanner.nextInt();

                if (option == 1) {
                    System.out.println();
                    System.out.println("\nObjetos en la mochila:");

                    for (int i = 0; i < backpackContents.size(); i++) {
                        Backpack item = backpackContents.get(i);
                        GameObject gameObject = item.getGameObject(); // Obtén el GameObject asociado

                        if (gameObject != null) {
                            System.out.printf("%d. %s\n", i + 1, gameObject.getName());
                        } else {
                            System.out.printf("%d. Objeto sin nombre\n", i + 1);
                        }
                    }

                    System.out.println();
                    System.out.println(
                            "Selecciona el objeto que quieres reemplazar (1-" + backpackContents.size() + "):");

                    int replaceOption = scanner.nextInt();
                    if (replaceOption >= 1 && replaceOption <= backpackContents.size()) {
                        // Obtener el objeto a reemplazar según el índice
                        Backpack selectedBackpackItem = backpackContents.get(replaceOption - 1);

                        backpackController.updateObject(selectedBackpackItem.getId(), objectDroped.getId());

                        System.out.println();
                        System.out.println("¡Has reemplazado el objeto en la mochila!");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("UPS! Has perdido el objeto...");
                        System.out.println();
                        return null;
                    }
                } else {
                    System.out.println();
                    System.out.println("Ignoras el objeto y decides dejarlo en el suelo");
                    System.out.println();
                    return null;
                }
            } else {
                backpackController.addObject(objectDroped.getId());
            }

            return objectDroped;
        }

        return null; // Retorna null si no hay objetos no especiales
    }

    // BACKPACK - FIND SPECIAL OBJECT
    public GameObject findSpecialObject(List<GameObject> objects, ResponseEntity<List<Backpack>> backpack,
            Character monster) {
        // Filtra los objetos especiales (special == true)
        List<Backpack> backpackContents = backpack.getBody();
        List<GameObject> specialObjects = objects.stream()
                .filter(obj -> obj.getSpecial())
                .collect(Collectors.toList());

        // Selecciona un objeto aleatorio de los no especiales
        if (!specialObjects.isEmpty()) {
            Random random = new Random();

            GameObject objectDroped = specialObjects.get(random.nextInt(specialObjects.size()));

            System.out.println();
            System.out.println("Enhorabuena! El " + monster.getName() + " te ha dropeado: " + objectDroped.getName());
            System.out.println();

            if (backpackContents.size() >= 3) {
                System.out.println("La mochila está llena... ");
                System.out.println("OPCIONES: 1- Reemplazar objeto | 2- No recoger objeto");

                int option = scanner.nextInt();

                if (option == 1) {
                    System.out.println();
                    System.out.println("\nObjetos en la mochila:");

                    for (int i = 0; i < backpackContents.size(); i++) {
                        Backpack item = backpackContents.get(i);
                        GameObject gameObject = item.getGameObject(); // Obtén el GameObject asociado

                        if (gameObject != null) {
                            System.out.printf("%d. %s\n", i + 1, gameObject.getName());
                        } else {
                            System.out.printf("%d. Objeto sin nombre\n", i + 1);
                        }
                    }

                    System.out.println();
                    System.out.println(
                            "Selecciona el objeto que quieres reemplazar (1-" + backpackContents.size() + "):");

                    int replaceOption = scanner.nextInt();
                    if (replaceOption >= 1 && replaceOption <= backpackContents.size()) {
                        // Obtener el objeto a reemplazar según el índice
                        Backpack selectedBackpackItem = backpackContents.get(replaceOption - 1);

                        backpackController.updateObject(selectedBackpackItem.getId(), objectDroped.getId());

                        System.out.println();
                        System.out.println("¡Has reemplazado el objeto en la mochila!");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("UPS! Has perdido el objeto...");
                        System.out.println();
                        return null;
                    }
                } else {
                    System.out.println();
                    System.out.println("Ignoras el objeto y decides dejarlo en el suelo");
                    System.out.println();
                    return null;
                }
            } else {
                backpackController.addObject(objectDroped.getId());
            }

            return objectDroped;
        }

        return null; // Retorna null si no hay objetos no especiales
    }

    // BACKPACK - USE OBJECT
    public void useObject(Backpack chosenObject, Character aiden) {
        GameObject gameObject = chosenObject.getGameObject();

        if (gameObject == null) {
            System.out.println("el objeto seleccionado no es valido");
            return;
        }

        System.out.println();
        System.out.println("Vas a usar el objeto: " + gameObject.getName());
        System.out.println("Descripción: " + gameObject.getDescription());
        System.out.println();

        switch (gameObject.getName()) {
            case "Espada encantada":
                aiden.setStrength(aiden.getStrength() + 15);
                characterController.updateCharacter(aiden);
                break;
            case "Poción de vida":
                aiden.setHealth(100);
                characterController.updateCharacter(aiden);
                break;
            case "Amuleto mágico":
                System.out.println("Esta habilidad esta en desarrollo..");
                break;
            case "Capa de invisibilidad":
                System.out.println("Esta habilidad esta en desarrollo..");
                break;
            case "Gafas mágicas":
                System.out.println("Esta habilidad esta en desarrollo..");
                break;
            case "Collar de ajos":
                System.out.println("Esta habilidad esta en desarrollo..");
                break;
            default:
                System.out.println("Esta habilidad esta en desarrollo..");
                break;
        }

        backpackController.deleteObject(gameObject.getId());
    }

    // GHOST - INVISIVILITY
    private boolean ghostInvisivility() {
        return Math.random() < 0.5;
    }

    // VAMPIRE - RECOVER HEALTH
    private boolean recoverHealth(Character recovered) {
        boolean result = Math.random() < 0.5;

        if (result) {
            System.out.println();
            System.out.println("El vampiro te ha mordido y se recupera " + recovered.getStrength() + " puntos de vida");
            System.out.println();

            recovered.setHealth(recovered.getHealth() + recovered.getStrength());
            characterController.updateCharacter(recovered);
        }

        System.out.println();
        System.out.println("El vampiro no ha activado su habilidad y no te roba vida...");
        System.out.println();

        return true;
    }
}
