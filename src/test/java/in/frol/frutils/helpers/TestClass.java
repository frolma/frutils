package in.frol.frutils.helpers;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

@SuppressWarnings("ALL")
public class TestClass {

    public void biConsumer(BiConsumer<TestA, TestB> biConsumer) {
        biConsumer.accept(null, null);
    }

    public void biFunction(BiFunction<TestA, TestB, TestB> biFunction) {
        biFunction.apply(null, null);
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

    public void voidUncheckedExceptionThrow() throws Exception {
        throw new RuntimeException();
    }

    public TestA withReturnUncheckedExceptionThrow() throws Exception {
        throw new RuntimeException();
    }

    public TestB withInputUncheckedExceptionThrow(TestA ignored) throws Exception {
        throw new RuntimeException();
    }

    public TestB with2InputUncheckedExceptionThrow(TestA ignored1, TestB ignored2) throws Exception {
        throw new RuntimeException();
    }

    public void voidWithInputUncheckedExceptionThrow(TestA ignored) throws Exception {
        throw new RuntimeException();
    }

    public void voidWith2InputUncheckedExceptionThrow(TestA ignored1, TestB ignored2) throws Exception {
        throw new RuntimeException();
    }
}
