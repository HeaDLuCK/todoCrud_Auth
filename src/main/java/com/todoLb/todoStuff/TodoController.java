package com.todoLb.todoStuff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TodoController {

    private TodoService todosrv;

    @Autowired
    public TodoController(TodoService todosrv) {
        this.todosrv = todosrv;
    }

    @GetMapping
    public List<Todo> GetTodo() {
        return todosrv.getTodo();
    }

}
