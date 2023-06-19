package com.ebanma.cloud.design_mode.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 于秦涛
 * @version $ Id: StrategyController, v 0.1 2023/06/13 15:04 98077 Exp $
 * 策略模式实现
 */
@Controller
@RequestMapping("/test")
public class StrategyController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private final Map<String,PaymentStrategy> strategyMap= new ConcurrentHashMap<>();

    @GetMapping("test/{method}")
    private void payment(@PathVariable String method){
        paymentService.payment(strategyMap.get(method),100);
    }

}

