package com.mysite.sbb.Answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.Question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setCreateDate(LocalDateTime.now());
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
    public Answer getAnswer(Integer id){
        Optional<Answer> oa = this.answerRepository.findById(id);

        if(oa.isPresent()){
            return oa.get();
        } else {
            throw new DataNotFoundException("answer is not founded");
        }
    }
    public void modify(Answer answer, String content){
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
