package com.aanassar.hackerrank

import org.junit.Test

class TestContainersOfBalls {

    @Test
    void testEmptyCollection() {
        def input = new ContainersOfBalls([])
        assert ContainersOfBalls.canOrganize(input)
    }

    @Test
    void testAlreadyOrganized() {
        def input = new ContainersOfBalls([[1, 0], [0, 1]])
        assert ContainersOfBalls.canOrganize(input)
    }

    @Test
    void testCaseOne() {
        def input = new ContainersOfBalls([[0, 2], [1, 1]])
        assert !ContainersOfBalls.canOrganize(input)
    }
}
