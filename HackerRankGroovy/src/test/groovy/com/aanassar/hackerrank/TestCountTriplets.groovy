package com.aanassar.hackerrank

class TestCountTriplets {

    Map countCandidates(int[] l, int[] r) {
        Map result = [:]
        int from = 0
        for (int q : l) {
            println "Checking q = $q"
            final int insertion = Arrays.binarySearch(r, from, r.length, q)
            from = insertion < 0 ? -insertion - 1 : insertion
            int length = insertion < 0 ? -insertion - 1 : insertion + 1
            println "Candidate values for p: ${r[0..<length]}"
            result[q] = length
        }

        return result
    }

    int countTriples(int[] a, int[] b, int[] c) {
        Arrays.sort(a);
        Arrays.sort(b);
        Arrays.sort(c);

        countCandidates(b, a)

        0
    }
}
