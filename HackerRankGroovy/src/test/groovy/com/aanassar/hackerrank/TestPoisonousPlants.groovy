package com.aanassar.hackerrank

import java.util.function.Function
import org.junit.Test

class TestPoisonousPlants {

    int reduce(Stack problem) {
        List reductions = [0]
        final Comparator<Integer> comparator = Comparator.<Integer>reverseOrder()
        while (problem.size() > 1) {
            List l = problem.pop()
            int ix = Collections.binarySearch(l, problem.peek().last)

        }
        return reductions.max()
    }

    int poisonousPlants(int[] a) {
        List q = []
        Stack problem = [q]
        int previous = Integer.MIN_VALUE
        for (int n : a) {
            if (n > previous) {
                q = []
                problem << q
            }
            q << n
            previous = n
        }
        reduce problem
    }

    int poisonousPlants(String input) {
        int[] l = input.tokenize()*.toInteger().toArray()
        poisonousPlants l
    }

    @Test
    void testSamples() {
        assert poisonousPlants('6 5 8 4 7 10 9') == 2
        assert poisonousPlants('3 2 5 4') == 2
        assert poisonousPlants('4 3 7 5 6 4 2') == 3
    }

    @Test
    void testTestCases() {
        // # 7
        assert poisonousPlants('3 1 10 7 3 5 6 6' ) == 3
        // # 9
        assert poisonousPlants('11 7 19 6 12 12 8 8 7 1 10 15 5 12') == 5
        // # 10
        assert poisonousPlants('2 1 3 1 4 2 1 4 3 3') == 3
    }

    @Test
    void testTony() {
        assert poisonousPlants('1 2 2 2') == 3
        assert poisonousPlants('2 2 2 2') == 0
        assert poisonousPlants('1 1 1 2 2 2 3 3 3') == 3
        assert poisonousPlants((0..9).reverse().toList()) == 0
    }

    @Test
    void testComparator() {
        def comparator = Comparator.<Integer>naturalOrder().reversed()

        def l = [ 9, 8, 7, 6, 4, 4, 3, 2, 1, 0 ]

        println l

        println Collections.binarySearch(l, -1, comparator)
        println Collections.binarySearch(l, 5, comparator)
        println Collections.binarySearch(l, 4, comparator)
        println Collections.binarySearch(l, 11, comparator)
        println Collections.binarySearch(l, 9, comparator)
    }
}
