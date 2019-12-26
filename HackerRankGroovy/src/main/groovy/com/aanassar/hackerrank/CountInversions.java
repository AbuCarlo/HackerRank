package com.aanassar.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class CountInversions {

    static class Element implements Comparable<Element> {

        final int value;
        final int index;

        Element(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Element o) {
            final int ech = Integer.compare(value, o.value);
            return ech != 0 ? ech : Integer.compare(index, o.index);
        }
    }

    static long countInversions(int[] a) {
        TreeSet<Element> m = new TreeSet<Element>();
        long result = 0L;
        for (int i = a.length - 1; i >= 0; --i) {
            Element e = new Element(a[i], i);
            Set sub = m.headSet(e, false);
            result += sub.size();
            m.add(e);
        }
        return result;
    }

    static long countInversions(String input) {
        String[] s = input.split(" ");
        int[]  a = Arrays.stream(s).parallel().mapToInt(Integer::parseInt).toArray();
        return countInversions(a);
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            final int problems = Integer.parseInt(reader.readLine());
            for (int i = 0; i < problems; ++i) {
                reader.readLine();
                final String input = reader.readLine();
                final long output = countInversions(input);
                System.out.println("" + output);
            }
        }
    }

}
