package com.aanassar.hackerrank

// https://www.hackerrank.com/challenges/beautiful-triplets/problem

def findPairs(final int d, a) {
    def pairs = []
    for (int i = 0; i < a.size() - 1; ++i) {
        for (int j = i + 1; j < a.size(); ++j) {
            int difference = a[j] - a[i]
            if (difference >= d) {
                if (difference == d) {
                    pairs << [i, j]
                } else {
                    break
                }
            }
        }
    }
    pairs
}

// https://www.hackerrank.com/challenges/beautiful-triplets/problem

def beautifulTriplets(final int d, a) {
    final pairs = findPairs(d, a)
    int count = 0
    for (int i = 0; i < pairs.size() - 1; ++i) {
        for (int j = i + 1; j < pairs.size(); ++j) {
            def insideRight = pairs[j][0]
            def insideLeft = pairs[i][1]
            if (insideRight == insideLeft) {
                // println "Merging ${pairs[i]} and ${pairs[j]}"
                ++count
            } else if (insideRight > insideLeft) {
                break
            }
        }
    }
    count
}


println beautifulTriplets(3, [1, 2, 4, 5, 7, 8, 10])

println beautifulTriplets(3, [ 1, 6, 7, 7, 8, 10, 12, 13, 14, 19])

