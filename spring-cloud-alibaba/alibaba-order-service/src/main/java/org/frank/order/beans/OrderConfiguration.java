package org.frank.order.beans;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import feign.Logger;
import org.frank.order.enums.ExceptionType;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OrderConfiguration {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
    
    @Bean
    public Map<Class<? extends Exception>, ExceptionType> exceptionTypeMap(){
        Map<Class<? extends Exception>, ExceptionType> exceptionTypeMap = new HashMap<>();
        exceptionTypeMap.put(FlowException.class, ExceptionType.FLOW_EXCEPTION);
        exceptionTypeMap.put(DegradeException.class, ExceptionType.DEGRADE_EXCEPTION);
        exceptionTypeMap.put(ParamFlowException.class, ExceptionType.PARAM_FLOW_EXCEPTION);
        exceptionTypeMap.put(SystemBlockException.class, ExceptionType.SYSTEM_BLOCK_EXCEPTION);
        exceptionTypeMap.put(AuthorityException.class, ExceptionType.AUTHORITY_EXCEPTION);
        return exceptionTypeMap;
    }
}
