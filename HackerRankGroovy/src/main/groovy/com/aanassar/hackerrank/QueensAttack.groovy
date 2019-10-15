package com.aanassar.hackerrank

class QueensAttack {

    static int queensAttack(reader) {
        def (int n, int k) = reader.readLine().split(' ')*.toInteger()
        // Java is 0-based; the problem is 1-based.
        def (int queensRow, int queensColumn) = reader.readLine().split(' ')*.toInteger()*.minus(1)

        int upwardObstacle = -1
        int downwardObstacle = n
        int leftwardObstacle = -1
        int rightwardObstacle = n

        // Track only the row of the closest diagonal obstacle.
        int northwestObstacle = Math.max(-1, queensRow - queensColumn - 1)
        int southwestObstacle = Math.min(n, queensRow + queensColumn + 1)
        int northeastObstacle = Math.max(-1, queensRow - (n - queensColumn))
        int southeastObstacle = Math.min(n, queensRow + (n - queensColumn))

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
            } else if (row - column == queensRow - queensColumn) {
                // downward diagonal
                if (row < queensRow) {
                    if (row > northwestObstacle)
                        northwestObstacle = row
                } else if (row < southeastObstacle)
                    southeastObstacle = row
            } else if (row + column == queensRow + queensColumn) {
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
            int solution = queensAttack reader
            println solution
        }
    }
}