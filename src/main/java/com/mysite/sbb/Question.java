package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Question {
    //Id는 프라이머리키를 의미한다
    //GeneratedValue 는 자동생성을 의미한다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //length = 200 는 varchar와 동일하다
    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    //외래키는 Answer테이블에 있고, 주체는 question이다라는 것을 알리는 것
    //Cascade 질문이 없이지면 답변도 같이 삭제하겠다는 것을 알리는 것
    @OneToMany(mappedBy = "question" ,  cascade = CascadeType.REMOVE)
    private List<Answer> answerList;
}
