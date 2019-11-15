package com.aanassar.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KaprekarNumbers {

    static boolean isKaprekar(final int n) {
        if (n == 1) {
            return true;
        }

        if (n < 9) {
            return false;
        }

        final String square = Long.toString(Long.valueOf(n) * Long.valueOf(n));
        final int d = Integer.toString(n).length();

        final String least = square.substring(square.length() - d);
        final String most = square.substring(0, square.length() - least.length());
        return Integer.parseInt(least) > 0 && Integer.parseInt(most) + Integer.parseInt(least) == n;
    }

    static List<Integer> findKaprekar(final int p, final int q) {
        List<Integer> result = new ArrayList<>();
        return IntStream.rangeClosed(p, q).parallel().filter(KaprekarNumbers::isKaprekar).mapToObj(Integer::valueOf).collect(Collectors.toList());
    }

    static void kaprekarNumbers(int p, int q) {
        List<Integer> numbers = findKaprekar(p, q);
        if (numbers.isEmpty()) {
            System.out.println("INVALID RANGE");
        } else {
            boolean first = true;
            for (int n : numbers) {
                if (!first) {
                    System.out.print(' ');
                } else {
                    first = false;
                }
                System.out.print(n);
            }
        }
    }
}
