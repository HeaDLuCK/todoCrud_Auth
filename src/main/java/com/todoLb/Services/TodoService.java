package com.todoLb.todoStuff;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    private TodoRepository todoRepo;

    @Autowired
    public TodoService(TodoRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    public List<Todo> getTodo() {
        return this.todoRepo.findAll();
    }

    public void addTodo(Todo todo) {
        try {
            if (todo.getId() != null) {
                Optional<Todo> check = this.todoRepo.findById(todo.getId());
                if (check.isPresent())
                    throw new Exception();
                this.todoRepo.save(todo);
            } else {
                this.todoRepo.save(todo);
            }
        } catch (Exception e) {
            System.out.println("Already exists");
        }

    }

    public void updateTodo(String id, Todo todo) {
        Optional<Todo> object = this.todoRepo.findById(id);
        if (object.isPresent()) {
            Todo doc = object.get();
            doc.setTitle(todo.getTitle() != "" || todo.getTitle() != null ? todo.getTitle() : doc.getTitle());
            doc.setCompleted(todo.getCompleted() != null ? todo.getCompleted() : doc.getCompleted());
            doc.setDate(todo.getDate().compareTo(doc.getDate()) > 0 ? todo.getDate() : doc.getDate());
            this.todoRepo.save(doc);
        } else {
            System.out.println("Not Found");
        }

    }

    public void deleteTodo(String id) {
        Optional<Todo> check = this.todoRepo.findById(id);
        if (check.isPresent()) {
            this.todoRepo.deleteById(id);
        }else{
            System.out.println(id+" Not Found");
        }
    }

}
