package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService (CharacterRepository character_repository) {
        this.characterRepository = character_repository;
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    public void updateOne(Character character) {
        if (characterRepository.existsById(character.getId())) {
            characterRepository.save(character);
        } else {
            throw new IllegalArgumentException("character identifier" + character.getId() + "doesn't exist");
        }
    }
}