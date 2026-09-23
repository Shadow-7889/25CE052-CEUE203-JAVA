import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class SampleTests {
    @Run
    public void firstTest() {
        System.out.println("firstTest ran");
    }

    @Run
    public void secondTest() {
        System.out.println("secondTest ran");
    }

    public void helperMethod() {
        System.out.println("helperMethod ran");
    }

    @Run
    public void testWithArgument(String value) {
        System.out.println(value);
    }
}

public class MiniTest {
    public static int runTests(Class<?> testClass) {
        int ran = 0;

        try {
            Object testInstance = testClass.getDeclaredConstructor().newInstance();

            for (Method method : testClass.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(Run.class)) {
                    continue;
                }

                if (method.getParameterCount() != 0) {
                    System.out.println("Skipping " + method.getName()
                            + ": @Run methods must have no arguments");
                    continue;
                }

                method.setAccessible(true);

                try {
                    method.invoke(testInstance);
                    ran++;
                } catch (InvocationTargetException e) {
                    Throwable cause = e.getCause();
                    System.out.println("Test failed: " + method.getName()
                            + " - " + cause);
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                    "Could not create test class: " + testClass.getName(), e);
        }

        return ran;
    }

    public static void main(String[] args) {
        int count = runTests(SampleTests.class);
        System.out.println("Ran " + count + " test(s)");
    }
}
