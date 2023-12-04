package pl.edu.agh.to2.example.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@ComponentScan(basePackages = "pl.edu.agh.to2.example.dao")
public class RegisterService {

    //@Autowired
    //public UserDAO userDAO;

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
        return true;
        //return !email.isEmpty() && emailPattern.matcher(email).matches() && checkIfEmailExists(email);
    }

    public boolean registerPassword(String password){
        return !password.isEmpty();
    }

   //public void addUser(User user){
   //    userDAO.save(user);
   //}
//
   //private boolean checkIfEmailExists(String email){
   //    return Boolean.TRUE.equals(userDAO.findByEmail(email).hasElement().block());
   //}

}
