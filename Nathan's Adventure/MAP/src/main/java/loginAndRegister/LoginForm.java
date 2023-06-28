/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loginAndRegister;

import gameEngine.GameDescription;
import utils.ClassificaForm;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import entity.User;

/**
 *
 * @author 39333
 */
public class LoginForm extends javax.swing.JFrame {

    private GameDescription gameSelected = null;
    private User user = new User();
    private boolean isReady;

    /**
     * Creates new form loginForm
     */
    public LoginForm() {
        initComponents();
        this.isReady = false;
        getContentPane().requestFocusInWindow();

        this.setLocationRelativeTo(null);

        Border jpanel_title_border = BorderFactory.createMatteBorder(0, 1, 1, 1, new Color(255, 204, 0));
        jpanel_title.setBorder(jpanel_title_border);
        jPanel2.setBorder(jpanel_title_border);

        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 102));
        jLabel_close.setBorder(label_border);
        jLabel_minimize.setBorder(label_border);

        Border global_panel = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 204, 0));
        jPanel1.setBorder(global_panel);

        username_icon.setIcon(new ImageIcon(".\\resources\\NathansAdventure's images\\username icon.png"));
        Border jlabel_icon_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(153, 153, 153));
        username_icon.setBorder(jlabel_icon_border);
        password_icon.setIcon(new ImageIcon(".\\resources\\NathansAdventure's images\\password icon.jpg"));
        password_icon.setBorder(jlabel_icon_border);

        Border field_border = BorderFactory.createMatteBorder(1, 5, 1, 1, Color.white);
        jTextField_username.setBorder(field_border);
        jPasswordField1.setBorder(field_border);

        Border createAccount_border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.gray);
        jLabel_createAccount.setBorder(createAccount_border);

    }

    public boolean isReady() {
        return this.isReady;
    }

    public void setReadyState(boolean state) {
        this.isReady = state;
    }

    public GameDescription getGame() {
        return this.gameSelected;
    }

    public void setGame(GameDescription game) {
        this.gameSelected = game;
    }

    public User getUser() {
        return this.user;
    }

    public void verifyCredentials(String username, String password) {

        //create a select query to check if the username and the password exist in the database
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        Properties dbprops = new Properties();

        if (username.trim().equals("username")) {

            JOptionPane.showMessageDialog(null, "Inserisci il tuo username", "Username mancante", 2);

        } else if (password.trim().equals("password")) {

            JOptionPane.showMessageDialog(null, "Inserisci la tua password", "Password mancante", 2);

        } else {

            try {
                Connection conn = DriverManager.getConnection("jdbc:h2:.\\database\\DBUsers", dbprops);
                //nel percorso db rappresenta il nome che avrà il database creato,
                //folder_db rappresenta la cartella dove sarà contenuto.
                Statement stm = conn.createStatement();

                PreparedStatement pstm = conn.prepareStatement(query);
                pstm.setString(1, username);
                pstm.setString(2, password);

                ResultSet rs = pstm.executeQuery();

                if (rs.next()) {

                    this.user.setUsername(username);

                    this.setVisible(false);

                    StartForm sf = new StartForm(this, this.user);

                    sf.setVisible(true);
                    sf.pack();
                    sf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                } else {

                    //errore
                    JOptionPane.showMessageDialog(null, "Username / Password non validi", "Errore di Login", 2);

                }
                stm.close();
                conn.close();
            } catch (SQLException ex) {
                System.err.println(ex.getSQLState() + ": " + ex.getMessage());
            }

        }

    }

    public static void createTable() {

        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS users "
                + "(username VARCHAR(20) PRIMARY KEY,"
                + "password VARCHAR(20),"
                + "match BLOB,"
                + "battle1 BLOB,"
                + "battle2 BLOB,"
                + "battle3 BLOB,"
                + "battle4 BLOB)";

        Properties dbprops = new Properties();

        try {
            Connection conn = DriverManager.getConnection("jdbc:h2:.\\database\\DBUsers", dbprops);
            //nel percorso db rappresenta il nome che avrà il database creato,
            //folder_db rappresenta la cartella dove sarà contenuto.
            Statement stm = conn.createStatement();
            stm.executeUpdate(CREATE_TABLE);
            stm.close();
            conn.close();
        } catch (SQLException ex) {
            System.err.println(ex.getSQLState() + ": " + ex.getMessage());
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_close = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        username_icon = new javax.swing.JLabel();
        jTextField_username = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        password_icon = new javax.swing.JLabel();
        jButton_accedi = new javax.swing.JButton();
        jLabel_createAccount = new javax.swing.JLabel();
        jButton_classifica = new javax.swing.JButton();
        jButton_esci = new javax.swing.JButton();
        jLabel_minimize = new javax.swing.JLabel();
        jpanel_title = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jPanel2.setBackground(new java.awt.Color(204, 153, 255));
        jPanel2.setForeground(new java.awt.Color(0, 255, 0));

        jLabel_close.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_close.setForeground(new java.awt.Color(0, 0, 102));
        jLabel_close.setText(" X ");
        jLabel_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseExited(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));
        jPanel3.setForeground(new java.awt.Color(153, 153, 255));

        username_icon.setBackground(new java.awt.Color(255, 255, 0));
        username_icon.setForeground(new java.awt.Color(255, 255, 0));
        username_icon.setOpaque(true);

        jTextField_username.setForeground(new java.awt.Color(153, 153, 153));
        jTextField_username.setText("username");
        jTextField_username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_usernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_usernameFocusLost(evt);
            }
        });

        jPasswordField1.setForeground(new java.awt.Color(153, 153, 153));
        jPasswordField1.setText("password");
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });

        password_icon.setBackground(new java.awt.Color(255, 255, 0));
        password_icon.setOpaque(true);

        jButton_accedi.setBackground(new java.awt.Color(0, 0, 102));
        jButton_accedi.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_accedi.setForeground(new java.awt.Color(255, 255, 255));
        jButton_accedi.setText("Accedi");
        jButton_accedi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_accediActionPerformed(evt);
            }
        });

        jLabel_createAccount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel_createAccount.setForeground(new java.awt.Color(102, 0, 0));
        jLabel_createAccount.setText(">>Non hai un account? Creane uno!");
        jLabel_createAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_createAccountMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_createAccountMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_createAccountMouseExited(evt);
            }
        });

        jButton_classifica.setBackground(new java.awt.Color(0, 0, 102));
        jButton_classifica.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_classifica.setForeground(new java.awt.Color(255, 255, 255));
        jButton_classifica.setText("Classifica");
        jButton_classifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_classificaActionPerformed(evt);
            }
        });

        jButton_esci.setBackground(new java.awt.Color(0, 0, 102));
        jButton_esci.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton_esci.setForeground(new java.awt.Color(255, 255, 255));
        jButton_esci.setText("Esci");
        jButton_esci.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_esciActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(75, 75, 75)
                            .addComponent(password_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(146, 146, 146)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel_createAccount)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton_classifica, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                                    .addComponent(jButton_accedi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton_esci, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(username_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jButton_accedi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_username, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(username_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(password_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_createAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton_classifica, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_esci, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel_minimize.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel_minimize.setForeground(new java.awt.Color(0, 0, 102));
        jLabel_minimize.setText(" - ");
        jLabel_minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel_minimizeMouseExited(evt);
            }
        });

        jpanel_title.setBackground(new java.awt.Color(0, 0, 102));

        jLabel3.setFont(new java.awt.Font("Segoe Script", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Nathan's adventure");

        javax.swing.GroupLayout jpanel_titleLayout = new javax.swing.GroupLayout(jpanel_title);
        jpanel_title.setLayout(jpanel_titleLayout);
        jpanel_titleLayout.setHorizontalGroup(
            jpanel_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_titleLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        jpanel_titleLayout.setVerticalGroup(
            jpanel_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpanel_titleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe Script", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("login");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 102));
        jLabel2.setText("Game created by: Volpicella S.D. /  Valentino F. /  Sequenza R.A.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jpanel_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel_minimize)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel_close)
                        .addGap(25, 25, 25))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel_close)
                            .addComponent(jLabel_minimize)))
                    .addComponent(jpanel_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(204, 204, 204))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_minimizeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseEntered
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabel_minimize.setBorder(label_border);
        jLabel_minimize.setForeground(Color.white);
    }//GEN-LAST:event_jLabel_minimizeMouseEntered

    private void jLabel_minimizeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseExited
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 102));
        jLabel_minimize.setBorder(label_border);
        jLabel_minimize.setForeground(new Color(0, 0, 102));
    }//GEN-LAST:event_jLabel_minimizeMouseExited

    private void jLabel_closeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseEntered
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white);
        jLabel_close.setBorder(label_border);
        jLabel_close.setForeground(Color.white);
    }//GEN-LAST:event_jLabel_closeMouseEntered

    private void jLabel_closeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseExited
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0, 0, 102));
        jLabel_close.setBorder(label_border);
        jLabel_close.setForeground(new Color(0, 0, 102));
    }//GEN-LAST:event_jLabel_closeMouseExited

    private void jTextField_usernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_usernameFocusGained
        // TODO add your handling code here:

        /*
        Nel caso in cui si inserisce un username allora la scritta username deve sparire
        Ciò deve accadere solo nel caso in cui è scritto username: se ad esempio l'utente aveva iniziato a
        scrivere un nome e poi ritorna a scrivere qui, allora ciò che aveva scritto non deve essere cancellato!
         */
        if (jTextField_username.getText().trim().toLowerCase().equals("username")) {
            jTextField_username.setText("");
            jTextField_username.setForeground(Color.black);
        }

        //il bordo dell'icona dell'utente cambia colore quando si vuole scrivere nel campo utente
        Border jlabel_icon_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 204, 0));
        username_icon.setBorder(jlabel_icon_border);


    }//GEN-LAST:event_jTextField_usernameFocusGained

    private void jTextField_usernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_usernameFocusLost
        // TODO add your handling code here:
        /*Se il campo usarname è vuoto o contiene username allora inseriremo "username" */
        if (jTextField_username.getText().trim().toLowerCase().equals("username")
                || jTextField_username.getText().trim().toLowerCase().equals("")) {
            jTextField_username.setText("username");
            jTextField_username.setForeground(new Color(153, 153, 153));
        }

        //il colore del bordo ritorna normale se focus lost
        Border jlabel_icon_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(153, 153, 153));
        username_icon.setBorder(jlabel_icon_border);

    }//GEN-LAST:event_jTextField_usernameFocusLost

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        // TODO add your handling code here:

        //pulisce il campo dedicato alla password se all'interno c'è "password" in caso di scrittura
        String pass = String.valueOf(jPasswordField1.getPassword());

        if (pass.trim().toLowerCase().equals("password")) {
            jPasswordField1.setText("");
            jPasswordField1.setForeground(Color.black);
        }
        Border jlabel_icon_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(255, 204, 0));
        password_icon.setBorder(jlabel_icon_border);

    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        // TODO add your handling code here:

        //se il campo password, una volta perso il focus, è vuoto o contiene password allora si scriverà password
        String pass = String.valueOf(jPasswordField1.getPassword());

        if (pass.trim().toLowerCase().equals("password") || pass.trim().toLowerCase().equals("")) {
            jPasswordField1.setText("password");
            jPasswordField1.setForeground(new Color(153, 153, 153));
        }

        Border jlabel_icon_border = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(153, 153, 153));
        password_icon.setBorder(jlabel_icon_border);

    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jLabel_minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_minimizeMouseClicked
        // TODO add your handling code here:

        this.setState(JFrame.ICONIFIED);

    }//GEN-LAST:event_jLabel_minimizeMouseClicked

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void jButton_accediActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_accediActionPerformed
        // TODO add your handling code here:

        String username = jTextField_username.getText();
        String password = String.valueOf(jPasswordField1.getPassword());

        verifyCredentials(username, password);

    }//GEN-LAST:event_jButton_accediActionPerformed

    private void jLabel_createAccountMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_createAccountMouseEntered
        // TODO add your handling code here:
        Border label_border = BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(102, 0, 0));
        jLabel_createAccount.setBorder(label_border);
    }//GEN-LAST:event_jLabel_createAccountMouseEntered

    private void jLabel_createAccountMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_createAccountMouseExited
        // TODO add your handling code here:
        //Border createAccount_border = BorderFactory.createMatteBorder(0,0,1,0,Color.gray);
        jLabel_createAccount.setBorder(null);
    }//GEN-LAST:event_jLabel_createAccountMouseExited

    private void jLabel_createAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_createAccountMouseClicked
        // TODO add your handling code here:

        RegisterForm rf = new RegisterForm(this);
        rf.setVisible(true);
        rf.pack();
        rf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(false);

    }//GEN-LAST:event_jLabel_createAccountMouseClicked

    private void jButton_esciActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_esciActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton_esciActionPerformed

    private void jButton_classificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_classificaActionPerformed

        ClassificaForm cf = new ClassificaForm();
        cf.setVisible(true);
        cf.pack();
        cf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton_classificaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_accedi;
    private javax.swing.JButton jButton_classifica;
    private javax.swing.JButton jButton_esci;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JLabel jLabel_createAccount;
    private javax.swing.JLabel jLabel_minimize;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JPanel jpanel_title;
    private javax.swing.JLabel password_icon;
    private javax.swing.JLabel username_icon;
    // End of variables declaration//GEN-END:variables
}
