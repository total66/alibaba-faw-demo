package com.ebanma.cloud.webchat.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 于秦涛
 * @version $ Id: NettyWebChatController, v 0.1 2023/04/21 10:49 98077 Exp $
 */
@Controller
public class NettyWebChatController {

    @GetMapping("/hello")
    public String webChat(){
        return "chat";
    }

}

