package com.jonathan.springnettytest.endserver;

import com.jonathan.springnettytest.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SimpleEndServerHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        String src = msg.getSrc();
        String dst = msg.getDst();
        String newBody = msg.getBody() + " [REACHED ENDSERVER]";

        ctx.writeAndFlush(new Message(src, dst, newBody));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("E1AA");
    }

}
