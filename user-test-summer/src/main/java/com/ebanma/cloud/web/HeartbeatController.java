package com.ebanma.cloud.web;  
  
import com.ebanma.cloud.service.HeartbeatService;  
import com.ebanma.cloud.summer.mvc.annotation.Autowired;  
import com.ebanma.cloud.summer.mvc.annotation.Controller;  
import com.ebanma.cloud.summer.mvc.annotation.RequestMapping;  
  
@Controller  
@RequestMapping("/test")  
public class HeartbeatController {  
  
    @Autowired  
    private HeartbeatService heartbeatService;  
  
    @RequestMapping("/heartbeat")  
    public String heartbeat(String msg) {  
        return heartbeatService.getMessage(msg);  
    }  
}