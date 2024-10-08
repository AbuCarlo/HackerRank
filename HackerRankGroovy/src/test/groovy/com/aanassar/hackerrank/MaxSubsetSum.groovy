package com.aanassar.hackerrank

def l00 = [ 3, 7, 4, 6, 5 ]
def l01 = [ 2, 1, 5, 8, 4 ]
def l02 = [ 3, 5, -7, 8, 10 ]

def maxSubsetSum(a) {
    int far = 0
    int near = 0
    for (int n in a) {
        if (n <= 0) {
            far = Math.max(far, near)
        } else {
            int newFar = Math.max(far, near)
            near = far + n
            far = newFar        
        }    
    }
    return Math.max(far, near)
}

assert maxSubsetSum(l00) == 13
assert maxSubsetSum(l01) == 11
assert maxSubsetSum(l02) == 15