package com.sbbrep1;

import com.sbbrep1.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDataPush {
    @Autowired
    private QuestionService questionService;

    @Test
    void testPush() {
        for(int i = 0 ; i < 300; i++){
            String subject = "subject"+i;
            String content = "content"+i;
            questionService.createQuestion(subject,content);
        }
    }

}
