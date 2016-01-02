package com;

import java.util.Stack;
//this is the code for the log in screen
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.Image;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.beans.PropertyChangeListener;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

public class LogInScreen extends JFrame implements Runnable {

    private static JFrame progressBarWindow;
    private static final String url = "jdbc:mysql://localhost:3306/gmail";
    private static final InboxFrame2 inboxFrame = new InboxFrame2();
    public Stack emailsInThe_DB;
    public JComboBox<String> emailsFieldBox;
    public JEditorPane errorWindow = new JEditorPane();
    private static JLabel iconLabel;
    public JPanel iconsPanel = new JPanel();
    private JPanel credentialsPanel = new JPanel();
    private static final JLabel emailsLabel = new JLabel("Email Address");
    private static final JLabel passwordLabel = new JLabel("Password");
    public JPasswordField passwordField = new JPasswordField();
    private static final JPanel bottomPanel = new JPanel();
    public JButton signInButton = new JButton("Sign In");
    public JButton signUpButton = new JButton("Have no account ?\nSign Up");
    private static final RegistrationForm regForm = new RegistrationForm();
    private static String enteredEmailAddress;
    private static String enteredPassword;
    private static String userName;
    private static Stack passwordsStack;
    private static String firstName;
    private static String lastName;
    private static String yearOfBirth;
    private static String phoneNumber;
    private static String emailAddress;
    private static String password;
    private static String reEnteredPassword;
    private static String address;
    private static JLabel imgLabel;
    private static ImageIcon imgIcon;
    private static PropertyChangeListener listerner;

    public LogInScreen() {

        super("UniMessenger");

        //repaint();
        //iconsPanel.setToolTipText("Gmail accout log in screen... \nenter Credentials \nand log in to your account");
        errorWindow.setEnabled(false);

        //GET TOOLKIT USE IT TO SET UP IMAGES
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int height = screenSize.height;
        int width = screenSize.width;
        Image IconImg = kit.getImage("anonimas.jpg");
        Image labelImage = kit.getImage("imge.png");

        //SET FRAME PROPERTIES
        setSize(600, 700);

        setLocation(getLocation().x, getLocation().y);
        setLayout(new GridLayout(2, 1, 1, 1));
        setResizable(false);
        setIconImage(new ImageIcon("anonimas.jpg").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(false);
        setIconImage(IconImg);

        //surgeted progress bar window
        progressBarWindow = new JFrame("UniMessegner");
        progressBarWindow.setSize(200, 100);
        progressBarWindow.setVisible(false);
        progressBarWindow.setUndecorated(true);
        setLocation(30, 30);
        setResizable(false);

        imgIcon = new ImageIcon(getClass().getResource("img.png"));
        imgLabel = new JLabel(imgIcon);

        signInButton.setMnemonic(KeyEvent.VK_ENTER);
        signInButton.setForeground(Color.WHITE);
        signUpButton.setForeground(Color.WHITE);
        signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //ADD ERROR DISPLAY EDITOR PANE TO ERROR PANEL
        errorWindow.setBackground(Color.white);

        emailsInThe_DB = new Stack();
        passwordsStack = new Stack();
        //DEFINE SIGN IN BUTTON
        signInButton.setBackground(Color.blue);
        signUpButton.setBackground(Color.blue);
        signInButton.setEnabled(false);
        signUpButton.setEnabled(true);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        bottomPanel.add(signInButton, BorderLayout.EAST);
        //bottomPanel.setBackground(Color.white);
        bottomPanel.add(signUpButton);

        //set icons panel attributes
        iconsPanel.add(imgLabel);
        iconsPanel.setBackground(Color.WHITE);

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

        //SHOW WELCOME MESSAGE  NOT WORKING
        showMessage("");

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
            JOptionPane.showMessageDialog(null, "A security breach was detected in your System\n An automatic shut down is scheduled", "SECURITY BREACH WARNING" + e.getMessage(), JOptionPane.WARNING_MESSAGE);

        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "" + e.getMessage(), "UniMessenger", JOptionPane.PLAIN_MESSAGE);
        }

        //ADD  PHONE NUMBER VALUES TO PHONE NUMBER TEXT FIELD 
        emailsFieldBox = new JComboBox<>(emailsInThe_DB);
        emailsFieldBox.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        //SET FOCUS LISTENER FOR THE emailsField COMBO BOX
        emailsFieldBox.setForeground(Color.BLUE);
        emailsFieldBox.addFocusListener(
                new FocusListener() {

                    @Override
                    public void focusGained(FocusEvent event) {

                        emailsFieldBox.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLUE, Color.BLUE));

                    }

