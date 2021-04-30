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

    private final ServerBootstrap mediatorDownstreamBootstrap;

    private final Bootstrap mediatorUpstreamBootstrap;

    private final InetSocketAddress downstreamPort;

    public void start(){
        try {
//            mediatorUpstreamBootstrap.connect("localhost", 8081).sync();
//            mediatorUpstreamBootstrap.connect("localhost", 8082).sync();
            mediatorDownstreamBootstrap.bind(downstreamPort).sync();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
