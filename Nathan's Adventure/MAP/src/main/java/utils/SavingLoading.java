/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gameEngine.GameDescription;
import entity.Room;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import battle.Combattimento;

/**
 *
 * @author valen
 */
public class SavingLoading {

    public static void save(GameDescription game, String username) throws FileNotFoundException, IOException, SQLException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ObjectOutputStream objectWriter = new ObjectOutputStream(baos);
        objectWriter.writeObject(game);
        objectWriter.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        ObjectInputStream objectReader = new ObjectInputStream(bais);

        Connection connection = DriverManager.getConnection("jdbc:h2:.\\database\\DBUsers");
        PreparedStatement pdstmMatch = connection.prepareStatement("UPDATE users SET match = ? WHERE username like ?");

        pdstmMatch.setObject(1, objectReader.readObject());
        pdstmMatch.setString(2, username);

        pdstmMatch.executeUpdate();

        connection.close();
        objectWriter.close();
        pdstmMatch.close();

        List<SerializedBattle> battles = new ArrayList();
        SerializedBattle b1 = new SerializedBattle(game.getRooms().get(findRoom(3, game)).getBattle().getNomeNemico(), game.getRooms().get(findRoom(3, game)).getBattle().getVitaNemico(), game.getRooms().get(findRoom(3, game)).getBattle().getImagePath(), game.getRooms().get(findRoom(3, game)).getBattle().isActive(), game.getRooms().get(findRoom(3, game)).getBattle().continua(), game.getRooms().get(findRoom(3, game)).getBattle().getTurno());
        SerializedBattle b2 = new SerializedBattle(game.getRooms().get(findRoom(4, game)).getBattle().getNomeNemico(), game.getRooms().get(findRoom(4, game)).getBattle().getVitaNemico(), game.getRooms().get(findRoom(4, game)).getBattle().getImagePath(), game.getRooms().get(findRoom(4, game)).getBattle().isActive(), game.getRooms().get(findRoom(4, game)).getBattle().continua(), game.getRooms().get(findRoom(4, game)).getBattle().getTurno());
        SerializedBattle b3 = new SerializedBattle(game.getRooms().get(findRoom(6, game)).getBattle().getNomeNemico(), game.getRooms().get(findRoom(6, game)).getBattle().getVitaNemico(), game.getRooms().get(findRoom(6, game)).getBattle().getImagePath(), game.getRooms().get(findRoom(6, game)).getBattle().isActive(), game.getRooms().get(findRoom(6, game)).getBattle().continua(), game.getRooms().get(findRoom(6, game)).getBattle().getTurno());
        SerializedBattle b4 = new SerializedBattle(game.getRooms().get(findRoom(9, game)).getBattle().getNomeNemico(), game.getRooms().get(findRoom(9, game)).getBattle().getVitaNemico(), game.getRooms().get(findRoom(9, game)).getBattle().getImagePath(), game.getRooms().get(findRoom(9, game)).getBattle().isActive(), game.getRooms().get(findRoom(9, game)).getBattle().continua(), game.getRooms().get(findRoom(9, game)).getBattle().getTurno());

        battles.add(b1);
        battles.add(b2);
        battles.add(b3);
        battles.add(b4);

