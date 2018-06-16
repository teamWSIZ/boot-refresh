package com.example.demo.service;

import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

//JPA
public interface QuestionRepo extends CrudRepository<Question,Integer> {

    List<Question> findByActiveTrue();

    List<Question> findByTextStartingWith(String textPrefix);

    List<Question> findByTypeid(Integer typeid);

    List<Question> findByTypeidAndActiveTrue(Integer typeid);

    List<Question> findByAnswer1EndingWith(String koniecA1);

    //google: JPA find all order by
    List<Question> findAllByOrderByQidAsc();


    //W ten sposób piszemy zapytania typu join, czyli wymagające informacji z
    //więcej niż jednej tabeli
    @Query("select q from Question q, QType qt " +
            "where q.typeid = qt.qtid and qt.description LIKE ?1")
    List<Question> findAllInterestingQuestionsWithTypeSimilarTo(String typeDescriptionFragment);





    @Transactional
    @Modifying
    //wykonuje: Hibernate: delete from egzamin.question where qid=?
    void deleteByTypeid(Integer typeid);
}
