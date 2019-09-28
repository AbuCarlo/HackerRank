package com.aanassar.hackerrank

int queensAttack(List input) {
    def (int n, int k) = input[0].split(' ')*.toInteger()
    // Java is 0-based; the problem is 1-based.
    def (int queensRow, int queensColumn) = input[1].split(' ')*.toInteger()*.minus(1)
    Map obstacles = input.drop(2).inject([:]) { Map m, String line ->
        def (int row, int column) = line.split(' ')*.toInteger()*.minus(1)
        m.computeIfAbsent(row, { new HashSet() }).add(column)
        m
    }

    upward(queensRow, queensColumn, obstacles) + downward(n, queensRow, queensColumn, obstacles) +
            leftward(queensRow, queensColumn, obstacles) + rightward(n, queensRow, queensColumn, obstacles) +
            northwest(queensRow, queensColumn, obstacles) + northeast(n, queensRow, queensColumn, obstacles) +
            southwest(n, queensRow, queensColumn, obstacles) + southeast(n, queensRow, queensColumn, obstacles)
}

private int upward(int queensRow, int queensColumn, Map obstacles) {
    int attacks = 0
    for (int r = queensRow - 1; r >= 0; --r) {
        if (obstacles.containsKey(r) && obstacles.get(r).contains(queensColumn)) {
            break
        }
        ++attacks
    }
    attacks
}

private int downward(final int n, int queensRow, int queensColumn, Map obstacles) {
    int attacks = 0
    for (int r = queensRow + 1; r < n; ++r) {
        if (obstacles.containsKey(r) && obstacles.get(r).contains(queensColumn)) {
            break
        }
        ++attacks
    }
    attacks
}

private int leftward(final int queensRow, int queensColumn, Map obstacles) {
    int attacks = 0
    for (int c = queensColumn - 1; c >= 0; --c) {
        if (obstacles.containsKey(queensRow) && obstacles.get(queensRow).contains(c)) {
            break
        }
        ++attacks
    }
    attacks
}

private int rightward(final int n, final int queensRow, final int queensColumn, Map obstacles) {
    int attacks = 0
    for (int c = queensColumn + 1; c < n; ++c) {
        if (obstacles.containsKey(queensRow) && obstacles.get(queensRow).contains(c)) {
            break
        }
        ++attacks
    }
    attacks
}

private int northwest(final int queensRow, final int queensColumn, Map obstacles) {
    int attacks = 0
    int r = queensRow - 1
    for (int c = queensColumn - 1; c >= 0 && r >= 0; --c) {
        if (obstacles.containsKey(r) && obstacles.get(r).contains(c)) {
            break
        }
        ++attacks
        --r
    }
    attacks
}

private int northeast(final int n, final int queensRow, final int queensColumn, Map obstacles) {
    int attacks = 0
    int r = queensRow - 1
    for (int c = queensColumn + 1; c < n && r >= 0; ++c) {
        if (obstacles.containsKey(r) && obstacles.get(r).contains(c)) {
            break
        }
        ++attacks
        --r
    }
    attacks
}

private int southwest(final int n, final int queensRow, final int queensColumn, Map obstacles) {
    int attacks = 0
    int r = queensRow + 1
    for (int c = queensColumn - 1; c >= 0 && r < n; --c) {
        if (obstacles.containsKey(r) && obstacles.get(r).contains(c)) {
            break
        }
        ++attacks
        ++r
    }
    attacks
}

private int southeast(final int n, final int queensRow, final int queensColumn, Map obstacles) {
    int attacks = 0
    int r = queensRow + 1
    for (int c = queensColumn + 1; c <= n && r < n; ++c) {
        if (obstacles.containsKey(r) && obstacles.get(r).contains(c)) {
            break
        }
        ++attacks
        ++r
    }
    attacks
}

def sampleZero = '''4 0
4 4
'''

assert queensAttack(sampleZero.readLines()) == 9

def sampleOne = '''5 3
4 3
5 5
4 2
2 3
'''

assert queensAttack(sampleOne.readLines()) == 10