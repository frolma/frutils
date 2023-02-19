package in.frol.frutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Utility class for simple operations with Collections/Maps without
 * creating additional objects and improving code readability
 */
public final class Collections {

    private Collections() {
        /* empty body */
    }

    /** Check that the Collection doesn't contain any items or null */
    public static boolean isEmpty(final Collection<?> collection) {
        return collection == null
                || collection.isEmpty();
    }

    /** Check that the Collection contains any items and not null */
    public static boolean notEmpty(final Collection<?> collection) {
        return !isEmpty(collection);
    }

    /** Check that the Map doesn't contain any entries or null */
    public static boolean isEmpty(final Map<?, ?> map) {
        return map == null
                || map.isEmpty();
    }

    /** Check that the Map contains any entries and not null */
    public static boolean notEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <T> List<List<T>> toChunks(final List<T> list, final int chunkSize) {
        if (list == null) {
            return null;
        }
        if (list.isEmpty()) {
            return List.of();
        }
        final List<List<T>> chunks = new ArrayList<>();
        int fromIndex = 0;
        while (fromIndex < list.size()) {
            int toIndex = Math.min(fromIndex + chunkSize, list.size());
            chunks.add(list.subList(fromIndex, toIndex));
            fromIndex += chunkSize;
        }
        return chunks;
    }
}
