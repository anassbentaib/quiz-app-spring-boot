package com.project.QuizApp.service;

import com.project.QuizApp.controller.QuestionWrapper;
import com.project.QuizApp.dao.QuestionDao;
import com.project.QuizApp.dao.QuizDoe;
import com.project.QuizApp.model.Question;
import com.project.QuizApp.model.Quiz;
import com.project.QuizApp.model.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    QuizDoe quizDoe;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDoe.save(quiz);

        return new ResponseEntity<>("Succes", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Optional<Quiz> quiz = quizDoe.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
                    q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }

        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDoe.findById(id).get();
        List<Question> questions = quiz.getQuestions();
int right = 0;
int i = 0;
        for(Response response : responses) {
            if (response.getResponse().equals(questions.get(i).getRightAnswer())) 
                right++;
                i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
}
}