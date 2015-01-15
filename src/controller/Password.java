/**
 * Filename: Password.java
 * Description: Program that defines password objects for users to utilize when logging in to their accounts.
 */

package controller;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * Class Name: Password
 * Description: Password object that users utilize to log in to their accounts.
 */
public class Password {
    /**
     * In charge of creating passwords.
     * @param input Not used.
     * @return the new password
     */
	public String passwordConvertor(String input) throws NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException{
		Security.addProvider(new BouncyCastleProvider());

		String data = "hello world";

		MessageDigest mda = MessageDigest.getInstance("SHA-512", "BC");
		byte [] digesta = mda.digest(data.getBytes());

		MessageDigest mdb = MessageDigest.getInstance("SHA-512", "BC");
		byte [] digestb = mdb.digest(data.getBytes());

		System.out.println(MessageDigest.isEqual(digesta, digestb));

		System.out.println(Hex.encodeHex(digesta));
		return new String(Hex.encodeHex(digesta)); // returns password
		
	}
}
