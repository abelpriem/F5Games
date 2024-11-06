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

    public CharacterController(CharacterService characterService) {
        this.characterservice = characterService;
    }

    @GetMapping(path = "/character")
    public List<Character> getCharacter() {
        return characterservice.getAll();
    }

    @PutMapping(path = "/character/attack")
    public ResponseEntity<Character> attack(@RequestBody Character character, @RequestBody Character target) {
        return new ResponseEntity<>(characterservice.attack(character, target), HttpStatus.OK);
    }

    @PutMapping(path = "/character/powerfull-attack")
    public ResponseEntity<Character> powerfullAttack(@RequestBody Character character, @RequestBody Character target) {
        return new ResponseEntity<>(characterservice.powerfullAttack(character, target), HttpStatus.OK);
    }

    @PutMapping(path = "/character/attack-with-shield")
    public ResponseEntity<Character> attackWithShield(@RequestBody Character character, @RequestBody Character target) {
        return new ResponseEntity<>(characterservice.attackWithShield(character, target), HttpStatus.OK);
    }

    @PutMapping(path = "/character/use-potion")
    public ResponseEntity<Character> usePotion(@RequestBody Character character) {
        return new ResponseEntity<>(characterservice.potion(character), HttpStatus.OK);
    }

    @PutMapping(path = "/character")
    public ResponseEntity<Character> updateCharacter(@RequestBody Character character) {
        return new ResponseEntity<>(characterservice.updateOne(character), HttpStatus.OK);
    }

}
