package org.f5games.aiden_game.controllers;

import java.util.List;

import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.services.BackpackService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackpackController {

    private BackpackService services;

    public BackpackController(BackpackService services) {
        this.services = services;
    }

    // LISTAR OBJETOS DE LA MOCHILA
    @GetMapping(path = "/backpack")
    public List<Backpack> retrieveObjects() {
        return services.getAll();
    }

    // AÑADIR OBJETO EN LA MOCHILA
    @PostMapping(path = "/backpack/{objectId}")
    public void addObject(@RequestBody Backpack backpack) {
        services.insert(backpack);
    }

    // MODIFICAR OBJETO DE LA MOCHILA
    @PutMapping(path = "/backpack/{objectId}")
    public void updateObject(@PathVariable Long objectId, @RequestBody Backpack backpack) {
        services.update(objectId, backpack);
    }

    // ELIMINAR OBJETO DE LA MOCHILA
    @DeleteMapping(path = "/backpack/{objectId}")
    public void deleteObject(@PathVariable Long objectId) {
        services.delete(objectId);
    }
}
