package test.skip;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author wangql
 * @since 2021-05-06 19:31
 */
public class Test {

  private static final ConcurrentMap<String, ConcurrentMap<String, String>> zkListeners = new ConcurrentHashMap<>();

  public static void main(String[] args) {

    ConcurrentMap<String, String> listener = zkListeners.computeIfAbsent("1", k -> {
      System.out.println(k);
      return new ConcurrentHashMap<>();
    });

    listener.computeIfAbsent("1", k -> "1");

  }
}
