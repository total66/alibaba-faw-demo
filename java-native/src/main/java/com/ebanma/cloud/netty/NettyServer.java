package com.ebanma.cloud.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author 于秦涛
 * @version $ Id: NettyServer, v 0.1 2023/04/19 14:10 98077 Exp $
 */
public class NettyServer {

    /**
     * 创建bossGroup线程组: 处理网络事件--连接事件
     * 创建workerGroup线程组: 处理网络事件--读写事件
     * 创建服务端启动助手
     * 设置bossGroup线程组和workerGroup线程组
     * 设置服务端通道实现为NIO
     * 参数设置
     * 创建一个通道初始化对象
     * 向pipeline中添加自定义业务处理handler
     * 启动服务端并绑定端口,同时将异步改为同步
     * 关闭通道和关闭连接池
     */

    public static void main(String[] args) throws InterruptedException {
        //1.创建bossGroup线程组: 处理网络事件
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //2.创建workerGroup线程组: 处理网络事件--读写事件
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //3.创建服务端启动助手
        ServerBootstrap boot = new ServerBootstrap();
        try {
            //4.设置bossGroup、workerGroup线程组
            boot.group(bossGroup, workerGroup)
                    //5.设置服务端通道实现;
                    .channel(NioServerSocketChannel.class)
                    //6.参数设置-设置线程队列中等待连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE)
                    //7.创建一个通道初始化对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    //8、向pipeline中添加自定义业务处理handler
                                    .addLast(new NettyServerHandler());
                        }
                    });
            //9、启动服务端并绑定端口，同时将异步改为同步
            ChannelFuture future = boot.bind(9999).sync();
            //10、关闭通道(并不是真正意义上的关闭,而是监听通道关闭状态)和关闭连接池
            future.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }

}

