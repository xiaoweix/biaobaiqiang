package com.biaobai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.biaobai.domain.User;
import com.biaobai.domain.UserVO;
import com.biaobai.mapper.UserMapper;
import com.biaobai.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.biaobai.utils.Encrypt;
import com.biaobai.utils.IdGen;
import com.biaobai.utils.MailUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserVO login(UserVO user) {
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("email", user.getEmail());
        wrapper.eq("password", Encrypt.md5(user.getPassword(), user.getEmail()));
        List<User> users = baseMapper.selectList(wrapper);
        UserVO vo;
        if (null != users && users.size() > 0 && null != users.get(0)) {
            vo = new UserVO();
            // 后期增加查询消息
            vo.setCount("1");
            BeanUtil.copyProperties(users.get(0), vo);

            return vo;
        }
        return null;
    }

    @Override
    public List<User> verifRegiter(UserVO vo) {
        // 根据邮箱查询
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("email", vo.getEmail());
        List<User> users = baseMapper.selectList(wrapper);
        return users;
    }

    @Override
    public User insert(UserVO vo) {
        User user = new User();
        BeanUtil.copyProperties(vo, user);
        user.setId(IdGen.get().nextId());
        user.setState(2);
        // 随机码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);//生成短信验证码
        user.setCode(verifyCode);
        user.setCreateDate(new Date());
        user.setPassword(Encrypt.md5(vo.getPassword(), vo.getEmail()));
        Integer insert = baseMapper.insert(user);
        // 发送邮箱
        MailUtil.sendMail(user.getEmail(), user.getCode());
        return user;
    }

    @Override
    public int verifCode(UserVO vo) {
        User user = baseMapper.selectById(vo.getId());
        if (vo.getCode().equals(user.getCode())) {
            user.setState(1);
            user.setCode("");
            user.setAvatarUrl(vo.getAvatarUrl());
            baseMapper.updateById(user);
            return 1;
        }
        return 0;
    }

}
