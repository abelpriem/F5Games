package org.f5games.aiden_game.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GHOST")
public class Ghost extends Character {
    
}