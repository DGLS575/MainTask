package com.main.Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserUtility {
	
	/*
	 * Compare password with stored password
	 * @param password the password
	 * @param storedPassword the stored (hashed) password
	 */
	public static boolean verifyPassword(String password, String storedPassword) {
		return storedPassword.equals(hashPassword(password));
	}

	/*
	 * Compares two passwords
	 * 
	 * @param password first password
	 * @param confirmPassword second password
	 * @return
	 */
	public static boolean doPasswordsMatch(String password, String confirmPassword) {
		return password != null && password.equals(confirmPassword);
	}

	/*
	 * Hashes the password
	 * 
	 * @param password the password
	 * @return the hashed password
	 */
	public static String hashPassword(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(password.getBytes());
			return bytesToHex(hash);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Password hashing error" + e);
		}
	}

	/*
	 * Converts a byte array int hex
	 * @param bytes the byte array
	 * @return the hex string
	 */
	private static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();

		for (byte b : bytes)
			sb.append(String.format("%02x", b));

		return sb.toString();
	}
}
