package com.lzn.dietservice.controller;


import com.lzn.dietservice.service.DietService;
import com.lzn.dietservice.controller.utils.R;

import com.lzn.dietservice.domain.Diet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @PostMapping("/save_diet_pic")
    public R save(@RequestParam("file") List<MultipartFile> files){
        System.out.println(files);
        System.out.println(files.size());
        R re=dietService.saveDietPic(files);
        System.out.println(re);
        return re;
    }

    @PostMapping("/save_diet")
    @ResponseBody
    public R save(@RequestBody Diet diet, @RequestParam("id") String userid){
        System.out.println(diet);
        System.out.println(userid);
        if(dietService.checkPrimary(diet,userid)!=null){
            System.out.println("这一天的这一顿饭已经登记了");
            return new R(false,"这一天的这一顿饭已经登记了",null);
        }
        Boolean bl=dietService.saveDiet(diet,userid);
        System.out.println(bl);
        return new R(bl);

    }

    @PostMapping("/get_diet")
    @ResponseBody
    public R get(@RequestBody Diet diet,@RequestParam("id") String userid){
        System.out.println(diet);
        System.out.println(userid);
        Diet diet1=dietService.checkPrimary(diet,userid);
        if(diet1==null){
            System.out.println("这顿饭之前没有登记");
            return new R(false,"这顿饭之前没有登记",null);
        }
        else{
            return new R(true,null,diet1);
        }
    }


    @GetMapping("/get_diet_all")
    @ResponseBody
    public R getAll(@RequestParam("id") String userid){
        return new R(true,null,dietService.getDietAll(userid));
    }

    @GetMapping("/get_diet_content")
    @ResponseBody
    public R getByContent(@RequestParam("id") String userid,@RequestParam("food") String food){
        return new R(true,null,dietService.getDietByFood(userid,food));
    }

    @DeleteMapping("/delete_diet")
    @ResponseBody
    public R delete(@RequestBody Diet diet, @RequestParam("id") String userid){
        return new R(dietService.deleteDiet(diet,userid));
    }


    @GetMapping("now")
    public String now(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
