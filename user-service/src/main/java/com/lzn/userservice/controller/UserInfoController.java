package com.lzn.userservice.controller;



import com.lzn.feign.domain.R;
import com.lzn.userservice.domain.User;
import com.lzn.userservice.service.UserInfoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;



    @GetMapping("/{id}")
    public R getById(@PathVariable String id){
        return new R(true,null,userInfoService.getById(id));
    }

    @PostMapping
    public R save(@RequestBody User user){
        return new R(userInfoService.save(user));
    }
    
    @PutMapping
    public R updatepic(@RequestParam("file") MultipartFile file) {
        return userInfoService.saveUserPic(file);
    }
    @PutMapping("/{id}")
    public R update(@RequestBody User user,@PathVariable String id) {
        return new R(userInfoService.updateByIdWithoutPs(user));
    }
    @PutMapping("/ps/{id}")
    public R updatePs(@RequestBody User user,@PathVariable String id) {
        return new R(userInfoService.updateByIdPs(user));
    }

    @DeleteMapping("/{id}")
    public R remove(@PathVariable String id){
        return new R(userInfoService.deleteById(id));
    }
    @GetMapping("usernow")
    public String usernow(){
        return userInfoService.usernow();
    }
}
