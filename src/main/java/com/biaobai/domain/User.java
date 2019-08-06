package com.biaobai.domain;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("userName")
    private String userName;
    @TableField("phoneNum")
    private String phoneNum;
    private Integer sex;
    private Integer state; // 1正常 2待激活 0锁定
    @TableField("avatarUrl")
    private String avatarUrl;
    @TableField("createDate")
    private Date createDate;
    private String password;
    private String code;
    private String email;


}
