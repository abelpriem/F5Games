package org.f5games.aiden_game.services;

import java.util.List;
import java.util.stream.Collectors;

import org.f5games.aiden_game.models.Aiden;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.models.Ghost;
import org.f5games.aiden_game.models.Skeleton;
import org.f5games.aiden_game.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService (CharacterRepository character_repository) {
        this.characterRepository = character_repository;
    }

    public Aiden findAiden(){
        return characterRepository.findAll().stream().filter(Aiden.class::isInstance).map(Aiden.class::cast).collect(Collectors.toList()).get(0);
    }
    public Skeleton findSkeleton(){
        return characterRepository.findAll().stream().filter(Skeleton.class::isInstance).map(Skeleton.class::cast).collect(Collectors.toList()).get(0);
    }
    public Ghost findGhost(){
        return characterRepository.findAll().stream().filter(Ghost.class::isInstance).map(Ghost.class::cast).collect(Collectors.toList()).get(0);
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    public Character updateOne(Character character) {
        if (characterRepository.existsById(character.getId())) {
            return characterRepository.save(character);
        } else {
            throw new IllegalArgumentException("character identifier" + character.getId() + "doesn't exist");
        }
    }
}