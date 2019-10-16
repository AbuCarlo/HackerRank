val input000 =
  """4 3
    |1 7 2 4""".stripMargin

val input016 =
  """15 7
    |278 576 496 727 410 124 338 149 209 702 282 718 771 575 436""".stripMargin

def nonDivisibleSubset(k: Int, s: Array[Int]): Int = {
  val countsByMod = s.groupBy(n => n % k).view.mapValues(_.size)
  println(countsByMod.toMap)
  if (k % 2 == 0)
    (1 until k / 2).map(m => Math.max(countsByMod.getOrElse(m, 0), countsByMod.getOrElse(k - m, 0))).sum +
      (if (countsByMod.contains(k / 2)) 1 else 0) +
      (if (countsByMod.contains(0)) 1 else 0)
  else
    (1 to k / 2).map(m => Math.max(countsByMod.getOrElse(m, 0), countsByMod.getOrElse(k - m, 0))).sum +
      (if (countsByMod.contains(0)) 1 else 0)
}

nonDivisibleSubset(3, Array(1, 7, 2, 4)) // 3

nonDivisibleSubset(7, Array(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436)) // 11

nonDivisibleSubset(1, (1 to 5).toArray) // 1

nonDivisibleSubset(5, (1 to 10).toArray) // 5
