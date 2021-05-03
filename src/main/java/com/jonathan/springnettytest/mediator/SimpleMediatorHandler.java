package com.jonathan.springnettytest.mediator;

import com.jonathan.springnettytest.model.Message;
import com.jonathan.springnettytest.repository.ChannelRepository;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class SimpleMediatorHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        msg.show();

        String src = msg.getSrc();
        String svc = msg.getSvc();
        String dst = msg.getDst();
        if(dst.equals("AA"))
            ChannelRepository.put(src, ctx.channel());
        else if('0' <= dst.charAt(1) && dst.charAt(1) <= '9'){
            if ('a'<=svc.charAt(0) && svc.charAt(0) <= 'z'){   //lowercase indicates it visited EndServer.
                Channel dstChannel = ChannelRepository.get(dst);
                dstChannel.writeAndFlush(msg);
            }else if ('A'<=svc.charAt(0) && svc.charAt(0) <= 'Z'){
                Channel svcChannel = ChannelRepository.get(svc);
                svcChannel.writeAndFlush(msg);
            }
        }
    }
}
