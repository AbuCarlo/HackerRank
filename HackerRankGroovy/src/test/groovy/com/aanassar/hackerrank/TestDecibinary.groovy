package com.aanassar.hackerrank

List toDecibinary(int x) {
    println("toDecibinary($x)")
    if (x == 0) 
        return [0]
    if (x == 1)
        return [1]
    final result = []
    final int remainder = x % 2
    for (int i = 0; i <= 8 && i < x - remainder; i += 2) {
        def most = toDecibinary((x - i) >> 1)
        // Leading zeros are getting dropped.
        most.each { result << it * 10 + i + remainder }
    }
    result.sort()
    println("toDecibinary($x) => $result")
    return result
}

toDecibinary(17)