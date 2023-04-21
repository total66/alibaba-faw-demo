package com.ebanma.cloud.lambda.cart;

import java.util.ArrayList;
import java.util.List;

public class CartV1Service {

    /**
     * 获取商品信息列表
     * @return
     */
    public static List<Sku> getCartSkuList() {
        return BaseCartService.getCartSkuList();
    }

    /**
     * Version 1.0
     * 找出购物车中所有电子产品
     * @param cartSkuList
     * @return
     */
    public static List<Sku> filterElectronicsSkus(
            List<Sku> cartSkuList) {

        List<Sku> result = new ArrayList<Sku>();
        for (Sku sku: cartSkuList) {
            // 如果商品类型 等于 电子类
            if (SkuCategoryEnum.ELECTRONICS.
                    equals(sku.getSkuCategory())) {
                result.add(sku);
            }
        }
        return result;
    }

    public static List<Sku> filterBookSkus(List<Sku> cartSkuList){
        ArrayList<Sku> result=new ArrayList<Sku>();
        for (Sku sku: cartSkuList){
            if(SkuCategoryEnum.BOOKS.equals(sku.getSkuCategory())){
                result.add(sku);
            }
        }
        return result;

    }

}
