package com.ninja.ClanService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class ClanServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClanServiceApplication.class, args);
	}
}

@RepositoryRestResource
interface ClanRepository extends JpaRepository<Clan, Long> {
}

@Component
class InsertData implements CommandLineRunner {
    @Resource
    ClanRepository clanRepository;

    @Override
    public void run(String... args) throws Exception {
        Clan clan = new Clan("Hand");
        this.clanRepository.save(clan);
        this.clanRepository.findAll().forEach(x -> {
            System.out.print(x.getName());
        });
    }
}

@RestController
@RequestMapping(path = "clan")
class ClanController {
	@Resource
	private ClanRepository clanRepository;

	@GetMapping("")
	public List<Clan> getAll() {
		this.clanRepository.findAll().forEach(x -> {
			System.out.println(x);
		});
		return this.clanRepository.findAll();
	}

	@GetMapping("/{id}")
	public Clan getById(@PathVariable Long id) {
		return this.clanRepository.findById(id).get();
	}
}


@Entity
class Clan {
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
    @Column
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    @Column
	public String getClanClass() {
		return clanClass;
	}

	public void setClanClass(String clanClass) {
		this.clanClass = clanClass;
	}
}



