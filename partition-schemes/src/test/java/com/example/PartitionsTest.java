package com.example;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PartitionsTest {

    private static final int[] SORTED = {10, 17, 19, 21, 44, 55, 57, 63, 65, 67};
    private static final int[] EMPTY = {};
    private static final int[] UNSORTED = {84, 3, 7, 1, 9, 6, 2, 5};

    @Test
    public void lomuto_sortedArray() {
        int[] a = SORTED.clone();
        int[] before = SORTED.clone();

        int p = Partitions.lomutoPartition(a, 0, a.length - 1);
        assertEquals(a.length - 1, p);

        assertLomutoProperty(a, 0, a.length - 1, p);
        assertSameMultiset(before, a);
    }

    @Test
    public void lomuto_emptyArray() {
        int[] a = EMPTY.clone();
        int p = Partitions.lomutoPartition(a, 0, -1);
        assertEquals(-1, p);
        assertArrayEquals(EMPTY, a);
    }

    @Test
    public void lomuto_unsortedArray() {
        int[] a = UNSORTED.clone();
        int[] before = UNSORTED.clone();

        int p = Partitions.lomutoPartition(a, 0, a.length - 1);
        assertTrue(p >= 0 && p < a.length);

        assertLomutoProperty(a, 0, a.length - 1, p);
        assertSameMultiset(before, a);
    }

    @Test
    public void hoare_sortedArray() {
        int[] a = SORTED.clone();
        int[] before = SORTED.clone();

        int j = Partitions.hoarePartition(a, 0, a.length - 1);
        assertTrue(j >= 0 && j < a.length);

        int pivot = before[(0 + (before.length - 1)) / 2];
        assertHoareProperty(a, 0, a.length - 1, j, pivot);
        assertSameMultiset(before, a);
    }

    @Test
    public void hoare_emptyArray() {
        int[] a = EMPTY.clone();
        int j = Partitions.hoarePartition(a, 0, -1);
        assertEquals(-1, j);
        assertArrayEquals(EMPTY, a);
    }

    @Test
public void hoare_unsortedArray() {
    int[] a = UNSORTED.clone();
    int[] before = UNSORTED.clone();

    int low = 0;
    int high = a.length - 1;

    int j = Partitions.hoarePartition(a, low, high);
    assertTrue(j >= low && j < high);

    int pivot = before[low + (high - low) / 2];

    for (int i = low; i <= j; i++) assertTrue(a[i] <= pivot);
    for (int i = j + 1; i <= high; i++) assertTrue(a[i] >= pivot);

    assertSameMultiset(before, a);
}

    private static void assertLomutoProperty(int[] a, int low, int high, int p) {
        int pivot = a[p];
        for (int i = low; i < p; i++) assertTrue(a[i] <= pivot);
        for (int i = p + 1; i <= high; i++) assertTrue(a[i] > pivot);
    }

    private static void assertHoareProperty(int[] a, int low, int high, int j, int pivot) {
        for (int i = low; i <= j; i++) assertTrue(a[i] <= pivot);
        for (int i = j + 1; i <= high; i++) assertTrue(a[i] >= pivot);
    }

    private static void assertSameMultiset(int[] before, int[] after) {
        int[] b1 = before.clone();
        int[] b2 = after.clone();
        Arrays.sort(b1);
        Arrays.sort(b2);
        assertArrayEquals(b1, b2);
    }
}
