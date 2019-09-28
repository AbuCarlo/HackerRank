package com.aanassar.hackerrank

object MagicSquare extends App {

  private val N = 3

  private val MAGIC_NUMBER = 15

  def validateRows(l: IndexedSeq[Int]) = {
    (0 until N).forall { y =>
      val slice = l.slice(y * N, y * N + N)
      slice.sum == MAGIC_NUMBER
    }
  }

  def validateColumns(l: IndexedSeq[Int]) = {
    (0 until N).forall { x =>
      val indices = (0 until N).map(_ * N + x)
      indices.map(l(_)).sum == MAGIC_NUMBER
    }
  }

  def validateDownwardDiagonal(l: IndexedSeq[Int]) = {
    val indices = (0 until N).map(y => y * N + y)
    indices.map(l(_)).sum == MAGIC_NUMBER
  }

  def validateUpwardDiagonal(l: IndexedSeq[Int]) = {
    val indices = (0 until N).map { y => y * N + (N - y - 1) }
    indices.map(l(_)).sum == MAGIC_NUMBER
  }

  def isMagic(l: IndexedSeq[Int]) = {
    validateRows(l) && validateColumns(l) && validateDownwardDiagonal(l) && validateUpwardDiagonal(l)
  }

  private val KNOWN_MAGIC_SQUARES = (1 to 9).permutations.filter(isMagic(_)).toList

  def formingMagicSquare(s: Array[Array[Int]]): Int = {

    def calculateDifference(input: IndexedSeq[Int], square: IndexedSeq[Int]) = {
      input.zip(square).map { case (x, y) => Math.abs(x - y) }.sum
    }

    val input = s.flatten
    KNOWN_MAGIC_SQUARES.map(calculateDifference(input, _)).min
  }

  private val KNOWN_MAGIC_SQUARE = Array(Array(4, 9, 2), Array(3, 5, 7), Array(8, 1, 6)).flatten

  private val checkKnown = isMagic(KNOWN_MAGIC_SQUARE)

  assert(checkKnown)

  private val sampleOne = Array(Array(4, 9, 2), Array(3, 5, 7), Array(8, 1, 5))

  private val answerOne = formingMagicSquare(sampleOne)

  assert(answerOne == 1)

  private val sampleTwo = Array(Array(4, 8, 2), Array(4, 5, 7), Array(6, 1, 6))

  private val answerTwo = formingMagicSquare(sampleTwo)

  assert(answerTwo == 4)
}

