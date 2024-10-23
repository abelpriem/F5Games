package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.repositories.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Character getCharacterById(Long id){
        return characterRepository.findById(id).orElse(null);
    }

    public List<Character> getAll(){
        return characterRepository.findAll();
    }
}


