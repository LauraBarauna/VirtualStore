import Repository.UserRepository;
import model.Email;
import model.Phone;
import model.User;

public class Main {
    public static void main(String[] args) {
        Email email = new Email("laura@email.com");
        Phone phone = new Phone("47997574123");
        User user = new User("Laura", email, "lauralau12", phone);
        UserRepository userRepository = new UserRepository();
        userRepository.addUser(user);
    }
}
