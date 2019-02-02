package basic.thread;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception{
        String value="Callable return value";
        System.out.println("Ready to work");
        Thread.currentThread().sleep(5000);
        System.out.println("finish work");
        return  value;
    }

}
