//package test.gc;
//
//import java.util.concurrent.TimeoutException;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;
//
///**
// * @author wangql
// * @since 2020-04-07 17:59
// */
//
//
//public class Account {
//
//  private int balance;
//
//  private final Lock lock = new ReentrantLock();
//
//  // 转账
//  void transfer(Account tar, int amt){
//    while (true) {
//      if(this.lock.tryLock()) {
//        try {
//          if (tar.lock.tryLock()) {
//            try {
//              this.balance -= amt;
//              tar.balance += amt;
//            } finally {
//              tar.lock.unlock();
//            }
//          }
//        } finally {
//          this.lock.unlock();
//        }
//      }//if
//    }//while
//  }//transfer
//
//
//  // 创建锁与条件变量
//  private final Lock lock = new ReentrantLock();
//  private final Condition done = lock.newCondition();
//
//  // 调用方通过该方法等待结果
//  Object get(int timeout){
//    long start = System.nanoTime();
//    lock.lock();
//    try {
//      while (!isDone()) {
//        done.await(timeout);
//        long cur=System.nanoTime();
//        if (isDone() || cur-start > timeout){
//          break;
//        }
//      }
//    } finally {
//      lock.unlock();
//    }
//    if (!isDone()) {
//      throw new TimeoutException();
//    }
//    return returnFromResponse();
//  }
//  // RPC结果是否已经返回
//  boolean isDone() {
//    return response != null;
//  }
//  // RPC结果返回时调用该方法
//  private void doReceived(Response res) {
//    lock.lock();
//    try {
//      response = res;
//      if (done != null) {
//        done.signal();
//      }
//    } finally {
//      lock.unlock();
//    }
//  }
//}
