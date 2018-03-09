package com.ninja.controllers;
import com.ninja.models.Ninja;
import com.ninja.repositories.ClanRepository;
import com.ninja.repositories.NinjaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "ninja")
public class NinjaController {
    @Resource
    private NinjaRepository ninjaRepository;

    @GetMapping("")
    public List<Ninja> getAll () {
        return this.ninjaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ninja getById (@PathVariable Long id) {
        return this.ninjaRepository.findById(id).get();
    }

    @GetMapping("/{clanname}")
    public List<Ninja> getNinjasByClanName (@PathVariable String clanName) {
        List<Ninja> ninjas = this.ninjaRepository.findAll();
        List<Ninja> retNinjas = new ArrayList<>();
        for (Ninja ninja: ninjas) {
            if (ninja.getClan().getName().equalsIgnoreCase(clanName))
                retNinjas.add(ninja);
        }
        return retNinjas;
    }
}
