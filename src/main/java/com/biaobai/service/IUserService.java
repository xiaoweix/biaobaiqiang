package com.biaobai.service;

import com.biaobai.domain.User;
import com.baomidou.mybatisplus.service.IService;
import com.biaobai.domain.UserVO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
public interface IUserService extends IService<User> {

    UserVO login(UserVO user);

    List<User> verifRegiter(@RequestBody UserVO vo);

    User insert(UserVO vo);

    int verifCode(UserVO vo);
}
