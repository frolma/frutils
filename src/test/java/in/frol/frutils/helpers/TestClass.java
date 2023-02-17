package in.frol.frutils.helpers;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@SuppressWarnings("ALL")
public class TestClass {

    public void biConsumer(BiConsumer<TestA, TestB> ignored) {
        /* do nothing */
    }

    public void biFunction(BiFunction<TestA, TestB, TestB> ignored) {
        /* do nothing */
    }

    public void voidUncheckedException() throws Exception {
        /* do nothing */
    }

    public TestA withReturnUncheckedException() throws Exception {
        return new TestA();
    }

    public TestB withInputUncheckedException(TestA ignored) throws Exception {
        return new TestB();
    }

    public TestB with2InputUncheckedException(TestA ignored1, TestB ignored2) throws Exception {
        return new TestB();
    }

    public void voidWithInputUncheckedException(TestA ignored) throws Exception {
        /* do nothing */
    }

    public void voidWith2InputUncheckedException(TestA ignored1, TestB ignored2) throws Exception {
        /* do nothing */
    }
}
