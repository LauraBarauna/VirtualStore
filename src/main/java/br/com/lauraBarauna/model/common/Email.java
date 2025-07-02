package br.com.lauraBarauna.model.common;

public class Email {

    private String emailAddress;

    public Email(String emailAddress) {

        if (emailAddress == null || emailAddress.isBlank()) {
            throw new IllegalArgumentException("Email address cannot be null or empty");
        }

        if (!isValid(emailAddress)) {
            throw new IllegalArgumentException("Invalid e-mail: " + emailAddress);
        }

        this.emailAddress = emailAddress;
    }

    private boolean isValid(String emailAddress) {
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
