package alpha.restaurant.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import foododering.ProductSaleScreen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.scene.control.DatePicker;

public class AlphaRestaurantManagementSystem extends JFrame {

    private static String dataValues[][];
    private static int rows;
    private static DatePicker myPicker;
    private static ProductSaleScreen productSaleScreen;
    private static JPanel rightPanel;
    private static JPanel topRightPanel;
    private static JPanel leftPanel;
    private static JLabel cartLabel;
    private static JLabel posNameLabel;
    private static JButton startSalesButton;
    private static JLabel taskLabel;
    private static JLabel taskListInfoLabel;
    private static JScrollPane tableScrollPane;
    private static JLabel piramidsLabel;
    private static JTable memoTable;
    private static JLabel contactsLabel;
    private static JLabel companyEmailLabel;

    public AlphaRestaurantManagementSystem() {
        super("Students Voting System");
        setSize(getMaximumSize().width, getMaximumSize().height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        topRightPanel = new JPanel();
        taskLabel = new JLabel("Task List");
        taskListInfoLabel = new JLabel("Task List provide information from Clerks and Voting office");
        contactsLabel = new JLabel("Contact Us.");
        companyEmailLabel = new JLabel("info@kiditech.org");
        rightPanel = new JPanel();
        leftPanel = new JPanel();
        cartLabel = new JLabel();
        posNameLabel = new JLabel();
        //productSaleScreen = new ProductSaleScreen();
        startSalesButton = new JButton("Start Sales");
        piramidsLabel = new JLabel();
        leftPanel.setBorder(new LineBorder(Color.BLACK));
        rightPanel.setBorder(new LineBorder(Color.BLACK));
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(null);
        posNameLabel.setIcon(new ImageIcon(getClass().getResource("posNameLabel.png")));
        cartLabel.setIcon(new ImageIcon(getClass().getResource("cart.png")));
        rightPanel.setBackground(Color.WHITE);
        startSalesButton.setBackground(Color.decode("#32CD32"));
        piramidsLabel.setIcon(new ImageIcon(getClass().getResource("moreposIcon.png")));
        topRightPanel.setBackground(Color.decode("#F5F5F5"));
        taskLabel.setFont(new Font("Time New Roman", Font.BOLD, 16));
        taskListInfoLabel.setFont(new Font("New Times Roman", Font.BOLD, 12));
        rightPanel.setLayout(null);
        String[] columns = {"ID", "From", "To", "Title", "Description", "Date"};
        String[][] data = {{"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"14", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"15", "Assitant Manager", "All workers", "SAlry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"},
        {"6", " Manager", "IT Depertment", "Salry Rise", "They will be a salary rise for all epmloyees", "2016-03-16"}

        };
        memoTable = new JTable(data, columns);
        memoTable.setBackground(Color.WHITE);
        tableScrollPane = new JScrollPane(memoTable);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tableScrollPane.setBounds(20, 50, 350, 350);
        taskListInfoLabel.setBounds(1, 90, 200, 10);
        topRightPanel.setBounds(840, 60, 400, 60);
        taskLabel.setBounds(200, 40, 100, 50);
        piramidsLabel.setBounds(260, 370, 100, 100);
        companyEmailLabel.setBounds(270, 340, 100, 20);
        contactsLabel.setBounds(285, 310, 100, 20);
        startSalesButton.setBounds(270, 250, 100, 50);
        posNameLabel.setBounds(180, 130, 300, 100);
        cartLabel.setBounds(250, 20, 200, 100);
        leftPanel.setBounds(20, 60, 800, 500);
        rightPanel.setBounds(840, 120, 400, 440);
        leftPanel.add(cartLabel);
        leftPanel.add(companyEmailLabel);
        leftPanel.add(posNameLabel);
        leftPanel.add(startSalesButton);
        leftPanel.add(contactsLabel);
        leftPanel.add(piramidsLabel);
        topRightPanel.add(taskLabel);
        topRightPanel.add(taskListInfoLabel);
        rightPanel.add(tableScrollPane);
        add(leftPanel);
        add(rightPanel);
        add(topRightPanel);
        repaint();
        setVisible(true);

        startSalesButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                       new  alpha.mainInterface(true);
                        setVisible(false);
                    }
                }
        );
    }

    public String[][] getMedicalRecords() {
        try (Connection conect = DriverManager.getConnection("jdbc:mysql://localhost:3306/online_students_registration", "root", "")) {
            Statement stm = conect.createStatement();
            String SELECT_QUERY = "SELECT COUNT(*) FROM medicalrecords ;";
            ResultSet cursor = stm.executeQuery(SELECT_QUERY);
            while (cursor.next()) {
                rows = cursor.getInt("COUNT(*)");
                System.out.println("Table will have " + rows + " Rows");
            }
            System.err.println("Contacts row count is obtained!!");
            if (rows < 1) {
                JOptionPane.showMessageDialog(null, "There is NO DATA");
                //contactsRowsCount = 1;
                //System.out.println("Table rows succefully reset to  " + contactsRowsCount + " Rows");
                dataValues = new String[1][8];
                //dataValues[1][5] = "No Values";

                for (int i = 0; i < 1; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (j == 0) {
                            dataValues[i][j] = "No Details Available";
                            System.out.println("" + dataValues);
                        } else {
                            dataValues[i][j] = "...";
                            System.out.println("Contacts" + dataValues[i][j]);
                        }
                    }
                }

                System.out.println("Return statement is being executed on 0 rows ");
                //return doctoredDataValues;
            } else if (rows > 0) {

                System.out.println("obtain contacts code is being run under  " + rows + " Rows");
                dataValues = new String[rows][8];    //declare array for contacts table data
                System.out.println("[ Line 1584 ]The dataValues object for the JTable succefully set");

                String SELECT_QUERY_CONTACT = "SELECT * FROM medicalrecords; ";

                //OBTAIN CONTACTS FROM DB WITH REGARD TO CONTACT CATEGORY SPECIFIED
                ResultSet contactsTableCursor = stm.executeQuery(SELECT_QUERY_CONTACT);
                //use iterator-algorithm to insert values into the JTable
                for (int i = 0; contactsTableCursor.next() && i < rows; i++) {
                    for (int j = 0; j < 8; j++) {
                        dataValues[i][j] = contactsTableCursor.getString(j + 1);
                        System.out.println("Contacts" + dataValues[i][j]);
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Medical Details Added Succefully!!");
        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Unable to Obtain contacts:Server is Offline(LINE 1568)" + e.getMessage());
        }

        return dataValues;

    }
    
    

    /*public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(
    new Runnable() {
    @Override
    public void run() {
    new AlphaRestaurantManagementSystem();
    }
    }
    );
    }
*/
}
