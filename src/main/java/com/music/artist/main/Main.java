package com.music.artist.main;

import com.music.artist.jpa.JpaRep;
import com.music.artist.model.Artist;
import com.music.artist.model.Music;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    Scanner teclado = new Scanner(System.in);
    private List<Artist> dadosArtistas = new ArrayList<>();
    private List<Music> dadosMusicas = new ArrayList<>();
    private JpaRep repositorio;

    public Main(JpaRep repositorio) {
        this.repositorio = repositorio;
    }

    public void exibirMenu(){
        var opcao = -1;
        while(opcao != 0) {
            String menu = """
                    ***Screen Sound Music***
                    1- Cadastrar artista
                    2- Cadastrar música
                    3- Listar músicas
                    4- Busca músicas por artista
                    5- Pesquisar lista dos artistas
                    
                    0- Sair.
                    """;

            System.out.println(menu);
            opcao = teclado.nextInt();
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtist();
                    break;
                case 2:
                    cadastrarMusic();
                    break;
                case 3:
                    listMusic();
                    break;
                case 4:
                    buscarMusicArtist();
                    break;
                case 5:
                    listArtist();
                    break;
                case 0:
                    System.out.println("Encerrado");
                    break;
                default:
                    System.out.println("Repita o numero");
            }
        }
    }

    private void cadastrarArtist(){
        var cadastrarNovo = "s";
        while(cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Insira o nome do artista: ");
            String nome = teclado.nextLine();
            System.out.println("Informe o tipo de artista ");
            String tipo = teclado.nextLine();

            Artist addArtista = new Artist(nome, tipo);
            //addArtista.setName(nome);
            //addArtista.setTipo(tipo);
            dadosArtistas.add(addArtista);
            repositorio.save(addArtista);
            System.out.println("Gostaria de cadastrar outro?");
            cadastrarNovo = teclado.nextLine();
        }
    }

    private void cadastrarMusic(){
        System.out.println("Insira o nome do Artista primeiro");
        var nome = teclado.nextLine();
        Optional<Artist> artista = repositorio.findByNameContainingIgnoreCase(nome);
        if (artista.isPresent()){
            System.out.println("Insira o nome da musica");
            var addMusic = teclado.nextLine();
            Music musica = new Music(addMusic);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        }else {
            System.out.println("Artista não encontrado");
        }
    }

    private void listMusic(){
        List<Artist> artista = repositorio.findAll();
        artista.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicArtist(){
        System.out.println("Digite o nome do artista para saber a musica");
        var nome = teclado.nextLine();
        List<Music> musics = repositorio.buscaMusicasPorArtista(nome);
        musics.forEach(System.out::println);

    }

    private void listArtist(){
        List<Artist> artistas = repositorio.findAll();
        artistas.forEach(System.out::println);
    }










}
