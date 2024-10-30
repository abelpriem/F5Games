package org.f5games.aiden_game;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.f5games.aiden_game.models.Skeleton;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.models.Ghost;
import org.f5games.aiden_game.models.Aiden;
import org.f5games.aiden_game.services.CharacterService;
import org.f5games.aiden_game.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AidenGameApplication implements CommandLineRunner{
	@Autowired
	CharacterService characterService;

	public static void main(String[] args) {
		SpringApplication.run(AidenGameApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Aiden aiden = characterService.findAiden();
		System.out.println(aiden);
		Skeleton s = characterService.findSkeleton();
		System.out.println(s);
		Ghost g = characterService.findGhost();
		System.out.println(g);
	}

}
