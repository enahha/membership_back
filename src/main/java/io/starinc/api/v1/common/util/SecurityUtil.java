/**
 * 
 */
package io.starinc.api.v1.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ahn
 *
 */
public class SecurityUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
	
	public static String getSHA512(String input){
		String toReturn = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-512");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%040x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			// e.printStackTrace();
			logger.debug(e.toString());
		}
	return toReturn;
	}
	/*
	public static String encryptSHA256(String str) {
		String sha = "";
		try {
			MessageDigest sh = MessageDigest.getInstance("SHA-256");
			sh.update(str.getBytes());
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			sha = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.debug("NoSuchAlgorithmException");
		}
		return sha;
	}
	*/
}
