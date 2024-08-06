package com.click.payment.Util;

public interface PasswordUtils {
    String passwordHashing(String password, String salt);

    String byteToHex(byte[] bytes);

    String generateSalt();
}
