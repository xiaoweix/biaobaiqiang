package com.biaobai.controller;


import com.biaobai.domain.ListLike;
import com.biaobai.service.IListLikeService;
import com.biaobai.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhujingchun
 * @since 2019-07-18
 */
@RestController
@RequestMapping("/listLike")
public class ListLikeController {

    @Autowired
    private IListLikeService likeService;

    @PostMapping("/add")
    public R add(@RequestBody ListLike like){
        int i = likeService.add(like);
        if (i == 0) {
            return R.error(500,"点赞失败！");
        }
        return R.ok();
    }
}

