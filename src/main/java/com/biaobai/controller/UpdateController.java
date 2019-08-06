package com.biaobai.controller;

import com.biaobai.domain.UserVO;
import com.biaobai.utils.R;
import com.biaobai.utils.Upload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/update")
public class UpdateController {

    @PostMapping("/updateImg")
    public R updateImg(MultipartFile file) {
        try {
            String path = "/Users/zhujingchun/image";
            String image = Upload.uploadImages(file, path);
            return R.ok().put("img", "/avatarUrl/" + image);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error(701, "上传头像出错!");
        }
    }
}
