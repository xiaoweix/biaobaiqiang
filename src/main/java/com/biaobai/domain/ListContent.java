package com.biaobai.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("listContent")
public class ListContent implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("userName")
    private String userName;
    @TableField("userId")
    private String userId;
    private String content;
    private Integer sex;
    @TableField("avatarUrl")
    private String avatarUrl;
    private Integer state;
    @TableField("createDate")
    private Date createDate;
    private String color;
    private String city;

}
