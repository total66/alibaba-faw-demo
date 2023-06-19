package com.ebanma.cloud.design_mode.strategy;

/**
 * @author 于秦涛
 * @version $ Id: PaymentService, v 0.1 2023/06/13 15:08 98077 Exp $
 */
public class PaymentService {

    public void payment(PaymentStrategy paymentStrategy, int count){
        paymentStrategy.payment(count);
    };

}

