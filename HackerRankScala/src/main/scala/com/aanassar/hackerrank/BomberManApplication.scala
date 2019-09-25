package com.aanassar.hackerrank

object BomberManApplication extends App {
  import scala.annotation.tailrec

  val BOMB = 'O'

  def bomberMan(n: Int, grid: Array[String]): Array[String] = {

    val state: Array[Array[Option[Int]]] = grid.map(_.toCharArray.map(c => if (c == BOMB) {
      Option(0)
    } else {
      Option.empty
    }).toArray)

    def plant(t: Int): Unit = {
      for {
        y <- state.indices
        row = state(y)
        x <- row.indices
        cell = row(x)
      } {
        if (cell.isEmpty) {
          row(x) = Option(t)
          println(s"Planted $t at ($y, $x)")
        }
      }
    }

    def detonateCell(y: Int, x: Int): Unit = {
      for (i <- y - 1 to y + 1; j <- x - 1 to x + 1) {
        if (i >= 0 && i < state.length && j >= 0 && j < state(i).length) {
          state(i)(j) = Option.empty
          println(s"Detonated cell at ($j, $i)")
        }
      }
    }

    def detonate(t: Int): Unit = {
      for (y <- state.indices; x <- state(y).indices) {
        if (state(y)(x).map(t - _ >= 3).getOrElse(false))
          detonateCell(y, x)
        println(s"Detonated cell at ($x, $y)")

      }
    }

    @tailrec
    def iterate(t: Int): Unit = {
      println(s"Iteration at $t")
      if (t > n) {
        ()
      } else if (t % 2 == 0) {
        plant(t);
        iterate(t + 1)
      } else {
        detonate(t)
        iterate(t + 1)
      }
    }

    def format() = {
      state.map(row => row.map(_.map(_ => BOMB).getOrElse('.')).mkString(""))
    }

    n match {
      case 1 => grid
      case _ => iterate(2); format();
    }
  }

  val input = Array("...", ".O.", "...", "...")

  val output = bomberMan(3, input)

  println(output.mkString("\n"))
}
