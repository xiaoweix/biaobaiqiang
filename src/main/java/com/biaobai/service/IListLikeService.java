package com.biaobai.service;

import com.biaobai.domain.ListLike;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
public interface IListLikeService extends IService<ListLike> {
    int add(ListLike like);
}
