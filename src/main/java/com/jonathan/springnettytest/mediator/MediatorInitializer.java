package com.jonathan.springnettytest.mediator;

import com.jonathan.springnettytest.model.Message;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

@Component
public class MediatorInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new LoggingHandler(LogLevel.INFO),
                new DelimiterBasedFrameDecoder(Message.MAX_LEN, Delimiters.lineDelimiter()),
                new FrameToMessageDecoder(),

                new MessageEncoder(),

                new SimpleMediatorHandler()
        );
    }
}