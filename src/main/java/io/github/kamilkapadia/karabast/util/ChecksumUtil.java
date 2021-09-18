package io.github.kamilkapadia.karabast.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import java.util.zip.CRC32;

import javax.xml.bind.DatatypeConverter;

public class ChecksumUtil {
	
	// long
	public static long getCRC32(byte[] data) {
		CRC32 crc = new CRC32();
		crc.update(data);
		return crc.getValue();
	}
	
	// long
	public static long getAdler32(byte[] data) {
		Adler32 adler = new Adler32();
		adler.update(data);
		return adler.getValue();
	}

	/**
	 * 
	 * @param data
	 * @return a 32 character string
	 */
	public static String getMD5(byte[] data) {
		String checksum = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(data);
		    byte[] digest = md.digest();
		    checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return checksum;
	}
	
	/**
	 * 
	 * @param data
	 * @return a 128 character string
	 */
	public static String getSHA512(byte[] data) {
		String checksum = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(data);
		    byte[] digest = md.digest();
		    checksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return checksum;
	}
}
