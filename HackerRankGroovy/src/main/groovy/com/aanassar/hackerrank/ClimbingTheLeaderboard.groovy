package com.aanassar.hackerrank

// https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem

int[] solve(BufferedReader reader) {
    def n = reader.readLine().toInteger()
    int[] scores = reader.readLine().split(' ')*.toInteger().unique().sort()
    def m = reader.readLine().toInteger()
    def alice = reader.readLine().split(' ')*.toInteger() as int[]
    assert alice.length == m

    int[] result = new int[alice.length]
    alice.eachWithIndex{ int score, int i ->
        // https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#binarySearch(int[],%20int)
        final int hit = Arrays.binarySearch(scores, score)
        if (hit >= 0) {
            result[i] = scores.length - hit
        } else if (hit == -1) {
            // Alice has the lowest score.
            result[i] = scores.length + 1
        } else {
            final int insertionPoint = -hit - 1
            // We need to know how many scores follow the insertion point.
            result[i] = scores.length - insertionPoint + 1
        }
    }

    result
}

int[] solve(String input) {
    solve(new BufferedReader(new StringReader(input)))
}

def sampleOne = '''7
100 100 50 40 40 20 10
4
5 25 50 120
'''

solve sampleOne

def sampleTwo = '''6
100 90 90 80 75 60
5
50 65 77 90 102
'''

solve sampleTwo

