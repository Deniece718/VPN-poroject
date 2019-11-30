import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;

public class SessionEncrypter {
     
	private SessionKey key;
	private IvParameterSpec iv;
	private Cipher cipher;
	
	public SessionEncrypter(Integer keyLength) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException 
	{
		this.key = new SessionKey(keyLength);//call SessionKey to obtain the session key		
		byte[] ivValues = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(ivValues);//generate the required random bytes iv
	    this.iv = new IvParameterSpec(ivValues);//generate an IvParameterSpec iv    
		this.cipher = Cipher.getInstance("AES/CTR/NoPadding");
		cipher.init(Cipher.ENCRYPT_MODE,key.getSecretKey(),iv); 
		//initialize the cipher	with key and initialization vector for encryption
	}
	
	public String encodeKey() 
	{ //call SessionKey method encodeKey() to obtain the encoded secret key
		String encodedKey = key.encodeKey();
		return encodedKey;
	}
	
	public String encodeIV() 
	{	//retrieve the iv as a Base64-encoded string
		String encodedIV =Base64.getEncoder().encodeToString(iv.getIV());
		return encodedIV;
	}
	
	public CipherOutputStream openCipherOutputStream(OutputStream output) 
	{ // generate the ciphertext in the cipher file
		return new CipherOutputStream(output,cipher);
	}
	

}
