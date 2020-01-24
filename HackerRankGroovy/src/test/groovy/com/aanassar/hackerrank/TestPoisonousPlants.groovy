package com.aanassar.hackerrank

import org.junit.Test

import java.nio.file.Path
import java.nio.file.Paths

class TestPoisonousPlants {

    static final Path testFiles = Paths.get("test-files/poisonous-plants")

    int poisonousPlants(String input) {
        int[] l = input.tokenize()*.toInteger().toArray()
        PoisonousPlants.poisonousPlants l
    }

    int poisonousPlants(Path input) {
        def lines = input.readLines()
        poisonousPlants(lines[1])
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
        // # 29
        assert poisonousPlants('3 2 5 4') == 2
        // # 30
        assert poisonousPlants('4 3 7 5 6 4 2') == 3
    }

    @Test
    void testTestCaseFiles() {

        // assert poisonousPlants(testFiles.resolve('input12.txt')) == 16

        assert poisonousPlants(testFiles.resolve('input20.txt')) == 24

        assert poisonousPlants(testFiles.resolve('input21.txt')) == 15

        assert poisonousPlants(testFiles.resolve('input23.txt')) == 80802

    }

    @Test
    void testTony() {
        assert poisonousPlants('1 2 2 2') == 3
        assert poisonousPlants('2 2 2 2') == 0
        assert poisonousPlants('1 1 1 2 2 2 3 3 3') == 3
        assert poisonousPlants('5 4 3 2 1 0') == 0
    }
}
