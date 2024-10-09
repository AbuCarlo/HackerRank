package com.aanassar.hackerrank;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TestTripleSum {

    /**
     * From <a href="https://www.hackerrank.com/challenges/triple-sum/problem">...</a>
     *
     * @param l an array providing the first value of every pair
     * @param r an array from which to select values <= the previous
     * @return a map from firsts to # of candidate seconds
     */
    static long[] countCandidates(int[] l, int[] r) {
        long[] result = new long[l.length];
        int from = 0;
        for (int i = 0; i < l.length; ++i) {
            final int q = l[i];
            // println "Checking q = $q"
            final int insertion = Arrays.binarySearch(r, from, r.length, q);
            from = insertion < 0 ? -insertion - 1 : insertion;
            int length = insertion < 0 ? -insertion - 1 : insertion + 1;
            // println "Candidate values for p: ${r[0..<length]}"
            result[i] = length;
        }

        return result;
    }

    static long triplets(int[] a, int[] b, int[] c) {
        a = Arrays.stream(a).distinct().sorted().toArray();
        b = Arrays.stream(b).distinct().sorted().toArray();
        c = Arrays.stream(c).distinct().sorted().toArray();

        long[] m = countCandidates(b, a);
        long[] n = countCandidates(b, c);

        return IntStream.range(0, b.length).mapToLong(i -> m[i] * n[i]).sum();
    }

    @Test
    public void testSample00() {
        int[] a = { 1, 3, 5 };
        int[] b = { 2, 3, };
        int[] c = { 1, 2, 3 };

        Assert.assertEquals(8, triplets(a, b, c));
    }
}
