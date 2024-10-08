package com.aanassar.hackerrank;

/**
 * <a href="https://www.hackerrank.com/challenges/bigger-is-greater/problem">...</a>
 */
public class BiggerIsGreater {

    static String biggerIsGreater(String s) {
        String result = nextPermutation(s);
        return result.equals(s) ? "no answer" : result;
    }

    static String nextPermutation(String s) {
        return new String(nextPermutation(s.toCharArray()));
    }

    static char[] nextPermutation(char[] array) {
        // Find longest non-increasing suffix
        int i = array.length - 1;
        while (i > 0 && array[i - 1] >= array[i])
            i--;
        // Now i is the head index of the suffix

        // Are we at the last permutation already?
        if (i <= 0)
            return array;

        // Let array[i - 1] be the pivot
        // Find rightmost element that exceeds the pivot
        int j = array.length - 1;
        while (array[j] <= array[i - 1])
            j--;
        // Now the value array[j] will become the new pivot
        // Assertion: j >= i

        // Swap the pivot with j
        char temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        // Reverse the suffix
        j = array.length - 1;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return array;
    }
}
