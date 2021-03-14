package com.example.center.web.controller.remote;

import com.alibaba.fastjson.JSON;
import com.example.center.api.baby.AsyncService;
import com.example.center.common.entity.BabyInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: wwh
 * @Date: 2021/2/18
 * @Description:
 */
@Component
public class AsyncManagerImpl {
    @Autowired
    private AsyncService asyncService;

    public void asyncOne(Long id) {
        CompletableFuture<BabyInfo> future = asyncService.asyncOne(id);
        //判断执行是否结束
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println(JSON.toJSONString(v));
            }
        });
        System.out.println("异步调用，这一步可能会先执行");
    }

    public void asyncTwo(Long id) {
        asyncService.asyncTwo(id);
        //获取CompletableFuture对象
        CompletableFuture<BabyInfo> future = RpcContext.getContext().getCompletableFuture();
        //等待执行完成
        future.whenComplete((v, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                System.out.println(JSON.toJSONString(v));
            }
        });
        System.out.println("异步调用two，这一步可能先执行");


        //第二种方式
        CompletableFuture<BabyInfo> future1 = RpcContext.getContext().asyncCall(
                () -> asyncService.asyncTwo(id)
        );
        try {
            System.out.println("异步调用two，这一步可能先执行");
            //调用get方法会阻塞，直接结果返回
            System.out.println(JSON.toJSONString(future1.get()));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
