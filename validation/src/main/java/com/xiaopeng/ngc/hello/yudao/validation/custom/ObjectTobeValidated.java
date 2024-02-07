package com.xiaopeng.ngc.hello.yudao.validation.custom;

/**
 * @author yyHuangfu
 * @create 2024/2/7
 * @description
 */
public class ObjectTobeValidated {
    public ObjectTobeValidated(String keyShouldNotBeNull) {
        this.keyShouldNotBeNull = keyShouldNotBeNull;
    }

    private String keyShouldNotBeNull;


}
