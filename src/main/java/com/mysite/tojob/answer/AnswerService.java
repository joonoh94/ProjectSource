package com.mysite.tojob.answer;

import com.mysite.tojob.question.Question;
import com.mysite.tojob.user.SiteUser;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mysite.tojob.user.SiteUser;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
}