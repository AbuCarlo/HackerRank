package com.aanassar.hackerrank

import java.util.stream.Collectors

/**
 * https://www.hackerrank.com/challenges/queens-attack-2/problem
*/
public class QueensAttack {

    static class Position {
        final int row
        final int column

        Position(int row, int column) {
            this.row = row
            this.column = column
        }
    }

    static final Comparator<Position> ROW_COMPARATOR = Comparator.comparingInt({ Position p -> p.row })
    static final Comparator<Position> COLUMN_COMPARATOR = Comparator.comparingInt({ Position p -> p.column })

    static int queensAttackOptimized(Reader reader) {
        def (int n, int k) = reader.readLine().split(' ')*.toInteger()
        // Java is 0-based; the problem is 1-based.
        final def (int queensRow, int queensColumn) = reader.readLine().split(' ')*.toInteger()*.minus(1)

        // Track only the row of the closest diagonal obstacle.
        final int diagonalDifference = queensRow - queensColumn
        final int diagonalSum = queensRow + queensColumn

        final List<Position> obstacles = new BufferedReader(reader).lines().map { line ->
            int[] coordinates = line.split(' ')*.toInteger()*.minus(1)
            new Position(coordinates[0], coordinates[1])
        }.collect(Collectors.toList())


        def upwardObstacle = obstacles.stream().filter { p -> p.column == queensColumn && p.row < queensRow }.max(ROW_COMPARATOR).orElse(new Position(-1, queensColumn))
        def downwardObstacle = obstacles.stream().filter { p -> p.column == queensColumn && p.row > queensRow }.min(ROW_COMPARATOR).orElse(new Position(n, queensColumn))

        def leftwardObstacle = obstacles.stream().filter { p -> p.row == queensRow && p.column < queensColumn }.max(COLUMN_COMPARATOR).orElse(new Position(queensRow, -1))
        def rightwardObstacle = obstacles.stream().filter { p -> p.row == queensRow && p.column > queensColumn }.min(COLUMN_COMPARATOR).orElse(new Position(queensRow, n))

        def northwestObstacle =  obstacles.stream().filter { p -> p.row < queensRow && p.row - p.column == diagonalDifference }.max(ROW_COMPARATOR).orElse(new Position(Math.max(-1, diagonalDifference - 1), -1))
        def southeastObstacle =  obstacles.stream().filter { p -> p.row > queensRow && p.row - p.column == diagonalDifference }.min(ROW_COMPARATOR).orElse(new Position(Math.min(n, diagonalDifference + n), n))

        def southwestObstacle =  obstacles.stream().filter { p -> p.row > queensRow && p.row + p.column == diagonalSum }.min(ROW_COMPARATOR).orElse(new Position(Math.min(n, diagonalSum + 1), -1))
        def northeastObstacle =  obstacles.stream().filter { p -> p.row < queensRow && p.row + p.column == diagonalSum }.max(ROW_COMPARATOR).orElse(new Position(Math.max(-1, diagonalSum - n), n))

        (queensRow - upwardObstacle.row - 1) + (downwardObstacle.row - queensRow - 1) +
                (queensColumn - leftwardObstacle.column - 1) + (rightwardObstacle.column - queensColumn - 1) +
                (queensRow - northwestObstacle.row - 1) + (queensRow - northeastObstacle.row - 1) +
                (southwestObstacle.row - queensRow - 1) + (southeastObstacle.row - queensRow - 1)
    }

    static int queensAttack(Reader reader) {
        def (int n, int k) = reader.readLine().split(' ')*.toInteger()
        // Java is 0-based; the problem is 1-based.
        def (int queensRow, int queensColumn) = reader.readLine().split(' ')*.toInteger()*.minus(1)

        int upwardObstacle = -1
        int downwardObstacle = n
        int leftwardObstacle = -1
        int rightwardObstacle = n

        final int diagonalDifference = queensRow - queensColumn
        final int diagonalSum = queensRow + queensColumn

        // Track only the row of the closest diagonal obstacle.

        int northwestObstacle = Math.max(-1, diagonalDifference - 1)
        int southwestObstacle = Math.min(n, diagonalSum + 1)
        int northeastObstacle = Math.max(-1, diagonalSum - n)
        int southeastObstacle = Math.min(n, diagonalDifference + n)

        reader.eachLine { line ->
            // See above.
            def (int row, int column) = line.split(' ')*.toInteger()*.minus(1)
            if (row == queensRow) {
                if (column < queensColumn) {
                    if (column > leftwardObstacle)
                        leftwardObstacle = column
                } else if (column < rightwardObstacle) {
                    rightwardObstacle = column
                }
            } else if (column == queensColumn) {
                if (row < queensRow) {
                    if (row > upwardObstacle)
                        upwardObstacle = row
                } else if (row < downwardObstacle) {
                    downwardObstacle = row
                }
            } else if (row - column == diagonalDifference) {
                // downward diagonal
                if (row < queensRow) {
                    if (row > northwestObstacle)
                        northwestObstacle = row
                } else if (row < southeastObstacle)
                    southeastObstacle = row
            } else if (row + column == diagonalSum) {
                // upward diagonal
                if (row < queensRow) {
                    if (row > northeastObstacle)
                        northeastObstacle = row
                } else if (row < southwestObstacle) {
                    southwestObstacle = row
                }
            }
        }

        (queensRow - upwardObstacle - 1) + (downwardObstacle - queensRow - 1) +
                (queensColumn - leftwardObstacle - 1) + (rightwardObstacle - queensColumn - 1) +
                (queensRow - northwestObstacle - 1) + (queensRow - northeastObstacle - 1) +
                (southwestObstacle - queensRow - 1) + (southeastObstacle - queensRow - 1)
    }

    static void main(String[] args) {
        System.in.withReader("US-ASCII") { reader ->
            int solution = queensAttackOptimized reader
            println solution
        }
    }
}