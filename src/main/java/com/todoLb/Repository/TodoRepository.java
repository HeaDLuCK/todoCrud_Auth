package com.todoLb.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.todoLb.Document.Todo;
@Repository
public interface TodoRepository extends MongoRepository<Todo, String> {

}
