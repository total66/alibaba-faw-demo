package com.ebanma.cloud.design_mode.builder;

import org.junit.Test;

/**
 * @author 于秦涛
 * @version $ Id: TestBuilder, v 0.1 2023/05/11 21:22 98077 Exp $
 */
public class TestBuilder {

    @Test
    public void testBuilder() {
        Product.Builder Builder = new Product.Builder();
        Product product = Builder.buildBackground("my background")
                .buildIcon("my icon")
                .buildSounds("my sounds")
                .build();
        System.out.println(product);
    }

}

