package com.music.artist;

import com.music.artist.jpa.JpaRep;
import com.music.artist.main.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ArtistApplication implements CommandLineRunner {
	@Autowired
	private JpaRep repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ArtistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Main main = new Main(repositorio);
		main.exibirMenu();
	}
}
