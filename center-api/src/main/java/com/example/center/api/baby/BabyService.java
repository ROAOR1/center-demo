package com.example.center.api.baby;


import com.example.center.common.entity.BabyInfo;
import com.fosun.health.oss.common.bean.OssRequest;
import com.fosun.health.oss.common.bean.resp.CommonResult;
import com.fosun.health.oss.common.bean.resp.UploadResult;

import java.util.List;

/**
 * @Author: wwh
 * @Date: 2021/2/4
 * @Description:
 */
public interface BabyService {
    BabyInfo getBabyById(Long id);

    List<BabyInfo> listBabyByStatus(Integer status);

    void saveBaby(BabyInfo babyInfo);

    CommonResult<UploadResult> upload(OssRequest ossRequest, String url);
}
