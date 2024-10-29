package org.f5games.aiden_game;

import java.util.Scanner;

import org.f5games.aiden_game.assets.ascii.Forest;
import org.f5games.aiden_game.assets.ascii.Skeletons;
import org.f5games.aiden_game.assets.ascii.Title;
import org.f5games.aiden_game.assets.text.Intro;
import org.f5games.aiden_game.assets.text.MenuAiden;
import org.f5games.aiden_game.assets.text.MenuVillan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AidenGameApplication {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Obtén el contexto de la aplicación
		ApplicationContext context = SpringApplication.run(AidenGameApplication.class, args);

		boolean firstLevelExit = false;
		String start;
		boolean startLoop = false;

		// INTRO
		Title.main(args);
		Intro.main(args);
		System.out.println();

		do {
			// MENÚ DE INICIO
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
			System.out.println("- - INICIO:      1. Empezar a Jugar || 2. Salir del juego               - -");
			System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");

			start = scanner.nextLine();

			switch (start) {
				case "1":
					firstLevelExit = true;
					startLoop = true;
					break;

				case "2":
					firstLevelExit = false;
					startLoop = true;
					break;

				default:
					System.out.println("Opción no válida. Inténtelo de nuevo");
					System.out.println();
					break;
			}
		} while (!startLoop);

		// LEVEL 1 - FOREST
		if (firstLevelExit) {
			Forest.main(args);
			System.out.println();
			Skeletons.main(args);
			System.out.println();

			// FIRST MENU - SKELETONS
			int skeletons = 3;
			int firstCount = 1;
			boolean turn = false;
			int turnPlayer = 1;

			do {
				if (turn) {
					System.out.println("Player: AIDEN | Nº: " + turnPlayer);
					MenuAiden.main(args);
				} else {
					System.out.println("Player: ESQUELETO | Nº: " + firstCount + " | Habilidad: RAPIDEZ");
					MenuVillan.main(args);
					firstCount++;
				}

				turn = !turn;

				if (firstCount == 3) {
					firstLevelExit = false;
				}

			} while (firstCount <= skeletons && !firstLevelExit);
		} else {
			System.out.println("Juego finalizado! Hasta la próxima!");
		}

		scanner.close();

		// Cerrar el contexto de la aplicación de Spring
		SpringApplication.exit(context, () -> 0);
	}
}
