package basic.concurrency.core.threadcoreknowledge.startthread;

/**
 * 描述：     演示不能两次调用start方法，否则会报错
 * @author wangql
 */
public class CantStartTwice {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }







}
