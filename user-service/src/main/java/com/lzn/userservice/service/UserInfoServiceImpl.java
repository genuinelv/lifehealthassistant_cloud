package com.lzn.userservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzn.userservice.dao.UserInfoDao;
import com.lzn.userservice.domain.User;
import com.lzn.userservice.controller.utils.R;
import com.lzn.userservice.dao.UserEmailDao;
import com.lzn.userservice.domain.Useremail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoDao, User> implements UserInfoService{

    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    UserEmailDao userEmailDao;



    @Override
    public boolean save(User user) {
        userInfoDao.save_create_diet(user);
        userInfoDao.save_create_disease(user);
        userInfoDao.save_create_health(user);
        Useremail useremail=new Useremail();
        useremail.setId(user.getId());
        userEmailDao.insert(useremail);
        return (userInfoDao.insert(user)>0);
    }

    @Override
    public R saveUserPic(MultipartFile file) {
        if(file.isEmpty()){System.out.println("到saveDietPic循环判断空了");
            return new R(false,"文件名为空",null);
        }
        String originFilename =file.getOriginalFilename();
        System.out.println(originFilename);
        String fileName=System.currentTimeMillis()+"."+originFilename.substring(originFilename.lastIndexOf(".")+1);
        String filePath = "D:\\pic_user\\";
        File dest = new File(filePath+fileName);
        User user=new User();
        user.setPhoto(filePath+fileName);

        if(!dest.getParentFile().exists())
            dest.getParentFile().mkdirs();
        try {
            file.transferTo(dest);
        }catch (Exception e){
            e.printStackTrace();
            return new R(false,"上传失败，服务器错误",null);

        }
        return new R(true,null,user);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Boolean deleteById(String id) {
        List<Diet> dietList=new DietServiceImpl().getDietAll(id);
        for(int i=0;i<dietList.size();i++){
            String filename1=dietList.get(i).getPicture1();
            if(filename1!=null) {
                File file1 = new File(filename1);
                file1.delete();
            }
            String filename2=dietList.get(i).getPicture2();
            if(filename2!=null) {
                File file2 = new File(filename2);
                file2.delete();
            }
            String filename3=dietList.get(i).getPicture3();
            if(filename3!=null) {
                File file3 = new File(filename3);
                file3.delete();
            }
        }
        userInfoDao.delete_diet(id);
        List<Disease> diseaseList=new DiseaseServiceImpl().getDiseaseAll(id);
        for(int i=0;i<diseaseList.size();i++){
            String filename1=diseaseList.get(i).getSympic();
            if(filename1!=null) {
                File file1 = new File(filename1);
                file1.delete();
            }
            String filename2=diseaseList.get(i).getMedpic();
            if(filename2!=null) {
                File file2 = new File(filename2);
                file2.delete();
            }

        }
        userEmailDao.deleteById(id);
        userInfoDao.delete_disease(id);
        userInfoDao.delete_health(id);
        User user=userInfoDao.selectById(id);
        String filename=user.getPhoto();
        if(filename!=null){
            File file=new File(filename);
            file.delete();
        }
        return userInfoDao.deleteById(id)>0;
    }

    @Override
    public Boolean updateByIdWithoutPs(User user) {
        System.out.println(user);
        return userInfoDao.updateByIdWithoutPs(user);
    }

    @Override
    public Boolean updateByIdPs(User user) {
        return userInfoDao.updateByIdPs(user);
    }


}
