package com.aanassar.hackerrank

import groovy.transform.Field

@Field
static final Map<Integer, Integer> counts = [0: 1, 1: 1]
@Field
static final Map<Integer, List<Integer>> numbers = [0: [0], 1: [1]]

int countDecibinaryNumbers(int x) {
    Integer result = counts.get(x)
    if (result != null)
        return result
    result = 0
    for (int least = x % 2; least < 10 && least <= x; least += 2) {
        def most = (x - least) >> 1
        result += countDecibinaryNumbers(most)
    }
    counts[x] = result
    return result
}

List getDecibinaryNumbers(int x) {
    List result = numbers.computeIfAbsent(x, { [] })
    if (result)
        return result
    // An empty list will be the one we just created.
    for (int least = x % 2; least < 10 && least <= x; least += 2) {
        def most = (x - least) >> 1
        def prefixes = getDecibinaryNumbers(most)
        // Leading zeros are getting dropped.
        prefixes.each { result << it * 10 + least }
    }
    result.sort()
    println("toDecibinary($x) => $result")
    return result
}

int nthDecibinaryNumber(int n) {
    int count = 0
    for (int m = 0; m < n; ++m) {
        int c = countDecibinaryNumbers(m)
        if (count + c < n) {
            count += c
        } else {
            List numerals = getDecibinaryNumbers(m)
            return numerals[n - count - 1]
        }
    }
}

assert countDecibinaryNumbers(8) == 10

def eight = getDecibinaryNumbers(8)

assert countDecibinaryNumbers(2) == 2
assert countDecibinaryNumbers(3) == 2
assert countDecibinaryNumbers(9) == 10

def seventeen = getDecibinaryNumbers(17)
assert seventeen.size() == countDecibinaryNumbers(17)

def sampleInputs01 = [8,
                      23,
                      19,
                      16,
                      26,
                      7,
                      6]
def sampleOutputs01 = [
        12,
        23,
        102,
        14,
        111,
        4,
        11
]

println sampleInputs01.collect { nthDecibinaryNumber(it) }