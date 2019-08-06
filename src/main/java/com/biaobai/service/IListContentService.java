package com.biaobai.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.biaobai.domain.ListContent;
import com.baomidou.mybatisplus.service.IService;
import com.biaobai.domain.ListContentListVO;
import com.biaobai.domain.ListQuery;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
public interface IListContentService extends IService<ListContent> {
    Page<ListContentListVO> page(ListQuery query);
    int add(ListContentListVO vo);
}
