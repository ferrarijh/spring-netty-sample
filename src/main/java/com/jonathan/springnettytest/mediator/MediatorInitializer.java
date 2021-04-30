package com.jonathan.springnettytest.mediator;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

import static com.jonathan.springnettytest.model.Message.MAX_LEN;

@Component
public class MediatorInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new LoggingHandler(LogLevel.INFO),
                new DelimiterBasedFrameDecoder(MAX_LEN, Delimiters.lineDelimiter()),
                new FrameToMessageDecoder(),

                new MessageEncoder(),

                new SimpleMediatorHandler()
        );
    }
}