package com.mysite.tojob.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@Controller
public class MainController {

    @GetMapping("/sjo")
    @ResponseBody
    public String index(){
        return "테스트 페이지 입니다. Live Reload 기능 확인";
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/question/list";
    }

}
