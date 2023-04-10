package com.todoLb.Controller;

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

import com.todoLb.Document.Todo;
import com.todoLb.Services.TodoService;

@RestController
@RequestMapping("/")
public class TodoController {
    @Autowired
    private TodoService todosrv;


    @GetMapping
    public List<Todo> GetTodos() {
        return todosrv.getTodo();
    }

    @PostMapping
    public void addTodo(@RequestBody Todo todo) {
        todosrv.addTodo(todo);
    }

    @PutMapping("{id}")
    public void setTodo(@PathVariable String id, @RequestBody Todo todo) {
        todosrv.updateTodo(id,todo);
    }

    @DeleteMapping("{id}")
    public void deleteTodo(@PathVariable String id) {
        todosrv.deleteTodo(id);
    }

}
