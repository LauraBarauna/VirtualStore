package br.com.lauraBarauna.model.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final String hashedPassword;

    private Password(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public static Password fromPlainText(String plainText) {
        if (!isValid(plainText)) {
            throw new IllegalArgumentException("Invalid password");
        }

        return new Password(encoder.encode(plainText));
    }

    public static Password fromHashed(String hashedPassword) {
        return new Password(hashedPassword);
    }

    public boolean matches(String rawPassword) {
        return encoder.matches(rawPassword, this.hashedPassword);
    }

    private static boolean isValid(String rawPassword) {
        return rawPassword != null && rawPassword.length() >= 8 && rawPassword.length() <= 16;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
