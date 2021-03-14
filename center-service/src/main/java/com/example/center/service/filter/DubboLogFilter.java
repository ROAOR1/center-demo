package com.example.center.service.filter;

/**
 * @author weidejiang
 */


import com.alibaba.dubbo.common.Constants;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;

@Activate(group = { CommonConstants.PROVIDER})
public class DubboLogFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(DubboLogFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        //调用服务前
        String invokeName = null;
        String role = CommonConstants.PROVIDER;
        Instant start = Instant.now();
        InetSocketAddress remoteAddress =null;
        try {
            RpcContext rpcContext = RpcContext.getContext();
            Class<?> invokerInterface = invoker.getInterface();
            remoteAddress = rpcContext.getRemoteAddress();
            if (invokerInterface.getName().startsWith("com.alibaba.cloud.dubbo.service.DubboMetadataService")) {
                return invoker.invoke(invocation);
            }
            if (rpcContext.isConsumerSide()) {
                role = Constants.CONSUMER;
            }
            if (logger.isInfoEnabled()) {
                String methodName = invocation.getMethodName();
                invokeName = invokerInterface.getName() + "#" + methodName;
                Object[] arguments = invocation.getArguments();
                logger.info("当前系统为：[{}],remoteAddress:[{}],调用服务接口：{},调用参数:{}", role, remoteAddress, invokeName, JSON.toJSONString(arguments));
            }
        } catch (JSONException e) {
            logger.info("当前系统为：[{}],remoteAddress:[{}],调用服务接口：{},调用参数:{}", role, remoteAddress, invokeName, invocation.getArguments());
        } catch (Exception e) {
            logger.error("DubboLogFilter:", e);
        }
        Result result = invoker.invoke(invocation);
        Object value = null;
        if (result != null) {
            value = result.getValue();
        }

        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        try {
            if (logger.isInfoEnabled()) {
                logger.info("当前系统为：[{}],调用服务接口：{},返回值:{},接口耗时{}ms", role, invokeName, JSON.toJSONString(value),between.toMillis());
            }
        }catch (JSONException e) {
            logger.info("当前系统为：[{}],调用服务接口：{},返回值:{},接口耗时{}ms", role, invokeName, value,between.toMillis());
        } catch (Exception e) {
            logger.error("DubboLogFilter:", e);
        }
        return result;
    }

}