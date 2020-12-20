package io.gen.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.*;

public class ChannelEx {

    public static void main(String[] args) throws Exception{
        String path = "/home/gen/work/大数据.xmind";
        channelTest(path);
    }
    public static void channelTest(String path)throws Exception{
        RandomAccessFile accessFile = new RandomAccessFile(path, "rw");
        FileChannel inChannel = accessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(4800);
        int bytesRead = inChannel.read(buffer);

        ByteBuffer header = ByteBuffer.allocate(120);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = {header, body};
        inChannel.read(byteBuffers);

        inChannel.write(byteBuffers);
        while (bytesRead != -1){
            System.out.println("Read" + bytesRead);
            //switches a Buffer from writing mode to reading mode.
//            buffer.flip();
////            while (buffer.hasRemaining()){
////                System.out.println((char) buffer.get());
////            }
            buffer.clear();
            bytesRead = inChannel.read(buffer);
        }
        accessFile.close();
    }
    public void  test() throws  Exception{

        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromFileChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel toChannel = fromFile.getChannel();

        long position = 0;
        long count = fromFile.length();
        toChannel.transferFrom(fromFileChannel, position, count);

    }

    public void test2(){
//        Selector selector = Selector.open();
//        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
//        FileChannel channel = fromFile.getChannel();
//        SelectionKey key =
    }
}
