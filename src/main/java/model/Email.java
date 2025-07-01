package model;

public class Email {

    private String emailAddress;

    public Email(String emailAddress) {

        if (!isValid(emailAddress)) {
            throw new IllegalArgumentException("Email inválido: " + emailAddress);
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

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Email{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
