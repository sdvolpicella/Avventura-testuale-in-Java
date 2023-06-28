package game;

import utils.Classifica;
import gameEngine.GameDescription;
import entity.MultipleChoiceDialog;
import parser.ParserOutput;
import entity.AdvObject;
import entity.Command;
import entity.CommandType;
import entity.Container;
import entity.Room;

import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import battle.Combattimento;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import battle.AttaccoNemico;
import entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import utils.HelpForm;

public class NathansAdventure extends GameDescription implements Serializable {
    
    User user = new User();
    
    public NathansAdventure(User userLoggedIn) {
        this.user = userLoggedIn;
    }
    
    @Override
    public void init() throws Exception {
        
        loadCommands();
        
        loadAdvObjects();
        
        setContainer();
        
        loadRooms();
        
        setRooms();
        
        setCurrentRoom(getRooms().get(findRoom(0)));
    }
    
    public int findObject(int id) {
        
        int index = -1;
        
        for (AdvObject o : getGameObjects()) {
            index++;
            if (o.getId() == id) {
                break;
            }
        }
        return index;
    }
    
    public int findRoom(int id) {
        int index = -1;
        
        for (Room r : getRooms()) {
            index++;
            if (r.getId() == id) {
                break;
            }
        }
        return index;
    }
    
    public void setContainer() {
        if (getGameObjects().get(findObject(2)) instanceof Container) {
            Container c = (Container) getGameObjects().get(findObject(2));
            c.getList().add(getGameObjects().get(findObject(3)));
            
            getGameObjects().remove(getGameObjects().get(findObject(2)));
            getGameObjects().add(c);
        }
        
        if (getGameObjects().get(findObject(6)) instanceof Container) {
            Container c = (Container) getGameObjects().get(findObject(6));
            c.getList().add(getGameObjects().get(findObject(7)));
            
            getGameObjects().remove(getGameObjects().get(findObject(6)));
            getGameObjects().add(c);
        }
        
        if (getGameObjects().get(findObject(8)) instanceof Container) {
            Container c = (Container) getGameObjects().get(findObject(8));
            c.getList().add(getGameObjects().get(findObject(9)));
            
            getGameObjects().remove(getGameObjects().get(findObject(8)));
            getGameObjects().add(c);
        }
    }
    
    private void setRooms() {

        //Stanza 0 : Stanza delle torture
        getRooms().get(findRoom(0)).setWest(getRooms().get(findRoom(1)));
        getRooms().get(findRoom(0)).setDialog(loadDialog(".\\resources\\NathansAdventure's files\\Dialoghi a scelta multipla\\Dialogo Iniziale.txt"));
        getRooms().get(findRoom(0)).getObjects().add(getGameObjects().get(findObject(0)));

        //Stanza 1 : Corridoio                   
        getRooms().get(findRoom(1)).setNorth(getRooms().get(findRoom(2)));
        getRooms().get(findRoom(1)).setEast(getRooms().get(findRoom(0)));
        getRooms().get(findRoom(1)).setSouth(getRooms().get(findRoom(7)));

        //Stanza 2 : Cella con prigionieri
        getRooms().get(findRoom(2)).setWest(getRooms().get(findRoom(3)));
        getRooms().get(findRoom(2)).setSouth(getRooms().get(findRoom(1)));
        getRooms().get(findRoom(2)).setDialog(loadDialog(".\\resources\\NathansAdventure's files\\Dialoghi a scelta multipla\\Dialogo con Prigioniero.txt"));

        //Stanza 3 : Alloggio delle guardie
        getRooms().get(findRoom(3)).setWest(getRooms().get(findRoom(4)));
        getRooms().get(findRoom(3)).setEast(getRooms().get(findRoom(2)));
        getRooms().get(findRoom(3)).setBattle(new Combattimento("Bandito Assonnato", 15, ".\\resources\\NathansAdventure's images\\bandito assonnato.jpg"));
        getRooms().get(findRoom(3)).getObjects().add(getGameObjects().get(findObject(1))); //.\\src\\main\\java\\images\\bandito assonnato.jpg
        getRooms().get(findRoom(3)).getObjects().add(getGameObjects().get(findObject(2)));
        getRooms().get(findRoom(3)).getObjects().add(getGameObjects().get(findObject(3)));

        //Stanza 4 : Archivio
        getRooms().get(findRoom(4)).setEast(getRooms().get(findRoom(3)));
        getRooms().get(findRoom(4)).setSouth(getRooms().get(findRoom(5)));

        //getRooms().get(findRoom(4)).setDialog(loadDialog(".\\src\\files di NathansAdv\\Dialoghi a scelta multipla\\Dialogo con Guardia.txt"));
        getRooms().get(findRoom(4)).setBattle(new Combattimento("Bandito Infuriato", 50, ".\\resources\\NathansAdventure's images\\bandito infuriato.jpg"));
        getRooms().get(findRoom(4)).getObjects().add(getGameObjects().get(findObject(5)));

        //Stanza 5 : Ingresso miniera
        getRooms().get(findRoom(5)).setWest(getRooms().get(findRoom(6)));
        getRooms().get(findRoom(5)).setNorth(getRooms().get(findRoom(4)));
        getRooms().get(findRoom(5)).setEast(getRooms().get(findRoom(9)));
        getRooms().get(findRoom(5)).getObjects().add(getGameObjects().get(findObject(10)));

        //Stanza 6 : Cuore miniera
        getRooms().get(findRoom(6)).setEast(getRooms().get(findRoom(5)));
        getRooms().get(findRoom(6)).setBattle(new Combattimento("Malzar", 100, ".\\resources\\NathansAdventure's images\\capo banditi.jpg"));
        getRooms().get(findRoom(6)).getObjects().add(getGameObjects().get(findObject(11)));

        //Stanza 7 : Cucina
        getRooms().get(findRoom(7)).setWest(getRooms().get(findRoom(8)));
        getRooms().get(findRoom(7)).setNorth(getRooms().get(findRoom(1)));
        getRooms().get(findRoom(7)).getObjects().add(getGameObjects().get(findObject(6)));
        getRooms().get(findRoom(7)).getObjects().add(getGameObjects().get(findObject(7)));

        //Stanza 8 : Deposito
        getRooms().get(findRoom(8)).setEast(getRooms().get(findRoom(7)));
        getRooms().get(findRoom(8)).getObjects().add(getGameObjects().get(findObject(8)));
        getRooms().get(findRoom(8)).getObjects().add(getGameObjects().get(findObject(9)));

        //Stanza 9 : Catacombe
        getRooms().get(findRoom(9)).setWest(getRooms().get(findRoom(5)));
        getRooms().get(findRoom(9)).setDialog(loadDialog(".\\resources\\NathansAdventure's files\\Dialoghi a scelta multipla\\Dialogo Finale.txt"));
        getRooms().get(findRoom(9)).setBattle(new Combattimento("Demone", 200, ".\\resources\\NathansAdventure's images\\demone.jpg"));
        getRooms().get(findRoom(9)).getObjects().add(getGameObjects().get(findObject(12)));
        
    }
    
