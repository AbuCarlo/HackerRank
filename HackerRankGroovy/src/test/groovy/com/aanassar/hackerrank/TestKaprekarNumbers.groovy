package com.aanassar.hackerrank

import org.junit.Test

class TestKaprekarNumbers {

    @Test
    void testOne() {
        assert KaprekarNumbers.isKaprekar(1);
    }

    @Test
    void testTwo() {
        assert !KaprekarNumbers.isKaprekar(2)
    }

    @Test
    void testNine() {
        assert KaprekarNumbers.isKaprekar(9)
    }

    @Test
    void testFortyFive() {
        assert KaprekarNumbers.isKaprekar(45)
    }

    @Test
    void testSampleOne() {
        assert KaprekarNumbers.findKaprekar(1, 100) == [1, 9, 45, 55, 99]
    }

    @Test
    void testSeven() {
        KaprekarNumbers.findKaprekar(1, 99999)
    }

    @Test
    void testBlah() {
        // 1 9 45 55 99 297 703 999 2223 2728 4950 5050 7272 7777 9999 17344 22222 77778 82656 95121 99999
        assert KaprekarNumbers.isKaprekar(99999)
    }
}
