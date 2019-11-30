import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SessionKey {

	private SecretKey secretKey;
	
    public SessionKey(int keyLength) throws NoSuchAlgorithmException
    { 
    	SecureRandom random = new SecureRandom();
    	//provide a cryptographically strong random number generator  
    	KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    	keyGen.init(keyLength, random);//initializes the key generator with a specified keysize 
    	//using a user-provided source (SecureRandom) of randomness 
    	secretKey = keyGen.generateKey();//generate a random secretKey
    }
   
    public SessionKey(String encodedKey)
    {
    	byte[] decodedKey=Base64.getDecoder().decode(encodedKey);
    	secretKey = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
    	//convert the String to secretKey 
    }
    
    public SecretKey getSecretKey() {
  	   return secretKey;// to retrieve the corresponding SecretKey
    }
    
    public String encodeKey() {
    	byte[] keyValue= secretKey.getEncoded();
    	String encodedKey=Base64.getEncoder().encodeToString(keyValue);
    	return encodedKey;//using Base64 to encode the specified byte array into a String
    }
}