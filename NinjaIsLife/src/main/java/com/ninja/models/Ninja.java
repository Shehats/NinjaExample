package com.ninja.models;

import javax.persistence.*;

public class Ninja {
    private Long id;
    private String weapon;
    private int ki;
    private int kills;
    private Clan clan;

    public Ninja() {
    }

    public Ninja(String weapon, Clan clan) {
        this.weapon = weapon;
        this.clan = clan;
    }

    public Ninja(String weapon, int ki, int kills, Clan clan) {
        this.weapon = weapon;
        this.ki = ki;
        this.kills = kills;
        this.clan = clan;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getWeapon() {
        return weapon;
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    public int getKi() {
        return ki;
    }
    public void setKi(int ki) {
        this.ki = ki;
    }

    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }

    public Clan getClan() {
        return clan;
    }
    public void setClan(Clan clan) {
        this.clan = clan;
    }
}
