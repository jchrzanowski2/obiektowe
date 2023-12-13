package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.UserDAO;
import pl.edu.agh.to2.example.model.LoginUser;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

@Service
public class RegisterService {

    @Autowired
    public UserDAO userDAO;

    private final Pattern firstNamePattern = Pattern.compile("^[A-Z][a-z]+$");
    private final Pattern lastNamePattern = Pattern.compile("^[A-Z][a-z]+$");
    private final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean validateFirstName(String firstName){
        return !firstName.isEmpty() && firstNamePattern.matcher(firstName).matches();
    }

    public boolean validateLastName(String lastName){
        return !lastName.isEmpty() && lastNamePattern.matcher(lastName).matches();
    }

    public boolean validateEmail(String email){
        return !email.isEmpty() && emailPattern.matcher(email).matches() && !emailExists(email);
    }

    public boolean validatePassword(String password){
        return !password.isEmpty();
    }

    public Mono<LoginUser> addUser(LoginUser user){
        return this.userDAO.save(user);
    }

    private boolean emailExists(String email){
        return userDAO.findByEmail(email)
                .hasElement()
                .blockOptional()
                .orElse(false);
    }
}
