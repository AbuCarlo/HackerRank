import com.aanassar.hackerrank.BomberManApplication
import org.junit.Test

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
        def output = BomberManApplication.bomberMan(3, lines)

        println output
    }

    @Test
    void testCase07() {
        def lines = Paths.get("test-files/test-case-7.txt").readLines('US-ASCII') as String[]

        def output = BomberManApplication.bomberMan(197, lines)
    }
}