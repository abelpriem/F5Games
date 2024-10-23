package org.f5games.aiden_game.repositories;

import org.f5games.aiden_game.models.GameObject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameObjectRepository extends JpaRepository<GameObject, Long>{
    
}
