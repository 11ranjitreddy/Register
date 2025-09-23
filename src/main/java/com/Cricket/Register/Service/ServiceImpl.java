package com.Cricket.Register.Service;

import com.Cricket.Register.Entity.User;
import com.Cricket.Register.Repository.UserRepository;
import com.Cricket.Register.utils.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public ServiceImpl (UserRepository userRepository, PasswordEncoder passwordEncoder,JwtUtil jwtUtil){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public User registerUser(String firstname, String lastname,String email, String username, String password) {

        if(userRepository.findByEmail(email).isPresent())
            throw new RuntimeException(" Email already in user ");

        if(userRepository.findByUserName(username).isPresent())
            throw new RuntimeException(" Username already in user ");

        User user=new User();
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setEmail(email);
        user.setUserName(username);
               user .setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);


    }

    @Override
    public String login(String username, String password) {
        Optional<User> userOpt= userRepository.findByUserName(username);

        if(userOpt.isPresent() && passwordEncoder.matches(password,userOpt.get().getPassword())){
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Invalid username or passwor");


    }
}
