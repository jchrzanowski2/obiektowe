package pl.edu.agh.to2.example.service;

import org.springframework.stereotype.Service;
import pl.edu.agh.to2.example.model.LoginUser;

@Service
public class PermissionService {

    public boolean canViewBooks(LoginUser user) {
        // Users with role 1 or 2 can view books
        return user.getRoleid() == 0 || user.getRoleid() == 1 || user.getRoleid() == 2;
    }

    public boolean canAddBooks(LoginUser user) {
        // Only users with role 1 can add books
        return user.getRoleid() == 1 || user.getRoleid() == 0;
    }

    public boolean canEditBooks(LoginUser user) {
        // Only users with role 1 can edit books
        return user.getRoleid() == 1 || user.getRoleid() == 0;
    }

    public boolean canDeleteBooks(LoginUser user) {
        // Only users with role 1 can delete books
        return user.getRoleid() == 1 || user.getRoleid() == 0;
    }

    public boolean canBorrowBooks(LoginUser user) {
        // Only users with role 2 can borrow books
        return user.getRoleid() == 2;
    }

    public boolean canAccessUserPanel(LoginUser user) {
        // Everyone can access user panel
        return true;
    }
}

