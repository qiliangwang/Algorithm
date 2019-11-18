package test;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * AcceptHandler类实现了CompletionHandler接口的completed方法。
 * 它还有两个模板参数，第一个是异步通道，第二个就是Nio2Server本身
 * @author wangql
 * @since 2019/11/18 2:16 下午
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, Nio2Server> {
    @Override
    public void completed(AsynchronousSocketChannel result, Nio2Server attachment) {

    }

    @Override
    public void failed(Throwable exc, Nio2Server attachment) {

    }

    //具体处理连接请求的就是completed方法，它有两个参数：第一个是异步通道，第二个就是上面传入的NioServer对象
//    @Override
//    public void completed(AsynchronousSocketChannel asc, Nio2Server attachment) {
//        //调用accept方法继续接收其他客户端的请求
//        attachment.assc.accept(attachment, this);
//
//        //1. 先分配好Buffer，告诉内核，数据拷贝到哪里去
//        ByteBuffer buf = ByteBuffer.allocate(1024);
//
//        //2. 调用read函数读取数据，除了把buf作为参数传入，还传入读回调类
//        channel.read(buf, buf, new ReadHandler(asc));
//
    }
