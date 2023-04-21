package com.ebanma.cloud.stream;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.lambda.cart.BaseCartService;
import com.ebanma.cloud.lambda.cart.Sku;
import com.ebanma.cloud.lambda.cart.SkuCategoryEnum;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author 于秦涛
 * @version $ Id: StreamVs, v 0.1 2023/03/01 14:13 98077 Exp $
 */
public class StreamVs {

    /**
     * 需求
     * 1. 想看看购物车中都有什么商品
     * 2. 图书类商品都给买
     * 3. 其余的商品中买两件最贵的
     * 4. 只需要两件商品的名称和总价
     */

    @Test
    public void oldCartHandle() {
        List<Sku> cartSkuList = BaseCartService.getCartSkuList();
        for (Sku sku : cartSkuList) {
            System.out.println(JSON.toJSONString(sku, true));
        }

        List<Sku> notBooks = new ArrayList<>();
        for (Sku sku : cartSkuList) {
            if (!SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())) {
                notBooks.add(sku);
            }
        }

        //notBooks.sort((Sku sku1, Sku sku2)-> sku2.getTotalPrice().compareTo(sku1.getTotalPrice()));
        notBooks.sort(Comparator.comparing(Sku::getTotalPrice).reversed());

        List<Sku> top2Books = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            top2Books.add(notBooks.get(i));
        }
        for (Sku sku : top2Books) {
            System.out.println(JSON.toJSONString(sku, true));
        }

        Integer money = 0;
        List<String> names = new ArrayList<>();

        for (Sku sku : top2Books) {
            money += sku.getTotalPrice();
            names.add(sku.getSkuName());
        }
        System.out.println("商品金额:" + money);
        System.out.println("商品名称:" + JSON.toJSONString(names, true));
    }

    @Test
    public void newCartHandle() {
        AtomicInteger money = new AtomicInteger(0);
        //流支持并行。
        List<String> names=BaseCartService.getCartSkuList().stream()
                .filter(sku -> !SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory()))
                .sorted(Comparator.comparing(Sku::getTotalPrice).reversed())
                .limit(2)
                //并行要考虑线程安全性，所以使用原子类的int
                .peek(sku -> money.set(money.get()+sku.getTotalPrice()))
                .map(sku -> sku.getSkuName())
                //.peek(sku -> System.out.println(JSON.toJSONString(sku, true)))
                .collect(Collectors.toList());

        System.out.println("商品金额:" + money);
        System.out.println("商品名称:" + JSON.toJSONString(names, true));

    }

}

