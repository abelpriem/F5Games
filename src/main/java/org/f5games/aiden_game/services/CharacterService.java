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

    public Character updateOne(Character character) {
        if (characterRepository.existsById(character.getId())) {
            return characterRepository.save(character);
        } else {
            throw new IllegalArgumentException("character identifier" + character.getId() + "doesn't exist");
        }
    }

    public Character getCharacterByName(String name) {
        return characterRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un personaje con el nombre: " + name));
    }

    public Character getRandomMonster() {
        // Aquí puedes personalizar cómo seleccionar un monstruo aleatorio
        List<Character> monsters = characterRepository.findAll(); // O usa un método que filtre solo monstruos
        return monsters.get((int) (Math.random() * monsters.size()));
    }
}
