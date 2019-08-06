package com.biaobai.service.impl;

import com.biaobai.domain.ListLike;
import com.biaobai.mapper.ListLikeMapper;
import com.biaobai.service.IListLikeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.biaobai.utils.IdGen;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
@Service
public class ListLikeServiceImpl extends ServiceImpl<ListLikeMapper, ListLike> implements IListLikeService {

    @Override
    public int add(ListLike like) {
        like.setId(IdGen.get().nextId());
        like.setCreateDate(new Date());
        like.setState(1);

        return baseMapper.insert(like);
    }
}
