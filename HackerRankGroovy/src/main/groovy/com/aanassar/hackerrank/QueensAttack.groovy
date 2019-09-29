package com.aanassar.hackerrank

class QueensAttack {

    static int queensAttack(List input) {
        def (int n, int k) = input[0].split(' ')*.toInteger()
        // Java is 0-based; the problem is 1-based.
        def (int queensRow, int queensColumn) = input[1].split(' ')*.toInteger()*.minus(1)
        Map obstacles = [:]
        int upwardAttacks = queensRow
        int leftwardAttacks = queensColumn
        int downwardAttacks = n - queensRow - 1
        int rightwardAttacks = n - queensColumn - 1
        int southeastAttacks = Math.min(n - queensColumn - 1, n - queensRow - 1)
        int northwestAttacks = Math.min(queensColumn, queensRow)
        int southwestAttacks = Math.min(n - queensRow - 1, queensColumn)
        int northeastAttacks = Math.min(queensRow, n - queensColumn - 1)
        for (line in input.drop(2)) {
            def (int row, int column) = line.split(' ')*.toInteger()*.minus(1)
            obstacles.computeIfAbsent(row, { new HashSet() }).add(column)
            if (row == queensRow) {
                // If the obstacle is between the queen and the edge...
                def horizontalDistance = Math.abs(queensColumn - column) - 1
                if (column < queensColumn && horizontalDistance < leftwardAttacks) {
                    leftwardAttacks = horizontalDistance
                } else if (horizontalDistance < rightwardAttacks) {
                    rightwardAttacks = horizontalDistance
                }
            } else if (column == queensColumn) {
                def verticalDistance = Math.abs(queensRow - row) - 1
                if (row < queensRow && verticalDistance < upwardAttacks) {
                    upwardAttacks = verticalDistance
                } else if (verticalDistance < downwardAttacks) {
                    downwardAttacks = verticalDistance
                }
            } else if (row - column == queensRow - queensColumn) {
                final int attacks = Math.min(Math.abs(row - queensRow), Math.abs(column - queensColumn)) - 1
                if (row < queensRow && attacks < northwestAttacks)
                    northwestAttacks = attacks
                else if (attacks < southeastAttacks)
                    southeastAttacks = attacks
            } else if (row + column == queensRow + queensColumn) {
                final int attacks = Math.min(Math.abs(row - queensRow), Math.abs(column - queensColumn)) - 1
                if (row < queensRow && attacks < northeastAttacks) {
                    northeastAttacks = attacks
                } else if (attacks < southwestAttacks) {
                    southwestAttacks = attacks
                }
            }
        }

        upwardAttacks + downwardAttacks + leftwardAttacks + rightwardAttacks +
                northwestAttacks + northeastAttacks +
                southwestAttacks + southeastAttacks
    }
}