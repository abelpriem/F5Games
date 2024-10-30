package org.f5games.aiden_game.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Skeleton extends Character {
    
    private boolean skill1;
    
    public Skeleton(Long id, String name, Integer strength, Integer health, boolean skill1) {
        super(id, name, strength, health);
        this.skill1 = skill1;
    }
    
    public Skeleton() {
    }

    public boolean fast(){
        return true;
    }

}
