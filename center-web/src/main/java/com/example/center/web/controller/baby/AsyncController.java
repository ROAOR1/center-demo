package com.example.center.web.controller.baby;

import com.example.center.api.baby.AsyncService;
import com.example.center.web.controller.remote.AsyncManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wwh
 * @Date: 2021/2/18
 * @Description: dubbo异步调用
 */
@RestController
@RequestMapping("/async")
public class AsyncController {
    @Autowired
    private AsyncManagerImpl asyncManager;
    /**
     * 方式一，提供者直接返回CompletableFuture对象
     * @param id
     */
    @GetMapping("/one")
    public void asyncOne(@RequestParam("id") Long id){
        asyncManager.asyncOne(id);
    }

    /**
     * 方式二，提供者正常返回，提供者需要配置@Method(name = "asyncTwo", async = true)，
     * 调用者通过RpcContext拿到CompletableFuture对象
     * @param id
     */
    @GetMapping("/two")
    public void asyncTwo(@RequestParam("id") Long id){
        asyncManager.asyncTwo(id);
    }
}
