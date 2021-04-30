package com.jonathan.springnettytest.mediator;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@RequiredArgsConstructor
@Component
public class MediatorServer {

    @Qualifier("mediatorDownstreamBootstrap")
    private final ServerBootstrap mediatorDownstreamBootstrap;

    @Qualifier("mediatorUpstreamBootstrap")
    private final Bootstrap mediatorUpstreamBoostrap;

    private final InetSocketAddress downstreamPort;

    public void start(){
        try {
            mediatorDownstreamBootstrap.bind(downstreamPort).sync();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
