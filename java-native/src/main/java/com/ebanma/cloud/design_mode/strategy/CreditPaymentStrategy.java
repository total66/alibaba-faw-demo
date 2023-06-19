package com.ebanma.cloud.design_mode.strategy;

/**
 * @author 于秦涛
 * @version $ Id: CreditPaymentStrategy, v 0.1 2023/06/13 15:06 98077 Exp $
 */
public class CreditPaymentStrategy implements PaymentStrategy{
    @Override
    public void payment(int count) {
        System.out.println("this is credit payment strategy! count is: "+count);
    }
}

