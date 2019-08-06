package com.biaobai.domain;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    private String id;
    private String userName;
    private String phoneNum;
    private Integer sex;
    private Integer state; // 1正常 2待激活 0锁定
    private String avatarUrl;
    private Date createDate;
    private String password;
    private String code;
    private String email;
    private String count; // 消息
}
