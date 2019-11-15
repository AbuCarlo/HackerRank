package com.aanassar.hackerrank

import org.junit.Test

class TestLongFactorial {

    @Test
    void testOne() {
        assert LongFactorial.solve(1) == 1
    }

    @Test
    void testTen() {
        assert LongFactorial.solve(10) == 3628800
    }

    @Test
    void testTwentyFive() {
        assert LongFactorial.solve(25) == new BigInteger("15511210043330985984000000")
    }
}
