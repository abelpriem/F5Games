package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.exceptions.ResourceNotFoundException;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<Character> getAll() {
        return characterRepository.findAll();
    }

    public Character updateOne(Character character) {
        if (characterRepository.existsById(character.getId())) {
            return characterRepository.save(character);
        } else {
            throw new ResourceNotFoundException("character identifier" + character.getId() + "doesn't exist");
        }
    }

    public Character attack(Character attacker, Character target ) {

        if ((characterRepository.existsById(attacker.getId())) && (characterRepository.existsById(target.getId()))) {
        
            target.setHealth(target.getHealth()- attacker.getStrength());
            
            return characterRepository.save(target);

        } else {
            throw new IllegalArgumentException("Character identifier" + attacker.getId() + " or " + target.getId() + "don't exist");
        }
    }

    public Character getCharacterById(Long id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un personaje con el ID: " + id));
    }

    
}
