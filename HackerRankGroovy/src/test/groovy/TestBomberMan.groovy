import com.aanassar.hackerrank.BomberManApplication
import org.junit.Test

import java.nio.file.Path
import java.nio.file.Paths

class TestBomberMan {

    def splitInput(String input) {
        return input.readLines() as String[]
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