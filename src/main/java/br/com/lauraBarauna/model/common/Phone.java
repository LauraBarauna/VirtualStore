package br.com.lauraBarauna.model.common;

public class Phone {

    private String number;

    public Phone(String number) {

        if (number == null || number.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        if (!isValid(number)) {
            throw new IllegalArgumentException("Invalid phone number: " + number);
        }

        this.number = number;
    }

    private boolean isValid(String number) {
        return number != null && number.matches("^\\d{11}$");
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number='" + number + '\'' +
                '}';
    }
}
