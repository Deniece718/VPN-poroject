import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class SessionDecrypter {
    
	private Cipher cipher;
	
	public SessionDecrypter(String key, String iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException 
    {
		SessionKey secretKey = new SessionKey(key);
     	byte[] decodedIv = Base64.getDecoder().decode(iv);//decode the encrypted iv
     	IvParameterSpec originalIv = new IvParameterSpec(decodedIv);
     	this.cipher = Cipher.getInstance("AES/CTR/NoPadding");
    	cipher.init(Cipher.DECRYPT_MODE,secretKey.getSecretKey(),originalIv);
    	//initialize the cipher	with same key and initialization vector for decryption
    }
    
    public CipherInputStream openCipherInputStream(InputStream input) 
    {  // generate decrypted plaintext in the plainoutput file
		return new CipherInputStream(input,cipher);
    }

}
