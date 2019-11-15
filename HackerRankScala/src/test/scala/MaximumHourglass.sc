
def hourglassSum(arr: Array[Array[Int]]): Int = {
  val hourglasses = for {
    i <- 1 to 4
    j <- 1 to 4
  } yield {
    arr(i - 1).slice(j - 1, j + 2).sum + arr(i)(j) + arr(i + 1).slice(j - 1, j + 2).sum
  }
  hourglasses.max
}
