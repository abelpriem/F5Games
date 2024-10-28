package org.f5games.aiden_game.controllers;

import java.util.List;

import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.services.BackpackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackpackController {

    private BackpackService services;

    public BackpackController(BackpackService services) {
        this.services = services;
    }

    // LISTAR OBJETOS DE LA MOCHILA
    @GetMapping(path = "/backpack")
    public ResponseEntity<List<Backpack>> retrieveObjects() {
        return new ResponseEntity<>(services.getAll(),HttpStatus.OK);
    }

    // AÑADIR OBJETO EN LA MOCHILA
    @PostMapping(path = "/backpack/{objectId}")
    public ResponseEntity<Backpack> addObject(@PathVariable Long objectId) {
        return new ResponseEntity<>(services.insertOne(objectId),HttpStatus.CREATED);
    }

    // MODIFICAR OBJETO DE LA MOCHILA
    @PutMapping(path = "/backpack/{id}")
    public ResponseEntity<Backpack> updateObject(@PathVariable Long id, @RequestParam Long objectId) {
        return new ResponseEntity<>(services.updateOne(id, objectId),HttpStatus.OK);
    }

    // ELIMINAR OBJETO DE LA MOCHILA
    @DeleteMapping(path = "/backpack/{id}")
    public ResponseEntity<Void> deleteObject(@PathVariable Long id) {
        services.deleteOne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
