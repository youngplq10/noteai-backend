package dev.starzynski.noteAI_backend.Service;

import dev.starzynski.noteAI_backend.Model.User;
import dev.starzynski.noteAI_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    public String login(User user) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            }
            else {
                return "Failed";
            }
        } catch (AuthenticationException e) {
            return "Failed";
        }
    }

    public String register(User user) {
        userRepository.insert(user);
        return jwtService.generateToken(user.getUsername());
    }

    public String createUser(User user) {
        try {
            Optional<User> existingUsername = userRepository.findByUsername(user.getUsername());
            Optional<User> existingEmail = userRepository.findByEmail(user.getEmail());

            if (existingEmail.isPresent() || existingUsername.isPresent()) {
                return "Failed";
            }
            else {
                userRepository.insert(user);
                return jwtService.generateToken(user.getUsername());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String loginUser(User user) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            if(authentication.isAuthenticated()){
                return jwtService.generateToken(user.getUsername());
            }
            else {
                return "Failed";
            }
        } catch (AuthenticationException e) {
            return "Failed";
        }
    }

    public User getUserByUsername(String username) {
        try {
            return userRepository.findByUsername(username).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
