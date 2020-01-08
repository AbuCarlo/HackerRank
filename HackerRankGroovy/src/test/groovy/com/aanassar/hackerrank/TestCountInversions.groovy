package com.aanassar.hackerrank

import org.junit.Test

import java.nio.file.Path
import java.nio.file.Paths

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
        assert CountInversions.countInversions(b) == 45
    }

    @Test
    void testSamples() {
        int[] a = [ 1, 1, 1, 2, 2 ]
        assert CountInversions.countInversions(a) == 0
        assert CountInversions.countInversions('2 1 3 1 2') == 4
    }

    final Path testFiles = Paths.get('test-files/count-inversions')

    void testFile(input, output) {
        List expected = testFiles.resolve(output).readLines()*.toInteger()
        testFiles.resolve(input).withReader('US-ASCII') { reader ->
            final int size = reader.readLine().toInteger()
            for (int i = 0; i < size; ++i) {
                final int n = reader.readLine().toInteger()
                final int[] a = reader.readLine().tokenize()*.toInteger().toArray()
                long actual = CountInversions.countInversions(a)
                assert actual == expected[i]: "Problem $i in $input expected ${expected[i]}; got $actual"
            }
        }
    }

    @Test
    void test04() {
        testFile "input04.txt", "output04.txt"
    }

    @Test
    void test02() {
        testFile "input02.txt", "output02.txt"
    }
}
