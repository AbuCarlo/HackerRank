def surfaceArea(a: Array[Array[Int]]): Int = {
  // Assume a rectangle.
  val areas = for {
    i <- 0 until a.length
    j <- 0 until a(i).length
    height = a(i)(j)
  } yield {
    // top and bottom
    var area = 2
    // Is there something above?
    if (i == 0)
      area += height
    else if (height > a(i -1)(j))
      area += height - a(i -1)(j)
    // Is there something below?
    if (i == a.length - 1)
      area += height
    else if (height > a(i + 1)(j))
      area += height - a(i + 1)(j)
    // Is there something to the left?
    if (j == 0)
      area += height
    else if (height > a(i)(j - 1))
      area += height - a(i)(j - 1)
    // To the right?
    if (j == a(i).length - 1)
      area += height
    else if (height > a(i)(j + 1))
      area += height - a(i)(j + 1)
    area
  }
  areas.sum
}

surfaceArea(Array(Array(1, 2, 3)))