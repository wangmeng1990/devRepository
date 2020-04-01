package com.yinuo.common.sms;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

/**
 * @Title 秘钥工具类
 */
public class DigestUtils {
	public static final String ALGORITHM_MD5 = "md5";
	public static final String ALGORITHM_SHA = "sha";
	public static final String ALGORITHM_DES = "DES";

	private static final String AES_ALG = "AES";
	/**
	 * AES算法
	 */
	private static final String AES_CBC_PCK_ALG = "AES/CBC/PKCS5Padding";

	private static final byte[] AES_IV = initIv(AES_CBC_PCK_ALG);

	/**
	 * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
	 *
	 * @param fullAlg
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static byte[] initIv(String fullAlg) {

		try {
			Cipher cipher = Cipher.getInstance(fullAlg);
			int blockSize = cipher.getBlockSize();
			byte[] iv = new byte[blockSize];
			for (int i = 0; i < blockSize; ++i) {
				iv[i] = 0;
			}
			return iv;
		} catch (Exception e) {

			int blockSize = 16;
			byte[] iv = new byte[blockSize];
			for (int i = 0; i < blockSize; ++i) {
				iv[i] = 0;
			}
			return iv;
		}
	}

	/**
	 * AES加密
	 * 
	 * @param content
	 * @param aesKey
	 * @param charset
	 * @param aesIv   可为Null
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 */
	public static String aesEncrypt(String content, String aesKey, String charset, byte[] aesIv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {

		Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);

		if (aesIv == null) {
			aesIv = AES_IV;
		}
		
		
		IvParameterSpec iv = new IvParameterSpec(aesIv);
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(aesKey.getBytes(charset), AES_ALG), iv);
		
//		content.getBytes(charset);
//		content.getBytes();
		byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
		return new String(Base64.encodeBase64(encryptBytes));

	}

	/**
	 * AES解密
	 * 
	 * @param content
	 * @param key
	 * @param charset
	 * @param aesIv   可为null
	 * @return
	 * @throws NoSuchPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidAlgorithmParameterException
	 * @throws InvalidKeyException
	 * @throws BadPaddingException
	 * @throws IllegalBlockSizeException
	 * @throws UnsupportedEncodingException
	 */
	public static String aesDecrypt(String content, String key, String charset, byte[] aesIv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);

		if (aesIv == null) {
			aesIv = AES_IV;
		}

		IvParameterSpec iv = new IvParameterSpec(aesIv);
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(charset), AES_ALG), iv);

		byte[] cleanBytes = cipher.doFinal(Base64.decodeBase64(content.getBytes()));
		return new String(cleanBytes, charset);
	}

	/**
	 * MD5加密
	 *
	 * @param source 明文
	 * @return 密文
	 * @throws IOException
	 *
	 */
	public static String md5(String source) throws IOException {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance(ALGORITHM_MD5);
		} catch (NoSuchAlgorithmException e) {
			throw new IOException(e);
		}
		digest.reset();
		digest.update(source.getBytes(Charset.forName("UTF-8")));
		byte[] bytes = digest.digest();
		return new String(Hex.encodeHex(bytes));
	}

	/**
	 * SHA加密
	 *
	 * @param source 明文
	 * @return 密文
	 * @throws NoSuchAlgorithmException
	 */
	public static String sha(String source) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(ALGORITHM_SHA);
		digest.reset();
		digest.update(source.getBytes());
		byte[] bytes = digest.digest();
		return new String(Hex.encodeHex(bytes));
	}

	/**
	 * base64编码
	 *
	 * @param source 明文
	 * @return 密文
	 */
	public static String base64Encode(String source) {
		if (source == null) {
			return null;
		}
		return Base64.encodeBase64String(source.getBytes(Charset.forName("UTF-8")));
	}

	public static String base64Encode(String source, String charset) {
		if (source == null) {
			return null;
		}
		return Base64.encodeBase64String(source.getBytes(Charset.forName(charset)));
	}

	/**
	 * base64解码
	 *
	 * @param target 密文
	 * @return 明文
	 */
	public static String base64Decode(String target) {
		if (target == null) {
			return null;
		}
		byte[] bytes = Base64.decodeBase64(target.getBytes(Charset.forName("UTF-8")));
		return new String(bytes, Charset.forName("UTF-8"));
	}

	public static String base64Decode(String target, String charset) {
		if (target == null) {
			return null;
		}
		byte[] bytes = Base64.decodeBase64(target.getBytes(Charset.forName(charset)));
		return new String(bytes, Charset.forName(charset));
	}

	/**
	 * des加密
	 *
	 * @param key    密钥
	 * @param source 明文
	 * @return 密文
	 * @throws InvalidKeyException 异常
	 * @throws InvalidKeySpecException 异常
	 * @throws NoSuchAlgorithmException 异常
	 * @throws NoSuchPaddingException 异常
	 * @throws IllegalBlockSizeException 异常
	 * @throws BadPaddingException 异常
	 */
	public static String desEncrypt(String key, String source) throws InvalidKeyException, InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Key secretKey = generateKey(key, ALGORITHM_DES);
		Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		return Base64.encodeBase64String(cipher.doFinal(source.getBytes()));
	}

	/**
	 * des解密
	 *
	 * @param key    密钥
	 * @param target 密文
	 * @return 明文
	 * @throws InvalidKeyException 异常
	 * @throws InvalidKeySpecException 异常
	 * @throws NoSuchAlgorithmException 异常
	 * @throws NoSuchPaddingException 异常
	 * @throws IllegalBlockSizeException 异常
	 * @throws BadPaddingException 异常
	 */
	public static byte[] desDecrypt(String key, String target) throws InvalidKeyException, InvalidKeySpecException,
			NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Key secretKey = generateKey(key, ALGORITHM_DES);
		Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		return cipher.doFinal(Base64.decodeBase64(target));
	}

	/**
	 * 生成key
	 *
	 * @param key       密钥字符串
	 * @param algorithm 加解密算法
	 * @return 密钥
	 * @throws InvalidKeyException 异常
	 * @throws NoSuchAlgorithmException 异常
	 * @throws InvalidKeySpecException 异常
	 */
	private static Key generateKey(String key, String algorithm)
			throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
		if (ALGORITHM_DES.equals(algorithm)) {
			DESKeySpec keySpec = new DESKeySpec(Base64.decodeBase64(key));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM_DES);

			return keyFactory.generateSecret(keySpec);
		} else {
			return new SecretKeySpec(Base64.decodeBase64(key), algorithm);
		}
	}

	/**
	 * 生成密钥
	 *
	 * @param seed 种子
	 * @return 密钥
	 * @throws Exception 异常
	 */
	public static String initKey(String seed) throws Exception {
		SecureRandom secureRandom;

		if (seed != null) {
			secureRandom = new SecureRandom(base64Decode(seed).getBytes());
		} else {
			secureRandom = new SecureRandom();
		}

		KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_DES);
		kg.init(secureRandom);

		SecretKey secretKey = kg.generateKey();

		return Base64.encodeBase64String(secretKey.getEncoded());
	}

	public static void main(String[] args) throws Exception {
		String inputStr = "111111";
		String key = initKey(null);
		System.err.println("原文:\t" + inputStr);
		System.err.println("密钥:\t" + key);
		// inputData = encrypt(inputData, key);
		inputStr = desEncrypt(key, inputStr);
		System.err.println("加密后:\t" + inputStr);
		byte[] outputData = desDecrypt(key, inputStr);
		String outputStr = new String(outputData);
		System.err.println("解密后:\t" + outputStr);
	}
}
