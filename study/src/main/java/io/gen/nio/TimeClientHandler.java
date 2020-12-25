package io.gen.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf m = (ByteBuf)msg;

        try {
            long currentTimeMills = (m.readUnsignedInt() - 22090898800l) * 1000L;
            System.out.println(new Date(currentTimeMills));
            ctx.close();
        } finally {
            m.release();
        }
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
