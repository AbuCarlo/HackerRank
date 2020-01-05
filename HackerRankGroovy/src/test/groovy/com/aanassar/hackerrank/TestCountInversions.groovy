package com.aanassar.hackerrank

import org.junit.Test

class TestCountInversions {

    @Test
    void testEmpty() {
        int[] a = []
        assert CountInversions.countInversions(a) == 0L
    }

    @Test
    void testSorted() {
        int[] a = (0..9).toArray()
        assert CountInversions.countInversions(a) == 0

        int[] b = (0..9).toList().reverse().toArray()
        assert CountInversions.countInversions(b) == 55
    }

    @Test
    void testSamples() {
        int[] a = [ 1, 1, 1, 2, 2 ]
        assert CountInversions.countInversions(a) == 0
        assert CountInversions.countInversions('2 1 3 1 2') == 4
    }
}
