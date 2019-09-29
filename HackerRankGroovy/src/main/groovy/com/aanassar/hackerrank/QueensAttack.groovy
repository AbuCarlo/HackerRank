package com.aanassar.hackerrank

class QueensAttack {

    static int queensAttack(List input) {
        def (int n, int k) = input[0].split(' ')*.toInteger()
        // Java is 0-based; the problem is 1-based.
        def (int queensRow, int queensColumn) = input[1].split(' ')*.toInteger()*.minus(1)
        Map obstacles = [:]
        int upwardObstacle = -1
        int downwardObstacle = n

        int leftwardObstacle = -1
        int rightwardObstacle = n

        int southeastAttacks = Math.min(n - queensColumn - 1, n - queensRow - 1)
        int northwestAttacks = Math.min(queensColumn, queensRow)
        int southwestAttacks = Math.min(n - queensRow - 1, queensColumn)
        int northeastAttacks = Math.min(queensRow, n - queensColumn - 1)
        for (line in input.drop(2)) {
            def (int row, int column) = line.split(' ')*.toInteger()*.minus(1)
            obstacles.computeIfAbsent(row, { new HashSet() }).add(column)
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

        (queensRow - upwardObstacle - 1) + (downwardObstacle - queensRow - 1) +
                (queensColumn - leftwardObstacle - 1) + (rightwardObstacle - queensColumn - 1) +
                northwestAttacks + northeastAttacks +
                southwestAttacks + southeastAttacks
    }
}