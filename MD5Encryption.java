/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package SystemSecurity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

/**
 *
 * @author samuel owino
 */
public class MD5Encryption {
    
    private static String cipherText;
    
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog(null,"Enter password");
        
        if(getCiphertext(input).equals(getCiphertext("emma"))){
            JOptionPane.showMessageDialog(null,"plus");
        }else {
            JOptionPane.showMessageDialog(null,"negative");
        }
    }
    
    public  static String getCiphertext(String password){
        try {
            //Create a messageDigest instance
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            //Add a password to digest
            messageDigest.update(password.getBytes());
            
            //Get the hash bytes
            byte[] bytes = messageDigest.digest();
            
            //this byte[] has bytes in dcimal format
            //Convert to hexadecimal format
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0;i<bytes.length;i++){
                stringBuilder.append(Integer.toString((bytes[i] & 0xFF)+0x100,16).substring(1));
            }
            //Get complete hashed password in hex format
            cipherText = stringBuilder.toString();
            System.err.println(cipherText);
            
        } catch (NoSuchAlgorithmException e) {
        }
        return cipherText;
    }
    
    protected static String getDecryptedPassword(){
        return "";
    }
}
