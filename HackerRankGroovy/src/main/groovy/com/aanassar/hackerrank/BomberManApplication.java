package com.aanassar.hackerrank;

import java.util.Arrays;

public class BomberManApplication {

    private static final char BOMB = 'O';
    private static final char NO_BOMB = '.';

    private static void detonateNeighbors(char[][] grid, int y, int x) {
        if (y > 0)
            grid[y - 1][x] = NO_BOMB;
        if (y < grid.length - 1)
            grid[y + 1][x] = NO_BOMB;
        if (x > 0)
            grid[y][x - 1] = NO_BOMB;
        if (x < grid[y].length - 1)
            grid[y][x + 1] = NO_BOMB;
    }

    public static String[] bomberMan(int n, String[] grid) {

        final int height = grid.length;
        final int width = grid[0].length();

        // Bombs were planted at t = 0 (i.e. that's the input), and
        // nothing was due to detonate at t = 1.

        if (n < 2) {
            return grid;
        } else if (n %2 == 0) {
            // This is a performance hack. On even-numbered intervals, new bombs will be planted in every
            // empty cell, meaning that every cell will end up with a bomb. So we can skip the algorithm.
            final char[] allBombs = new char[width];
            Arrays.fill(allBombs, BOMB);
            final String row = new String(allBombs);
            final String[] fullGrid = new String[grid.length];
            Arrays.fill(fullGrid, row);
            return fullGrid;
        }

        char[][] oldBombs = Arrays.stream(grid).map(String::toCharArray).toArray(char[][]::new);
        final char[] noBombs = new char[width];
        Arrays.fill(noBombs, NO_BOMB);
        char[][] newBombs = Arrays.stream(grid).map(row -> Arrays.copyOf(noBombs, width)).toArray(char[][]::new);

        for (int t = 2; t <= n; ++t) {

            if (t % 2 == 1) {
                // Detonate.
                for (int y = 0; y < height; ++y) {
                    final char[] row = oldBombs[y];
                    for (int x = 0; x < width; ++x) {
                        if (row[x] == BOMB) {
                            detonateNeighbors(newBombs, y, x);
                            row[x] = NO_BOMB;
                        }
                    }
                }

                // Swap the arrays.
                final char[][] tmp = oldBombs;
                oldBombs = newBombs;
                newBombs = tmp;

            } else {

                // Plant new bombs.

                for (int y = 0; y < height; ++y) {
                    final char[] row = newBombs[y];
                    for (int x = 0; x < width; ++x) {
                        if (row[x] != BOMB && oldBombs[y][x] != BOMB) {
                            row[x] = BOMB;
                        }
                    }
                }

            }
        }

        return formatOutput(newBombs, oldBombs);
    }

    private static String[] formatOutput(char[][] l, char[][] r) {
        for (int y = 0; y < l.length; ++y) {
            for (int x = 0; x < l[y].length; ++x) {
                if (r[y][x] == BOMB) {
                    l[y][x] = BOMB;
                }
            }
        }
        return Arrays.stream(l).map(String::new).toArray(String[]::new);
    }
}
