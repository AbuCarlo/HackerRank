package com.aanassar.hackerrank

int queensAttack(List input) {
    def (int n, int k) = input[0].split(' ')*.toInteger()
    // Java is 0-based; the problem is 1-based.
    def (int queensRow, int queensColumn) = input[1].split(' ')*.toInteger()*.minus(1)
    Map obstacles = [:]
    int upwardAttacks = queensRow
    int leftwardAttacks = queensColumn
    int downwardAttacks = n - queensRow - 1
    int rightwardAttacks = n - queensColumn - 1
    for (line in input.drop(2)) {
        def (int row, int column) = line.split(' ')*.toInteger()*.minus(1)
        obstacles.computeIfAbsent(row, { new HashSet() }).add(column)
        if (row == queensRow) {
            // If the obstacle is between the queen and the edge...
            if (column < queensColumn && queensColumn - column - 1 < leftwardAttacks) {
                leftwardAttacks = queensColumn - column - 1
            } else if (column - queensColumn - 1 < rightwardAttacks) {
                rightwardAttacks = column - queensColumn - 1
            }
        }
        if (column == queensColumn) {
            if (row < queensRow && queensRow - row - 1 < upwardAttacks) {
                upwardAttacks = queensRow - row - 1
            } else if (row - queensRow - 1 < downwardAttacks) {
                downwardAttacks = row - queensRow - 1
            }
        }
    }

    upwardAttacks + downwardAttacks + leftwardAttacks + rightwardAttacks +
            northwest(queensRow, queensColumn, obstacles) + northeast(n, queensRow, queensColumn, obstacles) +
            southwest(n, queensRow, queensColumn, obstacles) + southeast(n, queensRow, queensColumn, obstacles)
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
    for (int c = queensColumn + 1; c < n && r < n; ++c) {
        if (obstacles.containsKey(r) && obstacles.get(r).contains(c)) {
            break
        }
        ++attacks
        ++r
    }
    attacks
}

def sampleNone = '''4 4
2 2
1 2
2 3
2 1
3 2
'''

assert queensAttack(sampleNone.readLines()) == 5

def sampleBlah = '''7 8
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

assert queensAttack(sampleBlah.readLines()) == 16

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

def testCaseThree = '''100000 0
4187 5068
'''

assert queensAttack(testCaseThree.readLines()) == 308369
