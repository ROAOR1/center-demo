package com.example.center.service.service.baby;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.center.api.baby.BabyService;
import com.example.center.common.entity.BabyInfo;
import com.example.center.dao.BabyInfoMapper;
import com.fosun.health.oss.api.RemoteOssProvider;
import com.fosun.health.oss.common.bean.OssRequest;
import com.fosun.health.oss.common.bean.resp.CommonResult;
import com.fosun.health.oss.common.bean.resp.UploadResult;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: wwh
 * @Date: 2021/2/4
 * @Description:
 */
@Component
@DubboService(
        version = "${service.version}",
        interfaceClass = BabyService.class,
        cluster = "failfast",
        loadbalance = "roundrobin",
        group = "baby"
)
public class BabyServiceImpl implements BabyService  {
    @Autowired
    private BabyInfoMapper babyInfoMapper;
    @DubboReference(
            version = "1.0.0",
            interfaceClass = RemoteOssProvider.class,
            cluster = "failfast",
            loadbalance = "roundrobin"
    )
    private RemoteOssProvider remoteOssProvider;

    @Override
    public BabyInfo getBabyById(Long id) {
        return babyInfoMapper.selectById(id);
    }

    @Override
    public List<BabyInfo> listBabyByStatus(Integer status) {
        return babyInfoMapper.selectList(new QueryWrapper<BabyInfo>().eq(
                "status",status));
    }

    @Override
    public void saveBaby(BabyInfo babyInfo) {
        babyInfoMapper.insert(babyInfo);
    }

    @Override
    public CommonResult<UploadResult> upload(OssRequest ossRequest, String url) {
        return remoteOssProvider.upload(ossRequest, url);
    }
}
