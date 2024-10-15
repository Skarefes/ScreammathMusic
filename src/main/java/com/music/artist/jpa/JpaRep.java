package com.music.artist.jpa;

import com.music.artist.model.Artist;
import com.music.artist.model.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaRep extends JpaRepository<Artist, Long> {

    Optional<Artist> findByNameContainingIgnoreCase(String nome);

    @Query("SELECT m from Artist a JOIN a.musicas m WHERE a.name ILIKE %:nome%")
    List<Music> buscaMusicasPorArtista(String nome);
}


