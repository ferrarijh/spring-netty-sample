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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.net.InetSocketAddress;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(MediatorConfigProperties.class)
public class MediatorConfig {

    private final MediatorConfigProperties mediatorConfigProperties;

    //TODO("self dependency cycle - don't do this")
//    @Qualifier("mediatorBossGroup")
//    private final NioEventLoopGroup bossGroup;
//
//    @Qualifier("mediatorWorkerGroup")
//    private final NioEventLoopGroup workerGroup;

    @Primary
    @Bean(name="mediatorDownstreamBootstrap")
    public ServerBootstrap mediatorDownstreamBootstrap(MediatorInitializer mInitializer){
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup(), workerGroup())
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel.class)
                .childHandler(mInitializer);
        return sb;
    }

    @Bean(name="mediatorUpstreamBootstrap")
    public Bootstrap mediatorDownstreamBoostrap(){
        Bootstrap b = new Bootstrap();
        b.group(workerGroup())
                .channel(NioSocketChannel.class)
                .handler(new MediatorInitializer());
        return b;
    }

    @Bean
    public InetSocketAddress inetSocketAddress(){
        return new InetSocketAddress(mediatorConfigProperties.getMediatorDownstreamPort());
    }

    @Primary
    @Bean(name="mediatorBossGroup")
    public NioEventLoopGroup bossGroup(){
        return new NioEventLoopGroup(mediatorConfigProperties.getBossCount());
    }

    @Bean(name="mediatorWorkerGroup")
    public NioEventLoopGroup workerGroup(){
        return new NioEventLoopGroup(mediatorConfigProperties.getWorkerCount());
    }
}
