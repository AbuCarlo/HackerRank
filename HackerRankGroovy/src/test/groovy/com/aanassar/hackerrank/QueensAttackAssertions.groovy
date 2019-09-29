package com.aanassar.hackerrank


def findLimits(final int n, final int row, final int column) {
    def (int r, int c) = [row, column]
    while (r > -1 && c < n) {
        --r
        ++c
    }
    assert r == Math.max(-1, row - (n - column))
    println "NE: ($r, $c)"

    (r, c) = [row, column]
    while (r > -1 && c > -1) {
        --r
        --c
    }
    assert r == Math.max(-1, row - column - 1)
    println "NW: ($r, $c)"

    (r, c) = [row, column]
    while (r < n && c > -1) {
        ++r
        --c
    }
    assert r == Math.min(n, row + column + 1)
    println "SW: ($r, $c)"

    (r, c) = [row, column]
    while (r < n && c < n) {
        ++r
        ++c
    }
    assert r == Math.min(n, row + (n - column))
    println "SE: ($r, $c)"
}

final int n = 8

for (int r in 0..<n) {
    for (int c in 0..<n)
        findLimits(n, r, c)
}
