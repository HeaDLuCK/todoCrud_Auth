package com.todoLb.todoStuff;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    private String id;
    @NonNull
    private String title;
    @NonNull
    private Boolean completed;
    @NonNull
    private LocalDate date;
}
