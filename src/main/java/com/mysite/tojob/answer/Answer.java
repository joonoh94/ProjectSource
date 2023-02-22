package com.mysite.tojob.answer;

import com.mysite.tojob.question.Question;
import com.mysite.tojob.user.SiteUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//답변엔티티에서 질문엔티티를 참고하기 위해(N:1 답변은 여러개/질문은 한개)
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.CreatedDate;

//@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @ManyToOne
    private Question question;

    @ManyToOne
    private SiteUser author;
}
