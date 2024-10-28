package org.f5games.aiden_game.models;

public class Skeleton extends Character {
    
    private long id;
    private boolean skill1;
    
    public Skeleton(Long id, String name, Integer strength, Integer health, boolean skill1) {
        super(id, name, strength, health);
        this.skill1 = skill1;
    }
    
    public Skeleton() {
    }

    public Skeleton(Long id, boolean skill1) {
        this.id = id;
        this.skill1 = skill1;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSkill1() {
        return skill1;
    }

    public void setSkill1(boolean skill1) {
        this.skill1 = skill1;
    }

    public boolean fast(){
        return true;
    }

}
