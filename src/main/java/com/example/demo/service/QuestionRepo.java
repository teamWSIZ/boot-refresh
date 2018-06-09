package com.example.demo.service;

import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepo extends CrudRepository<Question,Integer> {
    List<Question> findByActiveTrue();
    List<Question> findByTextStartingWith(String textPrefix);
    List<Question> findByTypeid(Integer typeid);

    List<Question> findByTypeidAndActiveTrue(Integer typeid);

    @Transactional
    @Modifying
    //wykonuje: Hibernate: delete from egzamin.question where qid=?
    void deleteByTypeid(Integer typeid);
}
