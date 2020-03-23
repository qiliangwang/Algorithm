package test;

import java.lang.reflect.Method;

import java.lang.reflect.Type;

/**
 * @author wangql
 * @since 2020-03-04 14:23
 */
public class MethodDemo {

    public static void main(String[] args) {
        Method[] methods = SampleClass.class.getMethods();
        for (Method method: methods) {
            Type[] parameters = method.getGenericParameterTypes();
            for (int i = 0; i < parameters.length; i++) {
                System.out.println(parameters[i]);
            }
        }
    }
}

class SampleClass {
    private String sampleField;

    public String getSampleField() {
        return sampleField;
    }

    public void setSampleField(String sampleField) {
        this.sampleField = sampleField;
    }
}

