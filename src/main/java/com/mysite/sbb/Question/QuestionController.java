package com.mysite.sbb.Question;

import com.mysite.sbb.Answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping("/question")
@RequiredArgsConstructor
//생성자를 자동으로 생성해주는 어노테이션이다
@Controller
public class QuestionController {
    //@RequiredArgsConstructor으로 인해 생성자가 자동으로 만들어진다
    //@RequiredArgsConstructor을 사용하기 위해서는 final을 꼭 붙여줘야한다.
    private final QuestionService questionService;
    private final UserService userService;

    @GetMapping("/list")
    //@ResponseBody
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page){
        //현재는 @ResponseBody 어노테이션이 없기 때문에
        //Spring은 queestion List라는 이름의 템플릿을 찾아서 화면에 출력해준다

        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }
    @GetMapping(value = "/detail/{id}")
    public String getQuestion(Model model, @PathVariable("id") Integer id, AnswerForm answerForm){
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
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {

        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser =  this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }

}


