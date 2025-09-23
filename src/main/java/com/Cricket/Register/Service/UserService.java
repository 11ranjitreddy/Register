package com.Cricket.Register.Service;

import com.Cricket.Register.Entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User registerUser(String firstname, String lastname, String email, String username, String password);
    String login(String username, String password);
}

