package com.aanassar.hackerrank.strings

/**
 * https://www.hackerrank.com/challenges/reduced-string/problem
 *
 * @param s any input string
 * @return the string reduced per instruction
 */
def superReducedString(String s) {
    def stack = new Stack<Integer>()
    s.chars().forEach { c ->
        if (stack && stack.peek() == c) {
            stack.pop()
        } else {
            stack.push(c)
        }
    }
    if (!stack) {
        return 'Empty String'
    }

    def result = ""
    while (stack) {
        result += (char) stack.peek().intValue()
        stack.pop()
    }
    return result ? result.reverse() : 'Empty String'
}

// Example
assert superReducedString('abba') == 'Empty String'

// Sample Test Case 0
assert superReducedString('aaabccddd') == 'abd'
// s = System.in.text.trim()


