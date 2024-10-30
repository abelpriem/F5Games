package org.f5games.aiden_game.models;

import java.util.Random;

public class Mortis extends Character {
    private boolean skill1;

    public Mortis(Long id, String name, Integer strength, Integer health, boolean skill1) {
        super(id, name, strength, health);
        this.skill1 = skill1;
    }

    public Mortis(boolean skill1) {
        this.skill1 = skill1;
    }

    public Mortis() {
    }

    public boolean isSkill1() {
        return skill1;
    }

    public void setSkill1(boolean skill1) {
        this.skill1 = skill1;
    }

    public void selectSkill() {
        Random random = new Random();

        int x = random.nextInt(2)+1;
    
        switch(String.valueOf(x)) {
        case "1": boolean fastMortis = true;
        break;
        case "2": boolean invisibilityMortis = true;
        break;
        case "3": boolean stealHealth = true;
        break;
        default: System.out.println("Skill not availible");
        }
    }
}
