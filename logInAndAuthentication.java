package alpha.restaurant.management.system;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.util.*;

public class logInAndAuthentication extends JFrame {

    private static Stack<String> emailsInThe_DB;
    private static String enteredUserName;
    private static String enteredPassword;
    private static String userName;
    private static Stack<String> passwordsStack;
    private static JPanel topSignInPanel;
    private static JPanel credentialsPanel;
    private static JPanel copyrightInfoPanel;
    private static JLabel userNameLabel;
    private static JLabel pointOfSaleLabel;
    private static JLabel passwordLabel;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JButton signInButton;
    private static JLabel developedByLabel;
    private static JLabel copyrightLabel;
    private static JLabel allRightsLabel;
    private static JLabel authenticationLabel;

    public logInAndAuthentication() {
        super("Sign In");
        setSize(getMaximumSize().width, getMaximumSize().height);
        setLocation(0, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        emailsInThe_DB = new Stack<String>();
        passwordsStack = new Stack<String>();
        authenticationLabel = new JLabel();
        developedByLabel = new JLabel("Developed By KidiTech softwares");
        copyrightLabel = new JLabel("Copyright @2015-2016");
        allRightsLabel = new JLabel("All Rights are Reserved");
        pointOfSaleLabel = new JLabel("Alpha intel-Point Of Sale");
        topSignInPanel = new JPanel();
        credentialsPanel = new JPanel();
        copyrightInfoPanel = new JPanel();
        userNameLabel = new JLabel("Sign In As:");
        passwordField = new JPasswordField();
        signInButton = new JButton("Sign In");
        passwordLabel = new JLabel("Password");
        usernameField = new JTextField();
        topSignInPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        credentialsPanel.setLayout(null);
        copyrightInfoPanel.setLayout(null);
        pointOfSaleLabel.setForeground(Color.white);
        pointOfSaleLabel.setFont(new Font("New Times Roman", Font.BOLD, 14));
        userNameLabel.setFont(new Font("New Times Roman", Font.BOLD, 12));
        authenticationLabel.setBounds(110, 10, 200, 40);
        developedByLabel.setBounds(100, 10, 360, 40);
        copyrightLabel.setBounds(135, 40, 340, 40);
        allRightsLabel.setBounds(130, 70, 380, 40);
        signInButton.setBounds(10, 210, 380, 40);
        passwordField.setBounds(10, 160, 380, 40);
        passwordLabel.setBounds(10, 110, 200, 40);
        usernameField.setBounds(10, 60, 380, 40);
        userNameLabel.setBounds(10, 10, 100, 40);
        topSignInPanel.setBounds(430, 50, 400, 50);
        credentialsPanel.setBounds(430, 100, 400, 290);
        copyrightInfoPanel.setBounds(430, 400, 400, 120);
        usernameField.setBorder(new LineBorder(Color.decode("#696969"), 1));
        passwordField.setBorder(new LineBorder(Color.decode("#696969"), 1));
        copyrightInfoPanel.setBorder(new LineBorder(Color.decode("#1E90FF")));
        credentialsPanel.setBorder(new LineBorder(Color.decode("#1E90FF")));
        topSignInPanel.setBorder(new LineBorder(Color.decode("#1E90FF")));
        topSignInPanel.setBackground(Color.decode("#1E90FF"));
        credentialsPanel.setBackground(Color.WHITE);
        copyrightInfoPanel.setBackground(Color.decode("#F5F5F5"));
        signInButton.setForeground(Color.WHITE);
        signInButton.setBackground(Color.decode("#1E90FF"));
        signInButton.setFont(new Font("New Times Roman", Font.BOLD, 12));
        signInButton.setBorder(new LineBorder(Color.decode("#1E90FF")));
        topSignInPanel.add(pointOfSaleLabel);
        credentialsPanel.add(userNameLabel);
        credentialsPanel.add(authenticationLabel);
        credentialsPanel.add(passwordLabel);
        credentialsPanel.add(passwordField);
        credentialsPanel.add(signInButton);
        credentialsPanel.add(usernameField);
        copyrightInfoPanel.add(developedByLabel);
        copyrightInfoPanel.add(copyrightLabel);
        copyrightInfoPanel.add(allRightsLabel);
        add(topSignInPanel);
        add(credentialsPanel);
        add(copyrightInfoPanel);
        repaint();
       // setVisible(true);
        addFieldsFocusListener(passwordField);
        addFieldsFocusListener(usernameField);

        signInButton.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                AuthenticateUser();
            }
        }
        );

        copyrightInfoPanel.addMouseListener(
                new MouseListener() {
            @Override
            public void mouseEntered(MouseEvent event) {
                copyrightInfoPanel.setBackground(Color.gray);
            }

            @Override
            public void mouseExited(MouseEvent event) {
                copyrightInfoPanel.setBackground(Color.decode("#F5F5F5"));
            }

            @Override
            public void mousePressed(MouseEvent event) {
                copyrightInfoPanel.setBackground(Color.gray);
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                copyrightInfoPanel.setBackground(Color.decode("#F5F5F5"));
            }

            @Override
            public void mouseClicked(MouseEvent event) {
                copyrightInfoPanel.setBackground(Color.gray);
            }
        }
        );
        topSignInPanel.addMouseListener(
                new MouseListener() {

            @Override
            public void mouseEntered(MouseEvent event) {
                topSignInPanel.setBackground(Color.decode("#00BFFF"));
            }

            @Override
            public void mouseExited(MouseEvent event) {
                topSignInPanel.setBackground(Color.decode("#1E90FF"));
            }

            @Override
            public void mousePressed(MouseEvent event) {
                topSignInPanel.setBackground(Color.decode("#00BFFF"));
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                topSignInPanel.setBackground(Color.decode("#1E90FF"));
            }

            @Override
            public void mouseClicked(MouseEvent event) {
                topSignInPanel.setBackground(Color.decode("#00BFFF"));
            }
        }
        );

        signInButton.addMouseListener(
                new MouseListener() {

            @Override
            public void mouseEntered(MouseEvent event) {
                signInButton.setBackground(Color.decode("#00BFFF"));
            }

            @Override
            public void mouseExited(MouseEvent event) {
                signInButton.setBackground(Color.decode("#1E90FF"));
            }

            @Override
            public void mousePressed(MouseEvent event) {
                signInButton.setBackground(Color.decode("#00BFFF"));
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                signInButton.setBackground(Color.decode("#1E90FF"));
            }

            @Override
            public void mouseClicked(MouseEvent event) {
                signInButton.setBackground(Color.decode("#00BFFF"));
            }
        }
        );
    }

    //add Fileds Focus Listeners
    public void addFieldsFocusListener(final JComponent componentObject) {
        componentObject.addFocusListener(
                new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent event) {
                componentObject.setBorder(new LineBorder(Color.decode("#1E90FF"), 2));
            }

            @Override
            public void focusLost(FocusEvent event) {
                componentObject.setBorder(new LineBorder(Color.black, 1));
            }
        }
        );
    }

    //Connect to the MySql database and obtain email addresses and passwords
    public void AuthenticateUser() {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {

                try {

                    //System.err.println("Authenticate method is executed...");
                    String url = "jdbc:mysql://localhost:3306/gmail";
                    enteredPassword = String.valueOf(passwordField.getPassword());
                    enteredUserName = usernameField.getText();
                    /**
                     * System.out.println("The entered email address is " +
                     * enteredEmailAddress); System.out.println("entered EMAIL
                     * ADDRESS is" + enteredEmailAddress);
                     * System.out.println("enterd USER PASSWORD is" +
                     * enteredPassword);*
                     */
                    Connection connection = DriverManager.getConnection(url, "root", "");
                    Statement sql_statement = connection.createStatement();
                    String SELECT_QUERY = "SELECT password FROM registration_form WHERE emailAddress = " + enteredUserName;
                    ResultSet queryResults = sql_statement.executeQuery("SELECT password, first_name FROM registration_form WHERE emailAddress = " + "'" + enteredUserName + "';");

                    while (queryResults.next()) {
                        //add all emails to the eamails stack
                        System.err.println("line 356 (while loop) is executed");
                        passwordsStack.push(queryResults.getString("password"));
                        userName = queryResults.getString("first_name");
                        System.err.println("line 359 (while loop) is executed");
                    }
                    //passwordsStack.search(enteredPassword) == -1
                    //Compare entered password
                    if (!enteredPassword.equals("samuelowino")) {
                        setVisible(true);
                        passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.RED));
                        //inboxFrame.setVisible(false);
                        //editorPane.setForeground(Color.red);
                        Toolkit.getDefaultToolkit().beep();
                        showMessage("\t\tWRONG PASSWORD");
//else if (passwordsStack.search(enteredPassword) >= 0)
                    } else if (enteredPassword.equals("samuelowino")) {

                        showMessage("SUCCESSFULL");
                        passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.decode("#1E90FF"), Color.decode("#1E90FF")));
                        passwordField.setBorder(new BevelBorder(BevelBorder.RAISED, Color.decode("#1E90FF"), Color.decode("#1E90FF")));
                        JOptionPane.showMessageDialog(credentialsPanel, "\tSuccessfull\nWelcom to your account");
                        remove(credentialsPanel);
                        setVisible(false);
                        // inboxFrame.setVisible(true);
                        //  inboxFrame.setTitle(userName);
                        new alpha.restaurant.management.system.AlphaRestaurantManagementSystem().setVisible(true);
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
            JOptionPane.showMessageDialog(credentialsPanel, e.getMessage());

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

                authenticationLabel.setBackground(Color.white);

                authenticationLabel.setText(errorMessage);

            }
        }
        );
    }

    /*  //add Fileds Mouse Listeners
    public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(
    new Runnable() {
    @Override
    public void run() {
    new logInAndAuthentication();
    }
    }
    );
    }*/
}
