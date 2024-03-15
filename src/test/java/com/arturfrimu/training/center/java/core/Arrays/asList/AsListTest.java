package com.arturfrimu.training.center.java.core.Arrays.asList;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AsListTest {

    /**
     *  Demonstrates basic usage with String elements
     */
    @Test
    void testAsListWithStrings() {
        List<String> list = Arrays.asList("Element1", "Element2", "Element3"); // using var args

        assertEquals(3, list.size());

        assertEquals("Element1", list.get(0));
        assertEquals("Element2", list.get(1));
        assertEquals("Element3", list.get(2));
    }

    /**
     *  Demonstrates usage with Integer elements
     */
    @Test
    void testAsListWithIntegers() {
        List<Integer> list = Arrays.asList(1, 2, 3);  // using var args

        assertEquals(3, list.size());

        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    /**
     *  Demonstrates modifying an element
     */
    @Test
    void testAsListModification() {
        List<String> list = Arrays.asList("Element1", "Element2");

        list.set(1, "ModifiedElement2"); // Modify element

        // Test modified content
        assertEquals("ModifiedElement2", list.get(1));
    }

    /**
     * Attempting to add or remove elements throws UnsupportedOperationException
     */
    @Test
    void testAsListFixedSized() {
        List<String> list = Arrays.asList("Element1", "Element2");

        assertThrows(UnsupportedOperationException.class, () -> list.add("Element3"));
        assertThrows(UnsupportedOperationException.class, () -> list.remove("Element1"));
    }

    /**
     * Demonstrates incompatibility with primitive array directly <br>
     * List will treat the entire array as a single element
     */
    @Test
    void testAsListWithPrimitiveArray() {
        int[] primitiveArray = {1, 2, 3};
        List<int[]> list = Arrays.asList(primitiveArray);                                                                               //  <  This is a bad idea   >

        assertEquals(1, Arrays.asList(primitiveArray).size());                                              //  <   1 instead of 3  >
        assertNotEquals(3, list.size());
    }

    /**
     * Workaround for primitive array using boxed types
     */
    @Test
    void testAsListWithBoxedArray() {
        Integer[] boxedArray = {1, 2, 3};
        List<Integer> list = Arrays.asList(boxedArray);

        // Now it works as expected
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(1, list.get(0));
    }

    /**
     * Modify the array <br>
     * Change is reflected in the list
     */
    @Test
    void testAsListBackedByArray() {
        String[] array = {"Element1", "Element2"};
        List<String> list = Arrays.asList(array);

        array[0] = "ModifiedElement1";

        assertEquals("ModifiedElement1", list.get(0));
    }

    /**
     * Demonstrates converting to a real ArrayList for full functionality
     */
    @Test
    void testAsListConversionToArrayList() {
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
