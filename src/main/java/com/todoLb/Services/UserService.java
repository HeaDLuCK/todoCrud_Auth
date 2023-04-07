package com.todoLb.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepo userRepository;

    @Autowired
    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        Optional<User> user = this.userRepository.getUserByUserName(username);
        if (user.isEmpty())
            throw new IllegalAccessError("not found");
        return user.get();
    }

    public void addUser(User user) {
        if (user.getUsername() != "" && user.getPassword() != "") {
            this.userRepository.save(user);
        } else {
            System.out.println("username and password are empty");
        }

    }

    public void updateUser(String id, String password) {
        Optional<User> selectedDoc = this.userRepository.findById(id);
        if (selectedDoc.isPresent() && password != "") {
            selectedDoc.get().setPassword(password);
            this.userRepository.save(selectedDoc.get());
        } else {
            System.out.println("id not found or password is empty");
        }
    }

    public void deleteUser(String id) {
        Optional<User> selectedDoc = this.userRepository.findById(id);
        if (selectedDoc.isEmpty()) {
            System.out.println("id not found");
        } else {
            this.userRepository.deleteById(id);
        }
    }
}
