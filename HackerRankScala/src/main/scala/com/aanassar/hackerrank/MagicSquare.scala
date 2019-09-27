package com.aanassar.hackerrank

object MagicSquare extends App {

  private val N = 3

  private val MAGIC_NUMBER = 15

  def validateRows(l: List[Int]) = {
    (0 until N).forall { y =>
      val slice = l.slice(y * N, y * N + N)
      slice.sum == MAGIC_NUMBER
    }
  }

  def validateColumns(l: List[Int]) = {
    (0 until N).forall { x =>
      val indices = (0 until N).map(_ * N + x)
      indices.map(l(_)).sum == MAGIC_NUMBER
    }
  }

  def validateDownwardDiagonal(l: List[Int]) = {
    val indices = (0 until N).map(y => y * N + y)
    indices.map(l(_)).sum == MAGIC_NUMBER
  }

  def validateUpwardDiagonal(l: List[Int]) = {
    val indices = (0 until N).map { y => y * N + (N - y - 1) }
    indices.map(l(_)).sum == MAGIC_NUMBER
  }

  def isMagic(l: List[Int]) = {
    validateRows(l) && validateColumns(l) && validateDownwardDiagonal(l) && validateUpwardDiagonal(l)
  }

  private val KNOWN_MAGIC_SQUARE = Array(Array(4, 9, 2), Array(3, 5, 7), Array(8, 1, 6)).flatten.toList

  private val checkKnown = isMagic(KNOWN_MAGIC_SQUARE)

  assert(checkKnown)
}
