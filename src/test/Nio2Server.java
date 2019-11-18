package test;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wangql
 * @since 2019/11/18 2:05 下午
 */
public class Nio2Server {

//    void listen(){
//        //1.创建一个线程池
//        ExecutorService es = Executors.newCachedThreadPool();
//
//        //2.创建异步通道群组
//        AsynchronousChannelGroup tg = AsynchronousChannelGroup.withCachedThreadPool(es, 1);
//
//        //3.创建服务端异步通道
//        AsynchronousServerSocketChannel assc = AsynchronousServerSocketChannel.open(tg);
//
//        //4.绑定监听端口
//        assc.bind(new InetSocketAddress(8080));
//
//        //5. 监听连接，传入回调类处理连接请求
//        assc.accept(this, new AcceptHandler());
//    }
}