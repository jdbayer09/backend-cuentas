package com.jdbayer.cuentas.api.services.util.impl;

import com.jdbayer.cuentas.api.exceptions.util.NotEncryptException;
import com.jdbayer.cuentas.api.services.util.IEncryptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class EncryptServiceImpl implements IEncryptService {

    @Value("${cuentas.security.encrypt.key}")
    private String encryptTextPass;
    private static final String ENCRYPTION_ALG = "AES";
    private static final String HEX_CHARS = "0123456789ABCDEF";

    @Override
    public String encrypt(String plainText) {
        try {
            SecretKey key = generateKey();
            Cipher encryptor = Cipher.getInstance(ENCRYPTION_ALG);
            encryptor.init(Cipher.ENCRYPT_MODE, key);
            byte[] textEncrypt = encryptor.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(textEncrypt); // Convertir a hexadecimal en lugar de Base64
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new NotEncryptException("Problems encrypting text", ex);
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        try {
            SecretKey key = generateKey();
            Cipher decryptor = Cipher.getInstance(ENCRYPTION_ALG);
            decryptor.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = hexStringToByteArray(encryptedText);
            byte[] decryptedBytes = decryptor.doFinal(encryptedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            throw new NotEncryptException("Problems decrypting text", ex);
        }
    }


    private SecretKey generateKey() {
        int maxLength = 24;
        StringBuilder keyText = new StringBuilder(encryptTextPass);
        if (keyText.length() < maxLength) {
            while (keyText.length() < maxLength) {
                keyText.append("M");
            }
        } else {
            keyText = new StringBuilder(encryptTextPass.length() <= maxLength ? encryptTextPass : encryptTextPass.substring(0, maxLength));
        }
        byte[] key = keyText.toString().getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(key, ENCRYPTION_ALG);
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder(2 * bytes.length);
        for (byte b : bytes) {
            int highBits = (b & 0xF0) >> 4;
            int lowBits = b & 0x0F;
            hexStringBuilder.append(HEX_CHARS.charAt(highBits)).append(HEX_CHARS.charAt(lowBits));
        }
        return hexStringBuilder.toString();
    }

    private static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
