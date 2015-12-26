package com;
//this code handles contacts and mails
import java.awt.Color;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JEditorPane;

import java.util.Hashtable;
import java.util.Stack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsFrame extends JFrame {

    private static Stack contactsStack;
    private static JEditorPane emails;
    private static Hashtable<String,String> contactsHashtable;

    public ContactsFrame() {

        super("Contacts");

        setSize(500, 500);
        setLocation(300, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new GridLayout(1, 1));

        emails = new JEditorPane();
        emails.setEditable(false);
        contactsHashtable = new Hashtable<>();
        contactsStack = new Stack();
        
        pack();
        //CONNECT TO db OBTAIN EMAILS
        try {
            String connectUrl = "jdbc:mysql://localhost:3306/gmail";
            String SELECT_QUERY = "SELECT first_name,emailAddress FROM registration_form;";
            Connection sqlConnect = DriverManager.getConnection(connectUrl, "root", "");
            Statement sqlStmt = sqlConnect.createStatement();
            ResultSet result = sqlStmt.executeQuery(SELECT_QUERY);

            while (result.next()) {

                contactsHashtable.put(result.getString("first_name"),result.getString("emailAddress"));
                contactsStack.push(result.getString("emailAddress"));
            }
            System.out.println("emailAddress:"+contactsStack.pop());
            
            System.out.println("first_name:"+contactsHashtable.get("samuel"));
        } catch (SQLException e) {
        }
        
        //ADD EMAILS TO EDITOR PANE
    }
}
