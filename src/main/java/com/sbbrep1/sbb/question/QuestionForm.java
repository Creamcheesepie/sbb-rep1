package com.sbbrep1.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty
    @Size(max = 200)
    private String subject;
    @NotEmpty
    private String content;
}
