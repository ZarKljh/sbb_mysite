package com.mysite.sbb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//public class AnswrRepository {
//}

@Repository
public interface  AnswerRepository extends JpaRepository<Answer, Integer> {
}