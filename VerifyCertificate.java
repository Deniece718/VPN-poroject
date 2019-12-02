import java.io.FileInputStream;
import java.security.cert.*;


public class VerifyCertificate {

   public static void main (String[] args) throws Exception {
	   
	    FileInputStream caFile = new FileInputStream(args[0]);
	    FileInputStream userFile = new FileInputStream(args[1]);
	    CertificateFactory cf = CertificateFactory.getInstance("X.509");
	    X509Certificate caCert =(X509Certificate) cf.generateCertificate(caFile);
	    X509Certificate userCert =(X509Certificate) cf.generateCertificate(userFile);
	    boolean checkCaValid = false;
	    boolean checkCaCert = false;
	    boolean checkUserValid = false;
	    boolean checkUserCert = false;
	    
	    System.out.println(caCert.getSubjectDN().toString());
	    System.out.println(userCert.getSubjectDN().toString());
	    
	    try {
	    	caCert.checkValidity();
	    	checkCaValid = true;
		}catch(Exception e) {
			System.out.println("Fail. The CA is not valid now.");}
		
		try {
			caCert.verify(caCert.getPublicKey());
			checkCaCert = true;
		}catch(Exception e) {
			System.out.println("Fail. The CA is not signed correctly.");}
		
		try {
			userCert.checkValidity();
			checkUserValid = true;
		}catch(Exception e) {
			System.out.println("Fail. The user is not valid now.");}
		
		try {
			userCert.verify(caCert.getPublicKey());
			checkUserCert = true;
		}catch(Exception e) {System.out.println("Fail. The user is not signed correctly.");}
	    
	    
		if (checkCaValid&checkCaCert&checkUserValid&checkUserCert) {
			System.out.println("Pass");
		}
		
   }
}
