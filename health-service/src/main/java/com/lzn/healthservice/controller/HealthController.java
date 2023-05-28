package com.lzn.healthservice.controller;


import com.lzn.healthservice.controller.utils.R;
import com.lzn.healthservice.domain.Health;
import com.lzn.healthservice.service.HealthService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @PostMapping
    @ResponseBody
    public R save(@RequestBody Health health, @RequestParam("id") String userid){
        System.out.println(health);
        System.out.println(userid);

        Boolean bl=healthService.saveHealth(health,userid);
        System.out.println(bl);
        return new R(bl);

    }

    @GetMapping
    @ResponseBody
    public R getAll(@RequestParam("id") String userid){
        List<Health> healthList=healthService.getHealthAll(userid);
        System.out.println(healthList);
        return new R(true,null,healthList);
    }

    @PutMapping
    @ResponseBody
    public R update(@RequestBody Health health, @RequestParam("id") String userid){
        return new R(healthService.updateHealth(health,userid));
    }

    @DeleteMapping
    @ResponseBody
    public R delete(@RequestBody Health health, @RequestParam("id") String userid){
        return new R(healthService.deleteHealth(health,userid));
    }
}
