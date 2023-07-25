import java.io.IOException;
import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    int paramOne() ;

    int paramTwo();
}

class TestClass {
    @MyAnnotation(paramOne = 3, paramTwo = 4)
    public static void test(int paramOne, int paramTwo) {
        System.out.println("Param one = " + paramOne + ", param two = " + paramTwo);
        System.out.println("Sum = " + (paramOne + paramTwo));
    }
}


public class Main {
    public static void main(String[] args) {
        try {
            Class<?> cls = TestClass.class;
            Method method = cls.getMethod("test", int.class, int.class);
            if (method.isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation an = method.getAnnotation(MyAnnotation.class);
                TestClass obj = new TestClass();
                 System.out.println(an.paramOne());
                method.invoke(obj, an.paramOne(), an.paramTwo());
            }
            } catch(Exception e){
                throw new RuntimeException(e);
            }
    }

}
