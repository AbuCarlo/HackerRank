package com.aanassar.hackerrank

object GridSearchApplication extends App {

  def gridSearch(grid: Array[String], pattern: Array[String]): String = {

    def indexesOf(s: String, p: String) = {
      println(s"Finding $p in $s")

      @scala.annotation.tailrec
      def nextIndexOf(indices: Seq[Int], i: Int): Seq[Int] = {
        val index = s.indexOf(p, i)
        if (index == -1)
          indices
        else {
          println(s"Found at $index")
          nextIndexOf(indices :+ index, index + 1)
        }
      }

      nextIndexOf(Seq.empty, 0)
    }

    val corners = for {
      row <- 0 to grid.length - pattern.length
      column <- indexesOf(grid(row), pattern(0))
    } yield {
      (row, column)
    }

    val matches = corners.filter { case (row, column) =>
      (1 until pattern.length).forall { r =>
        val s = grid(row + r)
        val p = pattern(r)
        s.regionMatches(column, p, 0, pattern(r).length)
      }
    }

    if (matches.isEmpty)
      "NO"
    else
      "YES"
  }

  private  val lineEnding = raw"[\r\n]+"

  def testGridSearch(grid: String, pattern: String) = {
    gridSearch(grid.split(lineEnding), pattern.split(lineEnding))
  }

  val sampleGrid = """1234567890
                     |0987654321
                     |1111111111
                     |1111111111
                     |2222222222  """.stripMargin

  val samplePattern = """876543
                        |111111
                        |111111""".stripMargin

  testGridSearch(sampleGrid, samplePattern)

  val grid_0_0 = """7283455864
                   |6731158619
                   |8988242643
                   |3830589324
                   |2229505813
                   |5633845374
                   |6473530293
                   |7053106601
                   |0834282956
                   |4607924137""".stripMargin

  val pattern_0_0 = """9505
                      |3845
                      |3530""".stripMargin

  testGridSearch(grid_0_0, pattern_0_0)

  val grid_0_1 = """400453592126560
                   |114213133098692
                   |474386082879648
                   |522356951189169
                   |887109450487496
                   |252802633388782
                   |502771484966748
                   |075975207693780
                   |511799789562806
                   |404007454272504
                   |549043809916080
                   |962410809534811
                   |445893523733475
                   |768705303214174
                   |650629270887160""".stripMargin

  val pattern_0_1 = """99
                   |99""".stripMargin

  testGridSearch(grid_0_1, pattern_0_1)
}
