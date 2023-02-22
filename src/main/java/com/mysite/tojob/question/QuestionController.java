package com.mysite.tojob.question;


import com.mysite.tojob.answer.AnswerForm;
import com.mysite.tojob.user.SiteUser;
import com.mysite.tojob.user.UserService;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * QnA 관련 컨트롤러 클래스
 *
 * @author joonoh
 * @since 2023.02.22
 * @version 0.1
 *
 * == 개정이력(Modification Information) ==
 *
 * 수정일           수정자         수정내용
 * -----------     ---------     -------------------------
 * 2023.02.22      신준오          최초 생성
 *
 * Copyright (C) by SN All right reserved.
 */

@RequestMapping("/question")
@RequiredArgsConstructor    //롬복이 제공하는 애너테이션으로 final이 붙은 속성을 포함하는 생성자를 자동으로 생성
@Controller
public class QuestionController {

    private final QuestionService questionService;

    private final UserService userService;

    //QnA 목록 페이지
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    //QnA 상세 페이지
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    //QnA 등록 페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    //QnA 등록 페이지(Post방식)
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "question_form";
        }
        SiteUser siteUser = this.userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(),siteUser); // TODO 질문을 저장한다.
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}