package com.lzn.healthservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lzn.healthservice.domain.Health;


import java.util.List;

public interface HealthService extends IService<Health> {
    Boolean saveHealth(Health health, String id);
    List<Health> getHealthAll(String id);
    Boolean deleteHealth(Health health,String id);
    Boolean updateHealth(Health health,String id);
}
