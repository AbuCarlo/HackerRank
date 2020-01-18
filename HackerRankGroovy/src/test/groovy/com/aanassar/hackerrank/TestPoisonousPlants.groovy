package com.aanassar.hackerrank

import org.junit.Test

class TestPoisonousPlants {

    int countReductions(List l) {
        final int head = l.head()
        def counts = l.findAll({it != head}).countBy({it})
        return counts ? counts.values().max() : 0
    }

    int poisonousPlants(List l) {
        if (l.isEmpty())
            return 0
        List current = []
        List q = [current]
        int previous = Integer.MIN_VALUE
        l.each { int n ->
            if (n > previous) {
                current << n
            } else {
                current = [n]
                q << current
            }
            previous = n
        }
        if (q.every { it.size() == 1 })
            return 0
        int result = q.collect { countReductions(it) }.max()
        List next = q*.first()
        return result + poisonousPlants(next)
    }

    int poisonousPlants(String input) {
        List l = input.tokenize()*.toInteger().toList()
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
}
