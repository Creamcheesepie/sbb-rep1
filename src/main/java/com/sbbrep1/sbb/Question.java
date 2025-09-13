package com.sbbrep1.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id // 기본 키를 지정함.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키의 값 생성 전략을 지정
    private Long id;

    @Column(length = 200) // 이 칼럼의 길이를 지정
    private String subject;

    @Column(columnDefinition = "TEXT") // 이 칼럼의
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question" , cascade = CascadeType.REMOVE )
    private List<Answer> answers;

    public Question(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }
}
