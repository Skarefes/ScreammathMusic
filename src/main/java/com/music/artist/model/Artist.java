package com.music.artist.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Artistas")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    private String tipo;
    public Artist(){}
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Music> musicas = new ArrayList<>();

    public Artist(String nome, String tipo) {
        this.name = nome;
        this.tipo = tipo;
    }

    //Add musica ao artista
    public void addMusic(Music musica){
        musicas.add(musica);
    }

    //Retora a listade usica

    @Override
    public String toString() {
        return "Artist{" +
                "name='" + this.name + '\'' +
                ", tipo='" + this.tipo + '\'' +
                '}';
    }

    public void setMusicas(List<Music> musica){
        musica.forEach(e -> e.setArtista(this));
        this.musicas = musica;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Music> getMusicas() {
        return musicas;
    }
}
