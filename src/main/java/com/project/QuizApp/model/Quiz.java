package com.project.QuizApp.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
     private String title;
     @ManyToMany
     private List<Question> questions;

    public void setTitle(String title) {
    }

    public void setQuestions(List<Question> questions) {
    }


}
