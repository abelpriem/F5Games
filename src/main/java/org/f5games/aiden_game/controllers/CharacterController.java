package org.f5games.aiden_game.controllers;

import java.util.List;

import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.services.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class CharacterController {
    private CharacterService characterservice;

    public CharacterController (CharacterService characterService) {
        this.characterservice = characterService;
    }
    @GetMapping(path = "/character")
    public List<Character> getCharacter(@RequestParam String param) {
        return characterservice.getAll();
    }
    @PutMapping(path = "/character{id}")
    public void updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        characterservice.updateOne(character);
    }
}
