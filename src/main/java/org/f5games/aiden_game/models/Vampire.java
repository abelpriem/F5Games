package org.f5games.aiden_game.models;

public class Vampire extends Character {

    private long id;
    private boolean skill1;

    public Vampire() { // clase vacía pq puede ser necesario para desde el main, puede interesar llamar
                       // a la clase (instanciar) sin datos, vacío, para dárselos en main
    }

    //// constructor padre ////
    public Vampire(Long id, String name, Integer strength, Integer health, boolean skill1) {
        super(id, name, strength, health);
        this.skill1 = skill1;
    }

    //// constructor hijo ////
    public Vampire(long id, boolean skill1) {
        this.id = id;
        this.skill1 = skill1;
    }

    /////////// getters y setters ////////////
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isSkill1() { // es un get en caso de boolean
        return skill1;
    }

    public void setSkill1(boolean skill1) {
        this.skill1 = skill1;
    }

    //// método propio de Vampire ////
    public boolean stealHealth() {
        return true;
    }
}