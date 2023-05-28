package com.lzn.diseaseservice.controller;


import com.lzn.diseaseservice.domain.Disease;
import com.lzn.diseaseservice.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import com.lzn.diseaseservice.controller.utils.R;
import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @PostMapping("/save_disease_pic")
    public R save(@RequestParam("file") List<MultipartFile> files){
        System.out.println(files);
        System.out.println(files.size());
        R re=diseaseService.saveDiseasePic(files);
        System.out.println(re);
        return re;
    }

    @PostMapping("/save_disease")
    @ResponseBody
    public R save(@RequestBody Disease disease, @RequestParam("id") String userid){
        System.out.println(disease);
        System.out.println(userid);

        Boolean bl=diseaseService.saveDisease(disease,userid);
        System.out.println(bl);
        return new R(bl);

    }

    @GetMapping("/get_diseasebyname")
    @ResponseBody
    public R getByName(@RequestParam("id") String userid,@RequestParam("diseasename") String diseasename){
        System.out.println(diseasename);
        return new R(true,null,diseaseService.getDiseaseByName(diseasename,userid));
    }
    @GetMapping("/get_diseaseall")
    @ResponseBody
    public R getAll(@RequestParam("id") String userid){
        return new R(true,null,diseaseService.getDiseaseAll(userid));
    }

    @DeleteMapping("/delete_disease")
    @ResponseBody
    public R delete(@RequestBody Disease disease, @RequestParam("id") String userid){
        return new R(diseaseService.deleteDisease(disease,userid));
    }

}
