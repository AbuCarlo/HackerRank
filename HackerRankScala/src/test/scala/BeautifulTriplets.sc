
def findPairs(a: Seq[Int], d: Int): Seq[(Int, Int)] = {
  a match {
    case Seq() => Seq.empty
    case Seq(_) => Seq.empty
    case e :: t => t.map((e, _)).filter(p => p._2 - p._1 == d) ++ findPairs(t, d)
  }
}

def factorial(n: Int): Long = {
  if (n < 2)
    1
  else {
    var result = 1L
    for (i <- 1 to n) {
      result *= i
    }
    result
  }
}

def findTriplets(a: Array[Int], d: Int) = {

}


factorial(1)
factorial(5)

findPairs(Seq(1, 2, 3, 4, 5), 2).toArray

findTriplets(Array(1, 2, 4, 5, 7, 8, 10), 3)

// Abort! We don't even need to know the pairs.