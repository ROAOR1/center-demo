package com.example.center.service.service.baby;

import com.example.center.api.baby.AsyncService;
import com.example.center.common.entity.BabyInfo;
import com.example.center.dao.BabyInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: wwh
 * @Date: 2021/2/18
 * @Description:
 */
@Component
@DubboService(
        version = "${service.version}",
        interfaceClass = AsyncService.class,
        cluster = "failfast",
        loadbalance = "roundrobin",
        methods = {
            @Method(name = "asyncTwo", async = true)
        }
)
public class AsyncServiceImpl implements AsyncService {
    @Autowired
    private BabyInfoMapper babyInfoMapper;

    @Override
    public CompletableFuture<BabyInfo> asyncOne(Long id) {
        return CompletableFuture.supplyAsync(() -> babyInfoMapper.selectById(id));
    }

    @Override
    public BabyInfo asyncTwo(Long id) {
        return babyInfoMapper.selectById(id);
    }
}
