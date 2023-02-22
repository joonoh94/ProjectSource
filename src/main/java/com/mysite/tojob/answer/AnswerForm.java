package com.mysite.tojob.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

/**
 * QnA 답변 관련 폼
 *
 * @author joonoh
 * @since 2023.02.22
 * @version 0.1
 *
 * == 개정이력(Modification Information) ==
 *
 * 수정일           수정자         수정내용
 * -----------     ---------     -------------------------
 * 2023.02.22      신준오          최초 생성
 *
 * Copyright (C) by SN All right reserved.
 */

@Getter
@Setter
public class AnswerForm {
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}