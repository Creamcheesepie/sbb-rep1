package com.sbbrep1;

import com.sbbrep1.sbb.answer.Answer;
import com.sbbrep1.sbb.answer.AnswerRepository;
import com.sbbrep1.sbb.question.Question;
import com.sbbrep1.sbb.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class AnswerTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("답변 저장하기")
    void t1 () {
        Optional<Question> question = questionRepository.findById(1L);
        assertTrue(question.isPresent());

        Answer a = new Answer();
        a.setContent("네, 재미있습니다.");
        a.setQuestion(question.get());
        a.setCreateDate(LocalDateTime.now());

        answerRepository.save(a);
        answerRepository.findById(1L).ifPresent(answer -> {assertEquals(a.getContent(),answer.getContent());});
    }
}
