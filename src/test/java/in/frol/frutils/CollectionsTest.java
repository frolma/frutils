package in.frol.frutils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Collections Utils:")
class CollectionsTest {

    private static final Integer START_INCLUSIVE = 1;
    private static final Integer END_EXCLUSIVE = 90;
    private static final Integer END_INCLUSIVE = END_EXCLUSIVE - 1;

    private static final List<Integer> INT_LIST = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE).boxed().toList();
    private static final List<String> STRING_LIST = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE).mapToObj(Integer::toString).toList();
    private static final Set<Integer> INT_SET = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE).boxed().collect(Collectors.toSet());
    private static final Set<String> STRING_SET = IntStream.range(START_INCLUSIVE, END_EXCLUSIVE).mapToObj(Integer::toString)
            .collect(toCollection(LinkedHashSet::new));

    @Test
    @SuppressWarnings("ALL")
    void isEmptyCollection() {
        final Collection<?> nullList = null;
        final Collection<?> empty = new ArrayList<>();
        final Collection<?> listOf = List.of();
        final Collection<?> nonEmptyList = List.of(new Object());
        assertTrue(Collections.hasNoItems(nullList));
        assertTrue(Collections.hasNoItems(empty));
        assertTrue(Collections.hasNoItems(listOf));
        assertFalse(Collections.hasNoItems(nonEmptyList));
    }

    @Test
    @SuppressWarnings("ALL")
    void notEmptyCollection() {
        final Collection<?> nullList = null;
        final Collection<?> empty = new ArrayList<>();
        final Collection<?> listOf = List.of();
        final Collection<?> nonEmptyList = List.of(new Object());
        assertFalse(Collections.hasItems(nullList));
        assertFalse(Collections.hasItems(empty));
        assertFalse(Collections.hasItems(listOf));
        assertTrue(Collections.hasItems(nonEmptyList));
    }

    @Test
    @SuppressWarnings("ALL")
    void isEmptyMap() {
        final Map<?, ?> nullMap = null;
        final Map<?, ?> empty = new HashMap<>();
        final Map<?, ?> mapOf = new HashMap<>();
        final Map<String, Object> nonEmptyMap = Map.of("key", new Object());
        assertTrue(Collections.hasNoItems(nullMap));
        assertTrue(Collections.hasNoItems(empty));
        assertTrue(Collections.hasNoItems(mapOf));
        assertFalse(Collections.hasNoItems(nonEmptyMap));
    }

    @Test
    @SuppressWarnings("ALL")
    void notEmptyMap() {
        final Map<?, ?> nullMap = null;
        final Map<?, ?> empty = new HashMap<>();
        final Map<?, ?> mapOf = new HashMap<>();
        final Map<String, Object> nonEmptyMap = Map.of("key", new Object());
        assertFalse(Collections.hasItems(nullMap));
        assertFalse(Collections.hasItems(empty));
        assertFalse(Collections.hasItems(mapOf));
        assertTrue(Collections.hasItems(nonEmptyMap));
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

    @Test
    @SuppressWarnings("ALL")
    void firstItemFromSet() {
        Set set = null;
        assertNull(Collections.firstItem(set));
        assertNull(Collections.firstItem(Set.of()));
        assertEquals(START_INCLUSIVE.toString(), Collections.firstItem(STRING_SET));
        assertEquals(START_INCLUSIVE, Collections.firstItem(INT_SET));
    }

    @Test
    @SuppressWarnings("ALL")
    void firstItemFromList() {
        final List list = null;
        assertNull(Collections.firstItem(list));
        assertNull(Collections.firstItem(List.of()));
        assertEquals(START_INCLUSIVE.toString(), Collections.firstItem(STRING_LIST));
        assertEquals(START_INCLUSIVE, Collections.firstItem(INT_LIST));
    }

    @Test
    @SuppressWarnings("ALL")
    void lastItemFromSet() {
        final Set set = null;
        assertNull(Collections.lastItem(set));
        assertNull(Collections.lastItem(Set.of()));
        assertEquals(END_INCLUSIVE.toString(), Collections.lastItem(STRING_SET));
        assertEquals(END_INCLUSIVE, Collections.lastItem(INT_SET));
    }

    @Test
    @SuppressWarnings("ALL")
    void lastItemFromList() {
        List list = null;
        assertNull(Collections.lastItem(list));
        assertNull(Collections.lastItem(List.of()));
        assertEquals(END_INCLUSIVE.toString(), Collections.lastItem(STRING_LIST));
        assertEquals(END_INCLUSIVE, Collections.lastItem(INT_LIST));
    }

    @Test
    @SuppressWarnings("ALL")
    void hasOneItem() {
        List list = null;
        assertFalse(Collections.hasOneItem(list));
        assertFalse(Collections.hasOneItem(List.of()));
        assertTrue(Collections.hasOneItem(List.of(0)));
        assertFalse(Collections.hasOneItem(List.of(0, 0)));
        assertFalse(Collections.hasOneItem(List.of(" ", " ")));
    }

    @Test
    @SuppressWarnings("ALL")
    void hasUniqueItem() {
//        List list = null;
//        assertFalse(Collections.hasUniqueItem(list));
//        assertFalse(Collections.hasUniqueItem(List.of()));
//        assertTrue(Collections.hasUniqueItem(List.of(0)));
        assertTrue(Collections.hasUniqueItem(List.of(0, 0)));
    }
}
