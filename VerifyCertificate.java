import java.security.*;
import java.security.cert.*;
//import java.io.FileInputStream;

public class VerifyCertificate {

	private X509Certificate caCert;
	private X509Certificate userCert;
	//private CertificateFactory cf = CertificateFactory.getInstance("X.509");
	
	public VerifyCertificate (X509Certificate caCert,X509Certificate userCert) throws CertificateException {
		this.caCert = caCert;
		this.userCert = userCert;
	}
	
	public String printDN () {
		return "DN for the CA: " + this.caCert.getSubjectDN().toString() + "\nDN for the user: " + this.userCert.getSubjectDN().toString();
	}
	
	public String verifyCert() {
		if (!this.userCert.getIssuerDN().equals(this.caCert.getIssuerDN())) {
			return "Fail";
		}
		try {
		this.caCert.checkValidity();
		this.caCert.verify(this.caCert.getPublicKey());
		this.userCert.checkValidity();
		this.userCert.verify(this.userCert.getPublicKey());
		return "Pass";
		} catch(GeneralSecurityException e) {
			return "Fail";
		}
	}
	
	public PublicKey extractPublicKey(X509Certificate certificate) throws Exception{
	    return certificate.getPublicKey();
	  }
}
