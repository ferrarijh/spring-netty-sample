package com.jonathan.springnettytest.mediator.config;

import com.jonathan.springnettytest.mediator.MediatorInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Configuration
@EnableConfigurationProperties(MediatorConfigProperties.class)
@RequiredArgsConstructor
public class MediatorConfig {

    private final MediatorConfigProperties mediatorConfigProperties;

    //TODO("self dependency cycle - don't do this")
//    @Qualifier("mediatorBossGroup")
//    private final NioEventLoopGroup bossGroup;
//
//    @Qualifier("mediatorWorkerGroup")
//    private final NioEventLoopGroup workerGroup;

    @Bean
    public ServerBootstrap mediatorDownstreamBootstrap(MediatorInitializer mInitializer,
                                                       @Qualifier("mediatorBossGroup") NioEventLoopGroup bossGroup,
                                                       @Qualifier("mediatorWorkerGroup") NioEventLoopGroup workerGroup
                                                       ){
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel.class)
                .childHandler(mInitializer);
        return sb;
    }

    @Bean
    public Bootstrap mediatorDownstreamBoostrap(
            @Qualifier("mediatorWorkerGroup") NioEventLoopGroup workerGroup){
        Bootstrap b = new Bootstrap();
        b.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new MediatorInitializer());
        return b;
    }

    @Bean
    public InetSocketAddress inetSocketAddress(){
        return new InetSocketAddress(mediatorConfigProperties.getMediatorDownstreamPort());
    }

    @Configuration
    @RequiredArgsConstructor
    @EnableConfigurationProperties(MediatorConfigProperties.class)
    class MediatorLoopGroup{

        @Primary
        @Bean(name="mediatorBossGroup")
        public NioEventLoopGroup bossGroup(){
            int bs = mediatorConfigProperties.getBossCount();
            System.out.println(bs);
            return new NioEventLoopGroup(bs);
        }

        @Bean(name="mediatorWorkerGroup")
        public NioEventLoopGroup workerGroup(){
            return new NioEventLoopGroup(mediatorConfigProperties.getWorkerCount());
        }
    }
}
