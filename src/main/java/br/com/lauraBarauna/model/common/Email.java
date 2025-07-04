package br.com.lauraBarauna.model.common;

public class Email {

    private String emailAddress;

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public static Email from (String rawEmail) {
        if (rawEmail == null || rawEmail.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }

        if (!isValid(rawEmail)) {
            throw new IllegalArgumentException("Invalid e-mail: " + rawEmail);
        }
        return new Email(rawEmail);
    }

    public static Email fromDatabase (String storedEmail) {
        return new Email(storedEmail);
    }

    private static boolean isValid(String emailAddress) {
        String regex = "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";
        return emailAddress.matches(regex);
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
