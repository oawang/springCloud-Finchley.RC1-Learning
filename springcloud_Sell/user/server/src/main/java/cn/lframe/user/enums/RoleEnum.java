package cn.lframe.user.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    BUYER(1, "买家"),
    SELLER(2, "卖家"),;

    private Integer code;
    private String messgae;

    RoleEnum(Integer code, String messgae) {
        this.code = code;
        this.messgae = messgae;
    }
}
