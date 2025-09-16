package com.sbbrep1.sbb.question;

import com.sbbrep1.sbb.DataNotFoundException;
import com.sbbrep1.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/question")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model){
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            Model model,
            @PathVariable Long id,
            AnswerForm answerForm
    ) throws DataNotFoundException {
        Question question = this.questionService.getQuestionByid(id);
        model.addAttribute("question",question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionForm(
    ){
        return "question_form";
    }

    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm, BindingResult bindingResult

    ){
        if(bindingResult.hasErrors()){
            return "question_form";
        }
        questionService.createQuestion(questionForm.getSubject(),questionForm.getContent());
        return "redirect:/question/list";
    }

}
