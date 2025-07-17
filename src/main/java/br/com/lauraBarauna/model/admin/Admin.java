package br.com.lauraBarauna.model.admin;

import br.com.lauraBarauna.model.user.User;

public class Admin {
    private User user;
    private String additionalRole;

    private Admin(User user, String additionalRole) {
        this.user = user;
        this.additionalRole = additionalRole;
    }

    public static Admin fromCreation(User user, String additionalRole) {
        if (user == null) {
            throw new IllegalArgumentException("User  cannot be null.");
        }

        if (additionalRole == null || additionalRole.isBlank()) {
            throw new IllegalArgumentException("Additional Role cannot be null or empty.");
        }

        return new Admin(user, additionalRole);
    }

    public static Admin fromUpdate (User user, String additionalRole) {
        return new Admin(user, additionalRole);
    }

    public static Admin fromDataBase (User user, String additionalRole) {
        return new Admin(user, additionalRole);
    }

    public User getUser() {
        return user;
    }

    public String getAdditionalRole() {
        return additionalRole;
    }
}
