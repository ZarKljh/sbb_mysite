package com.mysite.sbb.Question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
//생성자를 자동으로 생성해주는 어노테이션이다
@Controller
public class QuestionController {
    //@RequiredArgsConstructor으로 인해 생성자가 자동으로 만들어진다
    //@RequiredArgsConstructor을 사용하기 위해서는 final을 꼭 붙여줘야한다.
    private final QuestionService questionService;

    @GetMapping("/list")
    //@ResponseBody
    public String list(Model model){
        //현재는 @ResponseBody 어노테이션이 없기 때문에
        //Spring은 queestion List라는 이름의 템플릿을 찾아서 화면에 출력해준다

        List<Question> questionList = this.questionService.getList();

        model.addAttribute("questionList", questionList);

        return "question_list";
    }
    @GetMapping(value = "/detail/{id}")
    public String getQuestion(Model model, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

//    @PostMapping("/create/{id}")
//    @ResponseBody
//    public String createQuestion(){
//        return "Answer create Complete";
//    }
    @GetMapping("/create")
    public String questionCreate() {
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@RequestParam(value="subject") String subject, @RequestParam(value="content") String content) {
        // TODO 질문을 저장한다.
        this.questionService.create(subject, content);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}


