package com.mysite.sbb.Question;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



//jpa에서 만들어져있는 리포지터리를 상속받아야한다
@Repository
public interface  QuestionRepository extends JpaRepository<Question, Integer> {
    Question findBySubject(String subject);
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Pageable pageable);
    //검색조건을 받을 수 있는 findAll
    //오버로드 하기 때문에 메소드명이 같아도 상관없다
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);
}
