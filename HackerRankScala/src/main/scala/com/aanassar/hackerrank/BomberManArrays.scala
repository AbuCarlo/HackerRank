package com.aanassar.hackerrank

object BomberManArrays extends App {

  def bomberMan(n: Int, grid: Array[String]): Array[String] = {

    val BOMB = 'O'
    val NO_BOMB = '.'

    if (n < 2) {
      grid
    } else {

      var oldBombs = grid.map(_.toCharArray)
      var newBombs = grid.map(row => Array.fill(row.length)(NO_BOMB))

      def swap() = {
        val tmp = oldBombs
        oldBombs = newBombs
        newBombs = tmp
      }

      def formatOutput() = {
        val result = Array.ofDim[Char](grid.length, grid(0).length)
        for (y <- grid.indices; x <- grid(y).indices) {
          result(y)(x) = {
            if (oldBombs(y)(x) == BOMB || newBombs(y)(x) == BOMB)
              BOMB;
            else NO_BOMB
          }
        }
        result.map(_.mkString)
      }

      def detonateNeighbors(y: Int, x: Int): Unit = {
        println(s"Detonated neighbors of ($y, $x)")
        if (y > 0)
          newBombs(y - 1)(x) = NO_BOMB
        if (y < newBombs.length - 1)
          newBombs(y + 1)(x) = NO_BOMB
        if (x > 0)
          newBombs(y)(x - 1) = NO_BOMB
        if (x < newBombs(y).length - 1)
          newBombs(y)(x + 1) = NO_BOMB
      }

      var t = 2

      while (t <= n) {

        if (t % 2 == 1) {
          // Detonate.
          for (y <- oldBombs.indices; row = oldBombs(y); x <- row.indices if row(x) == BOMB) {
            detonateNeighbors(y, x)
            row(x) = NO_BOMB
          }

          swap()

        } else {

          // Plant new bombs.

          for (y <- newBombs.indices; row = newBombs(y); x <- row.indices if oldBombs(y)(x) != BOMB && newBombs(y)(x) != BOMB) {
            row(x) = BOMB
          }
        }
        t += 1
      }

      formatOutput()
    }

  }

  // val input = Array("...", ".O.", "...")

  val input = Array(".......", "...O...", "....O..", ".......", "OO.....", "OO.....")

  val output = bomberMan(3, input)

  println(output.mkString("\n"))
}
