package com.lzn.dietservice.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzn.dietservice.controller.utils.R;
import com.lzn.dietservice.domain.Diet;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface DietService extends IService<Diet> {
    R saveDietPic(List<MultipartFile> files);
    Boolean saveDiet(Diet diet,String id);
    Diet checkPrimary(Diet diet,String id);
    List<Diet> getDietAll(String id);
    List<Diet> getDietByFood(String id, String food);
    Boolean deleteDiet(Diet diet,String id);




}
