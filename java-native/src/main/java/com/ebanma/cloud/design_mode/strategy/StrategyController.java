package com.ebanma.cloud.design_mode.strategy;

/**
 * @author 于秦涛
 * @version $ Id: StrategyController, v 0.1 2023/06/13 15:04 98077 Exp $
 * 策略模式实现
 */
public class StrategyController {

    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService();
        paymentService.payment(new CreditPaymentStrategy(),100);
        paymentService.payment(new WechatPaymentStrategy(),10000);
    }

}

