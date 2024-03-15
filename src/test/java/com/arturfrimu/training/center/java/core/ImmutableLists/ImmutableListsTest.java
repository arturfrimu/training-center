package com.arturfrimu.training.center.java.core.ImmutableLists;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImmutableListsTest {
    @Test
    void testCollectionsUnmodifiableList() {
        ArrayList<String> elements = new ArrayList<>();
        elements.add("Element1");
        elements.add("Element2");

        List<String> list = Collections.unmodifiableList(elements);
        assertThrows(UnsupportedOperationException.class, () -> list.add("Element3"));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> list.set(1, "Element3")); // Collections.unmodifiableList DON ALLOWS element modification
    }

    @Test
    void testArraysAsList() {
        List<String> list = Arrays.asList("Element1", "Element2");
        assertDoesNotThrow(() -> list.set(1, "Modified"));              // Arrays.asList ALLOWS element modification
        assertThrows(UnsupportedOperationException.class, () -> list.add("Element3"));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
    }

    @Test
    void testListOf() {
        List<String> list = List.of("Element1", "Element2");
        assertThrows(UnsupportedOperationException.class, () -> list.add("Element3"));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> list.set(0, "Modified")); // ListOf DON ALLOWS element modification
    }

    @Test
    void testCollectionsSingletonList() {
        List<String> list = Collections.singletonList("SingleElement");
        assertThrows(UnsupportedOperationException.class, () -> list.add("Element2"));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
        assertThrows(UnsupportedOperationException.class, () -> list.set(0, "Modified")); // Collections.singletonList DON ALLOWS element modification
    }

    @Test
    void testCollectionsEmptyList() {
        List<String> list = Collections.emptyList();
        assertThrows(UnsupportedOperationException.class, () -> list.add("Element1"));
        assertThrows(UnsupportedOperationException.class, () -> list.remove(0));
    }
}
