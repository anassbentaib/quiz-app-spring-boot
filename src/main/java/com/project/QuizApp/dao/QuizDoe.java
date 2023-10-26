package com.project.QuizApp.dao;

import com.project.QuizApp.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDoe extends JpaRepository<Quiz, Integer> {
}
