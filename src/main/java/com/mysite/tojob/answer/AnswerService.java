package com.mysite.tojob.answer;

import com.mysite.tojob.DataNotFoundException;
import com.mysite.tojob.question.Question;
import com.mysite.tojob.user.SiteUser;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.mysite.tojob.user.SiteUser;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    /**
     * 답변 등록 기능
     * @param
     * @return form
     * @exception -
     */
    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }

    /**
     * 답변 조회 기능
     * @param
     * @return form
     * @exception -
     */
    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    /**
     * 답변 수정 기능
     * @param
     * @return form
     * @exception -
     */
    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }

    /**
     * 답변 삭제 기능
     * @param
     * @return form
     * @exception -
     */
    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    /**
     * 답변 추천 기능
     * @param
     * @return form
     * @exception -
     */
    public void vote(Answer answer, SiteUser siteUser) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }
}