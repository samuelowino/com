package com;

//   THIS IS THE CODE FOR THE iNBOX
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JFileChooser;
import javax.swing.JSplitPane;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;

public class InboxFrame2 extends JFrame {

    private static String[] typesOfMesssages = {"All", "Flagged", "Unread"};
    private static JComboBox allComboBox;
    private static JButton searchButton;
    private static JPanel onInboxPanel;
    private static JLabel inboxPanelLable;
    private static ImageIcon searchImage;
    private static JTextField panelSearchField;
    private static LineBorder lineInboxLabelBorder;

    private static JPanel panel2;
    private static JFileChooser fileChooser;
    private static JSplitPane splitPane;
    private static JPanel leftPanel;
    private static JPanel rightPanel;
    private static JButton inboxButton;

    private static JComboBox emailFieldBox;
    private static JLabel inboxLabel;
    private static JMenuBar menuBar;
    private static JMenuItem menuItem;
    private static JMenuItem newFile;
    private static JMenuItem saveFile;
    private static JMenuItem openFile;
    private static JMenuItem attachImage;
    private static JMenuItem attachFile;
    private static JMenuItem attachVideo;
    private static JMenuItem openWebCam;
    private static JMenuItem printFile;

    private static Font myFont;

    private static JMenuItem viewProfile;
    private static JMenuItem status;
    private static JMenuItem profilePic;

    private static JMenuItem mostRecent;
    private static JMenuItem filterByMailType;
    private static JMenuItem gmail;
    private static JMenuItem yahoo;
    private static JMenuItem POP;
    private static JMenuItem VoIPInbox;
    private static JMenuItem Bulksms;
    private static JMenuItem sms;
    private static JMenuItem outlook;
    private static JMenuItem other;
    private static JMenuItem all;

    private static JMenuItem viewAllContacts;
    private static JMenuItem addNewContacts;
    private static JMenuItem favouriteContact;
    private static JMenuItem familyContacts;
    private static JMenuItem friendsContacts;
    private static JMenuItem workContacts;
    private static JMenuItem coworkersContacts;
    private static JMenuItem logsContacts;

    private static JMenuItem accounts;
    private static JMenuItem Personalize;
    private static JMenuItem Reading;
    private static JMenuItem helpOption;
    private static JMenuItem help;
    private static JMenuItem trustCenter;
    private static JMenuItem feedBack;
    private static JMenuItem about;
    private static JMenuItem exitApp;
    private static JMenuItem logInwithAnotherAcc;

    private static JMenuItem addAccount;
    private static JMenuItem viewPresent;
    private static JMenuItem changeAccount;

    private static JMenuItem printToHtml;
    private static JMenu subMenu;
    private static JMenu menu;
    private static JMenu fileMenu;
    private static JMenu profileMenu;
    private static JMenu inboxMenu;
    private static JMenu contactsMenu;
    private static JMenu accountsMenu;
    private static JMenu settingsMenu;
    private static JMenu feedBackMenu;
    private static JMenu viewMenu;
    private static JMenu logOutMenu;
    private static JList folders;
    
    private static BufferedReader b_reader;
    private static URL helpHTMLPage;

    private static logInScreen2 logInScreen;
    private static JTextField searchField;

