package in.frol.frutils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Collections Utils:")
class CollectionsTest {

    private static final List<Integer> INT_LIST = IntStream.range(1, 90).boxed().toList();
    private static final List<String> STRING_LIST = IntStream.range(1, 90).mapToObj(Integer::toString).toList();

    @Test
    @SuppressWarnings("ALL")
    void isEmptyCollection() {
        final Collection<?> nullList = null;
        final Collection<?> empty = new ArrayList<>();
        final Collection<?> listOf = List.of();
        final Collection<?> nonEmptyList = List.of(new Object());
        assertTrue(Collections.isEmpty(nullList));
        assertTrue(Collections.isEmpty(empty));
        assertTrue(Collections.isEmpty(listOf));
        assertFalse(Collections.isEmpty(nonEmptyList));
    }

    @Test
    @SuppressWarnings("ALL")
    void notEmptyCollection() {
        final Collection<?> nullList = null;
        final Collection<?> empty = new ArrayList<>();
        final Collection<?> listOf = List.of();
        final Collection<?> nonEmptyList = List.of(new Object());
        assertFalse(Collections.notEmpty(nullList));
        assertFalse(Collections.notEmpty(empty));
        assertFalse(Collections.notEmpty(listOf));
        assertTrue(Collections.notEmpty(nonEmptyList));
    }

    @Test
    @SuppressWarnings("ALL")
    void isEmptyMap() {
        final Map<?, ?> nullMap = null;
        final Map<?, ?> empty = new HashMap<>();
        final Map<?, ?> mapOf = new HashMap<>();
        final Map<String, Object> nonEmptyMap = Map.of("key", new Object());
        assertTrue(Collections.isEmpty(nullMap));
        assertTrue(Collections.isEmpty(empty));
        assertTrue(Collections.isEmpty(mapOf));
        assertFalse(Collections.isEmpty(nonEmptyMap));
    }

    @Test
    @SuppressWarnings("ALL")
    void notEmptyMap() {
        final Map<?, ?> nullMap = null;
        final Map<?, ?> empty = new HashMap<>();
        final Map<?, ?> mapOf = new HashMap<>();
        final Map<String, Object> nonEmptyMap = Map.of("key", new Object());
        assertFalse(Collections.notEmpty(nullMap));
        assertFalse(Collections.notEmpty(empty));
        assertFalse(Collections.notEmpty(mapOf));
        assertTrue(Collections.notEmpty(nonEmptyMap));
    }

    @Test
    void toChunksInt() {
        var chunks = Collections.toChunks(INT_LIST, 10);
        assertEquals(9, chunks.size());
        assertEquals(INT_LIST.subList(0, 10), chunks.get(0));
    }

    @Test
    void toChunksString() {
        var chunks = Collections.toChunks(STRING_LIST, 10);
        assertEquals(9, chunks.size());
        assertEquals(STRING_LIST.subList(0, 10), chunks.get(0));
    }
}
