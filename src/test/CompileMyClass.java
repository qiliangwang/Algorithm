package test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CompileMyClass {

  public static void main(String[] args) {

    List<Integer> ids = Arrays.asList(1, 2, 3, 4, 5);
    List<String> idStrings = ids.stream().map(String::valueOf).collect(Collectors.toList());
//    System.out.println(ids);
    System.out.println(idStrings);


//    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//    int result = compiler.run(null, null, null, "/Users/mac/Desktop/wangql/Algorithm/src/test/MyClass.java");
//    System.out.println("Compile result code = " + result);
  }
}