package com.lzn.userservice.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzn.feign.clients.DietClient;
import com.lzn.feign.clients.DiseaseClient;
import com.lzn.feign.domain.Diet;
import com.lzn.feign.domain.Disease;
import com.lzn.feign.domain.R;
import com.lzn.userservice.dao.UserInfoDao;
import com.lzn.userservice.domain.User;
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

    @Autowired
    DietClient dietClient;
    @Autowired
    DiseaseClient diseaseClient;



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
        //List<Diet> dietList=new DietServiceImpl().getDietAll(id);

        R re = (R)dietClient.getAllDiet(id);
        //System.out.println(re.toString());
        List<Diet> dietList =(List<Diet>) re.getData();
        ObjectMapper mapper = new ObjectMapper();
        for(int i=0;i<dietList.size();i++){
            //System.out.println(dietList.get(i));

            Diet diet = mapper.convertValue(dietList.get(i),Diet.class);
            String filename1=diet.getPicture1();
            if(filename1!=null) {
                File file1 = new File(filename1);
                file1.delete();
            }
            String filename2=diet.getPicture2();
            if(filename2!=null) {
                File file2 = new File(filename2);
                file2.delete();
            }
            String filename3=diet.getPicture3();
            if(filename3!=null) {
                File file3 = new File(filename3);
                file3.delete();
            }
        }
        userInfoDao.delete_diet(id);
        List<Disease> diseaseList=(List<Disease>) diseaseClient.getAllDisease(id).getData();
        for(int i=0;i<diseaseList.size();i++){
            Disease disease = mapper.convertValue(diseaseList.get(i),Disease.class);
            String filename1=disease.getSympic();
            if(filename1!=null) {
                File file1 = new File(filename1);
                file1.delete();
            }
            String filename2=disease.getMedpic();
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
    public String usernow() {
        return dietClient.now();
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
