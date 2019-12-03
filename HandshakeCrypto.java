import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.*;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;

public class HandshakeCrypto {

	//encrypt the plaintext by the public key (private key) 
	public static byte[] encrypt(byte[] plaintext, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(cipher.ENCRYPT_MODE, key);
		return cipher.doFinal(plaintext);
	}
	
	//decrypt the ciphertext by the private key (public key)
	public static byte[] decrypt(byte[] ciphertext, Key key) throws Exception {
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(cipher.DECRYPT_MODE, key);
		return cipher.doFinal(ciphertext);
	}
	
	//extract the public key from the certificate file
	public static PublicKey getPublicKeyFromCertFile(String certfile) throws Exception {
		FileInputStream file = new FileInputStream(certfile);
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		X509Certificate cert = (X509Certificate) cf.generateCertificate(file);
		return cert.getPublicKey();	
	}
	
	//extract the private key from key pair file
	public static PrivateKey getPrivateKeyFromKeyFile(String keyfile) throws Exception {
		byte [] keyfileByte = Files.readAllBytes(Paths.get(keyfile));
		KeyFactory kf = KeyFactory.getInstance("RSA");
		PKCS8EncodedKeySpec ks= new PKCS8EncodedKeySpec(keyfileByte);
		return kf.generatePrivate(ks);
	}
}
