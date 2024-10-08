package com.aanassar.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 <a href="https://www.hackerrank.com/challenges/ctci-merge-sort/problem">...</a>

 "Given an array, return the number of inversions to sort the array."
 */
public class CountInversions {

    static long radixSort(int[] a, final int bit) {
        if (a.length < 2 || bit == 0)
            return 0;
        final int p = (int) Arrays.stream(a).filter(n -> (n & bit) == 0).count();
        ArrayList<Integer> lesser = new ArrayList<>(p);
        ArrayList<Integer> greater = new ArrayList<>(a.length - p);
        long inversions = 0L;
        for (int n : a) {
            if ((n & bit) == 0)
                lesser.add(n);
            else {
                greater.add(n);
                // The current integer needs to be "inverted" this many times,
                // i.e. moved past every value in "lesser".
                inversions += p - lesser.size();
            }
        }

        // Optimize per Sedgewick...
        return inversions +
                radixSort(lesser.stream().mapToInt(Integer::intValue).toArray(), bit >> 1) +
                radixSort(greater.stream().mapToInt(Integer::intValue).toArray(), bit >> 1);
    }

    public static long countInversions(int[] a) {
        if (a.length == 0)
            return 0;
        final int bit = Arrays.stream(a).map(Integer::highestOneBit).max().getAsInt();
        return radixSort(a, bit);
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
