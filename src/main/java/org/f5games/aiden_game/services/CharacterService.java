package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService (CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
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

    public Character getCharacterById(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un personaje con el ID: " + id));
    }
}
