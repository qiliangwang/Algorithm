package test;

import test.gc.GiftNew;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {

      Collections.emptyList().stream().collect(Collectors.toList());


//      Stream.of("one", "two", "three", "four")
//                       .filter(e -> e.length() > 3)
//                       .peek(e -> System.out.println("Filtered value: " + e))
//                       .map(String::toUpperCase)
//                       .peek(e -> System.out.println("Mapped value: " + e))
//                       .collect(Collectors.toList());
    }
}
