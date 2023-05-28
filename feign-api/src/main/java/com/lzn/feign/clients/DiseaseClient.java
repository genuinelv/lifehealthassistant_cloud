package com.lzn.feign.clients;

import com.lzn.feign.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: DiseaseClient
 * Description:
 *
 * @author lzn
 * @date 2023/05/28 22:23
 */
@FeignClient(value = "diseaseservice")
public interface DiseaseClient {
    @GetMapping("/get_diseaseall")
    public R getAllDisease(String userid);

}
