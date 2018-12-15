package jvm;

public class Demo {
    public static void main(String[] args) {
        System.out.println("MaxMemory " + Runtime.getRuntime().maxMemory() / ( 1024 * 1024) + "M");
        System.out.println("FreeMemory " + Runtime.getRuntime().freeMemory() / ( 1024 * 1024) + "M");
        System.out.println("AlreadyUsedMemory " + Runtime.getRuntime().totalMemory() / ( 1024 * 1024) + "M");
    }
}
