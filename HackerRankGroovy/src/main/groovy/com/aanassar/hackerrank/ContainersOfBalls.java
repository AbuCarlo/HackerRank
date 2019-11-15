package com.aanassar.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ContainersOfBalls {

    private final List<int[]> contents;
    private final int size;

    public ContainersOfBalls(List<List<Integer>> contents) {
        this.contents = contents.stream().map(l -> l.stream().mapToInt(Integer::intValue).toArray()).collect(Collectors.toList());
        this.size = this.contents.stream().mapToInt(a -> Arrays.stream(a).sum()).sum();
    }

    static String organizingContainers(int[][] containers) {
        return canOrganize(containers) ? "Possible" : "Impossible";
    }

    private static boolean canOrganize(int[][] containers) {
        int[] containerSizes = Arrays.stream(containers).mapToInt(a -> Arrays.stream(a).sum()).sorted().toArray();
        int[] sizesByColor = IntStream.range(0, containers.length)
                .map(i -> Arrays.stream(containers).mapToInt(a -> a[i]).sum()).sorted().toArray();
        return Arrays.equals(containerSizes, sizesByColor);
    }

    public static boolean canOrganize(ContainersOfBalls containers) {
        int[] containerSizes = containers.contents.stream().mapToInt(a -> Arrays.stream(a).sum()).sorted().toArray();
        int[] sizesByColor = IntStream.range(0, containers.contents.size())
                .map(i -> containers.contents.stream().mapToInt(a -> a[i]).sum()).sorted().toArray();
        return Arrays.equals(containerSizes, sizesByColor);
    }
}
