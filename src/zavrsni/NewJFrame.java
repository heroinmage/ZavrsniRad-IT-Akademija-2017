/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zavrsni;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author boost
 */
public class NewJFrame extends javax.swing.JFrame {

    static String url = "jdbc:mysql://localhost:3306/stomatologija?zeroDateTimeBehavior=convertToNull";
    static String user = "root";
    static String password = "xxxx";

    String brojevi = "[0-9]+"; // definisem regex za proveru brojeva 
    String slova = "[a-zA-z ]+"; //definisem regex za proveru slova

    public NewJFrame() {

        initComponents();
        Update_table(); // pozivam metodu da napuni tabelu sa trenutnim stanjem iz baze
        UpdateAdminTable();
    }

    public boolean proveraZaJmbg() {
        String sql = "SELECT * FROM pacijenti WHERE jmbg =?";

        try {

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, jmbgField.getText());
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public boolean proveraZaJmbg2() {
        String sql = "SELECT * FROM pacijenti WHERE jmbg =?";

        try {

            try (Connection conn = DriverManager.getConnection(url, user, password)) {

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, jmbgUpdateField.getText());
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void UpdateAdminTable() {
        String sql = "SELECT IME,PREZIME,POL,TELEFON,POSAO FROM zaposleni";
        String sql2 = "SELECT USERNAME,PASSWORD,JMBG,ADMINACC FROM zaposlenidetails";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);
            PreparedStatement pst = conn.prepareCall(sql2);

            ResultSet rs = ps.executeQuery();
            ResultSet rs2 = pst.executeQuery();

            tabelaAdmin.setModel(DbUtils.resultSetToTableModel(rs));
            tabelaAdminDetails.setModel(DbUtils.resultSetToTableModel(rs2));

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void Update_table() { // ovde pravim metodu da updat-uje tabelu pri paljenju programa

        String sql = "SELECT IME,PREZIME,JMBG,TELEFON,POL,USLUGA,KALENDAR FROM pacijenti";                 //selektujem sve iz tabele pacijenti

        try {
            //try metoda sa resursima 

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery(sql);

                tabela.setModel(DbUtils.resultSetToTableModel(rs));

            }
            UpdateAdminTable();
        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void pretraga() {

        String sql = "SELECT IME,PREZIME,JMBG,TELEFON,POL,USLUGA,KALENDAR FROM pacijenti WHERE "
                + odabirSearcha.getSelectedItem().toString().toLowerCase() + "= ?";

        try {
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = null;
                String uslov = odabirSearcha.getSelectedItem().toString().toLowerCase();
                String parametar = searchField1.getText();
                switch (uslov) {
                    case "ime":
                        if (parametar.matches(slova)) {

                            ps.setString(1, parametar);
                            rs = ps.executeQuery();
                            tabela.setModel(DbUtils.resultSetToTableModel(rs));
                        } else {
                            upozorenje2.setText("Nepravilan unos za  Ime");
                        }
                        break;
                    case "prezime":
                        if (parametar.matches(slova)) {

                            ps.setString(1, parametar);
                            rs = ps.executeQuery();
                            tabela.setModel(DbUtils.resultSetToTableModel(rs));

                        } else {
                            upozorenje2.setText("Nepravilan unos za  Prezime");
                        }
                        break;
                    case "jmbg":
                        if (parametar.matches(brojevi) && searchField1.getText().length() == 13) {

                            ps.setString(1, parametar);
                            rs = ps.executeQuery();
                            tabela.setModel(DbUtils.resultSetToTableModel(rs));

                        } else {
                            upozorenje2.setText("Nepravilan unos za JMBG");
                        }
                        break;
                    default:
                        upozorenje2.setText("Doslo je do neke greske");
                        break;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        imeField = new javax.swing.JTextField();
        prezimeField = new javax.swing.JTextField();
        telefonField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        uslugaBox = new javax.swing.JComboBox<>();
        jmbgField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        polmuski1 = new javax.swing.JRadioButton();
        polzenski = new javax.swing.JRadioButton();
        upozorenje = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        mrezaBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        zubarComboBOx = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        prezimeUpdateField = new javax.swing.JTextField();
        telefonUpdateField = new javax.swing.JTextField();
        odabirSearcha = new javax.swing.JComboBox<>();
        searchField1 = new javax.swing.JTextField();
        searchDugme = new javax.swing.JButton();
        imeUpdateField = new javax.swing.JTextField();
        deleteButton = new javax.swing.JButton();
        jmbgUpdateField = new javax.swing.JTextField();
        polUpdateBox = new javax.swing.JComboBox<>();
        uslugaBox1 = new javax.swing.JComboBox<>();
        updateButton = new javax.swing.JButton();
        upozorenje2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Prezime = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Telefon = new javax.swing.JLabel();
        Pol = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        JMBG = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabelaAdmin = new javax.swing.JTable();
        adminUpdateButton2 = new javax.swing.JButton();
        adminRefreshButton = new javax.swing.JButton();
        AdminPrezimeUpdateField = new javax.swing.JTextField();
        adminImeUpdateField = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        Prezime1 = new javax.swing.JLabel();
        adminUsernameUpdateField = new javax.swing.JTextField();
        Telefon1 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        JMBG1 = new javax.swing.JLabel();
        adminAccField = new javax.swing.JTextField();
        adminUpdateButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaAdminDetails = new javax.swing.JTable();
        adminJmbgUpdateField1 = new javax.swing.JTextField();
        adminPasswordUpdateField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        adminDeleteBtn1 = new javax.swing.JButton();
        upozorenje3 = new javax.swing.JLabel();

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Stomatoloska Ordinacija");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 768));

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(1024, 768));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setText("Prezime :");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel2.setText("Ime:");

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel3.setText("Broj telefona :");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setText("JMBG:");

        imeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imeFieldActionPerformed(evt);
            }
        });

        prezimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prezimeFieldActionPerformed(evt);
            }
        });

