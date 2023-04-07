package com.todoLb.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    private UserService userservice;

    @Autowired
    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userservice.getUsers();
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        this.userservice.addUser(user);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable String id, @RequestBody String password) {
        this.userservice.updateUser(id,password);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        this.userservice.deleteUser(id);
    }
}
