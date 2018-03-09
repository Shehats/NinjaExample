package com.ninja.controllers;
import com.ninja.models.Clan;
import com.ninja.repositories.ClanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(path = "clan")
public class ClanController {
    @Resource
    private ClanRepository clanRepository;

    @GetMapping("")
    public List<Clan> getAll() {
        return this.clanRepository.findAll();
    }

    @GetMapping("/{id}")
    public Clan getById(@PathVariable Long id) {
        return this.clanRepository.findById(id).get();
    }
}
