package com.weizidong.enums;

import java.util.Objects;

/**
 * 关注状态
 *
 * @author 魏自东
 * @date 2018/2/7 15:25
 */
public enum Subscribe {
    未关注(0), 已关注(1);
    private Integer value;

    Subscribe(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static Subscribe parse(Integer type) {
        for (Subscribe item : Subscribe.values()) {
            if (type != null && Objects.equals(type, item.getValue())) {
                return item;
            }
        }
        throw new RuntimeException("值【" + type + "】不是" + Subscribe.class + "的有效值。");
    }
}
