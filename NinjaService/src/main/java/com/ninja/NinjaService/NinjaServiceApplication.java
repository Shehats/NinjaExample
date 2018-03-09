package com.ninja.NinjaService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@EnableDiscoveryClient
@SpringBootApplication
public class NinjaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NinjaServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
	public RestTemplate buildRestTemplate(RestTemplateBuilder restTemplateBuilder){
		return restTemplateBuilder.build();
	}
}



@RepositoryRestResource
interface NinjaRepository extends JpaRepository<Ninja, Long> {
}

@RestController
@RequestMapping(path = "")
class NinjaController {
	@Autowired
	private RestTemplate restTemplate;

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

	@GetMapping("/clans/{id}")
	public Clan getClan(@PathVariable Long id){
		Clan clan = restTemplate.getForObject("http://clan-service/clan/"+id, Clan.class);
		return clan;
	}

	@PostMapping("/clans")
	public void postClan() {
		this.restTemplate.postForEntity("http://clan-service/clan", HttpMethod.POST,Clan.class,new Clan());
	}

	@GetMapping("/clans")
	public Collection<Clan> getClans () {
		ParameterizedTypeReference<List<Clan>> ptr = new ParameterizedTypeReference<List<Clan>>(){};
		ResponseEntity<List<Clan>> clanResponseEntity = this.restTemplate.exchange("http://clan-service/clan", HttpMethod.GET, null, ptr);
		return clanResponseEntity
				.getBody();
	}

	@GetMapping("/{clanname}")
	public List<Ninja> getNinjasByClanName (@PathVariable String clanName) {
		List<Ninja> ninjas = this.ninjaRepository.findAll();
		List<Ninja> retNinjas = new ArrayList<>();
		for (Ninja ninja: ninjas) {
			if (ninja.getClan().equalsIgnoreCase(clanName))
				retNinjas.add(ninja);
		}
		return retNinjas;
	}
}

class Clan {
	private Long id;
	private String name;
	private String clanClass;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getClanClass() {
		return clanClass;
	}
}
@Entity
class Ninja {
	private Long id;
	private String weapon;
	private int ki;
	private int kills;
	private String clan;

	public Ninja() {
	}

	public Ninja(String weapon, String clan) {
		this.weapon = weapon;
		this.clan = clan;
	}

	public Ninja(String weapon, int ki, int kills, String clan) {
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

	public String getClan() {
		return clan;
	}

	public void setClan(String clan) {
		this.clan = clan;
	}
}