        saveBattles(battles, username);
    }

    public static GameDescription load(String username) throws SQLException, FileNotFoundException, IOException, ClassNotFoundException {
        Connection connection = DriverManager.getConnection("jdbc:h2:.\\database\\DBUsers");
        PreparedStatement pdstm = connection.prepareStatement("SELECT * FROM users WHERE username like ?");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GameDescription game = null;

        pdstm.setString(1, username);

        ResultSet rs = pdstm.executeQuery();

        if (rs.next()) {
            if (rs.getBinaryStream("match") != null) {             
                rs.getBinaryStream("match").transferTo(baos);

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream objectReader = new ObjectInputStream(bais);
                game = (GameDescription) objectReader.readObject();

                bais.close();
                objectReader.close();

                loadBattles(game, username);
            }
        }

        connection.close();
        pdstm.close();
        baos.close();

        return game;
    }

    private static void saveBattles(List<SerializedBattle> battles, String username) throws IOException, SQLException, ClassNotFoundException {

        Connection connection = DriverManager.getConnection("jdbc:h2:.\\database\\DBUsers");
        PreparedStatement pdstmBattle1 = connection.prepareStatement("UPDATE users SET battle1 = ? WHERE username like ?");

        pdstmBattle1.setObject(1, battles.get(0));
        pdstmBattle1.setString(2, username);

        pdstmBattle1.executeUpdate();

        PreparedStatement pdstmBattle2 = connection.prepareStatement("UPDATE users SET battle2 = ? WHERE username like ?");

        pdstmBattle2.setObject(1, battles.get(1));
        pdstmBattle2.setString(2, username);

        pdstmBattle2.executeUpdate();

        PreparedStatement pdstmBattle3 = connection.prepareStatement("UPDATE users SET battle3 = ? WHERE username like ?");

        pdstmBattle3.setObject(1, battles.get(2));
        pdstmBattle3.setString(2, username);

        pdstmBattle3.executeUpdate();

        PreparedStatement pdstmBattle4 = connection.prepareStatement("UPDATE users SET battle4 = ? WHERE username like ?");

        pdstmBattle4.setObject(1, battles.get(3));
        pdstmBattle4.setString(2, username);

        pdstmBattle4.executeUpdate();

        pdstmBattle1.close();
        pdstmBattle2.close();
        pdstmBattle3.close();
        pdstmBattle4.close();

        connection.close();
    }

    private static void loadBattles(GameDescription game, String username) throws SQLException, IOException, ClassNotFoundException {

        Connection connection = DriverManager.getConnection("jdbc:h2:.\\database\\DBUsers");
        PreparedStatement pdstm = connection.prepareStatement("SELECT * FROM users WHERE username like ?");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        pdstm.setString(1, username);

        ResultSet rs = pdstm.executeQuery();

        if (rs.next()) {
            rs.getBinaryStream("battle1").transferTo(baos);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

        ObjectInputStream objectReader = new ObjectInputStream(bais);
        SerializedBattle b1 = (SerializedBattle) objectReader.readObject();

        game.getRooms().get(findRoom(3, game)).setBattle(new Combattimento(b1.getNomeNemico(), b1.getVitaNemico(), b1.getImagePath(), b1.isIsActive(), b1.isContinua(), b1.getTurno()));

        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        rs.getBinaryStream("battle2").transferTo(baos2);
        ByteArrayInputStream bais2 = new ByteArrayInputStream(baos2.toByteArray());
        ObjectInputStream objectReader2 = new ObjectInputStream(bais2);

        SerializedBattle b2 = (SerializedBattle) objectReader2.readObject();

        game.getRooms().get(findRoom(4, game)).setBattle(new Combattimento(b2.getNomeNemico(), b2.getVitaNemico(), b2.getImagePath(), b2.isIsActive(), b2.isContinua(), b2.getTurno()));

        ByteArrayOutputStream baos3 = new ByteArrayOutputStream();
        rs.getBinaryStream("battle3").transferTo(baos3);
        ByteArrayInputStream bais3 = new ByteArrayInputStream(baos3.toByteArray());
        ObjectInputStream objectReader3 = new ObjectInputStream(bais3);

        SerializedBattle b3 = (SerializedBattle) objectReader3.readObject();

        game.getRooms().get(findRoom(6, game)).setBattle(new Combattimento(b3.getNomeNemico(), b3.getVitaNemico(), b3.getImagePath(), b3.isIsActive(), b3.isContinua(), b3.getTurno()));

        ByteArrayOutputStream baos4 = new ByteArrayOutputStream();
        rs.getBinaryStream("battle4").transferTo(baos4);
        ByteArrayInputStream bais4 = new ByteArrayInputStream(baos4.toByteArray());
        ObjectInputStream objectReader4 = new ObjectInputStream(bais4);

        SerializedBattle b4 = (SerializedBattle) objectReader4.readObject();

        game.getRooms().get(findRoom(9, game)).setBattle(new Combattimento(b4.getNomeNemico(), b4.getVitaNemico(), b4.getImagePath(), b4.isIsActive(), b4.isContinua(), b4.getTurno()));

        connection.close();
        pdstm.close();
        baos.close();
        bais.close();
        objectReader.close();
        baos2.close();
        bais2.close();
        objectReader2.close();
        baos3.close();
        bais3.close();
        objectReader3.close();
        baos4.close();
        bais4.close();
        objectReader4.close();

    }

    private static int findRoom(int id, GameDescription game) {
        int index = -1;

        for (Room r : game.getRooms()) {
            index++;
            if (r.getId() == id) {
                break;
            }
        }
        return index;
    }
}
