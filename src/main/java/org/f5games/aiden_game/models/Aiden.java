package org.f5games.aiden_game.models;

public class Aiden extends Character {
    private long id;
    private int score;
    private int health;
    private int strength;

    public Aiden(long id, String name, int health, int strength, int score) {
        super(id, name, health, strength);
        this.id = id;
        this.score = score;
    }

    public Aiden(long id, int score) {
        this.id = id;
        this.score = score;
    }

    public Aiden() {
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean specialAttack() {
        return true;
    }

    public boolean shield() {
        return true;
    }
}
