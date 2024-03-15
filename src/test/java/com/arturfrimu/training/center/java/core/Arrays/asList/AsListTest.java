package com.arturfrimu.training.center.java.core.Arrays.asList;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsListTest {

    @Test
    void testAsListWithStrings() {
        // Demonstrates basic usage with String elements
        List<String> list = Arrays.asList("Element1", "Element2", "Element3");

        // Test size
        assertEquals(3, list.size());

        // Test content
        assertEquals("Element1", list.get(0));
        assertEquals("Element2", list.get(1));
        assertEquals("Element3", list.get(2));
    }

    @Test
    void testAsListWithIntegers() {
        // Demonstrates usage with Integer elements
        List<Integer> list = Arrays.asList(1, 2, 3);

        // Test size
        assertEquals(3, list.size());

        // Test content
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    void testAsListModification() {
        // Demonstrates modifying an element
        List<String> list = Arrays.asList("Element1", "Element2");
        list.set(1, "ModifiedElement2"); // Modify element

        // Test modified content
        assertEquals("ModifiedElement2", list.get(1));
    }

    @Test
    void testAsListFixedSized() {
        List<String> list = Arrays.asList("Element1", "Element2");

        // Attempting to add or remove elements throws UnsupportedOperationException
        assertThrows(UnsupportedOperationException.class, () -> list.add("Element3"));
        assertThrows(UnsupportedOperationException.class, () -> list.remove("Element1"));
    }

    @Test
    void testAsListWithPrimitiveArray() {
        // Demonstrates incompatibility with primitive array directly
        int[] primitiveArray = {1, 2, 3};
        List<int[]> list = Arrays.asList(primitiveArray);

        // List will treat the entire array as a single element
        assertEquals(1, list.size());
    }

    @Test
    void testAsListWithBoxedArray() {
        // Workaround for primitive array using boxed types
        Integer[] boxedArray = {1, 2, 3};
        List<Integer> list = Arrays.asList(boxedArray);

        // Now it works as expected
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
    }

    @Test
    void testAsListBackedByArray() {
        String[] array = {"Element1", "Element2"};
        List<String> list = Arrays.asList(array);

        // Modify the array
        array[0] = "ModifiedElement1";

        // Change is reflected in the list
        assertEquals("ModifiedElement1", list.get(0));
    }

    @Test
    void testAsListConversionToArrayList() {
        // Demonstrates converting to a real ArrayList for full functionality
        List<String> fixedList = Arrays.asList("Element1", "Element2");
        List<String> dynamicList = new ArrayList<>(fixedList);

        // Now you can add and remove elements
        dynamicList.add("Element3");
        dynamicList.remove("Element1");

        // Test modifications
        assertTrue(dynamicList.contains("Element3"));
        assertFalse(dynamicList.contains("Element1"));
    }
}
