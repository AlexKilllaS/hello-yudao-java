package com.xiaopeng.ngc.hello.yudao.validation.custom.embedding;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;

import java.util.Set;

/**
 * @author yyHuangfu
 * @create 2024/2/6
 * @description
 */
public class CustomValidator implements ConstraintValidator<DoValidate, Object> {

    @Override
    public void initialize(DoValidate annotation) {
        // 从annotation中获取信息，并完成初始化。
        System.out.println("init validator:" + this);
    }

    /**
     * also see {@link DoValidate.ValidatType}
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Class<? extends Payload> payload = ((ConstraintValidatorContextImpl) context).getConstraintDescriptor().getPayload().iterator().next();

        // 校验
        if (payload.equals(DoValidate.ValidatType.NotNull.class)) {
            return DoValidate.ValidatType.NotNull.validate(value, context);
        }
        return true;
    }


}