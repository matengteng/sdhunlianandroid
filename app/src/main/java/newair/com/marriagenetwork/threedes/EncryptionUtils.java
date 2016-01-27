package newair.com.marriagenetwork.threedes;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * 3DES加密解密类
 */
public class EncryptionUtils {
	private static String key = "sdhunlian";
	
	static {
		char[] chs = new char[24];
		for (int i = 0; i < chs.length; i++) {
			chs[i] = '0';
		}
		for (int i = 0; i < key.length(); i++) {
			chs[i] = key.charAt(i);
		}
		key = new String(chs);
	}
	
	/**
	 * 获取3DES加密字符串
	 * @param input
	 * @return
	 */
	public static String threeDESEncode(String input) {
		byte[] crypted = null;
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DESede");
		try {
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skey);
			crypted = cipher.doFinal(input.getBytes());
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return new String(Base64.encode(crypted, Base64.DEFAULT));
	}

	/**
	 * 获取3DES解密字符串
	 * @param input
	 * @return
	 */
	public static String threeDESDecode(String input) {
		if(input == null)
			return null;
		byte[] output = null;
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DESede");
		try {
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skey);
			output = cipher.doFinal(Base64.decode(input, Base64.DEFAULT));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}

		return new String(output);
	}
}
