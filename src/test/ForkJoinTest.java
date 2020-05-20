package test;

import java.util.concurrent.RecursiveTask;

/**
 * @author wangql
 * @since 2020-05-15 16:53
 */
public class ForkJoinTest {

  class Task extends RecursiveTask<Integer> {

    private int start;

    private int end;
    private int mid;

    public Task(int start, int end) {
      this.start = start;
      this.end = end;
    }

    @Override
    protected Integer compute() {
      int sum = 0;
      if (end - start < 6) {
        // 当任务很小时，直接进行计算
        for (int i = start; i <= end; i++) {
          sum += i;
        }
        System.out.println(Thread.currentThread().getName() + " count sum: " + sum);
      } else {
        // 否则，将任务进行拆分
        mid = (end - start) / 2 + start;
        Task left = new Task(start, mid);
        Task right = new Task(mid + 1, end);

        // 执行上一步拆分的子任务
        left.fork();
        right.fork();

        // 拿到子任务的执行结果
        sum += left.join();
        sum += right.join();
      }

      return sum;
    }
  }

}
