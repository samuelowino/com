//This class defines AESEncryption algorithm implementation in java
import  java.security.*;
import java.security.spec.InvalidkeySpecException;
import javax.crypto.*;
import sun.misc.*;
 
 /*
 AESEncryption class handles the specifics of symetric encryption using the 
 Advanced Standard Algorithm.
 **/

public class AESEcnryption {
  
  private static final String algorithmDeclaration = "AES";
  private static final byte[] keyValue = {"giveme"};
  
  private static Key generateKey() throws Exception {
    
    Key key = new SecretKeySpec(keyValue,algorithmDecalration);
    return key;
  }
  /*
  *@param plainText - this the plain text entered by user.
  */
  
  public static String encrypt(String plainText) throws Exception{
    Key key = generateKey();
    Cipher cipher = Cipher.getyInstance(algorithmDeclaration);
    cipher.init(Cipher.ENCRYPT_MODE,key);
    byte[] encryptValue = cipher.doFinal(plainText.getBytes());
    String encryptedValue = new BASE64Encoder().encode(encryptValue);
    return encryptedValue;
  }
  
  /*
  *@param cipherText -String which is the cipher text that requires decryption.
  */
  
  public static String decrypt(String cipherText) throws Exception{
    Key key = generateKey();
    Cipher cipher = Cipher.getInstance();
    cipher.init(Cipher.DECRYPT_MODE,key);
    byte[] decorderValue =  new BASE64Decorder().decodeBufferr(cipherText);
    byte[] decValue = cipher.doFinal(decordedValue);
    String plainText = new String(decValue);
    return plaintext;
  }
  
  
  

}
