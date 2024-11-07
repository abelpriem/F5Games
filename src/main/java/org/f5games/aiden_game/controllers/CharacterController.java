package org.f5games.aiden_game.controllers;

import java.util.List;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.services.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CharacterController {
    private CharacterService characterservice;

    public CharacterController (CharacterService characterService) {
        this.characterservice = characterService;
    }
    
    @GetMapping(path = "/character")
    public List<Character> getCharacter() {
        return characterservice.getAll();
    }

    @PutMapping(path = "/character")
    public ResponseEntity<Character> updateCharacter(@RequestBody Character character) {
        return new ResponseEntity<>(characterservice.updateOne(character),HttpStatus.OK);
    }

}




