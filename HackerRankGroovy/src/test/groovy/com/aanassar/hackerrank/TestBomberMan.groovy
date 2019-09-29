package com.aanassar.hackerrank

import org.junit.Test

import java.nio.file.Path
import java.nio.file.Paths

class TestBomberMan {

    final Random random = new Random()

    String[] createRandomGrid(int height, int width) {
        char[][] grid = new char[height][width]
        for (int y in 0..<height)
            for (int x in 0..<width)
                grid[y][x] = random.nextBoolean() ? BomberManApplication.BOMB : BomberManApplication.NO_BOMB

        grid.collect { new String(it) }
    }

    def splitInput(String input) {
        return input.readLines() as String[]
    }

    @Test
    void testCycles() {
        def grid = createRandomGrid(5, 5)

        println "After 1 iteration: \n\n$grid\n"

        for (int i in 0..10) {
            final int n = i * 2 + 3
            final def output = BomberManApplication.bomberMan(n, grid)
            println "After $n iterations: \n\n$output\n"
        }
    }

    @Test
    void testSample() {
        def input = '''...
.O.
...'''
        def lines = splitInput(input)
        println BomberManApplication.bomberMan(3, lines)

        println BomberManApplication.bomberMan(5, lines)

        println BomberManApplication.bomberMan(7, lines)
    }

    @Test
    void testEvenIterations() {
        def input = '''...
.O.
...'''
        def lines = splitInput(input)
        def output = BomberManApplication.bomberMan(100, lines)

        println output
    }

    @Test
    void testCase07() {
        def path = Paths.get("test-files/bomber-man/test-case-7.txt");

        def actual = BomberManApplication.fromInput(path);

        def expected = readAnswer("test-files/bomber-man/test-case-answer-7.txt")

        assert actual == expected
    }

    private String[] readAnswer(String answerPath) {
        readAnswer Paths.get(answerPath)
    }

    private String[] readAnswer(Path answerPath) {
        answerPath.readLines('US-ASCII')
    }
}