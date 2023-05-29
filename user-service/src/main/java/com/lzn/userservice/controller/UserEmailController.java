package com.lzn.userservice.controller;



import com.lzn.feign.domain.R;
import com.lzn.userservice.service.UserEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class UserEmailController {

    @Autowired
    private UserEmailService userEmailService;

    @GetMapping("/textcode/{email}")
    public R sendCode(@PathVariable String email){
        return new R(userEmailService.sendemail(email));
    }

    @PutMapping("/sendbindcode")
    public R sendbindcode(@RequestParam("id") String userid,@RequestParam("email") String email) {
        System.out.println("到email控制器了");
        return userEmailService.sendbindcode(userid,email);
    }
    @PutMapping("/sendupdatecode")
    public R sendupdatecode(@RequestParam("id") String userid) {
        return userEmailService.sendbindcode(userid);
    }
    @PutMapping("/getps")
    public R getps(@RequestParam("id") String userid) {
        return userEmailService.getPs(userid);
    }
    @PutMapping("/checkcode")
    public R checkcode(@RequestParam("id") String userid,@RequestParam("code") String code,@RequestParam("state") Integer i) {
        return userEmailService.checkbingcode(userid,code,i);
    }

    @GetMapping("/{id}")
    public R getById(@PathVariable String id){
        return new R(true,null,userEmailService.getById(id));
    }

}
