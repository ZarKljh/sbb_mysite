package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    // ("/") redirect에서 지정하는 요청으로 자동으로 넘어간다는 의미
    @GetMapping("/")
    @ResponseBody
    public String index()
    {
        return "redirect:/question/list";
    }

}
