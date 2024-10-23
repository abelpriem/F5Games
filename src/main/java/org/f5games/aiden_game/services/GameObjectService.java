package org.f5games.aiden_game.services;

import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.repositories.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class GameObjectService {
    private final GameObjectRepository gameObjectRepository;

    @Autowired
    public GameObjectService(GameObjectRepository gameObjectRepository) {
        this.gameObjectRepository = gameObjectRepository;
    }

    public GameObject getGameObjectById(Long id){
        return gameObjectRepository.findById(id).orElse(null);
    }
    
}
