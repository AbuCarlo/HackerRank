package com.aanassar.hackerrank

import org.junit.Test

import java.nio.file.Paths

class TestQueensAttack {

    @Test
    void testUnnamedSample() {
        def sampleNone = '''4 4
2 2
1 2
2 3
2 1
3 2
'''
        assert QueensAttack.queensAttack(sampleNone.readLines()) == 5
    }

    @Test
    void testObstaclesOnAxes() {
        def sampleWithAxes = '''7 8
4 4
1 4
2 4
4 1
4 2 
4 7
4 6
6 4
7 4
'''

        assert QueensAttack.queensAttack(sampleWithAxes.readLines()) == 16
    }

    @Test
    void testUnalignedDiagonals() {
        def sampleWithDiagonals = '''7 8
4 4
1 1
2 2
7 7 
6 6
1 7
2 6
7 1
6 2
'''
        assert QueensAttack.queensAttack(sampleWithDiagonals.readLines()) == 16
    }

    @Test
    void testSampleZero() {
        def sampleZero = '''4 0
4 4
'''

        assert QueensAttack.queensAttack(sampleZero.readLines()) == 9
    }

    @Test
    void testSampleOne() {
        def sampleOne = '''5 3
4 3
5 5
4 2
2 3
'''

        assert QueensAttack.queensAttack(sampleOne.readLines()) == 10
    }

    @Test
    void testCaseThree() {
        def testCaseThree = '''100000 0
4187 5068
'''

        assert QueensAttack.queensAttack(testCaseThree.readLines()) == 308369
    }

    @Test
    void testCaseTwelve() {
        def path = Paths.get("test-files/queens-attack/test-case-12.txt")
        def lines = path.readLines()
        def actual = QueensAttack.queensAttack(lines)
        assert actual == 1449
    }
}
