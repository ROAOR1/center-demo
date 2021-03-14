package com.example.center.web.controller.baby;

import com.example.center.api.baby.BabyService;
import com.example.center.common.entity.BabyInfo;
import com.fosun.health.oss.common.bean.OssRequest;
import com.fosun.health.oss.common.bean.resp.CommonResult;
import com.fosun.health.oss.common.bean.resp.UploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wwh
 * @Date: 2021/2/5
 * @Description:
 */
@RestController
@RequestMapping("/baby")
public class BabyController {
    @Autowired
    private BabyService babyService;

    @GetMapping("/get")
    public BabyInfo getBabyById(@RequestParam("id") Long id){
        return babyService.getBabyById(id);
    }

    @RequestMapping("/upload")
    public CommonResult<UploadResult> upload(OssRequest ossRequest, String url){
        return babyService.upload(ossRequest, url);
    }
}
