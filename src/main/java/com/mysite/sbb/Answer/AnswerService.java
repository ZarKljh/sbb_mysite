package com.mysite.sbb.Answer;

import com.mysite.sbb.Question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private AnswerRepository answerRepository;

    public void crate(Question question, String content){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());

        answerRepository.save(answer);
    }
}
