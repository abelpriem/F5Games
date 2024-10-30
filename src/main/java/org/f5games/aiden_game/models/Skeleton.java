package org.f5games.aiden_game.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SKELETON")
public class Skeleton extends Character {
    
}
