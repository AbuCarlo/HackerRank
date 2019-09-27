package com.aanassar.hackerrank

import groovy.transform.Field

@Field
final static int MAGIC_NUMBER = 15

@Field
final static int N = 3

static boolean validateRows(final List l) {
    (0..<N).every { int y ->
        def row = l.subList(y * N, y * N + N)
        row.sum() == MAGIC_NUMBER
    }
}

static boolean validateColumns(final List l) {
    (0..<N).every { int x ->
        def indices = (0..<N).collect { it * N + x }
        def column = indices.collect { int i -> l[i] }
        column.sum() == MAGIC_NUMBER
    }
}

static boolean validateDownwardDiagonal(List l) {
    List indices = (0..<N).collect { int y -> y * N + y }
    indices.collect { int i -> l[i] }.sum() == MAGIC_NUMBER
}

static boolean validateUpwardDiagonal(List l) {
    List indices = (0..<N).collect { int y -> y * N + (N - y - 1)}
    indices.collect { int i -> l[i] }.sum() == MAGIC_NUMBER
}

boolean isMagic(final List l) {
     validateRows(l) && validateColumns(l) && validateDownwardDiagonal(l) && validateUpwardDiagonal(l)
}

def knownMagicSquare = [ 4, 9, 2,
                         3, 5, 7,
                         8, 1, 6 ]

def checkKnown = isMagic(knownMagicSquare)

assert checkKnown

def magicSquares = (1..(N * N)).permutations().findAll(this.&isMagic)

println(magicSquares.size())