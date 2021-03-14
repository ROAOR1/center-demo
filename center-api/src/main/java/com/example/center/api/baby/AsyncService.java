package com.example.center.api.baby;


import com.example.center.common.entity.BabyInfo;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: wwh
 * @Date: 2021/2/18
 * @Description: 异步调用service
 */
public interface AsyncService {
    CompletableFuture<BabyInfo> asyncOne(Long id);
    BabyInfo asyncTwo(Long id);
}
