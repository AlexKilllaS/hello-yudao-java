package com.xiaopeng.ngc.hello.yudao.validation.custom.embedding;

/**
 * @author yyHuangfu
 * @create 2024/2/7
 * @description
 */

@DoValidate(payload = DoValidate.ValidatType.NotNull.class, message = "object check failed：{value}")
public class ObjectTobeValidated {

    @DoValidate(payload = DoValidate.ValidatType.NotNull.class, message = "field check failed：{value}")
    private String keyShouldNotBeNull = null; // 该字段不应为空, validation failed


}
