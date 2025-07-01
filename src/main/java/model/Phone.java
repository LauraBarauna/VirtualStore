package model;

public class Phone {

    private String number;

    public Phone(String number) {
        if (!isValid(number)) {
            throw new IllegalArgumentException("Número de telefone inválido: " + number);
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
