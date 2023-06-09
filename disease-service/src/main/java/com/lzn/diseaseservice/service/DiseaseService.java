package com.lzn.diseaseservice.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.lzn.diseaseservice.controller.utils.R;
import com.lzn.diseaseservice.domain.Disease;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DiseaseService extends IService<Disease> {

    R saveDiseasePic(List<MultipartFile> files);
    Boolean saveDisease(Disease disease, String id);
    List<Disease> getDiseaseAll(String id);
    List<Disease> getDiseaseByName(String name,String id);
    Boolean deleteDisease(Disease disease,String id);

}
