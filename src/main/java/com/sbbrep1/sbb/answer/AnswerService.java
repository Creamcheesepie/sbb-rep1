package com.sbbrep1.sbb.answer;

import com.sbbrep1.sbb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer createAnswer(Question question, String content){
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        return answerRepository.save(answer);
    }
}
