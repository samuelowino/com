package com;

import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.EmptyStackException;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;

import java.util.Stack;

public class InboxFrame extends JFrame {

    private static ContactsFrame contactsFrame;
    private static JList searchResult; 
    private static JButton searchButton;
    private static Stack first_names_stack;
    private static Stack last_name_stack;
    private static JLabel searchLable;
    private static JTextField search;
    private static final JPanel topPanel = new JPanel();
    private static final JPanel emailsPanel = new JPanel();
    private static final JPanel mail = new JPanel();
    private static final JPanel mail2 = new JPanel();
    private static final JPanel mail3 = new JPanel();
    private static final JPanel mail4 = new JPanel();
    private static final JPanel mail5 = new JPanel();
    private static JFileChooser saveFiles;
    private static JFileChooser openFiles;
    private static JMenuBar menuBar;
    private static JMenu Contacts;
    private static JMenu inbox;
    private static JMenu activityLog;
    private static JMenu sendSms;
    private static JMenu onlineChat;
    private static JMenu voiceChat;
    private static JMenu debug;
    private static JMenu proContacts;
    private static JMenu team;
    private static JMenu tools;
    private static JMenu window;
    private static JMenu help;
    private static JMenuItem newProject;
    private static JMenuItem myContacts;
    private static JMenuItem attachFile;
    private static JMenuItem saveFile;
    private static Object[] o;
    JButton testButton = new JButton("TESTR");

    public InboxFrame() {

        //setVisible(false);
        setSize(getMaximumSize().width,getMaximumSize().height);
        setBackground(Color.white);
        setLocation(getLocation().x,getLocation().y);
        setLayout(new BorderLayout());
        setResizable(true);
        setIconImage(new ImageIcon("anonimas.jpg").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

   
        myContacts = new JMenuItem("Contacts");
        contactsFrame = new ContactsFrame();
        searchLable = new JLabel("Search\t");
        search = new JTextField(20);
        searchResult = new JList();
        newProject = new JMenuItem("New Project");
        searchButton = new JButton("Search");
        
        first_names_stack = new Stack();
        last_name_stack = new Stack();

        Contacts = new JMenu("Contacts");
        inbox = new JMenu("Inbox");
        activityLog = new JMenu("Activity Logs");
        sendSms = new JMenu("Send SmS");
        onlineChat = new JMenu("Online Chat");
        voiceChat = new JMenu("Call");
        debug = new JMenu("Debug");
        proContacts = new JMenu("ProContacts");
        team = new JMenu("Team");
        tools = new JMenu("Tools");
        window = new JMenu("Window");
        help = new JMenu("Help");
        attachFile = new JMenuItem("Attach A File");
        saveFile = new JMenuItem("save File");

        saveFiles = new JFileChooser();
        openFiles = new JFileChooser();

        //SET UP A DROP DOWN MENU BAR
        menuBar = new JMenuBar();
        Contacts.add(newProject);
        Contacts.add(myContacts);
        inbox.add(attachFile);
        inbox.add(saveFile);
        menuBar.add(Contacts);
        menuBar.add(inbox);
        menuBar.add(activityLog);
        menuBar.add(sendSms);
        menuBar.add(onlineChat);
        menuBar.add(voiceChat);
        menuBar.add(debug);
        menuBar.add(proContacts);
        menuBar.add(team);
        menuBar.add(tools);
        menuBar.add(window);
        menuBar.add(help);

        setJMenuBar(menuBar);

        //set properties for the panels
        topPanel.setBackground(Color.PINK);

        emailsPanel.setBackground(Color.WHITE);

        mail.setBackground(Color.GREEN);

        mail.add(searchLable);
        mail.add(searchResult);
        mail.add(search);
        mail.add(searchButton);

        mail2.add(searchResult);
        
        
        search.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

                getSearchData(search.getText());
            }

            //DO NOTHING METHODS IMPLEMENTED FROM KEY_LISTENER INTERFACE
            @Override
            public void keyPressed(KeyEvent e) {
                // search_db(search.getText());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // search_db(search.getText());
            }

            
        });
        
        searchButton.addActionListener(
                
                new ActionListener(){
                
                    @Override
                    public void actionPerformed(ActionEvent event){
                        
                        String value = search.getText();
                        getSearchData(value);
                    }
                }
        );
        mail2.setBackground(Color.BLUE);

        mail3.setBackground(Color.BLACK);

        mail4.setBackground(Color.orange);

        mail5.setBackground(Color.GREEN);

        //add panel to frame
        add(topPanel, BorderLayout.NORTH);

        add(new JScrollPane(emailsPanel), BorderLayout.CENTER);

        emailsPanel.setLayout(new GridLayout(5, 1, 1, 1));
        emailsPanel.add(mail);

        emailsPanel.add(mail2);

        emailsPanel.add(mail3);

        emailsPanel.add(mail4);

        emailsPanel.add(mail5);
        //TO DO RESIZE PANEL TO FIT PERFECTLY IN THE FRAME...

        newProject.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {
                        JOptionPane.showMessageDialog(null, "New Project Created..");
                    }
                }
        );

        saveFile.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {

                        saveFiles.showSaveDialog(getContentPane());

                    }
                }
        );

        attachFile.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        openFiles.showOpenDialog(getContentPane());
                    }
                }
        );
        
        myContacts.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent event){
                    
                        contactsFrame.setVisible(true);
                    }
                }
        );

    }
    
   

    /**
    public void search_db(final String searchValue) {

        SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {
                        getSearchData(searchValue);
                    }

                }
        );

    }**/

    public void getSearchData(String user_search_input) {

        try {

            String SELECT_SEARCH_QUERY = "SELECT first_name FROM registration_form WHERE first_name LIKE "+"'"+"%" + user_search_input + "%"+"'"+";";
            String mysqlServerUrl = "jdbc:mysql://localhost:3306/gmail";
            Connection connect = DriverManager.getConnection(mysqlServerUrl, "root", "");
            Statement sqlStmt = connect.createStatement();
            ResultSet results = sqlStmt.executeQuery(SELECT_SEARCH_QUERY);

            while (results.next()) {

                int counter= 0;
                
                first_names_stack.push(results.getString("first_name"));
               // o[counter] = results.getString("first_name");
                
                counter++;
                //last_name_stack.push(results.getString("last_name"));
            }

            try {
                //System.out.println("First Name" + first_names_stack.peek());
                searchResult.setListData(o);
                //System.out.println("Last Name" + last_name_stack.pop());
            } catch (EmptyStackException e) {
                System.out.println("No Match Found");
                searchResult.add(new JLabel("No Result Found"));
            }

        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }
    /**
     * public static void main(String[]args){ new InboxFrame(); }*
     */
}
