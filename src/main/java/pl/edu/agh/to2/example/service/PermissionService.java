package pl.edu.agh.to2.example.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.model.LoginUser;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    public List<Boolean> getPermissionsForUser(LoginUser source) {
        ArrayList<Boolean> toRet = new ArrayList<>();
        toRet.add(true);
        return toRet;
    }
}

