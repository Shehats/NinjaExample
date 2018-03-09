package com.ninja;

import com.ninja.models.Clan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class NinjaApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(NinjaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Clan handClan = new Clan("Hand");

	}
}

