package com.aanassar.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class CountInversions {

    private final static Random random = new Random();

    // See https://en.wikipedia.org/wiki/Quicksort#Repeated_elements.
    static long quickSort(int[] a, final int from, final int to) {
        if (to - from < 2)
            return 0;

        long swaps = 0L;
        long inversions = 0L;
        final int pivotIndex = random.nextInt(to - from) + from;
        final int pivot = a[pivotIndex];
        int l = from;
        int r = to - 1;
        while (l < r) {
            if (a[l] <= pivot) {
                // inversions -= swaps;
                ++l;
            } else if (a[r] > pivot) {
                // inversions -= swaps;
                --r;
            } else {
                final int tmp = a[l];
                a[l] = a[r];
                a[r] = tmp;
                inversions -= swaps;
                ++swaps;
                inversions += r - l;

                ++l;
                --r;
            }
        }
        // Optimize per Sedgewick...
        return inversions + quickSort(a, 0, l) + quickSort(a, l, to);
    }

    public static long countInversions(int[] a) {
        return quickSort(a, 0, a.length);
    }

    public static long countInversions(String input) {
        String[] s = input.split(" ");
        int[]  a = Arrays.stream(s).parallel().mapToInt(Integer::parseInt).toArray();
        return countInversions(a);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int problems = Integer.parseInt(reader.readLine());
            for (int i = 0; i < problems; ++i) {
                reader.readLine();
                final String input = reader.readLine();
                final long output = countInversions(input);
                System.out.println("" + output);
            }
        }
    }

}
