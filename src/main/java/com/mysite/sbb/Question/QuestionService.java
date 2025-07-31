package com.mysite.sbb.Question;


import com.mysite.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList(){
        return this.questionRepository.findAll();
    }
    public Question getQuestion( int id){
        Optional<Question> oq = this.questionRepository.findById(id);
        if(oq.isEmpty()){
            throw new DataNotFoundException("question not found");
        } else {
            return oq.get();
        }
    }
}