                    @Override

                    public void focusLost(FocusEvent event) {

                        emailsFieldBox.setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.RED));

                    }
                }
        );

        emailsFieldBox.setEditable(true);

        //DEFINE CREDENTIALS FRAME
        credentialsPanel.setLayout(new GridLayout(6, 1, 3, 3));
        credentialsPanel.setBackground(Color.white);
        credentialsPanel.add(errorWindow);
        credentialsPanel.add(emailsLabel);
        credentialsPanel.add(emailsFieldBox);
        credentialsPanel.add(passwordLabel);
        credentialsPanel.add(passwordField);
        credentialsPanel.add(bottomPanel);

        //Add components to frame
        add(iconsPanel);
        add(credentialsPanel);

        //SET UP MNEMONICS
        signInButton.setMnemonic(KeyEvent.VK_ENTER);

        //Obtain log in credentials from user AND AUTHENTICATE USER.
        signInButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        AuthenticateUser();

                    }
                }
        );

        signUpButton.addActionListener(
                new ActionListener() {

                    @Override

                    public void actionPerformed(ActionEvent event) {

                        dispose();

                        regForm.setVisible(true);

                    }

                }
        );

        //SUBMIT USERS DETAILS TO THE MYSQL DATABASE AND TO THE BACK UP FILE
        regForm.submitButton.addActionListener(
                new ActionListener() {

                    @Override

                    public void actionPerformed(ActionEvent event) {

                        //reEnteredPassword = new String();
                        // enteredPassword = new String();
                        //Retrieve entered data from user  for user
                        firstName = regForm.firstNameField.getText().toString();
                        lastName = regForm.lastNameField.getText().toString();
                        yearOfBirth = regForm.yearsField.getSelectedItem().toString();
                        phoneNumber = regForm.phoneNumbersField.getText().toString();
                        emailAddress = regForm.emailAddressField.getText().toString();
                        password = regForm.passwordField.getText().toString();
                        reEnteredPassword = regForm.confirmPasswordField.getText().toString();

                        address = regForm.addressField.getSelectedItem().toString();

                        /**
                         * if (regForm.comparePasswords(enteredPassword,
                         * reEnteredPassword) == false) { //DO NOT PROCEDD TO
                         * REGISTER USER IF ENTERED PASSWORDS DO NOT MATCH WITH
                         * CONFIMR PASSWRD Toolkit.getDefaultToolkit().beep();
                         * JOptionPane.showMessageDialog(null, "Passwords dont
                         * match");
                }*
                         */
                        int serialNo;

                        serialNo = (int) (Math.random() * 15555) + 1;

                        //WRITE NEW USERS INFORMATION IN A BACK UP FILE backup.txt
                        File backup = new File("GmailBackup.txt");

                        try {

                            FileWriter writer = new FileWriter(backup);
                            BufferedWriter b_writer = new BufferedWriter(writer);

                            b_writer.append("\t\tUnimail Desktop  App\n\t\tPersonal Details  Back Up File\n");
                            b_writer.newLine();

                            b_writer.append("Record:\t " + serialNo);
                            b_writer.newLine();
                            b_writer.append("First Name\t" + firstName);
                            b_writer.newLine();
                            b_writer.append("Last Name\t" + lastName);
                            b_writer.newLine();
                            b_writer.append("Year of Birth\t" + yearOfBirth);
                            b_writer.newLine();
                            b_writer.append("Phone Number\t" + phoneNumber);
                            b_writer.newLine();
                            b_writer.append("Email Address\t" + emailAddress);
                            b_writer.newLine();
                            b_writer.append("Password\t" + password);
                            b_writer.newLine();

                            b_writer.flush();
                            b_writer.close();

                        } catch (IOException ex) {

                            JOptionPane.showMessageDialog(null, "ERROR line 363" + ex.getMessage());
                        }

                        //database connection
                        try {

                            String INSERT_QUERY = "INSERT INTO registration_form VALUES ('" + serialNo + "','" + firstName + "','" + lastName + "','" + phoneNumber + "','" + yearOfBirth + "','" + address + "','" + emailAddress + "','" + password + "')";
                            Connection serverConnection = DriverManager.getConnection(url, "root", "");
                            Statement sqlStatement = serverConnection.createStatement();

                            sqlStatement.execute(INSERT_QUERY);

                            JOptionPane.showConfirmDialog(null, "Your details have been succesffuly received \nClick yes to move to your new account", "Desktop Gmail", JOptionPane.YES_NO_OPTION);

                            regForm.setVisible(false);
                            int option = JOptionPane.showConfirmDialog(bottomPanel, "You can now log in with your new account\n just click YES ", "", JOptionPane.OK_CANCEL_OPTION);

                            if (option == JOptionPane.OK_OPTION) {

                                pack();
                                show();

                            } else {

                                regForm.setVisible(true);
                            }

                        } catch (SQLException ex) {

                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null, "Well thie is embarrasing but we couldn't connect to the server Details:\n" + ex.getMessage());
                        }

                    }

                }
        );

        regForm.signInButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        regForm.setVisible(false);
                        setVisible(true);
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
        //BLOCK NOT WORKING I.E. MAKE SIGN IN BUTTON DISABLE(TRUE) IF EMAILS FIELD == NULL
        passwordField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {
                        if (!event.getActionCommand().equals("")) {

                            ableToLogIn(true);

                        } else if (event.getActionCommand().equals("")) {

                            ableToLogIn(false);
                        }
                    }
                }
        );

        //CHECK FIELD FOR TEXT TO ENABLE SUBMIT
        regForm.firstNameField.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyReleased(KeyEvent event) {
                        ableToSubmit(true);
                    }

                    @Override
                    public void keyPressed(KeyEvent event) {

                    }

                    @Override
                    public void keyTyped(KeyEvent event) {

                    }
                }
        );

        regForm.lastNameField.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyReleased(KeyEvent event) {
                        ableToSubmit(true);
                    }

                    @Override
                    public void keyPressed(KeyEvent event) {

                    }

                    @Override
                    public void keyTyped(KeyEvent event) {

                    }
                }
        );

        regForm.passwordField.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyReleased(KeyEvent event) {

                        ableToSubmit(true);

                    }

                    @Override
                    public void keyPressed(KeyEvent event) {

                    }

                    @Override
                    public void keyTyped(KeyEvent event) {

                    }
                }
        );

        regForm.passwordField.addKeyListener(
                new KeyListener() {

                    @Override
                    public void keyReleased(KeyEvent event) {

                        ableToSubmit(true);
                    }

                    @Override
                    public void keyPressed(KeyEvent event) {

                    }

                    @Override
                    public void keyTyped(KeyEvent event) {

                    }
                }
        );
    }

    public void ableToSubmit(final boolean isAble) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        regForm.submitButton.setEnabled(isAble);

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

                            enteredPassword = passwordField.getText().toString();
                            enteredEmailAddress = emailsFieldBox.getSelectedItem().toString();

                            Connection connection = DriverManager.getConnection(url, "root", "");
                            Statement sql_statement = connection.createStatement();
                            String SELECT_QUERY = "SELECT password, first_name FROM registration_form WHERE emailAddress = " + "'" + enteredEmailAddress + "';";
                            ResultSet queryResults = sql_statement.executeQuery(SELECT_QUERY);

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
                                errorWindow.setForeground(Color.red);
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
    }

    //UPDATE ERROR MESSAGE PANE DURING RUN TIME WHEN WRONG PASSWORD IS TYPED
    private void showMessage(final String errorMessage) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override

                    public void run() {
                        
                        errorWindow.setFont(new Font("Times New Roman",Font.BOLD,14));
                        errorWindow.setBackground(Color.white);

                        errorWindow.setText(errorMessage);

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

    @Override

    public void run() {

        try {

            Thread.sleep(3000);

            AuthenticateUser();

        } catch (InterruptedException e) {

            e.getMessage();

        }

    }

    /**
     * public static void main(String[] args) {
     *
     * javax.swing.SwingUtilities.invokeLater( new Runnable() {
     *
     * @Override
     *
     * public void run() {
     *
     * try {
     *
     * Thread.sleep(12000);
     *
     * new LogInScreen();
     *
     * } catch (InterruptedException e) {
     *
     * System.out.println("The thread was interrupted...");
     * JOptionPane.showMessageDialog(null,"Unfortunately the application has
     * stopped workong...");
     *
     * }
     * }
     * }
     * ); //new
     *
     * //LogInScreen().setVisible(true);
    }*
     */
}
