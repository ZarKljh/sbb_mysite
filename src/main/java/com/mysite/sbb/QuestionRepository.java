package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//public class QuestionRepository {
//
//}

//jpa에서 만들어져있는 리포지터리를 상속받아야한다
@Repository
public interface  QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    List<Question> findBySubjectLike(String subject);
}
