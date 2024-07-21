package com.example.project_nodev.service;

import com.example.project_nodev.entity.Answer;
import com.example.project_nodev.entity.Cat;
import com.example.project_nodev.repository.AnswerRepository;
import com.example.project_nodev.repository.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private CatRepository catRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public Answer findAnswerForQuestion(Integer catId) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        return answerRepository.findById(catId)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found"));
    }

    public Answer createAnswer(Integer catId, Answer answerData) {
        Cat cat = catRepository.findById(catId)
                .orElseThrow(() -> new IllegalArgumentException("Question not found"));
        answerData.setCat(cat);
        return answerRepository.save(answerData);
    }

    public Answer updateAnswer(Integer answerId, Answer updatedData) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found"));
        answer.setContent(updatedData.getContent());
        return answerRepository.save(answer);
    }

    public void deleteAnswer(Integer answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new IllegalArgumentException("Answer not found"));
        answerRepository.delete(answer);
    }

    public void create(Answer answer, String content) {
        //Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        //answer.setCat(answer);
        answerRepository.save(answer);
    }

    public List<Answer> answerList( ){
        return answerRepository.findAll();
    }


}
