package br.com.lauraBarauna.model.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    private final String hashedPassword;

    public Password(String cleanPassword) {
        if (!isValid(cleanPassword)) {
            throw new IllegalArgumentException("Invalid password");
        }
        this.hashedPassword = encoder.encode(cleanPassword);
    }

    public boolean matches(String rawPassword) {
        return encoder.matches(rawPassword, this.hashedPassword);
    }

    public boolean isValid(String rawPassword) {
        return rawPassword != null && rawPassword.length() >= 8 && rawPassword.length() <= 16;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }
}
