package com.Cricket.Register.Controller;

import com.Cricket.Register.Entity.User;
import com.Cricket.Register.Repository.UserRepository;
import com.Cricket.Register.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;

    }

    @PostMapping("/register")
    public ResponseEntity<User> register(
            @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String username,
            @RequestParam String password
    ){
        return ResponseEntity.ok(userService.registerUser(firstname, lastname, email, username, password));
    }


    @PostMapping("/login")

    public ResponseEntity<String> login(@RequestParam String username,
                                        @RequestParam String password){
        return ResponseEntity.ok(userService.login(username,password));
    }

    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Spring Boot Backend is running!");
    }


}
