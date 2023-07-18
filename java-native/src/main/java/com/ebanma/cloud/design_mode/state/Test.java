package com.ebanma.cloud.design_mode.state;

public class Test {
    public static void main(String[] args) {
        AppContext context = new AppContext();
        context.favorite();
        context.comment("评论: 好文章，360个赞!");
    }
}
