package com.todoLb.todoStuff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private TodoRepository todoRepo;

    @Autowired
    public TodoService(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> getTodo(){
        return this.todoRepo.findAll();
    }
}
