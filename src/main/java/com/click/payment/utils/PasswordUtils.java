package com.click.payment.utils;

public interface PasswordUtils {
    String passwordHashing(String password, String salt);

    String byteToHex(byte[] bytes);

    String generateSalt();
}
