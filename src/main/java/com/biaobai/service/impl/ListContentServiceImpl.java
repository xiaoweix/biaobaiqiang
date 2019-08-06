package com.biaobai.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.biaobai.domain.*;
import com.biaobai.mapper.ListContentMapper;
import com.biaobai.service.IListContentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.biaobai.service.IListLikeService;
import com.biaobai.service.IUserService;
import com.biaobai.utils.IdGen;
import com.biaobai.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
@Service
public class ListContentServiceImpl extends ServiceImpl<ListContentMapper, ListContent> implements IListContentService {

    @Autowired
    private IListLikeService likeService;
    @Autowired
    private IUserService userService;

    @Override
    public Page<ListContentListVO> page(ListQuery query) {
        // 分页查询列表
        Page<ListContentListVO> page = PageUtil.of(query.getPageNo(),
                query.getPageSize());

        EntityWrapper<ListContent> pageW = new EntityWrapper<>();
        pageW.eq("state", "1");
        pageW.orderBy("createDate", false);

        List<ListContent> contentList = baseMapper.selectPage(page, pageW);

        // 根据用户ID文章 查询点赞
        List<ListContentListVO> contentListVO = new ArrayList<>();
        EntityWrapper<ListLike> wrapper;
        ListContentListVO vo;
        for (ListContent content : contentList) {
            vo = new ListContentListVO();
            BeanUtil.copyProperties(content, vo);
            wrapper = new EntityWrapper<>();
            wrapper.eq("listId", content.getId());
            wrapper.eq("userId", query.getUserId());
            wrapper.eq("state", 1);
            ListLike like = likeService.selectOne(wrapper);
            vo.setLike(like != null);
            contentListVO.add(vo);
        }
        page.setRecords(contentListVO);

        return page;
    }

    @Override
    public int add(ListContentListVO vo) {
        // 根据用户ID查询出用户信息
        User user = userService.selectById(vo.getUserId());
        if (user == null) {
            return 0;
        }
        ListContent content = new ListContent();
        content.setId(IdGen.get().nextId());
        content.setAvatarUrl(user.getAvatarUrl());
        content.setCity(vo.getCity());
        content.setColor(vo.getColor());
        content.setContent(vo.getContent());
        content.setCreateDate(new Date());
        content.setSex(user.getSex());
        content.setState(1);
        content.setUserId(user.getId());
        content.setUserName(user.getUserName());

        // 插入
        return baseMapper.insert(content);
    }
}
