package com.aanassar.hackerrank

import org.junit.Test

class TestPoisonousPlants {

    int reduce(List l) {
        if (l.size() < 2)
            return 0
        LinkedList previous = l.head()
        List reduction = [previous]
        for (LinkedList current : l.tail()) {
            assert current.head() > previous.last
            int m = current.removeFirst()
            if (current.empty) {
                continue
            }
            if (current.first <= previous.last) {
                previous.addAll(current)
            } else {
                previous = current
                reduction.add previous
            }
        }
        1 + reduce(reduction)
    }

    int poisonousPlants(int[] a) {
        if (a.length == 0)
            return 0
        LinkedList deque = []
        List problem = [deque]
        int previous = Integer.MAX_VALUE
        for (int n : a) {
            if (n > previous) {
                deque = [n] as LinkedList
                problem << deque
            } else {
                deque.addLast(n)
            }
            previous = n
        }
        reduce problem
    }

    int poisonousPlants(String input) {
        int[] a = input.tokenize()*.toInteger() as int[]
        poisonousPlants a
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
        assert poisonousPlants((0..9).reverse() as int[]) == 0
    }
}
