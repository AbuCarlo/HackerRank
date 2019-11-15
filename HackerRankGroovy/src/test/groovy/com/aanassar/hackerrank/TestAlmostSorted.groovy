package com.aanassar.hackerrank

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName

import java.nio.file.Path
import java.nio.file.Paths

class TestAlmostSorted {

    @Rule
    public TestName testName = new TestName();

    final Random random = new Random();

    final int[] input = (0..9).collect { random.nextInt(10000) }.unique().sort()

    @Before
    void printTestName() {
        println "Running ${testName.methodName}"
    }

    @Test
    void testAnySortedArray() {
        assert AlmostSorted.isSorted(input)
    }

    @Test
    void testSwappedArray() {
        final int firstError = random.nextInt(5)
        final int secondError = random.nextInt(4) + firstError + 1
        println "Swapping $firstError and $secondError"
        final int tmp = input[firstError]
        input[firstError] = input[secondError]
        input[secondError] = tmp
        AlmostSorted.almostSorted(input)
    }

    @Test
    void testImpossible() {
        AlmostSorted.almostSorted([3, 1, 2] as int[])
    }

    @Test
    void testWithReversal() {
        AlmostSorted.almostSorted(input.toList().reverse() as int[])
    }

    @Test
    void testOddLengthReversal() {
        AlmostSorted.almostSorted((0..10).toList().reverse() as int[])
    }

    @Test
    void testSampleTwo() {
        int[] input = [ 1, 5, 4, 3, 2, 6]
        AlmostSorted.almostSorted(input)
    }

    @Test
    void testCase14() {
        Path inputFile = Paths.get("test-files/almost-sorted/input14.txt")
        def lines = inputFile.readLines("US-ASCII")
        def input = lines[1].split(' ')*.toInteger() as int[]
        AlmostSorted.almostSorted(input)
    }
}
