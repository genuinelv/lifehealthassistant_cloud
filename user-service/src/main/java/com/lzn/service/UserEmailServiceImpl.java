package com.lzn.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.lzn.controller.utils.R;
import com.lzn.controller.utils.RandomUtil;
import com.lzn.dao.UserEmailDao;
import com.lzn.dao.UserInfoDao;
import com.lzn.domain.Useremail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserEmailServiceImpl extends ServiceImpl<UserEmailDao, Useremail> implements UserEmailService{
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    UserEmailDao userEmailDao;


    @Override
    public boolean sendemail(String email) {
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);  // 发送人
        message.setTo(email);
        Date now=new Date();
        message.setSentDate(now);
        message.setSubject("邮箱验证");
        String code= RandomUtil.generateStr(4);
        message.setText("您本次登录的验证码是：" + code + "，有效期5分钟。请妥善保管，切勿泄露");
        javaMailSender.send(message);

        return false;
    }

    @Override
    public R sendbindcode(String id, String email) {//绑定邮箱

        if(userEmailDao.selectById(id).getEmail()!=null){//已经绑定
            return new R(false,"已经绑定邮箱",null);
        }

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);  // 发送人
        message.setTo(email);
        Date now=new Date();
        message.setSentDate(now);
        message.setSubject("绑定邮箱验证");
        String code= RandomUtil.generateStr(4);
        message.setText("您本次登录的验证码是：" + code + "，有效期5分钟。请妥善保管，切勿泄露");
        javaMailSender.send(message);

        Useremail useremail=new Useremail();
        useremail.setId(id);
        useremail.setEmail(email);
        useremail.setCheckcode(code);
        useremail.setEmailstate(0);//表示未绑定,当输入验证码正确才绑定
        Date after=new Date(now.getTime()+300000);
        useremail.setExistdate(after);
        userEmailDao.updateById(useremail);
        return new R(true,"已经发送验证码",null);
    }
    @Override
    public R sendbindcode(String id) {//点击修改密码，需要发送验证码
        String email=userEmailDao.selectById(id).getEmail();
        if(email==null){//未绑定
            return new R(false,"未绑定邮箱,可直接修改",null);
        }

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);  // 发送人
        message.setTo(email);
        Date now=new Date();
        message.setSentDate(now);
        message.setSubject("修改密码邮箱验证");
        String code= RandomUtil.generateStr(4);
        message.setText("您本次修改密码的验证码是：" + code + "，有效期5分钟。请妥善保管，切勿泄露");
        javaMailSender.send(message);

        Useremail useremail=new Useremail();
        useremail.setId(id);
        useremail.setEmail(email);
        useremail.setCheckcode(code);
        useremail.setEmailstate(1);//表示绑定
        Date after=new Date(now.getTime()+300000);
        useremail.setExistdate(after);
        userEmailDao.updateById(useremail);
        return new R(true,"已经发送验证码",null);

    }
    @Override
    public R checkbingcode(String id,String code, int i) {//判定验证码是否正确
        if(i==1){//绑定邮箱时
            Useremail useremail=userEmailDao.selectById(id);
            Date date=new Date();
            if(useremail.getExistdate().before(date)){
                return new R(false,"超时",null);
            }
            if(code.equals(useremail.getCheckcode())){
                useremail.setEmailstate(1);
                userEmailDao.updateById(useremail);
                return new R(true,"成功绑定",null);
            }
            else
                return new R(false,"验证码错误",null);
        }
        else {//修改密码时
            Useremail useremail=userEmailDao.selectById(id);
            Date date=new Date();
            if(useremail.getExistdate().before(date)){
                return new R(false,"超时",null);
            }
            if(code.equals(useremail.getCheckcode())){
                useremail.setEmailstate(1);
                userEmailDao.updateById(useremail);

                return new R(true,"可以修改",null);
            }
            else
                return new R(false,"验证码错误",null);
        }

    }

    @Override
    public R getPs(String id) {
        String email=userEmailDao.selectById(id).getEmail();
        if(email==null){//未绑定
            return new R(false,"未绑定邮箱,不能找回",null);
        }

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(from);  // 发送人
        message.setTo(email);
        Date now=new Date();
        message.setSentDate(now);
        message.setSubject("密码服务邮箱验证");
        String code= userInfoDao.selectById(id).getPassword();
        message.setText("您绑定的账号的密码是：" + code + "。请妥善保管，阅完即焚，切勿泄露");

        javaMailSender.send(message);

        return new R(true,"成功发送",email);

    }

}
