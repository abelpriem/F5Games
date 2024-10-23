package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.repository.GameObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameObjectService {
    @Autowired
    private final GameObjectRepository gameObjectRepository;
    
    public GameObjectService (GameObjectRepository gameObjectRepository) {
        this.gameObjectRepository = gameObjectRepository;
    }
    public List<GameObject> getAll () {
        return gameObjectRepository.findAll();
    }
}