package com.click.payment.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class PasswordUtilsImpl implements PasswordUtils {
    @Override
    public String passwordHashing(String password, String salt) {
        byte[] digest = null;
        try {
            digest = sha256(password, salt.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        return byteToHex(digest);
    }

    public byte[] sha256(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        digest.update(salt);

        byte[] result = digest.digest(password.getBytes(StandardCharsets.UTF_8));

        result = digest.digest(result);

        return result;
    }

    @Override
    public String byteToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    @Override
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[8];
        random.nextBytes(bytes);
        return byteToHex(bytes);
    }

    public boolean verifyPassword(String inputPassword, String businessPassword, String salt) {
        String hashedInputPassword = passwordHashing(inputPassword, salt);

        return hashedInputPassword.equals(businessPassword);
    }
}
