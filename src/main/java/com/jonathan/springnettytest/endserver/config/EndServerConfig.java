package com.jonathan.springnettytest.endserver.config;

import com.jonathan.springnettytest.endserver.EndServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.InetSocketAddress;

@Configuration
@EnableConfigurationProperties(EndServerConfigProperties.class)
@RequiredArgsConstructor
public class EndServerConfig {

    private final EndServerConfigProperties properties;

    @Bean
    public ServerBootstrap endServerBootstrap(EndServerInitializer channelInitializer,
                                              @Qualifier("endServerBossGroup") EventLoopGroup bossGroup,
                                              @Qualifier("endServerWorkerGroup") EventLoopGroup workerGroup
                                              ){
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(channelInitializer);
        return sb;
    }

    @Bean
    public InetSocketAddress inetSocketAddress(){
        return new InetSocketAddress(properties.getEndServerPort1());
    }

    @Configuration
    class EndServerLoopGroup{
        @Bean(name="endServerBossGroup")
        public EventLoopGroup bossGroup(){
            return new NioEventLoopGroup(properties.getBossCount());
        }
        @Bean(name="endServerWorkerGroup")
        public EventLoopGroup workerGroup(){
            return new NioEventLoopGroup(properties.getWorkerCount());
        }
    }
}
