package com.ninja.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Clan {
    private Long id;
    private String name;
    private String clanClass;

    public Clan() {
    }

    public Clan(String name) {
        this.name = name;
    }

    public Clan(String name, String clanClass) {
        this.name = name;
        this.clanClass = clanClass;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getClanClass() {
        return clanClass;
    }

    public void setClanClass(String clanClass) {
        this.clanClass = clanClass;
    }
}