    private void loadRooms() {
        
        Scanner roomReader;
        String line;
        
        try {
            roomReader = new Scanner(new FileReader(".\\resources\\NathansAdventure's files\\Stanze.txt"));
            
            while (roomReader.hasNextLine()) {
                
                int id = -1;
                String name = "";
                String descriptionUnseen = "";
                String descriptionSeen = "";
                String look = "";
                String lookEventComplete = "";
                boolean isVisited = false;
                boolean eventComplete = false;
                boolean isDark = false;
                boolean isLocked = false;
                
                while (!(line = roomReader.nextLine()).matches("\\*")) {
                    
                    if (line.contains("ID")) {
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("=");
                        lineScanner.next();
                        id = Integer.parseInt(lineScanner.next());
                        lineScanner.close();
                    } else if (line.contains("NAME")) {
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("=");
                        lineScanner.next();
                        name = lineScanner.next();
                        lineScanner.close();
                    } else if (line.contains("UNVISITED")) {
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("=");
                        lineScanner.next();
                        descriptionUnseen = lineScanner.next();
                        while (!(line = roomReader.nextLine()).equals("-")) {
                            descriptionUnseen = descriptionUnseen.concat("\n" + line);
                        }
                        lineScanner.close();
                    } else if (line.contains("VISITED")) {
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("=");
                        lineScanner.next();
                        descriptionSeen = lineScanner.next();
                        while (!(line = roomReader.nextLine()).equals("-")) {
                            descriptionSeen = descriptionSeen.concat("\n" + line);
                        }
                        lineScanner.close();
                    } else if (line.contains("LOOK")) {
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("=");
                        lineScanner.next();
                        look = lineScanner.next();
                        while (!(line = roomReader.nextLine()).equals("-")) {
                            look = look.concat("\n" + line);
                        }
                        lineScanner.close();
                    } else if (line.contains("L00K EVENT COMPLETE")) {
                        Scanner lineScanner = new Scanner(line);
                        lineScanner.useDelimiter("=");
                        lineScanner.next();
                        lookEventComplete = lineScanner.next();
                        while (!(line = roomReader.nextLine()).equals("-")) {
                            lookEventComplete = lookEventComplete.concat("\n" + line);
                        }
                        lineScanner.close();
                    } else if (line.contains("EV3NT COMPLETE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        eventComplete = Boolean.parseBoolean(lineReader.next());
                        lineReader.close();
                    } else if (line.contains("ISDARK")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isDark = Boolean.parseBoolean(lineReader.next());
                        lineReader.close();
                    } else if (line.contains("ISLOCKED")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isLocked = Boolean.parseBoolean(lineReader.next());
                        lineReader.close();
                    }
                }
                Room r = new Room(id, name, isVisited, descriptionUnseen, descriptionSeen, look, lookEventComplete, eventComplete, isDark, isLocked);
                getRooms().add(r);
            }
            roomReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("ERR0RE NEL RITROVAMENTO DEL FILE!");
            System.out.println("Vedi nella directory principale del gioco se ï¿½ presente il file Stanze.txt e riavvia il gioco.");
        }
    }
    
    private void loadAdvObjects() {
        
        Scanner advObjectReader;
        String line;
        
        try {
            advObjectReader = new Scanner(new FileReader(".\\resources\\NathansAdventure's files\\Oggetti.txt"));
            
            while (advObjectReader.hasNextLine()) {
                
                int id = 0;
                String name = "";
                Set<String> alias = new LinkedHashSet<>();
                String description = "";
                boolean isPickable = false;
                boolean isUsable = false;
                boolean isPullable = false;
                boolean isWeapon = false;
                boolean isLeaveable = false;
                boolean isBreakable = false;
                boolean isReadable = false;
                boolean isOpenable = false;
                int damage = 0;
                int pointsWhenPicked = 0;
                String textOfItem = "";
                boolean isBroken = false;
                boolean isPulled = false;
                String author = "";
                boolean picked = false;
                boolean opened = false;
                boolean inside = false;
                boolean isContainer = false;
                boolean isActivated = false;
                
                boolean isContained = false;
                String containedIn = "";
                
                while (!(line = advObjectReader.nextLine()).matches("\\*")) {
                    
                    if (line.contains("ID")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        id = Integer.parseInt(lineReader.next());
                        //     System.out.println("ID = " + id);
                        lineReader.close();
                        
                    } else if (line.contains("NAME")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        name = lineReader.next();
                        //   System.out.println("NAME = " + name);
                        lineReader.close();
                    } else if (line.contains("ALIAS")) {
                        StringBuilder temp = new StringBuilder(line);
                        temp.delete(0, 6);
                        Scanner lineReader = new Scanner(temp.toString());
                        lineReader.useDelimiter("/");
                        while (lineReader.hasNext()) {
                            alias.add(lineReader.next());
                        }
                        //      System.out.println("ALIAS : " + alias);
                        lineReader.close();
                    } else if (line.contains("DESCRIPTION")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        String risp = lineReader.next();
                        if (risp.equals("NULL")) {
                            description = "";
                        } else {
                            description = risp;
                            while (!(line = advObjectReader.nextLine()).equals("-")) {
                                description = description.concat("\n" + line);
                            }
                        }
                        //      System.out.println("Desc : "+ description);
                        lineReader.close();
                    } else if (line.contains("ISPICKABLE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isPickable = Boolean.parseBoolean(lineReader.next());
                        //        System.out.println("PICKABLE = " + isPickable);
                        lineReader.close();
                    } else if (line.contains("ISUSABLE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isUsable = Boolean.parseBoolean(lineReader.next());
                        //      System.out.println("USABLE = " + isUsable);
                        lineReader.close();
                    } else if (line.contains("ISPULLABLE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isPullable = Boolean.parseBoolean(lineReader.next());
                        //      System.out.println("PULLABLE = " + isPullable);
                        lineReader.close();
                    } else if (line.contains("ISWEAPON")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isWeapon = Boolean.parseBoolean(lineReader.next());
                        //  		System.out.println("ISWEAPON = "+isWeapon);
                        lineReader.close();
                    } else if (line.contains("ISBREAKABLE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isBreakable = Boolean.parseBoolean(lineReader.next());
                        //         System.out.println("ISBREAK = " + isBreakable);
                        lineReader.close();
                    } else if (line.contains("ISLEAVEABLE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isLeaveable = Boolean.parseBoolean(lineReader.next());
                        //       System.out.println("ISLEAV = " + isLeaveable);
                        lineReader.close();
                    } else if (line.contains("ISREADABLE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isReadable = Boolean.parseBoolean(lineReader.next());
                        //       System.out.println("ISREAD = " + isReadable);
                        lineReader.close();
                    } else if (line.contains("DAMAGE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        String risp = lineReader.next();
                        if (risp.equals("NULL")) {
                            damage = 0;
                        } else {
                            damage = Integer.parseInt(risp);
                        }
                        //        System.out.println("DAMAGE = " + damage);
                        lineReader.close();
                    } else if (line.contains("POINTSWHENPICKED")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        String risp = lineReader.next();
                        if (risp.equals("NULL")) {
                            pointsWhenPicked = 0;
                        } else {
                            pointsWhenPicked = Integer.parseInt(risp);
                        }
                        //        System.out.println("POINTS... : " + pointsWhenPicked);
                        lineReader.close();
                    } else if (line.contains("TEXTOFITEM")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        String risp = lineReader.next();
                        if (risp.equals("NULL")) {
                            textOfItem = "";
                        } else {
                            textOfItem = risp;
                            while (!(line = advObjectReader.nextLine()).equals("-")) {
                                textOfItem = textOfItem.concat("\n" + line);
                            }
                        }
                        //       System.out.println("TEXT : " + textOfItem);
                        lineReader.close();
                    } else if (line.contains("ISOPENABLE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isOpenable = Boolean.parseBoolean(lineReader.next());
                        //       System.out.println("ISOP : "+ isOpenable);
                        lineReader.close();
                        
                    } else if (line.contains("ISBROKEN")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isBroken = Boolean.parseBoolean(lineReader.next());
                        //       System.out.println("ISBROKEN : "+ isBroken);
                        lineReader.close();
                        
                    } else if (line.contains("ISPULLED")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isPulled = Boolean.parseBoolean(lineReader.next());
                        //       System.out.println("ISPULLED : " + isPulled);
                        lineReader.close();
                        
                    } else if (line.contains("AUTHOR")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        author = lineReader.next();
                        if (author.equals("NULL")) {
                            author = "";
                        }
                        //       System.out.println("Autore : " + author);
                        lineReader.close();
                        
                    } else if (line.contains("ISIN")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        inside = Boolean.parseBoolean(lineReader.next());
                        //       System.out.println("INSIDE : "+inside);
                        lineReader.close();
                        
                    } else if (line.contains("ISCONTAINED")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isContained = Boolean.parseBoolean(lineReader.next());
                        lineReader.close();
                        
                    } else if (line.contains("CONTAINEDIN")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        String risp = lineReader.next();
                        if (risp.equals("NULL")) {
                            containedIn = "";
                        } else {
                            containedIn = risp;
                            while (!(line = advObjectReader.nextLine()).equals("-")) {
                                containedIn = containedIn.concat("\n" + line);
                            }
                        }
                        lineReader.close();
                        
                    } else if (line.contains("ISCONTAINER")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        isContainer = Boolean.parseBoolean(lineReader.next());
                        lineReader.close();
                        
                    }
                    
                }
                if (isContainer) {
                    
                    Container c = new Container(id, name, alias, description, isOpenable, opened, isContainer);
                    getGameObjects().add(c);
                } else {
                    
                    AdvObject o = new AdvObject(id, name, alias, description, isPickable, isUsable, isPullable,
                            isWeapon, isLeaveable, isBreakable, isReadable, damage,
                            pointsWhenPicked, textOfItem, isOpenable, isBroken, isPulled,
                            author, picked, opened, inside, isContained, containedIn, isContainer, isActivated);
                    
                    getGameObjects().add(o);
                }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERR0RE NEL RITROVAMENTO DEL FILE!");
            System.out.println("Vedi nella directory principale del gioco se e' presente il file Oggetti.txt e riavvia il gioco.");
        }
    }
    
    private void loadCommands() {
        
        Scanner commandsReader;
        
        try {
            commandsReader = new Scanner(new FileReader(".\\resources\\NathansAdventure's files\\Comandi.txt"));
            String line;
            
            while (commandsReader.hasNextLine()) {
                
                CommandType ct = null;
                String name = "";
                Set<String> alias = new LinkedHashSet<>();
                
                while (!(line = commandsReader.nextLine()).equals("*")) {
                    
                    if (line.contains("TYPE")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        String type = lineReader.next();
                        
                        if (type.equals("NORTH")) {
                            ct = CommandType.NORTH;
                        } else if (type.equals("SOUTH")) {
                            ct = CommandType.SOUTH;
                        } else if (type.equals("EAST")) {
                            ct = CommandType.EAST;
                        } else if (type.equals("WEST")) {
                            ct = CommandType.WEST;
                        } else if (type.equals("END")) {
                            ct = CommandType.END;
                        } else if (type.equals("LOOK AT")) {
                            ct = CommandType.LOOK_AT;
                        } else if (type.equals("PICK UP")) {
                            ct = CommandType.PICK_UP;
                        } else if (type.equals("OPEN")) {
                            ct = CommandType.OPEN;
                        } else if (type.equals("PULL")) {
                            ct = CommandType.PULL;
                        } else if (type.equals("INVENTORY")) {
                            ct = CommandType.INVENTORY;
                        } else if (type.equals("MENU")) {
                            ct = CommandType.MENU;
                        } else if (type.equals("BREAK")) {
                            ct = CommandType.BREAK;
                        } else if (type.equals("USE")) {
                            ct = CommandType.USE;
                        } else if (type.equals("HELP")) {
                            ct = CommandType.HELP;
                        } else if (type.equals("G1VE")) {
                            ct = CommandType.G1VE;
                        } else if (type.equals("READ")) {
                            ct = CommandType.READ;
                        } else if (type.equals("SAVE")) {
                            ct = CommandType.SAVE;
                        }
                        
                        lineReader.close();
                    } else if (line.contains("NAME")) {
                        Scanner lineReader = new Scanner(line);
                        lineReader.useDelimiter("=");
                        lineReader.next();
                        name = lineReader.next();
                        lineReader.close();
                    } else if (line.contains("ALIAS")) {
                        StringBuilder temp = new StringBuilder(line);
                        temp.delete(0, 6);
                        Scanner lineReader = new Scanner(temp.toString());
                        lineReader.useDelimiter("/");
                        while (lineReader.hasNext()) {
                            alias.add(lineReader.next());
                        }
                        lineReader.close();
                    }
                }
                getCommands().add(new Command(ct, name, alias));
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERR0RE NEL RITROVAMENTO DEL FILE!");
            System.out.println("Vedi nella directory principale del gioco se e' presente il file Comandi.txt e riavvia il gioco.");
        }
    }
    
    private MultipleChoiceDialog loadDialog(String path) {
        
        MultipleChoiceDialog d = new MultipleChoiceDialog();
        
        Scanner dialogReader;
        String line;
        
        try {
            dialogReader = new Scanner(new FileReader(path));
            
            while (dialogReader.hasNextLine()) {
                
                line = dialogReader.nextLine();
                
                if (line.contains("NAME")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    d.setNameDialog(lineReader.next());
                    lineReader.close();
                } else if (line.contains("CASE")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    d.setCaso(Integer.parseInt(lineReader.next()));
                    lineReader.close();
                } else if (line.contains("OP.A")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    d.setOpzioneA(lineReader.next());
                    lineReader.close();
                } else if (line.contains("OP.B")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setOpzioneB(null);
                    } else {
                        d.setOpzioneB(risp);
                    }
                    lineReader.close();
                } else if (line.contains("OP.C")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setOpzioneC(null);
                    } else {
                        d.setOpzioneC(risp);
                    }
                    lineReader.close();
                } else if (line.contains("RISP.A")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    d.setRispostaA(lineReader.next());
                    while (!(line = dialogReader.nextLine()).equals("-")) {
                        d.setRispostaA(d.getRispostaA().concat("\n" + line));
                    }
                    lineReader.close();
                } else if (line.contains("RISP.B")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setRispostaB(null);
                    } else {
                        d.setRispostaB(risp);
                    }
                    while (!(line = dialogReader.nextLine()).equals("-")) {
                        d.setRispostaB(d.getRispostaB().concat("\n" + line));
                    }
                    lineReader.close();
                } else if (line.contains("RISP.C")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setRispostaC(null);
                    } else {
                        d.setRispostaC(risp);
                    }
                    while (!(line = dialogReader.nextLine()).equals("-")) {
                        d.setRispostaC(d.getRispostaC().concat("\n" + line));
                    }
                    lineReader.close();
                } else if (line.contains("SUBO.A")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setSubOpzioneA(null);
                    } else {
                        d.setSubOpzioneA(risp);
                    }
                    lineReader.close();
                } else if (line.contains("SUBO.B")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setSubOpzioneB(null);
                    } else {
                        d.setSubOpzioneB(risp);
                    }
                    lineReader.close();
                } else if (line.contains("SUBO.C")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setSubOpzioneC(null);
                    } else {
                        d.setSubOpzioneC(risp);
                    }
                    lineReader.close();
                } else if (line.contains("SUBR.A")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setSubRispostaA(null);
                    } else {
                        d.setSubRispostaA(risp);
                    }
                    while (!(line = dialogReader.nextLine()).equals("-")) {
                        d.setSubRispostaA(d.getSubRispostaA().concat("\n" + line));
                    }
                    lineReader.close();
                } else if (line.contains("SUBR.B")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setSubRispostaB(null);
                    } else {
                        d.setSubRispostaB(risp);
                    }
                    while (!(line = dialogReader.nextLine()).equals("-")) {
                        d.setSubRispostaB(d.getSubRispostaB().concat("\n" + line));
                    }
                    lineReader.close();
                } else if (line.contains("SUBR.C")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setSubRispostaC(null);
                    } else {
                        d.setSubRispostaC(risp);
                    }
                    while (!(line = dialogReader.nextLine()).equals("-")) {
                        d.setSubRispostaC(d.getSubRispostaC().concat("\n" + line));
                    }
                    lineReader.close();
                } else if (line.contains("ENDDIALOG")) {
                    Scanner lineReader = new Scanner(line);
                    lineReader.useDelimiter("=");
                    lineReader.next();
                    String risp = lineReader.next();
                    if (risp.equals("NULL")) {
                        d.setEndDialog(null);
                    } else {
                        d.setEndDialog(risp);
                    }
                    while (!(line = dialogReader.nextLine()).equals("-")) {
                        d.setEndDialog(d.getEndDialog().concat("\n" + line));
                    }
                    lineReader.close();
                }
            }
            
        } catch (FileNotFoundException ex) {
            System.out.println("ERR0RE NEL RITROVAMENTO DEL FILE!");
            System.out.println("Vedi nella directory principale del gioco se ï¿½ presente il file Dialoghi Scelta Multipla.txt e riavvia il gioco.");
        }
        return d;
    }
    
    @Override
    public void nextMove(ParserOutput p) {
        
        if (p.getCommand() != null) {
            
            switch (p.getCommand().getType()) {
                
                case NORTH:
                    commandNorth();
                    break;
                
                case SOUTH:
                    commandSouth();
                    break;
                
                case EAST:
                    commandEast();
                    break;
                
                case WEST:
                    commandWest();
                    break;
                
                case BREAK:
                    commandBreak(p);
                    break;
                
                case LOOK_AT:
                    commandLook();
                    break;
                
                case PULL:
                    commandPull(p);
                    break;
                
                case READ:
                    commandRead(p);
                    break;
                
                case PICK_UP:
                    commandPick(p);
                    break;
                
                case OPEN:
                    commandOpen(p);
                    break;
                
                case INVENTORY:
                    commandInventory();
                    break;
                
                case G1VE:
                    commandGive(p);
                    break;
                
                case USE:
                    commandUse(p);
                    break;
                
                case HELP:
                    commandHelp();
                    break;
                
                default:
                    System.out.println("\nComando non riconosciuto...");
            }
        } else {
            System.out.println("\nComando non riconosciuto...");
        }
    }
    
    private void commandNorth() {
        
        boolean noRoom = false;
        boolean move = false;
        boolean isDark = false;
        boolean isLocked = false;
        
        if (getCurrentRoom().getNorth() != null) {
            noRoom = false;
            isDark = getCurrentRoom().getNorth().isDark();
            isLocked = getCurrentRoom().getNorth().isLocked();
            
            if (getGameObjects().get(findObject(10)).isActivated() == true) { //torcia attivata
                isDark = false;
            }
            
            if (!noRoom && !isDark && !isLocked) {
                move = true;
                setCurrentRoom(getCurrentRoom().getNorth());
            } else {
                move = false;
            }
        } else {
            noRoom = true;
        }
        
        checkMovement(noRoom, move, isDark, isLocked);
    }
    
    private void commandSouth() {
        
        boolean noRoom = false;
        boolean move = false;
        boolean isDark = false;
        boolean isLocked = false;
        
        if (getCurrentRoom().getSouth() != null) {
            noRoom = false;
            isDark = getCurrentRoom().getSouth().isDark();
            isLocked = getCurrentRoom().getSouth().isLocked();
            
            if (getGameObjects().get(findObject(10)).isActivated() == true) { //torcia attivata
                isDark = false;
            }
            
            if (!noRoom && !isDark && !isLocked) {
                move = true;
                setCurrentRoom(getCurrentRoom().getSouth());
            } else {
                move = false;
            }
        } else {
            noRoom = true;
        }
        
        checkMovement(noRoom, move, isDark, isLocked);
    }
    
    private void commandEast() {
        
        boolean noRoom = false;
        boolean move = false;
        boolean isDark = false;
        boolean isLocked = false;
        
        if (getCurrentRoom().getEast() != null) {
            noRoom = false;
            isDark = getCurrentRoom().getEast().isDark();
            isLocked = getCurrentRoom().getEast().isLocked();
            
            if (getGameObjects().get(findObject(10)).isActivated() == true) { //torcia attivata
                isDark = false;
            }
            
            if (!noRoom && !isDark && !isLocked) {
                move = true;
                setCurrentRoom(getCurrentRoom().getEast());
            } else {
                move = false;
            }
        } else {
            noRoom = true;
        }
        
        checkMovement(noRoom, move, isDark, isLocked);
        
    }
    
    private void commandWest() {
        
        boolean noRoom = false;
        boolean move = false;
        boolean isDark = false;
        boolean isLocked = false;
        
        if (getCurrentRoom().getWest() != null) {
            noRoom = false;
            isDark = getCurrentRoom().getWest().isDark();
            isLocked = getCurrentRoom().getWest().isLocked();
            
            if (getGameObjects().get(findObject(10)).isActivated() == true) { //torcia attivata
                isDark = false;
            }
            
            if (!noRoom && !isDark && !isLocked) {
                move = true;
                setCurrentRoom(getCurrentRoom().getWest());
            } else {
                move = false;
            }
        } else {
            noRoom = true;
        }
        
        checkMovement(noRoom, move, isDark, isLocked);
    }
    
    private void checkMovement(boolean noRoom, boolean move, boolean isDark, boolean isLocked) {
        
        if (noRoom) {
            System.out.println("\nNon posso proseguire in questa direzione, una parete mi blocca la strada.");
        } else if (isDark && isLocked == false) {
            System.out.println("\nNon posso proseguire da questa parte... la stanza e' troppo buia!");
        } else if (isLocked && isDark == false) {
            
            if (getCurrentRoom().getId() == 0) {
                System.out.println("\nNon posso andare da questa parte... delle sbarre arruginite mi bloccano la via.");
                
            } else if (getCurrentRoom().getId() == 5 && getCurrentRoom().getEast().isLocked()) {
                System.out.println("\nUn enorme porta nera ti blocca la strada... sembra che la porta possa essere aperta solo se si utilizza un'apposita chiave.");
            } else {
                System.out.println("\nNon posso proseguire da questa parte... l'accesso alla stanza e' bloccato!");
                
            }
            
        } else if (isLocked == true && isDark == true) {
            System.out.println("\nNon posso proseguire da questa parte... l'accesso alla stanza e' bloccato!");
        } else {
            System.out.println("\n[--- Posizione attuale: " + getCurrentRoom().getName() + " ---]\n");
            
            if (getCurrentRoom().isIsVisited() == false) {
                System.out.println(getCurrentRoom().getDescriptionUnseen());
            } else {
                System.out.println(getCurrentRoom().getDescriptionSeen());
            }
            
            if (getCurrentRoom().getId() == 2) { //Dialogo con il prigioniero attivato appena entri in 2.
                getCurrentRoom().getDialog().start();
            }
            
            if (getCurrentRoom().getId() == 4) {
                if (getCurrentRoom().getBattle().isActive()) {
                    combact(); //Combattimento contro il bandito nell'archivio
                }
            }
            
            if (getCurrentRoom().getId() == 6) {
                if (getCurrentRoom().getBattle().isActive()) {
                    combact();
                    getInventory().add(getGameObjects().get(findObject(14)));
                }
            }
            
            if (getCurrentRoom().getId() == 9) {
                if (getCurrentRoom().getBattle().isActive()) {
                    combact();
                }
            }
            
            getCurrentRoom().setIsVisited(true);
            
        }
        
    }
    
    private void commandLook() {
        if (getCurrentRoom().isEventComplete() == false) {
            System.out.println("\n" + getCurrentRoom().getLook());
        } else {
            System.out.println("\n" + getCurrentRoom().getLookEventComplete());
        }
    }
    
    private void commandBreak(ParserOutput p) {
        
        boolean isNotHere = false;
        
        if (getCurrentRoom().getObjects().contains(p.getObj())) {
            
            if (p.getObj().isBreakable() && p.getObj().isBroken() == false) {
                System.out.println("\nHai rotto: " + p.getObj().getName());
                p.getObj().setBroken(true);
                if (getCurrentRoom().getId() == 0) {
                    getCurrentRoom().setEventComplete(true);
                    getCurrentRoom().getWest().setLocked(false);
                }
            } else {
                
                if ((p.getObj().isBreakable() == false) && (isNotHere == false)) {
                    System.out.println("\nNon puoi rompere questo oggetto.");
                }
                if (p.getObj().isBroken()) {
                    System.out.println("\nHai gia' rotto questo oggetto.");
                }
                
            }
        } else {
            System.out.println("\nQuesto oggetto non si trova in questa stanza.");
        }
    }
    
    private void commandPull(ParserOutput p) {
        
        if (getCurrentRoom().getObjects().contains(p.getObj())) {
            
            if ((p.getObj().isPullable()) && (p.getObj().isPulled() == false)) {
                System.out.println("\nHai tirato: " + p.getObj().getName() + ". " + p.getObj().getDescription());
                p.getObj().setPulled(true);
                if (getCurrentRoom().getId() == 3) {
                    getCurrentRoom().setEventComplete(true);
                    getCurrentRoom().getEast().setEventComplete(true);
                    getRooms().get(findRoom(2)).getObjects().add(getGameObjects().get(findObject(4)));
                    getRooms().get(findRoom(2)).getObjects().add(getGameObjects().get(findObject(13)));

                    //Se non ha fatto usa tappi allora deve avviarsi il combattimento
                    if (getGameObjects().get(findObject(3)).isActivated() == false) {
                        
                        if (getCurrentRoom().getBattle().isActive()) {
                            combact(); //Combattimento contro bandito nell'alloggio
                        }
                        
                    }
                    
                }
            } else {
                
                if ((p.getObj().isPullable() == false)) {
                    System.out.println("\nNon puoi tirare questo oggetto.");
                }
                if (p.getObj().isPulled()) {
                    System.out.println("\nHai gia' tirato questo oggetto");
                }
            }
        } else {
            System.out.println("\nQuesto oggetto non si trova in questa stanza.");
        }
    }
    
    private void commandRead(ParserOutput p) {
        
        if (getCurrentRoom().getObjects().contains(p.getObj())) {
            
            if (p.getObj().isReadable()) {
                System.out.println("\n{\n" + p.getObj().getTextOfItem() + "\n\n" + p.getObj().getAuthor() + "\n}");
                
                if (getCurrentRoom().getId() == 4) {
                    getCurrentRoom().setEventComplete(true);
                }
                
                if (p.getObj().getId() == 12) {
                    getCurrentRoom().getDialog().start();
                    
                    Classifica.aggiornaClassifica(this, user);
                    
                    System.out.println("");
                    System.out.println("IL TUO PUNTEGGIO E' STATO INSERITO NELLA CLASSIFICA");
                    System.out.println("");
                    
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:h2:.\\database\\DBUsers");
                        PreparedStatement pstm = conn.prepareStatement("UPDATE users SET match = null WHERE username like ?");
                        
                        pstm.setString(1, this.user.getUsername());
                        
                        pstm.executeUpdate();
                        
                        conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(NathansAdventure.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    System.exit(0);
                }
                
            } else {
                
                if ((p.getObj().isReadable() == false)) {
                    System.out.println("\nNon puoi leggere questo oggetto.");
                }
            }
        } else {
            System.out.println("\nQuesto oggetto non si trova in questa stanza.");
        }
    }
    
    private void commandPick(ParserOutput p) {
        
        boolean isNotHere = true;

        //E' ALL'INTERNO DELLA STANZA?
        if (getCurrentRoom().getObjects().contains(p.getObj()) == false) {
            System.out.println("\nQuesto oggetto non si trova in questa stanza.");
            isNotHere = true;
        } else {

            //CASO IN CUI E' CONTENUTO
            if (p.getObj().isContained() && p.getObj().isPickable() && getCurrentRoom().getObjects().contains(p.getObj())) {
                
                for (AdvObject o : getCurrentRoom().getObjects()) {
                    if (p.getObj().getContainedIn().equals(o.getName())) {
                        Container c = (Container) o;
                        if (c.isOpened()) {
                            for (int i = 0; i < c.getList().size(); i++) {
                                if (c.getList().get(i).getName().equals(p.getObj().getName())) {
                                    
                                    if (p.getObj().getId() == 7) {
                                        getCurrentRoom().setEventComplete(true);
                                    }
                                    
                                    p.getObj().setPicked(true);
                                    getInventory().add(p.getObj());
                                    c.getList().remove(i);
                                    getCurrentRoom().getObjects().remove(p.getObj());
                                    System.out.println("\nHai raccolto: " + p.getObj().getName() + ". " + p.getObj().getDescription());
                                    
                                }
                            }
                            
                        } else {
                            System.out.println("\nQuesto oggetto non si trova in questa stanza.");
                        }
                        
                    }
                }
                //OGGETTO RACCOLTO DALLA STANZA
            } else if (getCurrentRoom().getObjects().contains(p.getObj()) && p.getObj().isPickable() && p.getObj().isPicked() == false && p.getObj().isContained() == false) {
                
                getInventory().add(p.getObj());
                p.getObj().setPicked(true);
                System.out.println("\nHai raccolto: " + p.getObj().getName() + ". " + p.getObj().getDescription());
                getCurrentRoom().getObjects().remove(p.getObj());
                
                if (p.getObj().getId() == 10) {
                    getCurrentRoom().setEventComplete(true);
                }
                
                if (p.getObj().getId() == 11) {
                    getCurrentRoom().setEventComplete(true);
                }
                
            } else if (p.getObj().isPickable() == false) {
                System.out.println("\nNon puoi raccogliere questo oggetto.");
            }
            
        }
        
    }
    
    private void commandOpen(ParserOutput p) {
        
        if (getCurrentRoom().getObjects().contains(p.getObj())) {
            
            if (p.getObj().isContainer() && p.getObj().isOpenable()) {
                
                Container c = (Container) getGameObjects().get(findObject(p.getObj().getId()));
                System.out.print("\nHai aperto: " + p.getObj().getName());
                p.getObj().setOpened(true);
                if (!c.getList().isEmpty()) {
                    System.out.println(", oggetti contenuti: ");
                    Iterator<AdvObject> it = c.getList().iterator();
                    while (it.hasNext()) {
                        AdvObject next = it.next();
                        System.out.println("-" + next.getName());
                    }
                } else {
                    System.out.println(", non c'e' nessun oggetto all'interno!");
                }
                
            } else {
                if (p.getObj().getId() == 8) {
                    System.out.println("\nI cani ti ringhiano contro impedendoti di avvicinarti al baule.");
                } else {
                    System.out.println("\nNon puoi aprire questo oggetto!");
                }
            }
            
        } else {
            System.out.println("\nQuesto oggetto non e' presente in questa stanza.");
        }
    }
    
    private void commandInventory() {
        
        if (!getInventory().isEmpty()) {
            
            System.out.println("\nOggetti contenuti nell'inventario: ");
            Iterator<AdvObject> it = getInventory().iterator();
            while (it.hasNext()) {
                AdvObject next = it.next();
                System.out.println("- " + next.getName());
            }
        } else {
            System.out.println("\nL'inventario e' vuoto");
        }
        
    }
    
    private void commandGive(ParserOutput p) {
        
        if (getInventory().contains(p.getObj())) {
            if (p.getObj().isLeaveable()) {
                getInventory().remove(p.getObj());
                
                p.getObj().setPicked(false);
                p.getObj().setIsContained(false);
                getCurrentRoom().getObjects().add(getGameObjects().get(findObject(p.getObj().getId())));
                
                System.out.println("\nHai rimosso " + p.getObj().getName() + " dal tuo inventario.");
                
                if (getCurrentRoom().getId() == 8 && p.getObj().getId() == 7) {
                    getCurrentRoom().setEventComplete(true);
                    getCurrentRoom().getObjects().get(findObject(0)).setOpenable(true);
                    System.out.println("\nI cani si precipitano sui croccantini e scodinzolano.");
                    getCurrentRoom().getObjects().remove(p.getObj());
                }
                
            } else {
                System.out.println("\nNon puoi lasciare questo oggetto.");
            }
            
        } else {
            System.out.println("\nNon puoi lasciare un oggetto che non si trova nel tuo inventario.");
        }
        
    }
    
    private void commandUse(ParserOutput p) {
        
        if (getInventory().contains(p.getObj())) {
            
            if (p.getObj().isUsable() == true) {
                
                if (p.getObj().isActivated() == false) {
                    
                    if (p.getObj().getId() == 3) {
                        if (getCurrentRoom().getId() == 3) {
                            
                            if (getGameObjects().get(findObject(1)).isPulled() == false) {
                                
                                p.getObj().setIsActivated(true);
                                System.out.println("\nHai messo i tappi per le orecchie al bandito.");
                                getInventory().remove(p.getObj());
                                
                            } else {
                                getInventory().remove(p.getObj());
                                p.getObj().setIsActivated(true);
                                System.out.println("\nHai messo i tappi per le orecchie al bandito... anche se e' gia' morto.");
                            }
                            
                        } else {
                            System.out.println("\nNon puoi usare qui questo oggetto.");
                        }
                        
                    } else if (p.getObj().getId() == 11) { //chiave

                        if (getCurrentRoom().getId() == 5) {
                            getRooms().get(findRoom(9)).setLocked(false);
                            System.out.println("\nLa porta si apre ed uno stormo di pipistrelli fuoriesce dalla stanza.");
                        } else {
                            System.out.println("\nNon puoi usare qui questo oggetto.");
                        }
                        
                    } else {

                        //GENERAL
                        p.getObj().setIsActivated(true);
                        System.out.println("\nStai utilizzando: " + p.getObj().getName());
                        
                    }
                    
                } else {
                    System.out.println("\nStai gia' usando utilizzando questo oggetto");
                }
                
            } else {
                System.out.println("\nNon puoi usare questo oggetto.");
            }
            
        } else {
            System.out.println("\nNon puoi usare un oggetto che non si trova nel tuo inventario.");
        }
        
    }
    
    private void commandHelp() {
        HelpForm hf = new HelpForm();
        hf.setVisible(true);
        hf.pack();
        hf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void combact() {
        
        List<AdvObject> weapons = new ArrayList();
        Scanner scanner = new Scanner(System.in);
        String anyDigit;
        boolean flag = false;
        
        if (getCurrentRoom().getBattle().getNomeNemico().equals("Bandito Assonnato")) {
            System.out.println("\nIl bandito si sveglia e inizia ad attaccarti.\n");
        }
        
        if (getCurrentRoom().getBattle().getNomeNemico().equals("Bandito Infuriato")) {
            System.out.println("\nBandito infuriato:\"Hey tu! Non dovresti essere qui... come hai fatto a liberarti!?\"");
            System.out.println("\nIl bandito infuriato inizia ad attaccarti con tutta la sua furia!\n");
        }
        
        if (getCurrentRoom().getBattle().getNomeNemico().equals("Malzar")) {
            System.out.println("\nMalzar:\"Come hai fatto a liberarti? Non importa, non mi faro' battere da te.\"\n");
        }
        
        if (getCurrentRoom().getBattle().getNomeNemico().equals("Demone")) {
            System.out.println("\nIl demone ti attacca!\n");
        }
        
        System.out.println("Lo scontro contro: " + getCurrentRoom().getBattle().getNomeNemico() + " sta pe iniziare! "
                + "Digita un carattere qualsiasi per poter continuare.\n");
        
        while (flag == false) {
            
            anyDigit = scanner.nextLine();
            
            if (!anyDigit.equals("")) {
                
                flag = true;
                
                for (AdvObject o : getInventory()) {
                    if (o.isWeapon()) {
                        weapons.add(o);
                    }
                }
                
                getCurrentRoom().getBattle().getInventory().setListaArmi(weapons);
                
                getCurrentRoom().getBattle().setVisible(true);
                AttaccoNemico attacco = new AttaccoNemico(getCurrentRoom().getBattle(), this.user); //cambia qui
                attacco.run();
                
            } else {
                
                System.out.println("Digita un carattere qualsiasi per poter continuare (a-z, 1-9).\n");
                
            }
            
        }
        
    }
    
}
