package com.mysite.sbb.Question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



//jpa에서 만들어져있는 리포지터리를 상속받아야한다
@Repository
public interface  QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    List<Question> findBySubjectLike(String subject);
}
