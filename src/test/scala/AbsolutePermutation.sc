def absolutePermutation(n: Int, k: Int): Array[Int] = {
  if (k == 0)
    Range(1, n + 1).toArray
  else if (n % k > 0 || (n / k) % 2 != 0)
    Array(-1)
  else {
    for (i <- 0 until n) yield {
      if (Math.floorMod(i / k, 2) == 0)
        i + 1 + k
      else
        i + 1 - k
    }
  }.toArray
}

absolutePermutation(2, 1)