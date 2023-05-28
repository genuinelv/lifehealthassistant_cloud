package com.lzn.dietservice.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lzn.dietservice.controller.utils.R;
import com.lzn.dietservice.dao.DietDao;
import com.lzn.dietservice.domain.Diet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class DietServiceImpl extends ServiceImpl<DietDao, Diet> implements DietService{

    @Autowired
    private DietDao dietDao;

    @Override
    public R saveDietPic(List<MultipartFile> files) {
        int filescount=files.size();

        Diet diet=new Diet();
        for(int i=0;i<filescount;i++){

            if(files.get(i).isEmpty()){System.out.println("到saveDietPic循环判断空了");
                return new R(false,"文件名为空",null);
            }
            String originFilename =files.get(i).getOriginalFilename();
            System.out.println(originFilename);
            String fileName=System.currentTimeMillis()+"."+originFilename.substring(originFilename.lastIndexOf(".")+1);
            String filePath = "D:\\pic_diet\\";
            File dest = new File(filePath+fileName);

            if(i==0)
                diet.setPicture1(filePath+fileName);
            else if(i==1)
                diet.setPicture2(filePath+fileName);
            else
                diet.setPicture3(filePath+fileName);

            if(!dest.getParentFile().exists())
                dest.getParentFile().mkdirs();
            try {
                files.get(i).transferTo(dest);
            }catch (Exception e){
                e.printStackTrace();
                return new R(false,"上传失败，服务器错误",null);

            }

        }
        return new R(true,null,diet);
    }

    @Override
    public Boolean saveDiet(Diet diet, String id) {

        return dietDao.insertDiet(diet,id)>0;
    }

    @Override
    public Diet checkPrimary(Diet diet,String id) {
        System.out.println(dietDao.selectAll(id));
        String date= new SimpleDateFormat("yyyy-MM-dd").format(diet.getDatetime()).toString();
        if(dietDao.selectPrimary(date,id,diet.getDietname()).size()>0){
            return dietDao.selectPrimary(date,id,diet.getDietname()).get(0);
             //已经存在
        }
        else
            return null;//不存在,可以放

    }

    @Override
    public List<Diet> getDietAll(String id) {
        return dietDao.selectAll(id);
    }

    @Override
    public List<Diet> getDietByFood(String id, String food) {
        return dietDao.selectByContent(id,food);
    }

    @Override
    public Boolean deleteDiet(Diet diet, String id) {
        return dietDao.deleteDiet(diet,id)>0;
    }


}
