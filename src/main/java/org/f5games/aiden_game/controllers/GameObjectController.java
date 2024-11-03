package org.f5games.aiden_game.controllers;

import java.util.List;

import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.services.GameObjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameObjectController {
    
    private final GameObjectService gameObjectService;

    public GameObjectController(GameObjectService gameObjectService) {
        this.gameObjectService = gameObjectService;
    }
    // OBTENER LA LISTA DE LOS OBJETOS
    @GetMapping(path = "/objects")
    public List<GameObject> getObjects() {
        return gameObjectService.getAll();
    }
}