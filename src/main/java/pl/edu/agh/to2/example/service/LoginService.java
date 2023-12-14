package pl.edu.agh.to2.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.dao.UserDAO;
import pl.edu.agh.to2.example.model.LoginUser;

import java.util.regex.Pattern;

@Service
public class LoginService {

    @Autowired
    public UserDAO userDAO;
    private final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public boolean validateEmail(String email){
        return !email.isEmpty() && emailPattern.matcher(email).matches();
    }

    public boolean validatePassword(String password){
        return !password.isEmpty();
    }

    public LoginUser userExists(String email, String password){
        return userDAO.findAllByEmailAndPassword(email, password).blockOptional().orElse(null);
    }
}
