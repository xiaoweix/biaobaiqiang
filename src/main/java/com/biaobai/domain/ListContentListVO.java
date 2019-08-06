package com.biaobai.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class ListContentListVO {
    private String id;
    private String userName;
    private String userId;
    private String content;
    private Integer sex;
    private String avatarUrl;
    private Integer state;
    private Date createDate;
    private boolean like;
    private String color;
    private String city;
}
