package pl.edu.agh.to2.example.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegisterService {

    public RegisterService() {
    }

    private final Pattern firstNamepattern = Pattern.compile("^[A-Z][a-z]+$");
    private final Pattern lastNamePattern = Pattern.compile("^[A-Z][a-z]+$");
    private final Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean registerFirstName(String firstName){
        return !firstName.isEmpty() && firstNamepattern.matcher(firstName).matches();
    }

    public boolean registerLastName(String lastName){
        return !lastName.isEmpty() && lastNamePattern.matcher(lastName).matches();
    }

    public boolean registerEmail(String email){
        return !email.isEmpty() && emailPattern.matcher(email).matches();
    }

    public boolean registerPassword(String password){
        return !password.isEmpty();
    }

    public String register(String firstName, String lastName, String email, String password) {
        if (firstName.isEmpty() || !firstNamepattern.matcher(firstName).matches()){
            return "Firstname Error";
        }
        if (lastName.isEmpty() || !lastNamePattern.matcher(lastName).matches()){
            return "Lastname Error";
        }
        if (email.isEmpty() || !emailPattern.matcher(email).matches()){
            return "Email Error";
        }
        if (password.isEmpty()){
            return "Password Error";
        }
        return "New User Was Created Succesfull";
    }
}
