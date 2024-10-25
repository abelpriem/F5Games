package org.f5games.aiden_game.services;
import java.util.List;
import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.repository.BackpackRepository;
import org.springframework.stereotype.Service;

@Service
public class BackpackService {

    private final BackpackRepository backpackRepository;

    public BackpackService(BackpackRepository backpackRepository) {
        this.backpackRepository = backpackRepository;
    }

    public List<Backpack> getAll() {
        return backpackRepository.findAll();
    }

    public void insertOne(Backpack backpack) {
        backpackRepository.save(backpack);
    }

    public void updateOne(Long objectId, Backpack backpack) {
        backpackRepository.save(backpack);
    }

    public void deleteOne(Long objectId) {
        backpackRepository.deleteById(objectId);
    }

}
