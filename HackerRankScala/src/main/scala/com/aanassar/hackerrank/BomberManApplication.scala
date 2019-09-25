package com.aanassar.hackerrank

object BomberManApplication extends App {
  import scala.annotation.tailrec

  def bomberMan(n: Int, grid: Array[String]): Array[String] = {

    val BOMB = 'O'

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

    def detonateWithNeighbors(y: Int, x: Int): Unit = {
      println(s"Detonated neighbors of ($y, $x)")
      state(y)(x) = Option.empty
      if (y > 0)
        state(y - 1)(x) = Option.empty
      if (y < state.length - 1)
        state(y + 1)(x) = Option.empty
      if (x > 0)
        state(y)(x - 1) = Option.empty
      if (x < state(y).length - 1)
        state(y)(x + 1) = Option.empty
    }

    def detonate(t: Int): Unit = {
      val bombs = for {
        y <- state.indices
        x <- state(y).indices
        if state(y)(x).map(t - _ == 3).getOrElse(false)
      } yield (y, x)
      // First, find all live bombs. Then implement detonation.
      bombs.foreach(b => detonateWithNeighbors(b._1, b._2))
    }

    @tailrec
    def iterate(t: Int): Unit = {
      println(s"Iteration at $t: \n${format().mkString("\n")}")
      if (t > n) {
        ()
      } else if (t % 2 == 0) {
        plant(t)
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

  val input = Array(".......","...O...", "....O..", ".......","OO.....", "OO.....")

  val output = bomberMan(3, input)
}
