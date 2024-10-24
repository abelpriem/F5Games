package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.repositories.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GameObjectService {
    private final GameObjectRepository gameObjectRepository;
    @Autowired
    public GameObjectService (GameObjectRepository gameObjectRepository) {
        this.gameObjectRepository = gameObjectRepository;
    }
    public List<GameObject> getAll (){
        return gameObjectRepository.findAll();
    }
    
}
