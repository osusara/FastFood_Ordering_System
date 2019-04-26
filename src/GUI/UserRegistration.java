/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Database.DatabaseConnection;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

/**
 *
 * @author Osusara
 */
public class UserRegistration extends javax.swing.JFrame {

    public UserRegistration() {
        initComponents();
        loadID();
        passwordField.setEchoChar((char) 0);
        passwordField.setForeground(Color.gray);
        confirmPasswordField.setEchoChar((char) 0);
        confirmPasswordField.setForeground(Color.gray);
        usernameTextField.setForeground(Color.gray);
        nameTextField.setForeground(Color.gray);
        genderTextField.setForeground(Color.gray);
        dobTextField.setForeground(Color.gray);
        phoneTextField.setForeground(Color.gray);
        emailTextField.setForeground(Color.gray);
        addressTextField.setForeground(Color.gray);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        iconLabel = new javax.swing.JLabel();
        titleLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        confirmPasswordField = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        nameTextField = new javax.swing.JTextField();
        genderTextField = new javax.swing.JTextField();
        dobTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        emailTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        managementCheckBox = new javax.swing.JCheckBox();
        userIDLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        confirmButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        mainPanel.setPreferredSize(new java.awt.Dimension(400, 500));
        mainPanel.setRequestFocusEnabled(false);

        iconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/users.png"))); // NOI18N

        titleLabel.setFont(new java.awt.Font("Century Gothic", 0, 36)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(51, 51, 51));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Register");
        titleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        usernameTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        usernameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameTextField.setText("Username");
        usernameTextField.setToolTipText("<html>Enter a New Username <br>\nEg: brian001");
        usernameTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        usernameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        usernameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                usernameTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                usernameTextFieldFocusLost(evt);
            }
        });
        usernameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usernameTextFieldMouseClicked(evt);
            }
        });
        usernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyTyped(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordField.setText("New Password");
        passwordField.setToolTipText("<html>Enter a New Password <br>\nEg: bahd34da");
        passwordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });

        confirmPasswordField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        confirmPasswordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        confirmPasswordField.setText("Retype the Password");
        confirmPasswordField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        confirmPasswordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                confirmPasswordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                confirmPasswordFieldFocusLost(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(51, 51, 51));

        nameTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        nameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameTextField.setText("Full Name");
        nameTextField.setToolTipText("<html>Enter Full Name <br>\nEg: Brain Adams");
        nameTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        nameTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        nameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameTextFieldFocusLost(evt);
            }
        });
        nameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameTextFieldMouseClicked(evt);
            }
        });
        nameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameTextFieldKeyTyped(evt);
            }
        });

        genderTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        genderTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        genderTextField.setText("Gender");
        genderTextField.setToolTipText("<html>Enter The Gender (m/f)<br>\nEg: m");
        genderTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        genderTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        genderTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                genderTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                genderTextFieldFocusLost(evt);
            }
        });
        genderTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                genderTextFieldMouseClicked(evt);
            }
        });
        genderTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderTextFieldActionPerformed(evt);
            }
        });
        genderTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                genderTextFieldKeyTyped(evt);
            }
        });

        dobTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        dobTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dobTextField.setText("Date of Birth");
        dobTextField.setToolTipText("<html>Enter Date of Birth in (Date/Month/Year) Format<br>\nEg: 18/05/1996");
        dobTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        dobTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        dobTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dobTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dobTextFieldFocusLost(evt);
            }
        });
        dobTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dobTextFieldMouseClicked(evt);
            }
        });
        dobTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dobTextFieldKeyTyped(evt);
            }
        });

        phoneTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        phoneTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        phoneTextField.setText("Phone");
        phoneTextField.setToolTipText("<html>Enter Mobile/Phone Number <br>\nEg: 0740490459");
        phoneTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        phoneTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        phoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                phoneTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                phoneTextFieldFocusLost(evt);
            }
        });
        phoneTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                phoneTextFieldMouseClicked(evt);
            }
        });
        phoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phoneTextFieldKeyTyped(evt);
            }
        });

        emailTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        emailTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        emailTextField.setText("Email");
        emailTextField.setToolTipText("<html>Enter Your Email <br>\nEg: brianadams@gmail.com");
        emailTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        emailTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        emailTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailTextFieldFocusLost(evt);
            }
        });
        emailTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emailTextFieldMouseClicked(evt);
            }
        });
        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyTyped(evt);
            }
        });

        addressTextField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        addressTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        addressTextField.setText("Address");
        addressTextField.setToolTipText("<html>Enter Your Address <br>\nEg: 12/B, Main Street, LA");
        addressTextField.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        addressTextField.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        addressTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                addressTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                addressTextFieldFocusLost(evt);
            }
        });
        addressTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addressTextFieldMouseClicked(evt);
            }
        });
        addressTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                addressTextFieldKeyTyped(evt);
            }
        });

        managementCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        managementCheckBox.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        managementCheckBox.setForeground(new java.awt.Color(51, 51, 51));
        managementCheckBox.setText("Management Access");
        managementCheckBox.setToolTipText("Check If the User is a Person in Managerial Level");
        managementCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        managementCheckBox.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        managementCheckBox.setIconTextGap(5);

        userIDLabel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        userIDLabel.setForeground(new java.awt.Color(51, 51, 51));
        userIDLabel.setText("User ID");

        idLabel.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        idLabel.setText("0");
        idLabel.setToolTipText("User's ID (Auto Generated)");

        confirmButton.setBackground(new java.awt.Color(30, 140, 50));
        confirmButton.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        confirmButton.setForeground(new java.awt.Color(255, 255, 255));
        confirmButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/ok.png"))); // NOI18N
        confirmButton.setText("Confirm");
        confirmButton.setBorderPainted(false);
        confirmButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        confirmButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                confirmButtonMouseEntered(evt);
            }
        });
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });

        cancelButton.setBackground(new java.awt.Color(255, 50, 30));
        cancelButton.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        cancelButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/cancel.png"))); // NOI18N
        cancelButton.setText("Close");
        cancelButton.setBorderPainted(false);
        cancelButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(usernameTextField)
                        .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(phoneTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(emailTextField)
                        .addComponent(addressTextField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(confirmPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainPanelLayout.createSequentialGroup()
                            .addComponent(genderTextField)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(dobTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(managementCheckBox)
                        .addGap(40, 40, 40)
                        .addComponent(userIDLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(confirmButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleLabel)
                .addGap(18, 18, 18)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(confirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dobTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(managementCheckBox)
                    .addComponent(userIDLabel)
                    .addComponent(idLabel))
                .addGap(18, 18, 18)
                .addComponent(confirmButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void loadID() {
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT COUNT('user_id') AS C FROM login");
            if (rs.next()) {
                int i = rs.getInt("C");
                idLabel.setText("" + (++i));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void resetFields() {
        usernameTextField.setForeground(Color.gray);
        usernameTextField.setText("Username");

        passwordField.setForeground(Color.gray);
        passwordField.setEchoChar((char) 0);
        passwordField.setText("New Password");
        passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));

        confirmPasswordField.setForeground(Color.gray);
        confirmPasswordField.setEchoChar((char) 0);
        confirmPasswordField.setText("Retype the Password");
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));

        nameTextField.setForeground(Color.gray);
        nameTextField.setText("Full Name");

        genderTextField.setForeground(Color.gray);
        genderTextField.setText("Gender");

        dobTextField.setForeground(Color.gray);
        dobTextField.setText("Date of Birth");

        phoneTextField.setForeground(Color.gray);
        phoneTextField.setText("Phone");

        emailTextField.setForeground(Color.gray);
        emailTextField.setText("Email");

        addressTextField.setForeground(Color.gray);
        addressTextField.setText("Address");
    }

    public boolean usernameCheck() {
        String s = usernameTextField.getText();
        boolean taken = true;
        try {
            ResultSet rs = DatabaseConnection.getConnection().executeQuery("SELECT * FROM login WHERE username = '" + s + "' LIMIT 1;");
            if (!rs.isBeforeFirst()) {
                taken = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return taken;
    }

    private void usernameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextFieldFocusGained

    }//GEN-LAST:event_usernameTextFieldFocusGained

    private void usernameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_usernameTextFieldFocusLost
        if (usernameTextField.getText().isEmpty()) {
            usernameTextField.setForeground(Color.gray);
            usernameTextField.setText("Username");
        }
    }//GEN-LAST:event_usernameTextFieldFocusLost

    private void usernameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usernameTextFieldMouseClicked
        usernameTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (usernameTextField.getForeground() == Color.gray) {
            usernameTextField.setText(null);
        }
    }//GEN-LAST:event_usernameTextFieldMouseClicked

    private void usernameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyTyped
        if (usernameTextField.getForeground() == Color.gray) {
            usernameTextField.setText(null);
        }
        usernameTextField.setForeground(Color.black);
    }//GEN-LAST:event_usernameTextFieldKeyTyped

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
        //Styling
        passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        passwordField.setText(null);
        passwordField.setEchoChar('●');
        passwordField.setForeground(Color.black);
        passwordField.setToolTipText("<html>Enter a New Password <br>\n" + "Eg: bahd34da");
    }//GEN-LAST:event_passwordFieldFocusGained

    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost
        //Styling
        if (passwordField.getText().isEmpty()) {
            passwordField.setForeground(Color.gray);
            passwordField.setEchoChar((char) 0);
            passwordField.setText("New Password");
        }

        //Validations
        int i = passwordField.getText().length();
        if (i >= 8 && passwordField.getForeground() == Color.black) {
            passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#32CD32")));
        } else if (i < 8 && passwordField.getForeground() == Color.black) {
            passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
            passwordField.setToolTipText("Password Must Have More Than 8 Characters");
        }
    }//GEN-LAST:event_passwordFieldFocusLost

    private void confirmButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmButtonMouseEntered

    }//GEN-LAST:event_confirmButtonMouseEntered

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        int userid = Integer.parseInt(idLabel.getText());
        String management;
        if (managementCheckBox.isSelected()) {
            management = "Manager";
        } else {
            management = "Cashier";
        }
        String username = usernameTextField.getText();
        String password = passwordField.getText();
        String name = nameTextField.getText();
        String gender = genderTextField.getText();
        String dob = dobTextField.getText();
        String phone = phoneTextField.getText();
        String email = emailTextField.getText();
        String address = addressTextField.getText();

        if (usernameTextField.getForeground() != Color.gray) {
            if (!usernameCheck()) {
                if (passwordField.getForeground() != Color.gray) {
                    if (confirmPasswordField.getForeground() != Color.gray && confirmPasswordField.getText().equals(passwordField.getText())) {
                        if (nameTextField.getForeground() != Color.gray) {
                            if (genderTextField.getForeground() != Color.gray) {
                                if (dobTextField.getForeground() != Color.gray) {
                                    if (phoneTextField.getForeground() != Color.gray) {
                                        if (emailTextField.getForeground() != Color.gray) {
                                            if (addressTextField.getForeground() != Color.gray) {
                                                System.out.println("Success!");

                                                try {

                                                    Statement s = DatabaseConnection.getConnection();
                                                    s.executeUpdate("INSERT INTO login (user_id, username, password, type, status) VALUES('" + userid + "','" + username + "','" + password + "','" + management + "','1');");
                                                    s.executeUpdate("INSERT INTO user (user_id, name, gender, dob, phone, email, address) VALUES('" + userid + "','" + name + "','" + gender + "','" + dob + "','" + phone + "','" + email + "','" + address + "');");

                                                    JOptionPane.showMessageDialog(this, "User Registration Successfull", "Successfully Message", JOptionPane.INFORMATION_MESSAGE);
                                                    loadID();
                                                    resetFields();
                                                } catch (Exception e) {
                                                    System.out.println(e);
                                                }

                                            } else {
                                                addressTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                                                JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        } else {
                                            emailTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                                            JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                    } else {
                                        phoneTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                                        JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                } else {
                                    dobTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                                    JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                genderTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                                JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            nameTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                            JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                        JOptionPane.showMessageDialog(this, "Passwords are Not Matching", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (passwordField.getText().length() >= 8) {
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                    JOptionPane.showMessageDialog(this, "Password Must Have Atleast 8 Characters", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    passwordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                    JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                usernameTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
                JOptionPane.showMessageDialog(this, "Username Already Exist", "Information Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            usernameTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
            JOptionPane.showMessageDialog(this, "All the Fields Must be Filled", "Information Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        if(JOptionPane.showConfirmDialog(this, "Do you really want to exit?", "User Registration", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            this.dispose();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void nameTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusGained
        
    }//GEN-LAST:event_nameTextFieldFocusGained

    private void nameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTextFieldFocusLost
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setForeground(Color.gray);
            nameTextField.setText("Full Name");
        }
    }//GEN-LAST:event_nameTextFieldFocusLost

    private void nameTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameTextFieldMouseClicked
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (nameTextField.getForeground() == Color.gray) {
            nameTextField.setText(null);
        }
    }//GEN-LAST:event_nameTextFieldMouseClicked

    private void nameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTextFieldKeyTyped
        if (nameTextField.getForeground() == Color.gray) {
            nameTextField.setText(null);
        }
        nameTextField.setForeground(Color.black);
    }//GEN-LAST:event_nameTextFieldKeyTyped

    private void dobTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobTextFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_dobTextFieldFocusGained

    private void dobTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dobTextFieldFocusLost
        if (dobTextField.getText().isEmpty()) {
            dobTextField.setForeground(Color.gray);
            dobTextField.setText("Date of Birth");
        }
    }//GEN-LAST:event_dobTextFieldFocusLost

    private void dobTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dobTextFieldMouseClicked
        dobTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (dobTextField.getForeground() == Color.gray) {
            dobTextField.setText(null);
        }
    }//GEN-LAST:event_dobTextFieldMouseClicked

    private void dobTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dobTextFieldKeyTyped
        if (dobTextField.getForeground() == Color.gray) {
            dobTextField.setText(null);
        }
        dobTextField.setForeground(Color.black);

        //Validations
        int i = dobTextField.getText().length();
        if (i == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_dobTextFieldKeyTyped

    private void phoneTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneTextFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneTextFieldFocusGained

    private void phoneTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phoneTextFieldFocusLost
        if (phoneTextField.getText().isEmpty()) {
            phoneTextField.setForeground(Color.gray);
            phoneTextField.setText("Phone");
        }
    }//GEN-LAST:event_phoneTextFieldFocusLost

    private void phoneTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_phoneTextFieldMouseClicked
        phoneTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (phoneTextField.getForeground() == Color.gray) {
            phoneTextField.setText(null);
        }
    }//GEN-LAST:event_phoneTextFieldMouseClicked

    private void phoneTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneTextFieldKeyTyped
        if (phoneTextField.getForeground() == Color.gray) {
            phoneTextField.setText(null);
        }
        phoneTextField.setForeground(Color.black);

        //Validations
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }

        int i = phoneTextField.getText().length();
        if (i == 10) {
            evt.consume();
        }
    }//GEN-LAST:event_phoneTextFieldKeyTyped

    private void emailTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldFocusGained

    private void emailTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailTextFieldFocusLost
        if (emailTextField.getText().isEmpty()) {
            emailTextField.setForeground(Color.gray);
            emailTextField.setText("Email");
        }
    }//GEN-LAST:event_emailTextFieldFocusLost

    private void emailTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emailTextFieldMouseClicked
        emailTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (emailTextField.getForeground() == Color.gray) {
            emailTextField.setText(null);
        }
    }//GEN-LAST:event_emailTextFieldMouseClicked

    private void emailTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTextFieldKeyTyped
        if (emailTextField.getForeground() == Color.gray) {
            emailTextField.setText(null);
        }
        emailTextField.setForeground(Color.black);


    }//GEN-LAST:event_emailTextFieldKeyTyped

    private void addressTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressTextFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextFieldFocusGained

    private void addressTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addressTextFieldFocusLost
        if (addressTextField.getText().isEmpty()) {
            addressTextField.setForeground(Color.gray);
            addressTextField.setText("Address");
        }
    }//GEN-LAST:event_addressTextFieldFocusLost

    private void addressTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addressTextFieldMouseClicked
        addressTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (addressTextField.getForeground() == Color.gray) {
            addressTextField.setText(null);
        }
    }//GEN-LAST:event_addressTextFieldMouseClicked

    private void addressTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addressTextFieldKeyTyped
        if (addressTextField.getForeground() == Color.gray) {
            addressTextField.setText(null);
        }
        addressTextField.setForeground(Color.black);
    }//GEN-LAST:event_addressTextFieldKeyTyped

    private void confirmPasswordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPasswordFieldFocusGained
        //Styling
        confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        confirmPasswordField.setText(null);
        confirmPasswordField.setEchoChar('●');
        confirmPasswordField.setForeground(Color.black);
        confirmPasswordField.setToolTipText("Retype the Password");
    }//GEN-LAST:event_confirmPasswordFieldFocusGained

    private void confirmPasswordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_confirmPasswordFieldFocusLost
        //Styling
        if (confirmPasswordField.getText().isEmpty()) {
            confirmPasswordField.setForeground(Color.gray);
            confirmPasswordField.setEchoChar((char) 0);
            confirmPasswordField.setText("Retype the Password");
        }

        //Validations
        int i = confirmPasswordField.getText().length();
        String s = confirmPasswordField.getText();
        if (i >= 8 && s.equals(passwordField.getText()) && confirmPasswordField.getForeground() == Color.black) {
            confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#32CD32")));
        } else if (confirmPasswordField.getForeground() == Color.black) {
            if (i < 8) {
                confirmPasswordField.setToolTipText("Password Must Have More Than 8 Characters");
            } else if (!(s.equals(passwordField.getText()))) {
                confirmPasswordField.setToolTipText("Passwords are Not Matching");
            }
            confirmPasswordField.setBorder(BorderFactory.createLineBorder(Color.decode("#F22B2B")));
        }
    }//GEN-LAST:event_confirmPasswordFieldFocusLost

    private void genderTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genderTextFieldFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_genderTextFieldFocusGained

    private void genderTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genderTextFieldFocusLost
        if (genderTextField.getText().isEmpty()) {
            genderTextField.setForeground(Color.gray);
            genderTextField.setText("Gender");
        }

        if (genderTextField.getText().equals("m") || genderTextField.getText().equals("M")) {
            genderTextField.setText("Male");
        } else if (genderTextField.getText().equals("f") || genderTextField.getText().equals("F")) {
            genderTextField.setText("Female");
        } else {
            genderTextField.setForeground(Color.gray);
            genderTextField.setText("Gender");
        }
    }//GEN-LAST:event_genderTextFieldFocusLost

    private void genderTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genderTextFieldMouseClicked
        genderTextField.setBorder(BorderFactory.createLineBorder(Color.decode("#999999")));
        if (genderTextField.getForeground() == Color.gray) {
            genderTextField.setText(null);
        }
    }//GEN-LAST:event_genderTextFieldMouseClicked

    private void genderTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_genderTextFieldKeyTyped
        if (genderTextField.getForeground() == Color.gray) {
            genderTextField.setText(null);
        }
        genderTextField.setForeground(Color.black);
    }//GEN-LAST:event_genderTextFieldKeyTyped

    private void genderTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_genderTextFieldActionPerformed

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
            java.util.logging.Logger.getLogger(UserRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserRegistration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressTextField;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton confirmButton;
    private javax.swing.JPasswordField confirmPasswordField;
    private javax.swing.JTextField dobTextField;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JTextField genderTextField;
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JCheckBox managementCheckBox;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel userIDLabel;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
