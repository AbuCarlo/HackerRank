package com.aanassar.hackerrank

import org.junit.Test

class TestPoisonousPlants {

    int reduce(Stack<List<Integer>> problem) {
        List reductions = [0]
        final Comparator<Integer> comparator = Comparator.<Integer>reverseOrder()
        while (problem.size() > 1) {
            List currentQueue = problem.pop()
            /*
                According to https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#binarySearch(java.util.List,%20T,%20java.util.Comparator),
                "If the list contains multiple elements equal to the specified object, there is no guarantee which one will be found."
             */
            List<Integer> previousQueue = problem.peek()
            final int last = previousQueue.last()
            int ix = Collections.binarySearch(currentQueue, last, comparator)
            int cutoff
            if (ix < 0) {
                // The value is not present; everything from the insertion point on.
                ix = -ix - 1
            } else {
                while (currentQueue[ix - 1] == last)
                    --ix
            }
            List tail = currentQueue.subList(ix, currentQueue.size())
            // We've discarded this many values.
            reductions << ix
            previousQueue.addAll(tail)
        }
        return reductions.max()
    }

    int poisonousPlants(int[] a) {
        List q = []
        Stack problem = new Stack()
        problem.push q
        int previous = Integer.MAX_VALUE
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
        // # 0
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
        assert poisonousPlants((0..9).reverse() as int[]) == 0
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
