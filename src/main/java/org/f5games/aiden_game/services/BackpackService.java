package org.f5games.aiden_game.services;

import java.util.List;

import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.repositories.BackpackRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BackpackService {
    private final BackpackRepository backpackRepository;
    @Autowired
    public BackpackService(BackpackRepository backpackRepository) {
        this.backpackRepository = backpackRepository;
    }
    public List<Backpack> getAll(){
        return backpackRepository.findAll();
    }
    public List<Backpack> updateBackpacks(List<Backpack> backpacks){
        return backpackRepository.saveAll(backpacks);
    }

    
}
