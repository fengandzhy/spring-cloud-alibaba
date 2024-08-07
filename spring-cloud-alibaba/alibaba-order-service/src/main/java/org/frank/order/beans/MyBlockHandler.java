package org.frank.order.beans;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import org.frank.order.enums.ExceptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class MyBlockHandler implements BlockExceptionHandler {    
    
    private Map<Class<? extends Exception>, ExceptionType> exceptionTypeMap;
    
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        Map<String,Object> backMap=new HashMap<>();
//        if (e instanceof FlowException){
//            backMap.put("code",-1);
//            backMap.put("msg","限流-异常啦");
//        }else if (e instanceof DegradeException){
//            backMap.put("code",-2);
//            backMap.put("msg","降级-异常啦");
//        }else if (e instanceof ParamFlowException){
//            backMap.put("code",-3);
//            backMap.put("msg","热点-异常啦");
//        }else if (e instanceof SystemBlockException){
//            backMap.put("code",-4);
//            backMap.put("msg","系统规则-异常啦");
//        }else if (e instanceof AuthorityException){
//            backMap.put("code",-5);
//            backMap.put("msg","认证-异常啦");
//        }

        ExceptionType exceptionType = exceptionTypeMap.get(e.getClass());
        backMap.put("code", exceptionType.getCode());
        backMap.put("msg", exceptionType.getMsg());

        // 设置返回json数据
        httpServletResponse.setStatus(200);
        httpServletResponse.setHeader("content-Type","application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(backMap));
    }

    @Autowired
    public void setExceptionTypeMap(Map<Class<? extends Exception>, ExceptionType> exceptionTypeMap) {
        this.exceptionTypeMap = exceptionTypeMap;
    }
}
