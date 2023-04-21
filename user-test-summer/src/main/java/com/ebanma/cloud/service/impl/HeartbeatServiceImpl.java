package com.ebanma.cloud.service.impl;
  
import com.ebanma.cloud.service.HeartbeatService;  
import com.ebanma.cloud.summer.mvc.annotation.Service;  
  
@Service("heartbeatService")  
public class HeartbeatServiceImpl implements HeartbeatService {  
    @Override  
    public String getMessage(String msg) {  
        System.out.println("HeartbeatServiceImpl 中的入参：" + msg);  
        return msg;  
    }  
}