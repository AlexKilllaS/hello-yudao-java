package com.xiaopeng.ngc.hello.yudao.validation.custom;

import java.util.Arrays;

/**
 * @author yyHuangfu
 * @create 2024/2/6
 * @description
 */
public enum GenderEnum implements IntArrayValuable {

    MALE(1, "男"),
    FEMALE(2, "女");
    /**
     * 性别值
     */
    private final Integer value;
    /**
     * 性别名
     */
    private final String name;

    GenderEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int[] array() {
        return Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();
    }

    public String getName() {
        return name;
    }

    public Integer getValue() {
        return value;
    }
}
