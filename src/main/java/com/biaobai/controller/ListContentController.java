package com.biaobai.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.biaobai.domain.ListContentListVO;
import com.biaobai.domain.ListQuery;
import com.biaobai.service.IListContentService;
import com.biaobai.utils.PageUtils;
import com.biaobai.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
@RestController
@RequestMapping("/listContent")
public class ListContentController {

    @Autowired
    private IListContentService iListContentService;


    /**
     * 分页查询
     *
     * @author : zhujingchun
     * @date : 2019-07-18
     */
    @PostMapping("/list")
    public R list(@RequestBody ListQuery query) {
        Page<ListContentListVO> resPage = iListContentService.page(query);
        PageUtils page = new PageUtils(resPage);
        return R.ok().put("page", page);
    }

    @PostMapping("/add")
    public R add(@RequestBody ListContentListVO vo) {
        int i = iListContentService.add(vo);
        if (i == 0) {
            return R.error(500,"发表失败！");
        }
        return R.ok();
    }
}

