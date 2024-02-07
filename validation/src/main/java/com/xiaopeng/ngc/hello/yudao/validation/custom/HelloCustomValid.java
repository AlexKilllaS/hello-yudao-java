package com.xiaopeng.ngc.hello.yudao.validation.custom;

import com.xiaopeng.ngc.hello.yudao.validation.custom.embedding.CustomValidator;
import com.xiaopeng.ngc.hello.yudao.validation.custom.embedding.ObjectTobeValidated;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

/**
 * @author yyHuangfu
 * @create 2024/2/6
 * @description
 */
public class HelloCustomValid {

    static CustomValidator validator = new CustomValidator();

    public static void main(String[] args) {
        ObjectTobeValidated object = new ObjectTobeValidated();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        // object 验证通过，但是field验证失败
        for (ConstraintViolation<Object> violation : violations) {
            // validation fail时进入
            System.out.println(violation.getMessage()); // validation message: null
        }
    }

}
