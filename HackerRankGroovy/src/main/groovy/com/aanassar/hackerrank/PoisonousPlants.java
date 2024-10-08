package com.aanassar.hackerrank;

import java.util.*;

public class PoisonousPlants {

    static int reduce(Deque<Deque<Integer>> problem) {

        int reductions = 0;

        while (problem.size() > 1) {

            final Deque<Deque<Integer>> nextProblem = new LinkedList<>();
            Deque<Integer> previous = problem.removeFirst();
            nextProblem.addLast(previous);

            while (!problem.isEmpty()) {
                final Deque<Integer> current = problem.removeFirst();

                assert (current.getFirst() > previous.getLast());
                current.removeFirst();

                if (current.isEmpty()) {
                    continue;
                }
                if (current.getFirst() <= previous.getLast()) {
                    current.forEach(previous::addLast);
                } else {
                    nextProblem.addLast(current);
                    previous = current;
                }
            }

            ++reductions;

            problem = nextProblem;
        }
        return reductions;
    }

    static int poisonousPlants(int[] a) {
        Deque<Integer> q = new LinkedList<>();
        Deque<Deque<Integer>> problem = new LinkedList<>();
        problem.add(q);
        int previous = Integer.MAX_VALUE;
        for (int n : a) {
            if (n > previous) {
                q = new LinkedList<>();
                problem.add(q);
            }
            q.add(n);
            previous = n;
        }
        return reduce(problem);
    }
}
