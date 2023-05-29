package com.lzn.feign.clients;

import com.lzn.feign.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ClassName: DietClient
 * Description:
 *
 * @author lzn
 * @date 2023/05/28 22:19
 */
@FeignClient(value = "dietservice")
public interface DietClient {
    @GetMapping("/diet/get_diet_all")
    public R getAllDiet(@RequestParam("id") String userid);

    @GetMapping("/diet/now")
    public String now();
}
