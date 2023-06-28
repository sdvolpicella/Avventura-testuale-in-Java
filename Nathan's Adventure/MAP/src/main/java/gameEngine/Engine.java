package gameEngine;

import parser.Parser;
import parser.ParserOutput;
import entity.CommandType;
import utils.Classifica;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import utils.Utils;
import loginAndRegister.LoginForm;
import entity.User;
import utils.SavingLoading;

public class Engine{

    private GameDescription game;

    private Parser parser;

    private User user;

    public Engine(GameDescription game) {
        this.game = game;
        try {
            if(game.getCurrentRoom() == null){
                this.game.init();
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        try {
            Set<String> stopwords = Utils.loadStopwords(new File(".\\resources\\NathansAdventure's files\\Stopword.txt"));
            parser = new Parser(stopwords);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void execute() {

        System.out.println("[--- Posizione attuale: " + game.getCurrentRoom().getName() + " ---]");

        System.out.println();
        System.out.println(game.getCurrentRoom().getDescriptionUnseen());

        game.getCurrentRoom().setIsVisited(true);
        if (game.getCurrentRoom().getDialog() != null) {
            game.getCurrentRoom().getDialog().start();
        }
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            ParserOutput p = parser.parse(command, game.getCommands(), game.getCurrentRoom().getObjects(), game.getInventory());
            if (p != null) {

                if (p.getCommand() != null && p.getCommand().getType() == CommandType.SAVE) {

                    try {
                        
                        SavingLoading.save(this.game, this.user.getUsername());
                        System.out.println("Salvataggio effettuato con successo!\n");
                    } catch (IOException | SQLException | ClassNotFoundException ex) {
                        Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (p.getCommand() != null && p.getCommand().getType() == CommandType.END) {
                    System.out.println("Addio!");
                    System.exit(0);
                } else {
                    game.nextMove(p);
                    System.out.println();
                }
            } else {
                System.out.println("\nComando non  valido...\n");
            }
        }

    }

    public static void main(String[] args) throws Exception {
        
        Classifica.createTable();
        
        LoginForm lf = new LoginForm();
        LoginForm.createTable();
        lf.setVisible(true);
        lf.pack();
        lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        while (true) {
            System.out.print("");
            if (lf.isReady()) {
                break;
            }
        }

        Engine game = new Engine(lf.getGame());
        game.setUser(lf.getUser());
        game.execute();
        
    }

    
    
}
