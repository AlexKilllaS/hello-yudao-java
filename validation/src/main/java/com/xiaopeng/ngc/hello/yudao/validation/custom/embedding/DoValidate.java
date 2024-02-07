package com.xiaopeng.ngc.hello.yudao.validation.custom.embedding;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * @author yyHuangfu
 * @create 2024/2/6
 * @description
 */

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
// 指定验证器
@Constraint(validatedBy = CustomValidator.class)
public @interface DoValidate {
    String message() default "validation message: {value}"; // 必须字段：默认错误信息

    Class<?>[] groups() default {}; // 必须字段：用于分组校验

    Class<? extends Payload>[] payload() default {}; // 必须字段：客户端可以通过payload传递任何元数据

    class ValidatType {

        // 这里可以放各种验证方法
        public static class ValidateMethod1 implements Payload {
        }

        public static class ValidateMethod2 implements Payload {
        }

        public static class NotNull implements Payload {
            public static boolean validate(Object value, ConstraintValidatorContext context) {
                if (null != value) {
                    return true;
                }
                // ConstraintViolation就是validate失败时候返回的数据结构
                // 默认会返回一个default，如果需要自定义，需要disable掉default，然后重新build一个
                context.disableDefaultConstraintViolation(); // 禁用默认的 message 的值,否则会打印两次错误信息
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                        .replaceAll("\\{value}", String.valueOf(value))).addConstraintViolation(); // 重新添加错误提示语句
                return false;
            }
        }
    }

}
