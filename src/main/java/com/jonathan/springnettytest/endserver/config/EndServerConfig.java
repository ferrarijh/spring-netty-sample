package com.jonathan.springnettytest.endserver.config;

import com.jonathan.springnettytest.endserver.EndServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.InetSocketAddress;

@Configuration
public class EndServerConfig {


//    @Bean
//    public ServerBootstrap endServerBootstrap(ChannelInitializer channelInitializer){
//        ServerBootstrap sb = new ServerBootstrap();
//        sb.childHandler(channelInitializer)
//    }
}
