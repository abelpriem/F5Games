package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.repository.BackpackRepository;
import org.f5games.aiden_game.repository.GameObjectRepository;
import org.springframework.stereotype.Service;

@Service
public class BackpackService {

    private final BackpackRepository backpackRepository;
    private final GameObjectRepository gameObjectRepository;

    public BackpackService(BackpackRepository backpackRepository, GameObjectRepository gameObjectRepository) {
        this.backpackRepository = backpackRepository;
        this.gameObjectRepository = gameObjectRepository;
    }

    public List<Backpack> getAll() {
        return backpackRepository.findAll();
    }

    public Backpack insertOne(Long objectId) {
        if (objectId == null) {
            throw new IllegalArgumentException("GameObject id cannot be null");
        }
        if (backpackRepository.count() > 3) {
            throw new IllegalArgumentException("Backpack is full");
        }
        GameObject gameObject = gameObjectRepository.findById(objectId)
                .orElseThrow(() -> new IllegalArgumentException("GameObject not found with id " + objectId));
        return backpackRepository.save(Backpack.builder().gameObject(gameObject).build());
    }

    public Backpack updateOne(Long id, Long objectId) {
        if (id == null) {
            throw new IllegalArgumentException("Backpack identifier cannot be null");
        }
        if (objectId == null) {
            throw new IllegalArgumentException("GameObject id cannot be null");
        }
        backpackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Backpack identifier " + id + " not found"));
        GameObject gameObject = gameObjectRepository.findById(objectId)
                .orElseThrow(() -> new IllegalArgumentException("GameObject not found with id " + objectId));
        return backpackRepository.save(Backpack.builder().id(id).gameObject(gameObject).build());
    }

    public void deleteOne(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Backpack identifier cannot be null");
        }
        Backpack backpack = backpackRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Backpack identifier " + id + " not found"));
        backpackRepository.delete(backpack);
    }

}
