package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JLabel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import java.util.Stack;

public class logInScreen2 extends JFrame {

    private static JLabel emailLabel;
    private static JLabel passwordLabel;
    private static JButton signInButton;
    private static JButton signUpButton;
    private static JPanel iconsPanel;
    private static JTextField passwordField;
    private static JPanel bottomPanel;
    private static JPanel credentialsPanel;
    private static JComboBox emailsFieldBox;
    private static JEditorPane editorPane;
    private static String enteredEmailAddress;
    private static String enteredPassword;
    private static String userName;
    private static Stack passwordsStack;
    private static Stack emailsInThe_DB;
    private static RegistrationForm registrationForm;

    private static InboxFrame2 inboxFrame = new InboxFrame2();

    public logInScreen2() {

        //SET FRAME PROPERTIES
        setSize(500, 600);
        setLocation(getLocation().x, getLocation().y);
        setLayout(new GridLayout(2, 1, 1, 1));
        setResizable(false);
        setIconImage(new ImageIcon("anonimas.jpg").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);

        registrationForm = new RegistrationForm();
        emailsInThe_DB = new Stack();
        passwordsStack = new Stack();
        credentialsPanel = new JPanel();
        bottomPanel = new JPanel();
        editorPane = new JEditorPane();
        passwordField = new JTextField();
        emailLabel = new JLabel("Email Address");
        passwordLabel = new JLabel("Password Label");
        iconsPanel = new JPanel();
        signInButton = new JButton("Sign In");
        signUpButton = new JButton("Have no account ?\nSign Up");

        //PREPARE TEXT FIELDS
        iconsPanel.setBackground(Color.WHITE);

        //PREPARE LABELS
        //ICONS PANEL SPECS
        //BOTTOM PANEL
        bottomPanel.add(signInButton, BorderLayout.EAST);
        bottomPanel.add(signUpButton, BorderLayout.WEST);

        //ADD  PHONE NUMBER VALUES TO PHONE NUMBER TEXT FIELD 
        emailsFieldBox = new JComboBox<>();
        emailsFieldBox.setEditable(true);

        //PREPARE BUTTONS
        signInButton.setMnemonic(KeyEvent.VK_ENTER);
        signInButton.setForeground(Color.WHITE);
        signUpButton.setForeground(Color.WHITE);
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signInButton.setBackground(Color.blue);
        signUpButton.setBackground(Color.blue);
        signInButton.setEnabled(false);
        signUpButton.setEnabled(true);

        //CREDENTIAL PANEL SPECS
        credentialsPanel.setBackground(Color.WHITE);
        credentialsPanel.setLayout(new GridLayout(6, 1, 3, 3));
        credentialsPanel.add(editorPane);
        credentialsPanel.add(emailLabel);
        credentialsPanel.add(emailsFieldBox);
        credentialsPanel.add(passwordLabel);
        credentialsPanel.add(passwordField);
        credentialsPanel.add(bottomPanel);

        //ADD PANELS TO THE FRAME
        add(iconsPanel);
        add(credentialsPanel);

        //SET UP FOCUS LISTENERS FOR THE GUI COMPONENTS
        signInButton.addFocusListener(
                new FocusListener() {

            @Override
            public void focusLost(FocusEvent event) {

                //signInButton.setBackground(Color.BLUE);
            }

            @Override
            public void focusGained(FocusEvent event) {

                signInButton.setBackground(Color.GREEN);
            }
        }
        );

        signUpButton.addFocusListener(
                new FocusListener() {

            @Override
            public void focusGained(FocusEvent event) {

                signUpButton.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent event) {

                signUpButton.setBackground(Color.BLUE);
            }
        }
        );

        passwordField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        passwordField.addFocusListener(
                new FocusListener() {

            @Override
            public void focusGained(FocusEvent event) {

                passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));

            }

            @Override
            public void focusLost(FocusEvent event) {
                if (passwordField.getText().toString().equals("")) {

                    Toolkit.getDefaultToolkit().beep();
                    passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.RED));
                } else {

                    passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.lightGray, Color.lightGray));
                }
            }
        }
        );

        //CHECK LOG IN SCREEN FIELD !NULL
        passwordField.addKeyListener(
                new KeyListener() {

            @Override
            public void keyPressed(KeyEvent event) {
            }

            @Override
            public void keyReleased(KeyEvent event) {
                ableToLogIn(true);
            }

            @Override
            public void keyTyped(KeyEvent event) {
            }

        }
        );

        //Obtain log in credentials from user AND AUTHENTICATE USER.
        signInButton.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {

                AuthenticateUser();

            }
        }
        );

        //OPEN REGISTRATION FROM ON REQUEST
        signUpButton.addActionListener(
                new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
                registrationForm.setVisible(true);
            }
        }
        );
    }

    //ALLOW LOG IN WHEN CREDENTIALS ARE ADDED
    private void ableToLogIn(final boolean canLogIn) {

        SwingUtilities.invokeLater(
                new Runnable() {

            @Override

            public void run() {

                signInButton.setEnabled(canLogIn);

            }
        }
        );
        
        
    }

    //Connect to the MySql database and obtain email addresses and passwords
    public void AuthenticateUser() {

        SwingUtilities.invokeLater(
                new Runnable() {

            @Override
            public void run() {

                try {

                    //System.err.println("Authenticate method is executed...");
                    String url = "jdbc:mysql://localhost:3306/gmail";
                    enteredPassword = passwordField.getText().toString();
                    enteredEmailAddress = emailsFieldBox.getSelectedItem().toString();
                    /**
                     * System.out.println("The entered email address is " +
                     * enteredEmailAddress); System.out.println("entered EMAIL
                     * ADDRESS is" + enteredEmailAddress);
                     * System.out.println("enterd USER PASSWORD is" +
                     * enteredPassword);*
                     */
                    Connection connection = DriverManager.getConnection(url, "root", "");
                    Statement sql_statement = connection.createStatement();
                    String SELECT_QUERY = "SELECT password FROM registration_form WHERE emailAddress = " + enteredEmailAddress;
                    ResultSet queryResults = sql_statement.executeQuery("SELECT password, first_name FROM registration_form WHERE emailAddress = " + "'" + enteredEmailAddress + "';");

                    while (queryResults.next()) {
                        //add all emails to the eamails stack
                        System.err.println("line 356 (while loop) is executed");
                        passwordsStack.push(queryResults.getString("password"));
                        userName = queryResults.getString("first_name");
                        System.err.println("line 359 (while loop) is executed");
                    }
                    //Compare entered password
                    if (passwordsStack.search(enteredPassword) == -1) {
                        setVisible(true);
                        passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.RED));
                        inboxFrame.setVisible(false);
                        editorPane.setForeground(Color.red);
                        Toolkit.getDefaultToolkit().beep();
                        showMessage("\t\tWRONG PASSWORD");

                    } else if (passwordsStack.search(enteredPassword) >= 0) {

                        showMessage("SUCCESSFULL");
                        passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));
                        emailsFieldBox.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));
                        JOptionPane.showMessageDialog(iconsPanel, "\tSuccessfull\nWelcom to your account");
                        remove(credentialsPanel);
                        setVisible(false);
                        inboxFrame.setVisible(true);
                        inboxFrame.setTitle(userName);
                    }

                } catch (SQLException e) {

                    JOptionPane.showMessageDialog(null, "" + e.getMessage());
                }
            }
        }
        );

        try {

            String SELECT_QUERY = "SELECT phone_name,emailAddress FROM registration_form ";
            String dbConnectionurl = "jdbc:mysql://localhost:3306/gmail";
            Connection connection = DriverManager.getConnection(dbConnectionurl, "root", "");
            Statement sqlStatement = connection.createStatement();
            ResultSet resultSet = sqlStatement.executeQuery(SELECT_QUERY);

            while (resultSet.next()) {

                emailsInThe_DB.push(resultSet.getString("emailAddress"));
            }

        } catch (SQLException e) {

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(iconsPanel, e.getMessage());

        } catch (SecurityException e) {

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "A security breach was detected in your System\n An automatic shut down is scheduled", "SECURITY BREACH WARNING", JOptionPane.WARNING_MESSAGE);

        } catch (StringIndexOutOfBoundsException e) {

        }

    }

    //UPDATE ERROR MESSAGE PANE DURING RUN TIME WHEN WRONG PASSWORD IS TYPED
    private void showMessage(final String errorMessage) {

        SwingUtilities.invokeLater(
                new Runnable() {

            @Override

            public void run() {

                editorPane.setBackground(Color.white);

                editorPane.setText(errorMessage);

            }
        }
        );
    }
    /**
     * public static void main(String[] args) {
     *
     * javax.swing.SwingUtilities.invokeLater( new Runnable() {
     *
     * @Override public void run() {
     *
     * new logInScreen2();
     *
     * }
     * }
     * ); }*
     */
}
