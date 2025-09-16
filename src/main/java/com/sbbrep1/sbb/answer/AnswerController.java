package com.sbbrep1.sbb.answer;

import com.sbbrep1.sbb.DataNotFoundException;
import com.sbbrep1.sbb.question.Question;
import com.sbbrep1.sbb.question.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/answer")
@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(
            Model model,
            @PathVariable("id") Long id,
            @Valid AnswerForm answerForm,
            BindingResult bindingResult
    ) throws DataNotFoundException {
        Question question = questionService.getQuestionByid(id);
        if(bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }
        answerService.createAnswer(question,answerForm.getContent());
        return String.format("redirect:/question/detail/%s".formatted(id));
    }

}
