package org.f5games.aiden_game.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Aiden extends Character {

    private int score;

    public Aiden(long id, String name, int health, int strength, int score) {
        super(id, name, health, strength);
        this.score = score;
    }

    public Aiden() {
    }

    public boolean specialAttack() {
        return true;
    }

    public boolean shield() {
        return true;
    }
}
