package com.aanassar.hackerrank;

import java.util.*;

public class PoisonousPlants {

    static int reduce(Stack<List<Integer>> problem) {
        List<Integer> reductions = new ArrayList<>();
        reductions.add(0);
        final Comparator<Integer> comparator = Comparator.reverseOrder();
        while (problem.size() > 1) {
            List<Integer> currentQueue = problem.pop();
            /*
                According to https://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#binarySearch(java.util.List,%20T,%20java.util.Comparator),
                "If the list contains multiple elements equal to the specified object, there is no guarantee which one will be found."
             */
            List<Integer> previousQueue = problem.peek();
            final int last = previousQueue.get(previousQueue.size() - 1);
            int ix = Collections.binarySearch(currentQueue, last, comparator);
            if (ix < 0) {
                // The value is not present; everything from the insertion point on.
                ix = -ix - 1;
            } else {
                while (currentQueue.get(ix - 1) == last)
                    --ix;
            }
            List tail = currentQueue.subList(ix, currentQueue.size());
            // We've discarded this many values.
            reductions.add(ix);
            previousQueue.addAll(tail);
        }
        return reductions.stream().max(Integer::compareTo).get();
    }

    static int poisonousPlants(int[] a) {
        List<Integer> q = new ArrayList<>();
        Stack<List<Integer>> problem = new Stack<>();
        problem.push(q);
        int previous = Integer.MAX_VALUE;
        for (int n : a) {
            if (n > previous) {
                q = new ArrayList<>();
                problem.push(q);
            }
            q.add(n);
            previous = n;
        }
        return reduce(problem);
    }
}
