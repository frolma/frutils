package in.frol.frutils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/**
 * Utility class for simple operations with Collections/Maps without
 * creating additional objects and improving code readability
 */
public final class Collections {

    private Collections() {
        /* empty body */
    }

    /** Check that the Collection doesn't contain any items or null */
    public static boolean hasNoItems(final Collection<?> collection) {
        return collection == null
                || collection.isEmpty();
    }

    /** Check that the Collection contains any items and not null */
    public static boolean hasItems(final Collection<?> collection) {
        return !hasNoItems(collection);
    }

    /** Check that the Map doesn't contain any entries or null */
    public static boolean hasNoItems(final Map<?, ?> map) {
        return map == null
                || map.isEmpty();
    }

    /** Check that the Map contains any entries and not null */
    public static boolean hasItems(final Map<?, ?> map) {
        return !hasNoItems(map);
    }

    /** Get first item from set or null */
    public static <T> T firstItem(final Set<T> set) {
        if (hasNoItems(set)) {
            return null;
        }
        if (set instanceof SortedSet) {
            return ((SortedSet<T>) set).first();
        }
        final Iterator<T> iterator = set.iterator();
        T first = null;
        if (iterator.hasNext()) {
            first = iterator.next();
        }
        return first;
    }

    /** Get first item from list or null */
    public static <T> T firstItem(final List<T> list) {
        if (hasNoItems(list)) {
            return null;
        }
        return list.get(0);
    }

    /** Get last item from set or null */
    public static <T> T lastItem(final Set<T> set) {
        if (hasNoItems(set)) {
            return null;
        }
        if (set instanceof SortedSet) {
            return ((SortedSet<T>) set).last();
        }
        T lastItem = null;
        for (T item : set) {
            lastItem = item;
        }
        return lastItem;
    }

    /** Get last item from list or null */
    public static <T> T lastItem(final List<T> list) {
        if (hasNoItems(list)) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    /** Split the list into lists of a certain size */
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
