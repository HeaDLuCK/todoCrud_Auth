package com.todoLb.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.todoLb.Document.RefreshToken;

@Repository
public interface Refreshtokenrepository extends MongoRepository<RefreshToken, String> {

}
