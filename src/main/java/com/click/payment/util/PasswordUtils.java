package com.click.payment.util;

public interface PasswordUtils {
    String passwordHashing(String password, String salt);

    String byteToHex(byte[] bytes);

    String generateSalt();
}
