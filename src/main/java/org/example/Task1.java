package org.example;


import org.example.DTOs.PlayerDTO;
import org.example.entities.Club;
import org.example.entities.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Task1 {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        Club realmadryt = Club.builder()
                .name("Real Madrid")
                .country("Spain")
                .titles(95)
                .players(new ArrayList<>())
                .build();
        Club barcelona = Club.builder()
                .name("FC Barcelona")
                .country("Spain")
                .titles(50)
                .players(new ArrayList<>())
                .build();
        Club chelmianka = Club.builder()
                .name("SKS Chełmianka Gdańsk")
                .country("Poland")
                .titles(0)
                .players(new ArrayList<>())
                .build();
        List<Club> clubs = List.of(realmadryt, barcelona, chelmianka);

        Player vini = Player.builder()
                .firstName("Vinicius")
                .lastName("Junior")
                .goals(123)
                .country("Brasil")
                .club(realmadryt)

                .build();

        Player mbappe = Player.builder()
                .firstName("Kylian")
                .lastName("Mbappe")
                .goals(542)
                .country("France")
                .club(realmadryt)
                .build();

        Player bellingham = Player.builder()
                .firstName("Jude")
                .lastName("Bellingham")
                .country("England")
                .goals(94)
                .club(realmadryt)
                .build();

        realmadryt.addPlayer(vini);
        realmadryt.addPlayer(mbappe);
        realmadryt.addPlayer(bellingham);

        Player lewandowski = Player.builder()
                .firstName("Robert")
                .lastName("Lewandowski")
                .country("Poland")
                .goals(650)
                .club(barcelona)
                .build();
        Player pedri = Player.builder()
                .firstName("Pedri")
                .lastName("Gonzalez")
                .country("Spain")
                .goals(34)
                .club(barcelona)
                .build();
        Player yamal = Player.builder()
                .firstName("Lamine")
                .lastName("Yamal")
                .country("Spain")
                .goals(54)
                .club(barcelona)
                .build();

        barcelona.addPlayer(lewandowski);
        barcelona.addPlayer(yamal);
        barcelona.addPlayer(pedri);

        Player sobel = Player.builder()
                .firstName("Michał")
                .lastName("Matysiak")
                .country("Poland")
                .goals(0)
                .club(chelmianka)
                .build();

        Player prezes = Player.builder()
                .firstName("Miłosz")
                .lastName("Sandach")
                .country("Poland")
                .goals(32)
                .club(chelmianka)
                .build();

        chelmianka.addPlayer(sobel);
        chelmianka.addPlayer(prezes);

        System.out.println("Kluby: \n ");
        clubs.forEach(club -> {
            System.out.println(club.toString());
            club.getPlayers().forEach(player -> System.out.println(" - " + player.toString()));
            System.out.println("\n");
        });

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Punkt 3");
        Set<Player> allPlayers = clubs.stream()
                .flatMap(club -> club.getPlayers().stream())
                .collect(Collectors.toSet());
        allPlayers.stream()
                .forEach(player -> System.out.println(player.toString()));
        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Punkt 4");
        allPlayers.stream()
                .filter(player -> player.getCountry().equals("Poland"))
                //.sorted((o1, o2) -> o1.getGoals())
                // zapytac pani czemu nie mozna reversed przy golach
                .sorted(Comparator.comparingInt(player -> player.getGoals()))
                .forEach(player -> System.out.println(player.toString()));

        System.out.println("\n");
        System.out.println("\n");
        System.out.println("Punkt 5");
        List<PlayerDTO> allplayersDTO = allPlayers.stream()
                .map(player -> PlayerDTO.builder()
                        .firstName(player.getFirstName())
                        .lastName(player.getLastName())
                        .country(player.getCountry())
                        .goals(player.getGoals())
                        .clubName(player.getClub() != null ? player.getClub().getName() : null)
                        .build()
                )
                .sorted()
                .collect(Collectors.toList());


        allplayersDTO.stream()
                .forEach(playerDTO -> System.out.println(playerDTO.toString()));
        //Punkt 6 Serializacja

        try {
            FileOutputStream fileout = new FileOutputStream("C:\\Users" +
                    "\\polsk\\InternetServicesArchitectures\\src\\main\\resources\\ClubsInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileout);
            clubs.forEach(club -> {
                try {
                    out.writeObject(club);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            out.close();
            fileout.close();
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        //Wczytywanie danych
        ArrayList<Club> clubsIn = new ArrayList<>();
        try {
            FileInputStream filein = new FileInputStream("C:\\Users" +
                    "\\polsk\\InternetServicesArchitectures\\src\\main\\resources\\ClubsInfo.ser");

            ObjectInputStream in = new ObjectInputStream(filein);
            while (true) {
                try {
                    Club club = (Club) in.readObject(); // odczyt pojedynczego obiektu
                    clubsIn.add(club);                    // dodanie do listy
                } catch (EOFException e) {
                    break; // koniec pliku, wychodzimy z pętli
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        clubsIn.forEach(club -> {
            System.out.println(club.toString());
            club.getPlayers().forEach(player -> System.out.println(" - " + player.toString()));
            System.out.println("\n");
        });

        // punkt 7
        ForkJoinPool customThreadpool = new ForkJoinPool(4);
            

    }








}