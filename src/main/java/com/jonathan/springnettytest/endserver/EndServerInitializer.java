package com.jonathan.springnettytest.endserver;

import com.jonathan.springnettytest.mediator.FrameToMessageDecoder;
import com.jonathan.springnettytest.mediator.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

import static com.jonathan.springnettytest.model.Message.MAX_LEN;

@Component
public class EndServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast(
                new LoggingHandler(LogLevel.INFO),
                new DelimiterBasedFrameDecoder(MAX_LEN, Delimiters.lineDelimiter()),
                new FrameToMessageDecoder(),

                new MessageEncoder(),

                new SimpleEndServerHandler()
        );
    }
}
