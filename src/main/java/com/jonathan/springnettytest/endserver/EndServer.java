package com.jonathan.springnettytest.endserver;

import io.netty.bootstrap.ServerBootstrap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@RequiredArgsConstructor
@Component
public class EndServer {

    private final ServerBootstrap endServerBootstrap;
    private final InetSocketAddress endServerDownstreamPort;

    public void start(){
        try {
            endServerBootstrap.bind(endServerDownstreamPort).sync();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
