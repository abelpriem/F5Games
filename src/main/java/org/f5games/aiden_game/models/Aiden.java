package org.f5games.aiden_game.models;

public class Aiden extends Character {
    private long id;
    private int score;

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
