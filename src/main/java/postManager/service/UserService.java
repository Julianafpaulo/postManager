package postManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import postManager.domain.User;
import postManager.domain.exception.UserNotFoundException;
import postManager.repository.UserRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User addUser(User user) {
            return repository.save(user);

    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User getUser(Long id) {
        return repository.findById(id).
                orElseThrow(() -> new UserNotFoundException(" User not found with id " + id));

    }

    public User updateUser(Long id, User user) {
        return repository.findById(id)
                .map(p -> {
                    p.setName(user.getName());
                    p.setEmail(user.getEmail());
                    return repository.save(p);
                }).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    public ResponseEntity<?> deleteUser(Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

    }

}