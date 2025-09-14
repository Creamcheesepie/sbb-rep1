package com.sbbrep1;

import com.sbbrep1.sbb.Question;
import com.sbbrep1.sbb.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class QuestionTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void contextLoads() {
    }

    @Test
    @DisplayName("질문 데이터 저장하기")
    void t1() {

        Question q1 = new Question("질문을 합니다","프로그래밍은 즐거운가요?");
        Question q2 = new Question("OCP 원칙에 대해","OCP 원칙이 어렵습니다. 예시를 잘 알려주세요");
        Question q3 = new Question("수정용 데이터 입니다.","내용을 수정해 주세요.");

        questionRepository.save(q1);
        questionRepository.save(q2);
        questionRepository.save(q3);
        List<Question> all = this.questionRepository.findAll();
        assertEquals(3,all.size());
    }

    @Test
    @DisplayName("질문 데이터 조회하기")
    void t2(){
        List<Question> all = this.questionRepository.findAll();
        assertEquals(3,all.size());

        Question q1 = all.get(0);
        assertEquals("질문을 합니다", q1.getSubject());
    }

    @Test
    @DisplayName("단일 항목 질문 조회")
    void t3(){
        Optional<Question> oq = this.questionRepository.findById(0L);
        if(oq.isPresent()){
            Question q = oq.get();
            assertEquals("질문을 합니다.", q.getSubject());
        }
    }

    @Test
    @DisplayName("질문 엔티티의 제목으로 데이터 조회")
    void t4(){
        Question question = this.questionRepository.findBySubject("질문을 합니다");

        assertEquals("질문을 합니다",question.getSubject());

    }

    @Test
    @DisplayName("제목과 내용을 동시에 조회하기")
    void t5(){
        String subject = "질문을 합니다";
        String content = "프로그래밍은 즐거운가요?";
        Question q = this.questionRepository.findBySubjectAndContent(subject,content);
        assertEquals(subject,q.getSubject());
        assertEquals(content,q.getContent());
    }

    @Test
    @DisplayName("부분 문자열 찾기 LIKE 사용")
    void t6(){
        String target = "OCP";
        List<Question> qList = this.questionRepository.findBySubjectLike(target);

        for(Question q : qList){
            assertTrue(q.getContent().contains(target));
        }
    }

    @Test
    @DisplayName("질문 데이터 수정하기")
    void t7() {
        Optional<Question> oq = this.questionRepository.findById(2L);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정한 제목입니다.");
        this.questionRepository.save(q);
    }

    @Test
    @DisplayName("질문 데이터 삭제하기")
    void t8(){
        long beforeSize = this.questionRepository.count();
        this.questionRepository.deleteById(3L);
        long afterSize = this.questionRepository.count();
        assertEquals(beforeSize - 1, afterSize);
    }

}
