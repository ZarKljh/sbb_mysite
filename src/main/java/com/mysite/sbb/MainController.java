package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {


    @GetMapping("/sbb")
    @ResponseBody
    public String index()
    {
        return String.format("안녕하세요 sbb에 오신것을 환영합니다.");
    }

    // ("/") redirect에서 지정하는 요청으로 자동으로 넘어간다는 의미
    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }

}
