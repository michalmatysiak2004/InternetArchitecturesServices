package org.example.application;

import org.example.DTOs.PlayerCreateDTO;
import org.example.DTOs.PlayerReadDTO;
import org.example.entities.Club;
import org.example.entities.Player;
import org.example.services.ClubService;
import org.example.services.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component

public class CommandLine implements CommandLineRunner {

    private final PlayerService playerService;
    private ClubService clubService;
    private String command = "";
    private Scanner scanner = new Scanner(System.in);

    public CommandLine(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Witaj");
        System.out.println("aby zobaczyc liste dostepnych komend wpisz help");

        while(!command.equals("quit")){
            command = scanner.nextLine();
            handleCommand(command);

        }
    }
    private void handleCommand(String command) {
        switch (command.toLowerCase()) {
            case "addplayer":
                this.addPlayer();
                break;

            case "listplayers":
                this.listplayers();
                break;
            case "listclubs":
                this.listclubs();
                break;

            case "listplayersfromclub":
                this.listplayersfromclub();
                break;
            case "deleteplayer":
                this.removePlayer();
                break;
            case "help":
                System.out.println("Dostępne komendy: addplayer, addclub, listplayers, listclubs, deleteplayer, listplayersfromclub,  quit");
                break;
            case "quit":
                System.out.println("Wyjście...");
                break;
            default:
                System.out.println("Nieznana komenda: " + command);
        }
    }

    private void listplayersfromclub() {
        System.out.println("Podaj nazwe klubu gracza(klub musi istniec w bazie)");
        String clubname = scanner.nextLine();
        List<Player> players = playerService.findPlayersByClubname(clubname);
        players.forEach(player -> System.out.println(player.toString()));
    }

    private  void listplayers(){
        List<PlayerReadDTO> players = playerService.fetchPlayerList();
        players.forEach(player -> System.out.println(player.toString()));
    }
    private void listclubs(){
        List<Club> clubs = clubService.fetchClubList();
        clubs.forEach(club -> System.out.println(club.toString()));
    }
    private void addPlayer(){
        System.out.println("Podaj imie gracza");
        String firstname = scanner.nextLine();
        System.out.println("Podaj nazwisko gracza");
        String lastname = scanner.nextLine();
        System.out.println("Podaj kraj gracza");
        String country = scanner.nextLine();
        System.out.println("Podaj liczbe goli gracza");
        int goals = Integer.parseInt(scanner.nextLine());
        System.out.println("Podaj nazwe klubu gracza(klub musi istniec w bazie)");
        String clubname = scanner.nextLine();
        playerService.savePlayer(firstname,lastname,country,goals,clubname);
    }
    private void removePlayer(){
        System.out.println("Podaj imie gracza");
        String firstname = scanner.nextLine();
        System.out.println("Podaj nazwisko gracza");
        String lastname = scanner.nextLine();
        playerService.deletePlayer2(firstname,lastname);
    }

}

