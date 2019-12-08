package com.aanassar.hackerrank

int countSwaps(List l) {
    int result = 0
    for (int i in 0..<l.size()) {
        for (int j in (i + 1)..<l.size()) {
            if (l[j] < l[i])
                ++result
        }

    }
    return result
}

assert countSwaps([3, 2, 1]) == 3
