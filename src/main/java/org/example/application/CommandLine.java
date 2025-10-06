package org.example.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("WELCOME");
        System.out.println("aby zobaczyc liste dostepnych komend wpisz help");
        String command = "";
        Scanner scanner = new Scanner(System.in);
        while(!command.equals("quit")){
            command = scanner.next();
            handleCommand(command);

        }
    }
    private static void handleCommand(String command) {
        switch (command.toLowerCase()) {
            case "addplayer":
                System.out.println("Dodawanie zawodnika...");

                break;
            case "addclub":
                System.out.println("Dodawanie klubu...");

                break;
            case "listplayers":
                System.out.println("Wyświetlanie zawodników...");

                break;
            case "help":
                System.out.println("Dostępne komendy: addplayer, addclub, listplayers, quit");
                break;
            case "quit":
                System.out.println("Wyjście...");
                break;
            default:
                System.out.println("Nieznana komenda: " + command);
        }
    }
}