    public InboxFrame2() {

        //SET UP THE FRAME
        super("InboxFrame2");
        logInScreen = new logInScreen2();
        //SET UP THE UI MANAGEMENT CODE
        // UIManager.put("JMenu.background",Color.BLUE);

        //USE TOOLKIT TO OBTASIN SCREEN SIZE
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        setSize(800, 660);
        //setVisible(true);
        setLocation(getLocation().x, getLocation().y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLUE);
        // pack();

        //SET UP LABEL
        panel2 = new JPanel();
        panel2.setBackground(Color.BLUE);
        inboxLabel = new JLabel("Inbox");

        String[] folderContnets = {"Folders", "\t\t", "\t\t", "Inbox", "\t\t", "\t\t", "Outbox", "\t\t", "\t\t", "Drafts", "\t\t", "\t\t", "Junk", "\t\t", "\t\t", "sent", "  "};
        folders = new JList(folderContnets);
        folders.setBackground(Color.BLUE);
        folders.setForeground(Color.WHITE);
        folders.setSize(200, getSize().height / 2);
        folders.setFont(new Font("ALGERIAN ", Font.PLAIN, 14));

        //emailFieldBox = new JComboBox<String>(emailInThe_DB);
        //SET UP Menu Bar
        menu = new JMenu("Profile");
        fileMenu = new JMenu("File");
        profileMenu = new JMenu("Profile");
        inboxMenu = new JMenu("Inbox");
        contactsMenu = new JMenu("Contacts");
        accountsMenu = new JMenu("Accounts");
        settingsMenu = new JMenu("Settings");
        feedBackMenu = new JMenu("FeedBack");
        viewMenu = new JMenu("View");
        logOutMenu = new JMenu("Log Out");
        menu = new JMenu("Profile");

        subMenu = new JMenu("submenu");

        menuItem = new JMenuItem("Change Profile settings...");
        newFile = new JMenuItem("New File...");
        saveFile = new JMenuItem("Save File...");
        openFile = new JMenuItem("Open File...");
        attachFile = new JMenuItem("Attach File...");
        attachVideo = new JMenuItem("Attach video...");
        openFile = new JMenuItem("Open File...");
        printFile = new JMenuItem("Print File...");
        attachImage = new JMenuItem("Attach Image...");
        openWebCam = new JMenuItem("Open web cam...");
        printToHtml = new JMenuItem("Print To HTML...");

        viewProfile = new JMenuItem("View Profile...");
        status = new JMenuItem("Status...");
        profilePic = new JMenuItem("Profile Picture...");

        mostRecent = new JMenuItem("Most Recent...");
        filterByMailType = new JMenuItem("Filter by Mail type...");
        gmail = new JMenuItem("Gmail...");
        yahoo = new JMenuItem("Yahoo...");
        POP = new JMenuItem("POP...");
        sms = new JMenuItem("SmS...");
        Bulksms = new JMenuItem("Bulk Sms...");
        VoIPInbox = new JMenuItem("VoIP Inbox...");
        outlook = new JMenuItem("Outlook...");
        other = new JMenuItem("Other...");
        all = new JMenuItem("All...");

        viewAllContacts = new JMenuItem("View all Contatcs...");
        viewAllContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addNewContacts = new JMenuItem("Add New Contacts...");
        addNewContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        favouriteContact = new JMenuItem("Favaourit Contacts...");
        favouriteContact.setCursor(new Cursor(Cursor.HAND_CURSOR));
        familyContacts = new JMenuItem("Family Contatcs...");
        familyContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        friendsContacts = new JMenuItem("Friends Contatcs...");
        friendsContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        workContacts = new JMenuItem("Work Contatcs...");
        workContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        coworkersContacts = new JMenuItem("Co-workers' Contatcs...");
        coworkersContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logsContacts = new JMenuItem("Contact Logs...");
        logsContacts.setCursor(new Cursor(Cursor.HAND_CURSOR));

        accounts = new JMenuItem("Accounts...");
        accounts.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Personalize = new JMenuItem("Personalize...");
        Personalize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        Reading = new JMenuItem("Reading...");
        Reading.setCursor(new Cursor(Cursor.HAND_CURSOR));
        helpOption = new JMenuItem("help...");
        helpOption.setCursor(new Cursor(Cursor.HAND_CURSOR));
        trustCenter = new JMenuItem("Trust center...");
        trustCenter.setCursor(new Cursor(Cursor.HAND_CURSOR));
        feedBack = new JMenuItem("Feed Back...");
        feedBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        about = new JMenuItem("About...");
        about.setCursor(new Cursor(Cursor.HAND_CURSOR));
        help = new JMenuItem("Help ?");
        help.setCursor(new Cursor(Cursor.HAND_CURSOR));

        addAccount = new JMenuItem("Add Accounting...");
        addAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewPresent = new JMenuItem("View Existing Accounting...");
        viewPresent.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changeAccount = new JMenuItem("Change Accounting Setting...");
        changeAccount.setCursor(new Cursor(Cursor.HAND_CURSOR));

        exitApp = new JMenuItem("Exit App");
        exitApp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logInwithAnotherAcc = new JMenuItem("Change Account");
        logInwithAnotherAcc.setCursor(new Cursor(Cursor.HAND_CURSOR));

        myFont = new Font("Times New Roman", Font.BOLD, 14);

        //fileMenu.setForeground(Color.LIGHT_GRAY);
        accountsMenu.setFont(myFont);
        accountsMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        accountsMenu.add(addAccount);
        accountsMenu.addSeparator();
        accountsMenu.add(viewPresent);
        accountsMenu.add(changeAccount);
        accountsMenu.addSeparator();
        subMenu.add(changeAccount);

        accountsMenu.add(subMenu);

        fileMenu.setFont(myFont);
        fileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fileMenu.setBackground(Color.WHITE);
        fileMenu.add(newFile);
        fileMenu.addSeparator();
        fileMenu.add(saveFile);
        fileMenu.add(openFile);
        fileMenu.addSeparator();
        fileMenu.add(attachImage);
        fileMenu.add(attachFile);
        fileMenu.add(attachVideo);
        fileMenu.addSeparator();
        fileMenu.addSeparator();
        fileMenu.add(openWebCam);
        fileMenu.addSeparator();
        fileMenu.add(printToHtml);
        fileMenu.addSeparator();
        fileMenu.add(printFile);

        profileMenu.setFont(myFont);
        profileMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //profileMenu.setBackground(Color.WHITE);
        profileMenu.add(new JMenuItem());
        profileMenu.addSeparator();
        profileMenu.add(viewProfile);
        profileMenu.addSeparator();
        profileMenu.add(new JMenuItem());
        profileMenu.add(new JMenuItem());
        profileMenu.addSeparator();
        profileMenu.addSeparator();
        profileMenu.addSeparator();
        profileMenu.add(status);
        profileMenu.addSeparator();
        profileMenu.addSeparator();
        profileMenu.add(new JMenuItem());
        profileMenu.add(profilePic);
        profileMenu.addSeparator();
        profileMenu.add(new JMenuItem());

        inboxMenu.setFont(myFont);
        inboxMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inboxMenu.setBackground(Color.WHITE);
        inboxMenu.add(mostRecent);
        inboxMenu.addSeparator();
        inboxMenu.add(filterByMailType);
        inboxMenu.addSeparator();
        inboxMenu.add(gmail);
        inboxMenu.add(yahoo);
        inboxMenu.add(POP);
        inboxMenu.addSeparator();
        inboxMenu.add(sms);
        inboxMenu.add(Bulksms);
        inboxMenu.addSeparator();
        inboxMenu.add(VoIPInbox);
        inboxMenu.addSeparator();
        inboxMenu.add(outlook);
        inboxMenu.addSeparator();
        inboxMenu.add(other);
        inboxMenu.addSeparator();
        inboxMenu.add(all);

        contactsMenu.setFont(myFont);
        contactsMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        contactsMenu.setBackground(Color.WHITE);
        contactsMenu.add(viewAllContacts);
        contactsMenu.addSeparator();
        contactsMenu.add(addNewContacts);
        contactsMenu.addSeparator();
        contactsMenu.add(favouriteContact);
        contactsMenu.addSeparator();
        contactsMenu.add(favouriteContact);
        contactsMenu.addSeparator();
        contactsMenu.add(familyContacts);
        contactsMenu.addSeparator();
        contactsMenu.add(workContacts);
        contactsMenu.addSeparator();
        contactsMenu.add(coworkersContacts);
        contactsMenu.addSeparator();
        contactsMenu.add(logsContacts);
        contactsMenu.add(friendsContacts);
        contactsMenu.addSeparator();

        settingsMenu.setFont(myFont);
        settingsMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        settingsMenu.setBackground(Color.WHITE);
        settingsMenu.add(accounts);
        settingsMenu.addSeparator();
        settingsMenu.add(Personalize);
        settingsMenu.addSeparator();
        settingsMenu.add(Reading);
        settingsMenu.addSeparator();
        settingsMenu.add(helpOption);
        settingsMenu.addSeparator();
        settingsMenu.add(help);
        settingsMenu.addSeparator();
        settingsMenu.add(trustCenter);
        settingsMenu.addSeparator();
        settingsMenu.add(feedBack);
        settingsMenu.addSeparator();
        settingsMenu.addSeparator();
        settingsMenu.add(about);

        menu.add(menuItem);

        logOutMenu.setFont(myFont);
        logOutMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logOutMenu.add(exitApp);
        logOutMenu.addSeparator();
        logOutMenu.add(new JMenuItem(" "));
        logOutMenu.add(logInwithAnotherAcc);

        feedBackMenu.setFont(myFont);
        viewMenu.setFont(myFont);

        menuBar = new JMenuBar();
        menuBar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        menuBar.setBackground(Color.BLUE);
        // menuBar.setSize(this.getMaximumSize().width,400);
        menuBar.setForeground(Color.WHITE);

        fileMenu.setForeground(Color.WHITE);
        profileMenu.setForeground(Color.WHITE);
        logOutMenu.setForeground(Color.WHITE);
        settingsMenu.setForeground(Color.WHITE);
        contactsMenu.setForeground(Color.WHITE);
        inboxMenu.setForeground(Color.WHITE);
        accountsMenu.setForeground(Color.WHITE);
        viewMenu.setForeground(Color.WHITE);
        feedBackMenu.setForeground(Color.WHITE);

        menuBar.add(fileMenu);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(profileMenu);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(inboxMenu);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(contactsMenu);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(accountsMenu);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(settingsMenu);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.add(feedBackMenu);
        menuBar.add(Box.createHorizontalStrut(50));
        menuBar.add(viewMenu);

        //CREATE A HORIZONTAL GLUE USING BOX LAYOUT
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(logOutMenu);
        menuBar.add(Box.createHorizontalStrut(30));
        menuBar.setForeground(Color.WHITE); //FOREGROUND COLOR NOT RENDERERING
        menuBar.setBorder(new LineBorder(Color.WHITE, 5));

        menuBar.add(Box.createVerticalGlue());
        menuBar.add(Box.createVerticalStrut(30));

        setJMenuBar(menuBar);

        //SET UP PANELS
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel.setLayout(null);
        panelSearchField = new JTextField();
        panelSearchField.setBounds(rightPanel.getLocation().x, rightPanel.getLocation().y, rightPanel.getSize().width, 00);
        rightPanel.add(panelSearchField);
        leftPanel.setLayout(new GridLayout(1, 1, 1, 1));
        //rightPanel.add( new JLabel("Welcome to your in box this section is still under construction"), new FlowLayout(FlowLayout.CENTER));

        folders.setFont(myFont);
        leftPanel.add(folders);

        //SETTUP THE SPLIT PANE
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        leftPanel.setBackground(Color.ORANGE);
        rightPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(new LineBorder(Color.WHITE));
        leftPanel.setPreferredSize(leftPanel.getPreferredSize());
        leftPanel.setMinimumSize(leftPanel.getPreferredSize());
        rightPanel.setMinimumSize(rightPanel.getMaximumSize());
        splitPane.setRightComponent(rightPanel);
        splitPane.setLeftComponent(leftPanel);
        splitPane.setDividerLocation(180);

        //SETUP KEYBOARD MNEMONICS AND ACCELERATORS
        saveFile.setMnemonic(KeyEvent.VK_S);
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        openFile.setMnemonic(KeyEvent.VK_O);
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        //SETUP THE FILE CHOOSER
        fileChooser = new JFileChooser();

        //@beta 
        saveFile.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        fileChooser.showSaveDialog(getContentPane());

                    }
                }
        );

        openFile.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        fileChooser.showOpenDialog(getContentPane());
                    }
                }
        );

        newFile.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "New File Created");
                    }

                }
        );

        //Set up app exit events
        exitApp.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        Toolkit.getDefaultToolkit().beep();
                        int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit ?", "", JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {

                            setVisible(false);
                            dispose();
                            System.exit(0);

                        }
                    }

                }
        );

        logInwithAnotherAcc.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent event) {

                        Toolkit.getDefaultToolkit().beep();
                        int option = JOptionPane.showConfirmDialog(rightPanel, "Are you sure you want to Log out?", "UniMessenger", JOptionPane.YES_NO_OPTION);

                        if (option == JOptionPane.YES_OPTION) {

                            setVisible(false);

                            logInScreen.setVisible(true);
                        }
                    }
                }
        );

        //CREATE ACTION LISTENER FOR THE FOLDERS LIST
        folders.addListSelectionListener(
                new ListSelectionListener() {

                    @Override
                    public void valueChanged(ListSelectionEvent event) {

                        String selection = folders.getSelectedValue().toString();

                        if (selection.equals("Inbox")) {

                            rightPanel.removeAll();
                            rightPanel.repaint();
                            rightPanel.add(new JLabel("There no messages to show currently"));
                            rightPanel.setBackground(Color.BLUE);

                        } else if (selection.equals("Drafts")) {

                            //rightPanel.setBackground(Color.red);
                            splitPane.remove(rightPanel);
                            splitPane.setRightComponent(panel2);
                            splitPane.repaint();

                        } else if (selection.equals("Outbox")) {

                            rightPanel.setBackground(Color.GREEN);

                        } else if (selection.equals("Junk")) {

                            rightPanel.setBackground(Color.black);
                        }
                    }
                }
        );

        //@BETA :: CODE IS NOT RENDERING
        //Trigger -> MenuItems Mouse events
        getMenuItemsMouseEvents(newFile);
        getMenuItemsMouseEvents(saveFile);
        getMenuItemsMouseEvents(openFile);
        getMenuItemsMouseEvents(attachFile);
        getMenuItemsMouseEvents(attachImage);
        getMenuItemsMouseEvents(attachVideo);
        getMenuItemsMouseEvents(openWebCam);
        getMenuItemsMouseEvents(printToHtml);
        getMenuItemsMouseEvents(printFile);

        //file menu  And menu bar -> Mouse events
        getMenusMouseEvents(fileMenu);

        getMenusMouseEvents(profileMenu);

        getMenusMouseEvents(logOutMenu);

        getMenusMouseEvents(settingsMenu);

        getMenusMouseEvents(contactsMenu);

        getMenusMouseEvents(inboxMenu);

        getMenusMouseEvents(accountsMenu);

        getMenusMouseEvents(feedBackMenu);

        getMenusMouseEvents(viewMenu);

        getMenuBarMouseEvent();
        /**
         *
         */
        /**
         * //INBOX PANEL CODE GOES HERE allComboBox = new
         * JComboBox(typesOfMesssages); lineInboxLabelBorder = new
         * LineBorder(Color.BLACK,1); inboxPanelLable = new JLabel("Inbox");
         * panelSearchField = new JTextField("Search"); searchButton = new
         * JButton(); searchButton.setBackground(Color.BLUE);
         * allComboBox.setBounds(451,30,100,30);
         * searchButton.setBounds(451,0,100,30); searchImage = new
         * ImageIcon(getClass().getResource("search.png"));
         * searchButton.setIcon(searchImage);
         *
         * onInboxPanel = new JPanel(); onInboxPanel.setBackground(Color.WHITE);
         * onInboxPanel.setBounds(0, 0, 600, 500); onInboxPanel.setLayout(null);
         *
         * inboxPanelLable.setBounds(0,31,450,30);
         * inboxPanelLable.setBorder(lineInboxLabelBorder);
         * panelSearchField.setBounds(0, 0, 450, 30);
         * panelSearchField.setEditable(false);
         *
         * onInboxPanel.add(searchField); onInboxPanel.add(searchButton);
         * onInboxPanel.add(inboxPanelLable); onInboxPanel.add(allComboBox);
         *
         *
         * searchField.addMouseListener( new MouseListener() {
         *
         * @Override public void mouseEntered(MouseEvent event) {
         *
         * }
         *
         * @Override public void mouseExited(MouseEvent event) {
         *
         * searchField.setEnabled(false); searchField.setEditable(false);
         * //searchField.setText("Search"); }
         *
         * @Override public void mousePressed(MouseEvent event) { //do nothing }
         *
         * @Override public void mouseReleased(MouseEvent event) { //do nothing
         * }
         *
         * @Override public void mouseClicked(MouseEvent event) {
         *
         * searchField.setText(""); searchField.setEnabled(true);
         * searchField.setEditable(true); } } );*
         */
        //ADD SPLIT PANE AND MENU BAR TO THE 
        add(splitPane);
        setJMenuBar(menuBar);
    }

    public void getMenuItemsMouseEvents(final Component object) {

        //CREATE MOUSE EVENT LISTENERS FOR THE MENU(S)
        object.addMouseListener(
                new MouseListener() {

                    @Override
                    public void mouseEntered(MouseEvent event) {

                    }

                    @Override
                    public void mouseExited(MouseEvent event) {

                    }

                    @Override
                    public void mousePressed(MouseEvent event) {

                    }

                    @Override
                    public void mouseClicked(MouseEvent event) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent event) {
                        //object.setBackground(Color.BLUE);
                        //DO NOTHING
                    }
                }
        );
    }

    public void getMenusMouseEvents(final Component object) {

        //CREATE MOUSE LISTENER FOR THE MENU(S)
        object.addMouseListener(
                new MouseListener() {

                    @Override
                    public void mouseExited(MouseEvent event) {

                        object.setFont(new Font("Times New Roman", Font.BOLD, 14));
                        object.setForeground(Color.WHITE);
                    }

                    @Override
                    public void mouseEntered(MouseEvent event) {

                        object.setForeground(Color.LIGHT_GRAY);
                        object.setFont(new Font("Times New Roman", Font.ITALIC, 15));
                        //menuBar.setBorder(new LineBorder(Color.lightGray, 3));
                    }

                    @Override
                    public void mousePressed(MouseEvent event) {

                        object.setForeground(Color.GREEN);
                    }

                    @Override
                    public void mouseReleased(MouseEvent event) {

                    }

                    @Override
                    public void mouseClicked(MouseEvent event) {
                        object.setForeground(Color.GREEN);
                    }
                }
        );
    }

    public void getMenuBarMouseEvent() {

        //CREATE MOUSE LISTNERS FOR THE MENUBAR 
        menuBar.addMouseListener(
                new MouseListener() {

                    @Override
                    public void mousePressed(MouseEvent event) {

                        //DON NOTHING 
                    }

                    @Override
                    public void mouseClicked(MouseEvent event) {

                        //DON NOTHING 
                    }

                    @Override
                    public void mouseReleased(MouseEvent event) {
                        //DON NOTHING
                    }

                    @Override
                    public void mouseExited(MouseEvent event) {

                        menuBar.setBorder(new LineBorder(Color.WHITE, 5));
                    }

                    @Override
                    public void mouseEntered(MouseEvent event) {

                        menuBar.setBorder(new LineBorder(Color.LIGHT_GRAY, 5));

                    }

                }
        );
        
        helpOption.addActionListener(
                
                new ActionListener(){
                    
                    @Override
                    public void actionPerformed(ActionEvent event){
                        
                        obtainHelpHTMLPage();
                    }
                }
        );
    }

    public void obtainHelpHTMLPage(){
        
        try{
            
             String helpContent;
             helpHTMLPage = new URL("file:///C:/Users/user/Desktop/JavaTestPage/thePage2.html");
             b_reader = new BufferedReader( new InputStreamReader(helpHTMLPage.openConnection().getInputStream()));
             
             while((helpContent = b_reader.readLine())!= null){
             
                 System.out.println(helpContent);
             }
             
             
            
        }catch(MalformedURLException e){
            
            e.printStackTrace();
            
        }catch(IOException e){
            
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        new InboxFrame2();
                    }
                }
        );

    }
}
