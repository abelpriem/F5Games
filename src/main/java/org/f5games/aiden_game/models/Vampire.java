package org.f5games.aiden_game.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("VAMPIRE")
public class Vampire extends Character {

}