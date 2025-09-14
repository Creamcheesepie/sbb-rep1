package com.sbbrep1.sbb.question;

import com.sbbrep1.sbb.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String list(Model model){
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    @GetMapping("/qeuestion/detail/{id}")
    public String detail(
            Model model,
            @PathVariable Long id
    ) throws DataNotFoundException {
        Question question = this.questionService.getQuestionByid(id);
        model.addAttribute("question",question);
        return "question_detail";
    }

}
