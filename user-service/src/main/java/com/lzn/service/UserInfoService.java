package com.lzn.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzn.controller.utils.R;
import com.lzn.domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService extends IService<User> {
    R saveUserPic(MultipartFile file);
    Boolean deleteById(String id);


    Boolean updateByIdWithoutPs(User user);

    Boolean updateByIdPs(User user);
}
