/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battle;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import entity.User;

/**
 *
 * @author valen
 */
public class AttaccoNemico extends Thread {

    private Combattimento combattimento;

    private User user;

    public AttaccoNemico(Combattimento combattimento, User u) {
        this.combattimento = combattimento;
        this.user = u;

    }

    @Override
    public void run() {

        while (combattimento.isActive()) {

            System.out.print("");

            if ((combattimento.getTurno() % 2) != 0) {

                StyledDocument document = (StyledDocument) combattimento.getConsolle().getDocument();

                SimpleAttributeSet attribs = new SimpleAttributeSet();

                String avvisoAttacco = "\n" + combattimento.getNomeNemico().toUpperCase() + " TI STA ATTACCANDO\n";
                String attacco = combattimento.getNomeNemico().toUpperCase() + ": Prendi questo!\n";

                StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
                combattimento.getConsolle().setParagraphAttributes(attribs, true);

                if (combattimento.continua()) {
                    try {
                        document.insertString(document.getLength(), avvisoAttacco, attribs);
                        Thread.sleep(500);
                    } catch (BadLocationException | InterruptedException ex) {
                        Logger.getLogger(AttaccoNemico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (combattimento.continua()) {
                    try {
                        document.insertString(document.getLength(), "3\n", attribs);
                        if (combattimento.getNomeNemico().equals("Bandito Assonnato")) {
                            Thread.sleep(1000);
                        } else if (combattimento.getNomeNemico().equals("Bandito Infuriato")) {
                            Thread.sleep(800);
                        } else if (combattimento.getNomeNemico().equals("Malzar")) {
                            Thread.sleep(600);
                        } else if (combattimento.getNomeNemico().equals("Demone")) {
                            Thread.sleep(400);
                        }

                    } catch (BadLocationException | InterruptedException ex) {
                        Logger.getLogger(AttaccoNemico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (combattimento.continua()) {
                    try {
                        document.insertString(document.getLength(), "2\n", attribs);
                        if (combattimento.getNomeNemico().equals("Bandito Assonnato")) {
                            Thread.sleep(1000);
                        } else if (combattimento.getNomeNemico().equals("Bandito Infuriato")) {
                            Thread.sleep(800);
                        } else if (combattimento.getNomeNemico().equals("Malzar")) {
                            Thread.sleep(600);
                        } else if (combattimento.getNomeNemico().equals("Demone")) {
                            Thread.sleep(400);
                        }
                    } catch (BadLocationException | InterruptedException ex) {
                        Logger.getLogger(AttaccoNemico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (combattimento.continua()) {
                    try {
                        document.insertString(document.getLength(), "1\n", attribs);
                        if (combattimento.getNomeNemico().equals("Bandito Assonnato")) {
                            Thread.sleep(1000);
                        } else if (combattimento.getNomeNemico().equals("Bandito Infuriato")) {
                            Thread.sleep(800);
                        } else if (combattimento.getNomeNemico().equals("Malzar")) {
                            Thread.sleep(600);
                        } else if (combattimento.getNomeNemico().equals("Demone")) {
                            Thread.sleep(400);
                        }
                    } catch (BadLocationException | InterruptedException ex) {
                        Logger.getLogger(AttaccoNemico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (combattimento.continua()) {
                    try {
                        document.insertString(document.getLength(), attacco, attribs);
                        combattimento.setContinua(false);
                        combattimento.setTurno(combattimento.getTurno() + 1);

                        user.setColpiSubiti(user.getColpiSubiti() + 1);

                        Thread.sleep(300);
                    } catch (BadLocationException | InterruptedException ex) {
                        Logger.getLogger(AttaccoNemico.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
