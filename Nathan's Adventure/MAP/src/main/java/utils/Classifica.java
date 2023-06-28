/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import gameEngine.GameDescription;
import entity.AdvObject;
import entity.Room;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import entity.User;

/**
 *
 * @author 39333
 */
public class Classifica {

    public static void aggiornaClassifica(GameDescription g, User u) {

        int stanze_esplorate = 0;
        int oggetti_raccolti = 0;
        int eventi_completati = 0;
        
        int punteggio_finale = 0;
        int punteggio_sottratto = 10;

        for (AdvObject o : g.getGameObjects()) {
            if (o.isPicked() == true) {
                oggetti_raccolti++;
                
                punteggio_finale += 10;
            }
        }

        for (Room r : g.getRooms()) {
            if (r.isVisited() == true) {
                stanze_esplorate++;
                
                punteggio_finale += 5;
            }
        }

        for (Room r : g.getRooms()) {
            if (r.isEventComplete() == true) {
                eventi_completati++;
                
                punteggio_finale+=15;
            }
        }
        
        punteggio_sottratto = punteggio_sottratto * u.getColpiSubiti();
        
        if(punteggio_sottratto > 999){
            punteggio_sottratto = 999;
        }
        
        if(u.getColpiSubiti() > 999){
            u.setColpiSubiti(999);
        }
        
        punteggio_finale = punteggio_finale - punteggio_sottratto;
        
        if(punteggio_finale < 0){
            punteggio_finale = 0;
        }
        
        

        String query = "INSERT INTO classifica (username, stanze_esplorate, oggetti_raccolti, eventi_completati, "
                + "colpi_subiti, punteggio_sottratto, punteggio_finale) VALUES (?,?,?,?,?,?,?)";

        Properties dbprops = new Properties();

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:.\\database\\DBClassifica", dbprops);
            Statement stm = conn.createStatement();

            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setString(1, u.getUsername());
            pstm.setInt(2, stanze_esplorate);
            pstm.setInt(3, oggetti_raccolti);
            pstm.setInt(4, eventi_completati);
            pstm.setInt(5, u.getColpiSubiti());
            pstm.setInt(6, punteggio_sottratto);
            pstm.setInt(7, punteggio_finale);

            pstm.executeUpdate();

            stm.close();
            conn.close();

        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

    }

    public static void createTable() {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS classifica "
                + "(id INT AUTO_INCREMENT,"
                + "username VARCHAR(20),"
                + "stanze_esplorate INT,"
                + "oggetti_raccolti INT,"
                + "eventi_completati INT,"
                + "colpi_subiti INT,"
                + "punteggio_sottratto INT,"
                + "punteggio_finale INT)";

        Properties dbprops = new Properties();

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:.\\database\\DBClassifica", dbprops);

            Statement stm = conn.createStatement();
            stm.executeUpdate(CREATE_TABLE);
            stm.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

    }

}
