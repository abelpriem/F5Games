package org.f5games.aiden_game.models;
import java.util.Random;

public class Ghost extends Character{

    private boolean skill1;

    
    public Ghost(Long id, String name, Integer strength, Integer health, boolean skill1) {
        super(id, name, strength, health);
        this.skill1 = skill1;
    }

    public Ghost(){

    }

    public Ghost(boolean skill1) {
        this.skill1 = skill1;
    }

    public boolean isSkill1() {
        return skill1;
    }

    public void setSkill1(boolean skill1) {
        this.skill1 = skill1;
    }

    public boolean invisibility(boolean skill1){

        Random rand = new Random();
        int randomInvisibility = rand.nextInt(100) + 1; 
        if(randomInvisibility <= 30){

            return true;
        }

        return false;

    }

    
}
