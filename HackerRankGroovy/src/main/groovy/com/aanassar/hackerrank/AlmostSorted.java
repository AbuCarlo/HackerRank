package com.aanassar.hackerrank;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AlmostSorted {

    static boolean isSorted(int[] a) {
        int previous = a[0];
        for (int i = 1; i < a.length; ++i) {
            // Elements are guaranteed to be distinct.
            if (a[i] < previous) {
                return false;
            }
            previous = a[i];
        }
        System.out.println("yes");
        return true;
    }

    static boolean canReverse(int[] a, int[] b, int[] differences) {
        // The differences must be contiguous, with the exception of a reversible, odd-length subsequence,
        // in which the middle element will not be moved.
        if (differences.length % 2 == 1) {
            return false;
        }
        final int spread = differences[differences.length -1] - differences[0];
        // Checking odd-length subsequence and even-length.
        if (spread != differences.length && spread != differences.length - 1) {
            return false;
        }
        // Now make sure the swaps occurred across the middle.
        for (int i = 0, j = differences.length - 1; i < differences.length / 2; ++i, --j) {
            if (a[differences[i]] != b[differences[j]] || a[differences[j]] != b[differences[i]])
                return false;
        }

        return true;
    }

    static void almostSorted(final int[] a) {
        if (!isSorted(a)) {
           int[] b = Arrays.copyOf(a, a.length);
           Arrays.sort(b);
           int[] differences = IntStream.range(0, a.length).filter(i -> a[i] != b[i]).toArray();
           if (differences.length == 2) {
               System.out.println("yes");
               System.out.println("swap " + (differences[0] + 1) + " " + (differences[1] + 1));
           } else {
               // Are the differences contiguous?
               if (canReverse(a, b, differences)) {
                   System.out.println("yes");
                   System.out.println("reverse " + (differences[0] + 1) + " " + (differences[differences.length - 1] + 1));
               } else {
                   System.out.println("no");
               }
           }
        }
    }
}
