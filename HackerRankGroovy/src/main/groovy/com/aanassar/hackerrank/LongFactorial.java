package com.aanassar.hackerrank;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class LongFactorial {

    public static BigInteger solve(int n) {
        List<Long> factors = new ArrayList<>();
        long factor = 1;
        for (int f = n; f > 0; --f) {
            if (factor > Integer.MAX_VALUE) {
                factors.add(factor);
                factor = f;
            } else {
                factor *= f;
            }
        }
        factors.add(factor);
        return factors.stream().map(f -> new BigInteger("" + f)).reduce((x, y) -> x.multiply(y)).get();
    }
}