        telefonField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefonField1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setText("Vrsta Usluge:");

        uslugaBox.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        uslugaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pregled", "Plombiranje", "Izbeljivanje zuba", "Uklanjanje kamenca", "Proteze", "Vestacke vilice x1", "Vestacke vilice x2" }));
        uslugaBox.setMinimumSize(new java.awt.Dimension(4, 19));
        uslugaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uslugaBoxActionPerformed(evt);
            }
        });

        jmbgField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmbgFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel4.setText("Pol :");

        buttonGroup1.add(polmuski1);
        polmuski1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        polmuski1.setSelected(true);
        polmuski1.setText("Muški");
        polmuski1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polmuski1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(polzenski);
        polzenski.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        polzenski.setText("Ženski");
        polzenski.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polzenskiActionPerformed(evt);
            }
        });

        upozorenje.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        upozorenje.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jButton1.setText("Registruj uslugu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        mrezaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "+38161", "+38162", "+38163", "+38164", "+38165", "+38166", "+38169" }));
        mrezaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mrezaBoxActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tooth.png"))); // NOI18N
        jLabel8.setText("jLabel7");

        zubarComboBOx.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        zubarComboBOx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Zubar 1", "Zubar 2" }));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel11.setText("Stomatolog: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(upozorenje, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel11))
                .addGap(329, 329, 329)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(prezimeField, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(imeField, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jmbgField, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(uslugaBox, 0, 190, Short.MAX_VALUE)
                    .addComponent(polzenski, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(polmuski1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(mrezaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(telefonField1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(zubarComboBOx, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(256, 256, 256))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(upozorenje, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(imeField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(prezimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(telefonField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jmbgField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addComponent(mrezaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(polmuski1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(polzenski, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(uslugaBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(zubarComboBOx, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 750));

        jTabbedPane1.addTab("Prijava korisnika", jPanel1);

        jPanel3.setPreferredSize(new java.awt.Dimension(1024, 768));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabela.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabelaFocusGained(evt);
            }
        });
        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabela);

        odabirSearcha.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        odabirSearcha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ime", "Prezime", "JMBG" }));
        odabirSearcha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                odabirSearchaFocusGained(evt);
            }
        });
        odabirSearcha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                odabirSearchaActionPerformed(evt);
            }
        });

        searchField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchField1ActionPerformed(evt);
            }
        });
        searchField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchField1KeyPressed(evt);
            }
        });

        searchDugme.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        searchDugme.setText("Search");
        searchDugme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchDugmeActionPerformed(evt);
            }
        });

        imeUpdateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imeUpdateFieldActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        deleteButton.setText("DELETE");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        polUpdateBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "muski", "zenski" }));

        uslugaBox1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        uslugaBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pregled", "Plombiranje", "Izbeljivanje zuba", "Uklanjanje kamenca", "Proteze", "Vestacke vilice x1", "Vestacke vilice x2" }));
        uslugaBox1.setMinimumSize(new java.awt.Dimension(4, 19));
        uslugaBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uslugaBox1ActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        updateButton.setText("UPDATE");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        upozorenje2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tooth.png"))); // NOI18N
        jLabel7.setText("jLabel7");

        Prezime.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Prezime.setText("Prezime");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel9.setText("Ime");

        Telefon.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Telefon.setText("Telefon");

        Pol.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Pol.setText("Pol");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel12.setText("Usluga");

        JMBG.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        JMBG.setText("JMBG");

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh-icon.png"))); // NOI18N
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(imeUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Prezime, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prezimeUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(Pol, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(polUpdateBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(telefonUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(uslugaBox1, 0, 1, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JMBG)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jmbgUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateButton)))
                        .addGap(142, 142, 142))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(odabirSearcha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(searchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchDugme)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(upozorenje2, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(deleteButton)
                                    .addComponent(refreshButton)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(upozorenje2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(odabirSearcha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchDugme, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Prezime)
                    .addComponent(jLabel9)
                    .addComponent(Telefon)
                    .addComponent(Pol)
                    .addComponent(jLabel12)
                    .addComponent(JMBG))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imeUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prezimeUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(polUpdateBox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jmbgUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(uslugaBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton))
                .addGap(54, 54, 54))
        );

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 600));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 600));

        jTabbedPane1.addTab("Svi korisnici", jPanel3);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/tooth.png"))); // NOI18N

        tabelaAdmin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaAdmin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tabelaAdminFocusGained(evt);
            }
        });
        tabelaAdmin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAdminMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabelaAdmin);

        adminUpdateButton2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        adminUpdateButton2.setText("UPDATE");
        adminUpdateButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminUpdateButton2ActionPerformed(evt);
            }
        });

        adminRefreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/refresh-icon.png"))); // NOI18N
        adminRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminRefreshButtonActionPerformed(evt);
            }
        });

        adminImeUpdateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminImeUpdateFieldActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel40.setText("Ime");

        Prezime1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Prezime1.setText("Prezime");

        Telefon1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Telefon1.setText("Password");

        jLabel41.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel41.setText("JMBG");

        JMBG1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        JMBG1.setText("Username");

        adminUpdateButton1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        adminUpdateButton1.setText("UPDATE");
        adminUpdateButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminUpdateButton1ActionPerformed(evt);
            }
        });

        tabelaAdminDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaAdminDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaAdminDetailsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabelaAdminDetails);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel10.setText("Admin");

        adminDeleteBtn1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        adminDeleteBtn1.setText("Delete");
        adminDeleteBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminDeleteBtn1ActionPerformed(evt);
            }
        });

        upozorenje3.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(29, 29, 29)
                        .addComponent(upozorenje3, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(adminImeUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(adminDeleteBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Prezime1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(adminUpdateButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(AdminPrezimeUpdateField)))))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(adminRefreshButton)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(adminUpdateButton2)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(adminUsernameUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(JMBG1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addComponent(adminPasswordUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(adminJmbgUpdateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(adminAccField, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel11Layout.createSequentialGroup()
                                            .addComponent(Telefon1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel11Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(upozorenje3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel11Layout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addComponent(adminRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(72, 72, 72))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Prezime1)
                            .addComponent(Telefon1)
                            .addComponent(jLabel41)
                            .addComponent(JMBG1)
                            .addComponent(jLabel40)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AdminPrezimeUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminAccField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminJmbgUpdateField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminPasswordUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminUsernameUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminImeUpdateField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminUpdateButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminDeleteBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminUpdateButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(209, Short.MAX_VALUE))
        );

        jPanel6.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 740));

        jTabbedPane1.addTab("Administracija", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        Update_table();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed

        //selektujem sve iz tabele pacijenti
        String sql = "UPDATE pacijenti SET IME='" + imeUpdateField.getText()
                + "', PREZIME = '" + prezimeUpdateField.getText() + "', POL ='"
                + polUpdateBox.getSelectedItem().toString() + "', TELEFON='"
                + telefonUpdateField.getText() + "', USLUGA = '"
                + uslugaBox1.getSelectedItem().toString() + "', JMBG='"
                + jmbgUpdateField.getText() + "' WHERE JMBG =?";
        try {

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                PreparedStatement ps = conn.prepareStatement(sql);
                if (jmbgUpdateField.getText().matches(brojevi) && jmbgUpdateField.getText().length() == 13) {
                    if (imeUpdateField.getText().matches(slova)) {
                        if (prezimeUpdateField.getText().matches(slova)) {
                            if (telefonUpdateField.getText().matches(brojevi)) {
                                TableModel model = tabela.getModel();
                                ps.setString(1, String.valueOf(model.getValueAt(tabela.getSelectedRow(), 2)));

                                ps.execute();
                                upozorenje2.setText("USPESAN UPDATE");
                            } else {
                                upozorenje2.setText("Greska pri unosu broja telefona");
                            }
                        } else {
                            upozorenje2.setText("Greska pri unosu prezimena");
                        }
                    } else {
                        upozorenje2.setText("Greska pri unosu imena");
                    }
                } else {
                    upozorenje2.setText("Greska pri unosu JMBG-a");
                }

                Update_table();
                upozorenje2.setText("");
            }

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void uslugaBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uslugaBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uslugaBox1ActionPerformed
    public void hideTab() {

        jTabbedPane1.setEnabledAt(2, false);
    }
    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int action = JOptionPane.showConfirmDialog(null, "Da li ste sigurni?", "Delete", JOptionPane.YES_NO_OPTION);

        String sqls = "SELECT PACIJENT_ID FROM pacijenti WHERE jmbg=?";
        String sqlu = "DELETE FROM zaposleni_pacijenti where PACIJENT_ID =?";
        String sql = "DELETE FROM pacijenti WHERE PACIJENT_ID=?";
        String sqli = "DELETE FROM zaposleni WHERE ZAPOSLENI_ID=?";

        if (action == 0) {
            try {
                //try metoda sa resursima

                try (Connection conn = DriverManager.getConnection(url, user, password)) {
                    PreparedStatement ps = conn.prepareStatement(sqls);
                    PreparedStatement psp = conn.prepareStatement(sqlu);
                    PreparedStatement pss = conn.prepareStatement(sql);
                    PreparedStatement psq = conn.prepareStatement(sqli);

                    ps.setString(1, String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 2)));
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        psp.setInt(1, rs.getInt("PACIJENT_ID"));
                        psp.executeUpdate();

                        pss.setInt(1, rs.getInt("PACIJENT_ID"));
                        pss.executeUpdate();
                    }

                    psq.setInt(1, rs.getInt("PACIJENT_ID"));
                    psq.executeUpdate();

                    Update_table();

                }

            } catch (SQLException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void searchDugmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchDugmeActionPerformed
        pretraga();
    }//GEN-LAST:event_searchDugmeActionPerformed

    private void searchField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchField1ActionPerformed

    private void odabirSearchaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_odabirSearchaActionPerformed

    }//GEN-LAST:event_odabirSearchaActionPerformed

    private void odabirSearchaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_odabirSearchaFocusGained

    }//GEN-LAST:event_odabirSearchaFocusGained

    private void mrezaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mrezaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mrezaBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {

            try (Connection conn = DriverManager.getConnection(url, user, password)) {

                if (jmbgField.getText().matches(brojevi) && jmbgField.getText().length() == 13 && proveraZaJmbg() != true) {
                    if (imeField.getText().matches(slova)) {
                        if (prezimeField.getText().matches(slova)) {
                            if (telefonField1.getText().matches(brojevi)) {

                                String sql = "SELECT * FROM zaposleni WHERE ZAPOSLENI_ID =?";
                                PreparedStatement ps = conn.prepareStatement(sql);
                                ResultSet rs;
                                Set<Pacijent> pacijenti = new HashSet<>(0);

                                Pacijent p1 = new Pacijent(imeField.getText(), prezimeField.getText(), 
                                        uslugaBox.getSelectedItem().toString(), jmbgField.getText());
                                
                                p1.setUsluga(uslugaBox.getSelectedItem().toString());

                                switch (mrezaBox.getSelectedIndex()) {  // switch struktura koja odlucuje koju je mrezu korisnik
                                    //izabrao iz JComboBox-a i dodaje prefix na tekst unet iz JTextField-a za broj telefona

                                    case 0:
                                        p1.setKontaktTelefon("38161" + telefonField1.getText());
                                        break;
                                    case 1: {
                                        p1.setKontaktTelefon("38162" + telefonField1.getText());
                                        break;
                                    }
                                    case 2: {
                                        p1.setKontaktTelefon("38163" + telefonField1.getText());
                                        break;
                                    }
                                    case 3: {
                                        p1.setKontaktTelefon("38164" + telefonField1.getText());
                                        break;
                                    }
                                    case 4: {
                                        p1.setKontaktTelefon("38165" + telefonField1.getText());
                                        break;
                                    }
                                    case 5: {
                                        p1.setKontaktTelefon("38166" + telefonField1.getText());
                                        break;
                                    }
                                    case 6: {
                                        p1.setKontaktTelefon("38169" + telefonField1.getText());
                                        break;
                                    }
                                }

                                PreparedStatement psp = conn.prepareStatement("SELECT * FROM zaposlenidetails WHERE zid =?");
                                ResultSet rsr;
                                Zaposleni z1 = null;
                                ZaposleniDetails zd = null;

                                if (polmuski1.isSelected()) {
                                    p1.setPol("muski");
                                } else {
                                    p1.setPol("zenski");
                                }

                                switch (zubarComboBOx.getSelectedIndex()) {
                                    case 0:
                                        ps.setInt(1, 1);
                                        psp.setInt(1, 1);

                                        rs = ps.executeQuery();
                                        rsr = psp.executeQuery();

                                        pacijenti.add(p1);
                                        if (rs.next() && rsr.next()) {
                                            z1 = new Zaposleni(rs.getString("IME"), rs.getString("PREZIME"), rs.getString("TELEFON"), rs.getString("POSAO"), rs.getString("POL"), pacijenti);

                                            zd = new ZaposleniDetails(rsr.getString("USERNAME"), rsr.getString("PASSWORD"), rsr.getString("JMBG"), rsr.getInt("adminAcc"));
                                        }

                                        break;
                                    case 1:

                                        ps.setInt(1, 2);
                                        psp.setInt(1, 2);

                                        rs = ps.executeQuery();
                                        rsr = psp.executeQuery();

                                        pacijenti.add(p1);
                                        if (rs.next() && rsr.next()) {
                                            z1 = new Zaposleni(rs.getString("IME"), rs.getString("PREZIME"), rs.getString("TELEFON"), rs.getString("POSAO"), rs.getString("POL"), pacijenti);

                                            zd = new ZaposleniDetails(rsr.getString("USERNAME"), rsr.getString("PASSWORD"), rsr.getString("JMBG"), rsr.getInt("adminAcc"));

                                        }
                                        break;
                                }

                                Session session = HibernateUtil.getFactory().openSession();
                                Transaction tx = session.beginTransaction();

                                try {
                                    zd.setZaposleni(z1);
                                    z1.setzDetails(zd);

                                    session.persist(z1);
                                    tx.commit();
                                } catch (Exception e) {

                                } finally {
                                    session.flush();
                                    session.close();

                                }

                                upozorenje.setText("Uspesno prijavljen korisnik");

                            } else {
                                upozorenje.setText("Nepravilan unos telefona");
                            }
                        } else {
                            upozorenje.setText("Nepravilan unos Prezimena");
                        }
                    } else {
                        upozorenje.setText("Nepravilan unos Imena");
                    }
                } else {
                    upozorenje.setText("Nepravilan JMBG unos");
                }

            } catch (NumberFormatException numberFormatException) {
                upozorenje.setText("Doslo je do greske u unosu! ");
            }

            

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            upozorenje.setText("doslo je do greske");

        }
        Update_table();
    }//GEN-LAST:event_jButton1ActionPerformed
    private void popuniAdminDetailsPoljaZaUpdate() {
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql1 = "SELECT username,password,jmbg,adminAcc FROM ZAPOSLENIDETAILS WHERE jmbg =?";
            PreparedStatement psp = conn.prepareStatement(sql1);

            psp.setString(1, String.valueOf(tabelaAdminDetails.getValueAt(tabelaAdminDetails.getSelectedRow(), 2)));
            ResultSet rss = psp.executeQuery();

            while (rss.next()) {

                adminUsernameUpdateField.setText(rss.getString("username"));
                adminPasswordUpdateField.setText(rss.getString("password"));
                adminJmbgUpdateField1.setText(rss.getString("jmbg"));
                adminAccField.setText(rss.getString("adminAcc"));
            }
            UpdateAdminTable();
            TableModel model2 = tabelaAdminDetails.getModel();
            tabelaAdminDetails.setRowSelectionInterval
        (getAdmin2RowByValue(model2, adminJmbgUpdateField1.getText()), getAdmin2RowByValue(model2, adminJmbgUpdateField1.getText()));

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void popuniAdminPoljaZaUpdate() {

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "SELECT IME,PREZIME FROM ZAPOSLENI WHERE PREZIME=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, String.valueOf(tabelaAdmin.getValueAt(tabelaAdmin.getSelectedRow(), 1)));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                adminImeUpdateField.setText(rs.getString("IME"));
                AdminPrezimeUpdateField.setText(rs.getString("PREZIME"));
            }

            UpdateAdminTable();
            TableModel model = tabelaAdmin.getModel();
            tabelaAdmin.setRowSelectionInterval(getAdminRowByValue(model, 
                    AdminPrezimeUpdateField.getText()), getAdminRowByValue(model, AdminPrezimeUpdateField.getText()));

        } catch (SQLException e) {

        }
    }

    private void popunPoljaZaUpdate() {
        String sql = "SELECT IME,PREZIME,TELEFON,JMBG FROM pacijenti where jmbg =?";
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, String.valueOf(tabela.getValueAt(tabela.getSelectedRow(), 2)));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                imeUpdateField.setText(rs.getString("IME"));
                prezimeUpdateField.setText(rs.getString("PREZIME"));
                telefonUpdateField.setText(rs.getString("TELEFON"));
                jmbgUpdateField.setText(rs.getString("JMBG"));
            }

            Update_table();
            TableModel model = tabela.getModel();
            tabela.setRowSelectionInterval(getRowByValue(model , jmbgUpdateField.getText()), getRowByValue(model, jmbgUpdateField.getText()));/// jako bitan deo koda za ostavljanje selekcije na tabeli

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private int getRowByValue(TableModel model, String jmbg) {
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            for (int j = model.getColumnCount() - 1; j >= 0; --j) {
                if (model.getValueAt(i, j).equals(jmbg)) {

                    return i;
                }
            }
        }
        return 0;
    }

    private int getAdminRowByValue(TableModel model, String prezime) {
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            for (int j = model.getColumnCount() - 1; j >= 0; --j) {
                if (model.getValueAt(i, j).equals(prezime)) {

                    return i;
                }
            }
        }
        return 0;
    }

    private int getAdmin2RowByValue(TableModel model, String jmbg) {
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            for (int j = model.getColumnCount() - 1; j >= 0; --j) {
                if (model.getValueAt(i, j).equals(jmbg)) {

                    return i;
                }
            }
        }
        return 0;
    }
    private void polzenskiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polzenskiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polzenskiActionPerformed

    private void polmuski1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polmuski1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_polmuski1ActionPerformed

    private void jmbgFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmbgFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jmbgFieldActionPerformed

    private void uslugaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uslugaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_uslugaBoxActionPerformed

    private void telefonField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefonField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefonField1ActionPerformed

    private void prezimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prezimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prezimeFieldActionPerformed

    private void imeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imeFieldActionPerformed

    private void tabelaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabelaFocusGained

    }//GEN-LAST:event_tabelaFocusGained

    private void tabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaMouseClicked
        popunPoljaZaUpdate();
    }//GEN-LAST:event_tabelaMouseClicked

    private void adminDeleteBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminDeleteBtn1ActionPerformed
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            String sql = "DELETE  FROM zaposlenidetails WHERE  zid=?";
            String sql1 = "DELETE  FROM ZAPOSLENI_PACIJENTI WHERE ZAPOSLENI_ID=?";
            String sql2 = "DELETE  FROM ZAPOSLENI WHERE prezime=?";
            String geter = "SELECT ZAPOSLENI_ID FROM ZAPOSLENI WHERE prezime=?";

            PreparedStatement pss = conn.prepareStatement(geter);
            pss.setString(1, String.valueOf(tabelaAdmin.getValueAt(tabelaAdmin.getSelectedRow(), 1)));
            ResultSet rs = pss.executeQuery();

            PreparedStatement ps = conn.prepareStatement(sql);

            while (rs.next()) {
                ps.setInt(1, rs.getInt("ZAPOSLENI_ID"));
            }
            rs.beforeFirst();
            ps.execute();

            PreparedStatement ps1 = conn.prepareStatement(sql1);

            while (rs.next()) {
                ps1.setInt(1, rs.getInt("ZAPOSLENI_ID"));
            }
            rs.beforeFirst();
            ps1.execute();

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, String.valueOf(tabelaAdmin.getValueAt(tabelaAdmin.getSelectedRow(), 1)));
            ps2.execute();
            UpdateAdminTable();
            popuniAdminPoljaZaUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_adminDeleteBtn1ActionPerformed

    private void adminUpdateButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminUpdateButton1ActionPerformed

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            if(adminImeUpdateField.getText().matches(slova) && AdminPrezimeUpdateField.getText().matches(slova)){
                String sql = "UPDATE ZAPOSLENI SET IME='" + adminImeUpdateField.getText() + "',PREZIME='" + AdminPrezimeUpdateField.getText() + "' WHERE TELEFON=?";

                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, String.valueOf(tabelaAdmin.getValueAt(tabelaAdmin.getSelectedRow(), 3)));
                ps.executeUpdate();

                popuniAdminPoljaZaUpdate();
                
            }else{upozorenje3.setText("Nepravilan format za ime i prezime");}

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_adminUpdateButton1ActionPerformed

    private void adminRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminRefreshButtonActionPerformed
        UpdateAdminTable();
    }//GEN-LAST:event_adminRefreshButtonActionPerformed

    private void adminUpdateButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminUpdateButton2ActionPerformed
        String sql = "UPDATE zaposlenidetails SET USERNAME='" + adminUsernameUpdateField.getText() + "', PASSWORD = '" +
                adminPasswordUpdateField.getText() + "', JMBG ='" + adminJmbgUpdateField1.getText() + "', adminAcc='" + adminAccField.getText() + "' WHERE JMBG =?";

        TableModel model = tabelaAdminDetails.getModel();
        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            PreparedStatement ps = conn.prepareStatement(sql);

            if (adminJmbgUpdateField1.getText().matches(brojevi) && adminJmbgUpdateField1.getText().length() == 13) {
                if (adminUsernameUpdateField.getText().matches(slova)) {
                    if (adminPasswordUpdateField.getText().matches(slova)) {
                        if (adminAccField.getText().matches(brojevi)) {

                            ps.setString(1, String.valueOf(model.getValueAt(tabelaAdminDetails.getSelectedRow(), 2)));

                            ps.executeUpdate();
                            upozorenje3.setText("USPESAN UPDATE");
                        } else {
                            upozorenje3.setText("Greska pri unosu adminAcc-a");
                        }
                    } else {
                        upozorenje3.setText("Greska pri unosu passworda");
                    }
                } else {
                    upozorenje3.setText("Greska pri unosu username");
                }
            } else {
                upozorenje3.setText("Greska pri unosu JMBG-a");
            }

            popuniAdminDetailsPoljaZaUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_adminUpdateButton2ActionPerformed

    private void adminImeUpdateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminImeUpdateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_adminImeUpdateFieldActionPerformed

    private void imeUpdateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imeUpdateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_imeUpdateFieldActionPerformed

    private void tabelaAdminFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabelaAdminFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelaAdminFocusGained

    private void tabelaAdminMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAdminMouseClicked

        popuniAdminPoljaZaUpdate();

    }//GEN-LAST:event_tabelaAdminMouseClicked

    private void tabelaAdminDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaAdminDetailsMouseClicked

        popuniAdminDetailsPoljaZaUpdate();


    }//GEN-LAST:event_tabelaAdminDetailsMouseClicked

    private void searchField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pretraga();
        }
    }//GEN-LAST:event_searchField1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AdminPrezimeUpdateField;
    private javax.swing.JLabel JMBG;
    private javax.swing.JLabel JMBG1;
    private javax.swing.JLabel Pol;
    private javax.swing.JLabel Prezime;
    private javax.swing.JLabel Prezime1;
    private javax.swing.JLabel Telefon;
    private javax.swing.JLabel Telefon1;
    private javax.swing.JTextField adminAccField;
    private javax.swing.JButton adminDeleteBtn1;
    private javax.swing.JTextField adminImeUpdateField;
    private javax.swing.JTextField adminJmbgUpdateField1;
    private javax.swing.JTextField adminPasswordUpdateField;
    private javax.swing.JButton adminRefreshButton;
    private javax.swing.JButton adminUpdateButton1;
    private javax.swing.JButton adminUpdateButton2;
    private javax.swing.JTextField adminUsernameUpdateField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField imeField;
    private javax.swing.JTextField imeUpdateField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jmbgField;
    private javax.swing.JTextField jmbgUpdateField;
    private javax.swing.JComboBox<String> mrezaBox;
    private javax.swing.JComboBox<String> odabirSearcha;
    private javax.swing.JComboBox<String> polUpdateBox;
    private javax.swing.JRadioButton polmuski1;
    private javax.swing.JRadioButton polzenski;
    private javax.swing.JTextField prezimeField;
    private javax.swing.JTextField prezimeUpdateField;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchDugme;
    private javax.swing.JTextField searchField1;
    private javax.swing.JTable tabela;
    private javax.swing.JTable tabelaAdmin;
    private javax.swing.JTable tabelaAdminDetails;
    private javax.swing.JTextField telefonField1;
    private javax.swing.JTextField telefonUpdateField;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel upozorenje;
    private javax.swing.JLabel upozorenje2;
    private javax.swing.JLabel upozorenje3;
    private javax.swing.JComboBox<String> uslugaBox;
    private javax.swing.JComboBox<String> uslugaBox1;
    private javax.swing.JComboBox<String> zubarComboBOx;
    // End of variables declaration//GEN-END:variables
}
