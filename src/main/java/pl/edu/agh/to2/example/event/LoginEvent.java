package pl.edu.agh.to2.example.event;

import org.springframework.context.ApplicationEvent;
import pl.edu.agh.to2.example.model.LoginUser;

public class LoginEvent extends ApplicationEvent {
    public LoginEvent(LoginUser user) {
        super(user);
    }
}
