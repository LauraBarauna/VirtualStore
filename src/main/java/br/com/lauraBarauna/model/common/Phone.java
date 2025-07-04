package br.com.lauraBarauna.model.common;

public class Phone {

    private String number;

    public Phone(String number) {
        this.number = number;
    }

    public static Phone from (String rawPhone) {
        if (rawPhone == null || rawPhone.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty");
        }

        if (!isValid(rawPhone)) {
            throw new IllegalArgumentException("Invalid phone number: " + rawPhone);
        }
        return new Phone(rawPhone);
    }

    public static Phone fromDataBase (String storedPhone) {
        return Phone.from(storedPhone);
    }

    private static boolean isValid(String number) {
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
