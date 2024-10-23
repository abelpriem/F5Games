package org.f5games.aiden_game.repository;

import org.f5games.aiden_game.models.Backpack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackpackRepository extends JpaRepository<Backpack, Long> {

    void insertOne(Backpack backpack);

    void updateOne(Long objectId, Backpack backpack);

    void deleteOne(Long objectId);

}
