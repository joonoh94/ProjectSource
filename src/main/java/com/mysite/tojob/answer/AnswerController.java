package com.mysite.tojob.answer;

import com.mysite.tojob.question.Question;
import com.mysite.tojob.question.QuestionService;
import com.mysite.tojob.user.SiteUser;
import com.mysite.tojob.user.UserService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * QnA 답변 관련 컨트롤러
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

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Integer id,
            @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
        Question question = this.questionService.getQuestion(id);
        //
        SiteUser siteUser = this.userService.getUser(principal.getName());
        //검증에 실패했을 경우 다시 답변을 등록할 수 있는 question_detail 템플릿을 렌더링
        //question_detail 템플릿은 Question객체가 필요하므로 model객체에 Question객체를 저장한 후 렌더링
        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        this.answerService.create(question, answerForm.getContent(), siteUser);
        return String.format("redirect:/question/detail/%s", id);
    }
}