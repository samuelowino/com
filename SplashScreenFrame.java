package com;
//this is the code for the spalsh screen....

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class SplashScreenFrame extends JFrame {

    private static int splashWaitTime;
    private static String splashScreenMessage;
    private static LogInScreen logInscreen;
    private static JPanel imagePanel;
    private static JProgressBar progressBar;
    private static ImageIcon splashImage;
    private static ImageIcon splashImage2;
    private static JLabel imgLabel;

    public SplashScreenFrame() {

        //CREATE FRAME WINDOW
        super("iNBox");
        setUndecorated(true);
        setSize(500, 320);
        setLocation(300, 150);
        setVisible(true);
        setLayout(null);

        //ASSING WIDGET OBJECTS
        splashImage = new ImageIcon(getClass().getResource("splash.png"));
        splashImage2 = new ImageIcon(getClass().getResource("img_1.png"));
        imagePanel = new JPanel();
        imgLabel = new JLabel(splashImage);
        progressBar = new JProgressBar(0, 100);

        //SET WIDGET PROPERTIES
        progressBar.setStringPainted(true);
        progressBar.setString("Loading modules...");
        progressBar.setForeground(Color.BLUE);
        progressBar.setBackground(Color.gray);
        imagePanel.setBackground(Color.blue);
        progressBar.setBounds(0, 300, getContentPane().getSize().width, 20);
        imgLabel.setBounds(0, 0, getContentPane().getSize().width, 319);
        imagePanel.setBounds(0, 0, getContentPane().getSize().width, 319);

        //ASSIN NEW logInScren class object this object is used for redisplaying the log in screen 
        logInscreen = new LogInScreen();

        //ADD THE imageIcon  to image panel
        imagePanel.add(imgLabel);

        //add components to the container (JFrame)
        add(progressBar);
        add(imagePanel);

        try {

            onRunBackgroundTask();

        } catch (ClassNotFoundException e) {
        }
    }

    //Application start up code
    public void onRunBackgroundTask() throws ClassNotFoundException {

        //create a seperate thread
        Thread backgroundScanningThread = new Thread(
                new Runnable() {

            @Override
            public void run() {

                for (int taskLevel = 0; taskLevel <= 100; taskLevel++) {

                    simulateBackgroundTask(taskLevel, getSplashTime(taskLevel), getSplashScrenMessage(taskLevel));
                }

            }
        }
        );

        backgroundScanningThread.start();

        /**
         * try { UIManager.setLookAndFeel(
         *
         * UIManager.getSystemLookAndFeelClassName() } catch
         * (InstantiationException ex) {
         * Logger.getLogger(SplashScreenFrame.class.getName()).log(Level.SEVERE,
         * null, ex); } catch (IllegalAccessException ex) {
         * Logger.getLogger(SplashScreenFrame.class.getName()).log(Level.SEVERE,
         * null, ex); } catch (UnsupportedLookAndFeelException ex) {
         * Logger.getLogger(SplashScreenFrame.class.getName()).log(Level.SEVERE,
         * null, ex); }*
         */
        //THIS METHOD SIMULATES THE BACKGROUND TASK ON THW SWING WORKER THREAD
    }

    //DETERMINE WAIT TIME FOR THE SPLASH
    protected int getSplashTime(int levelOfTask) {

        if (levelOfTask <= 45) {
            progressBar.setBackground(Color.BLACK);
            splashWaitTime = 100;
            
            
        } else if (levelOfTask <= 50 && levelOfTask > 45) {
            
            progressBar.setBackground(Color.GRAY);
            splashWaitTime = 100;
            
        } else if (levelOfTask <= 75 && levelOfTask > 50) {
            
            splashWaitTime = 100;
            
        } else if (levelOfTask <= 79 && levelOfTask > 75) {
            
            splashWaitTime = 100;
            
        } else if (levelOfTask <= 83 && levelOfTask > 79) {
            splashWaitTime = 2000;
            
        } else if (levelOfTask <= 85 && levelOfTask > 83) {
            
            splashWaitTime = 1000;
            
        } else if (levelOfTask <= 100 && levelOfTask > 85) {
            
            splashWaitTime = 1500;
        }

        return splashWaitTime;
    }

    //DETERMINE MESSAGE TO BE DISPLAYED IN THE SPLASH
    protected String getSplashScrenMessage(int levelOfTask) {

        if (levelOfTask <= 30) {

            splashScreenMessage = "Loading modules...";

        } else if (levelOfTask <= 35 && levelOfTask > 30) {

            splashScreenMessage = "Connecting to server...";

        } else if (levelOfTask <= 45 && levelOfTask > 35) {

            splashScreenMessage = "Obtaining app data...";

        } else if (levelOfTask <= 75 && levelOfTask > 45) {

            splashScreenMessage = "Creating workspace...";

        } else if (levelOfTask <= 79 && levelOfTask > 75) {

            splashScreenMessage = "creating workpace...";

        } else if (levelOfTask <= 83 && levelOfTask > 79) {

            splashScreenMessage = "Retrieving recent activities...";

        } else if (levelOfTask <= 85 && levelOfTask > 83) {

            splashScreenMessage = "Done modules";

        } else if (levelOfTask <= 98 && levelOfTask > 85) {

            splashScreenMessage = "Application start up completing... ";
            
            
        }
        else if (levelOfTask <= 100 && levelOfTask > 98) {

            splashScreenMessage = "Application start up complete... ";
            
            
        }
        return splashScreenMessage;
    }

    protected void simulateBackgroundTask(int levelOfTask, int waitTimeInMillis, String taskDesctriptionToDisplay) {

        progressBar.setString("" + taskDesctriptionToDisplay);
        progressBar.setValue(levelOfTask);
        try {

            Thread.sleep(waitTimeInMillis);

        } catch (InterruptedException e) {
        }

        if (levelOfTask == 100) {

            setVisible(false);
            logInscreen.setVisible(true);
        }

    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {

            @Override
            public void run() {

                new SplashScreenFrame();

            }
        }
        );
    }
}
