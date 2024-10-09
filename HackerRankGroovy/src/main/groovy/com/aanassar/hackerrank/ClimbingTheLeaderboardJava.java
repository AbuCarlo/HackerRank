package com.aanassar.hackerrank;

import java.util.Arrays;

// https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem

public class ClimbingTheLeaderboardJava {

    public static int[] climbingLeaderboard(int[] scores, int[] alice) {
        int[] uniqueScores = Arrays.stream(scores).distinct().sorted().toArray();
        int[] result = new int[alice.length];
        for (int i = 0; i < alice.length; ++i) {
            // https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html#binarySearch(int[],%20int)
            final int hit = Arrays.binarySearch(uniqueScores, alice[i]);
            if (hit >= 0) {
                result[i] = uniqueScores.length - hit;
            } else if (hit == -1) {
                // Alice has the lowest score.
                result[i] = uniqueScores.length + 1;
            } else {
                final int insertionPoint = -hit - 1;
                // We need to know how many scores follow the insertion point.
                result[i] = uniqueScores.length - insertionPoint + 1;
            }
        }

        return result;
    }
}
