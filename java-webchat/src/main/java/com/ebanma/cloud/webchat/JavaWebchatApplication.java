package com.ebanma.cloud.webchat;

import com.ebanma.cloud.webchat.config.NettyWebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class JavaWebchatApplication implements CommandLineRunner {
    @Autowired
    NettyWebSocketServer nettyWebSocketServer;
    public static void main(String[] args) {
        SpringApplication.run(JavaWebchatApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        new Thread(nettyWebSocketServer).start();
    }
}
