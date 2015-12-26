
package com;
//this is the code for the spalsh screen....
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class SplashScreenFrame extends JFrame {
    
    private static LogInScreen logInscreen ;
    private static JPanel imagePanel;
    private static JProgressBar progressBar;
    private static ImageIcon splashImage;
    private static JLabel imgLabel;
    
    public SplashScreenFrame(){
        
        super("");
        setUndecorated(true);
        setSize(500,320);
        setLocation(250,150);
        setVisible(true);
        setLayout(null);
        
        splashImage = new ImageIcon( getClass().getResource("splash.png"));
        imagePanel = new JPanel();
        imgLabel = new JLabel(splashImage);
        progressBar = new JProgressBar(0,100);
        progressBar.setStringPainted(true);
        progressBar.setString("Loading modules...");
        progressBar.setForeground(Color.BLUE);
        progressBar.setBackground(Color.gray);
        imagePanel.setBackground(Color.blue);
        
        logInscreen = new LogInScreen();
        
        progressBar.setBounds(0,300,getContentPane().getSize().width,20);
        imgLabel.setBounds(0,0,getContentPane().getSize().width,319);
        imagePanel.setBounds(0,0,getContentPane().getSize().width,319);
        
        imagePanel.add(imgLabel);
        
        add(progressBar);
        add(imagePanel);
        
        
        
    }
    
    
    //Application start up code
    
    public void onStart() throws ClassNotFoundException {
        
        //create a seperate thread
        Thread backgroundScanningThread = new Thread(
                
                new Runnable(){
                    
                    @Override
                    public void run(){
                        
                        int TASK_SIMULATOR = 0;
                        //TEST CONNECTION TO DATABASE
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                       
                        TASK_SIMULATOR = 6;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Connecting to server...");
                        
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        
                        TASK_SIMULATOR = 9;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Connecting to server...");
                        
                        
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        
                        TASK_SIMULATOR = 18;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Connecting to server...");
                        
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        
                        TASK_SIMULATOR = 25;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Connecting to server...");
                        
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        
                        TASK_SIMULATOR = 40;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Connecting to server...");
                        //OPEN BACK UP FILE
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        TASK_SIMULATOR = 60;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Loading app data...");
                        //CHECK INTERNET CONNECTION
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        TASK_SIMULATOR = 70;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("testing internet conection...");
                        //LOAD MODULES
                        
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        TASK_SIMULATOR = 80;
                        
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Loading modules");
                        //LOAD CONTACTS FROM THE DATABASE
                        
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        TASK_SIMULATOR = 96;
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Retieriving perosnal data...");
                        //DISPLAY LOG IN SCREEN
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Done loading modules");
                        TASK_SIMULATOR = 100;
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        progressBar.setValue(TASK_SIMULATOR);
                        progressBar.setString("Application start app complete...");
                        
                        try{
                            
                             Thread.sleep(100);
                            
                        }catch(InterruptedException e){}
                        setVisible(false);
                        
                        logInscreen.setVisible(true);
                        
                    }
                }
        );
        
        backgroundScanningThread.start();
        
        /**
        try {
            UIManager.setLookAndFeel(
                    
                    UIManager.getSystemLookAndFeelClassName()
        } catch (InstantiationException ex) {
            Logger.getLogger(SplashScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(SplashScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(SplashScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
        }**/
    }
    
    public static void main(String[]args)  {
        
        javax.swing.SwingUtilities.invokeLater(
                
                 new Runnable(){
                     
                     @Override
                     public void run(){
                         
                         try {
                         
                             Thread.sleep(10);
                             
                         }catch(InterruptedException e){}
                         
                         try {
                             new SplashScreenFrame().onStart();
                         } catch (ClassNotFoundException ex) {
                             Logger.getLogger(SplashScreenFrame.class.getName()).log(Level.SEVERE, null, ex);
                         }
                         
                         
                         
                     }
                 }
        );
    }
}
