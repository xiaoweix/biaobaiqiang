package com.biaobai.controller;


import com.biaobai.config.WebSecurityConfig;
import com.biaobai.domain.User;
import com.biaobai.domain.UserVO;
import com.biaobai.service.IUserService;
import com.biaobai.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public R login(@RequestBody UserVO user, HttpSession session) {
        UserVO login = userService.login(user);
        if (null == login) {
            return R.error(502, "账户或密码错误!");
        } else if (login.getState() == 0) {
            return R.error(503, "账户已被锁定,请联系管理员!");
        } else if (login.getState() == 2) {
            return R.error(504, "账户未激活,请前去激活!");
        }
        // 登录成功 加入session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, login);
        session.setMaxInactiveInterval(300000);
        return R.ok().put("user", login);
    }

    @GetMapping("/getSeUser")
    public R getUser(HttpSession session) {
        UserVO user = (UserVO) session.getAttribute(WebSecurityConfig.SESSION_KEY);
        return R.ok().put("user", user);
    }

    @GetMapping("logout")
    public R logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return R.ok();
    }

    @PostMapping("/verifRegiter")
    public R verifRegiter(@RequestBody UserVO vo) {
        List<User> users = userService.verifRegiter(vo);
        if (null != users) {
            if (users.size() > 0) {
                return R.error(601, "该邮箱已被注册!");
            }
        }
        // 新增
        User user = userService.insert(vo);
        return R.ok().put("id", user.getId());
    }

    @PostMapping("/verifCode")
    public R verifCode(@RequestBody UserVO vo) {
        int i = userService.verifCode(vo);
        if (i == 1) {
            return R.ok();
        }
        return R.error(602,"验证失败!");
    }


}


