package com.aanassar.hackerrank;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

// https://www.hackerrank.com/challenges/bomber-man/problem

public class BomberManApplication {

    public static final char BOMB = 'O';
    public static final char NO_BOMB = '.';

    private static final char EVEN_BOMB = 'E';
    private static final char ODD_BOMB = 'O';

    private static void detonateNeighbors(char[][] grid, int y, int x) {
        final char olderBombType = grid[y][x];
        final char newerBombType = olderBombType == ODD_BOMB ? EVEN_BOMB : ODD_BOMB;
        if (y > 0 && grid[y - 1][x] == newerBombType)
            grid[y - 1][x] = NO_BOMB;
        if (y < grid.length - 1 && grid[y + 1][x] == newerBombType)
            grid[y + 1][x] = NO_BOMB;
        if (x > 0 && grid[y][x - 1] == newerBombType)
            grid[y][x - 1] = NO_BOMB;
        if (x < grid[y].length - 1 && grid[y][x + 1] == newerBombType)
            grid[y][x + 1] = NO_BOMB;
    }

    public static String[] bomberMan(int n, String[] grid) {

        final int height = grid.length;
        final int width = grid[0].length();

        // Bombs were planted at t = 0 (i.e. that's the input), and
        // nothing was due to detonate at t = 1.

        if (n < 2) {
            // After interval 0 (i.e. the input), nothing will have changed. After
            // interval 1, no bombs will have exploded yet.
            return grid;
        } else if (n % 2 == 0) {
            // This is a performance hack. On even-numbered intervals, new bombs will be planted in every
            // empty cell, meaning that every cell will end up with a bomb. So we can skip the algorithm.
            return createFullGrid(grid);
        }

        // IT TURNS OUT THAT IT'S A TRICK QUESTION: THE GRID CYCLES.
        // The results of iterations 3, 7, 11 &c. are identical;
        // likewise iterations 5, 9, 13 &c.

        n = (n % 4) == 3 ? 3 : 5;

        // We know that this algorithm was called with an *odd* number of intervals. Therefore we can combine
        // planting new bombs and detonating older ones into a single loop iteration. Bombs planted during interval
        // 0 should detonate during interval 3. We'll call them "odd" because 3 / 2 is 1, an odd number. Bombs
        // we plant during iteration 2 are even because they will detonation in interval 5 (and 5 / 2 is 2).
        // So we don't need to know when a bomb was planted, only whether it was 1 or 3 iterations ago.

        // So the letter 'O' will stand for odd in our implementation;

        char[][] oddEvenGrid = Arrays.stream(grid).map(String::toCharArray).toArray(char[][]::new);

        for (int t = 2; t <= n; t += 2) {

            final boolean isEven = ((t + 1) / 2) % 2 == 0;
            // If an "even" iteration is coming up, plant odd bombs now, and v.v.
            final char bombsToPlant = isEven ? ODD_BOMB : EVEN_BOMB;
            for (int i = 0; i < height; ++i) {
                final char[] oddEvenRow = oddEvenGrid[i];
                for (int j = 0; j < width; ++j) {
                    if (oddEvenRow[j] == NO_BOMB)
                        oddEvenRow[j] = bombsToPlant;
                }
            }

            // Now detonate the bombs planted in the last iteration.
            final char bombsToDetonate = isEven ? EVEN_BOMB : ODD_BOMB;
            for (int y = 0; y < height; ++y) {
                final char[] row = oddEvenGrid[y];
                for (int x = 0; x < width; ++x) {
                    if (row[x] == bombsToDetonate) {
                        detonateNeighbors(oddEvenGrid, y, x);
                        row[x] = NO_BOMB;
                    }
                }
            }

        }

        return formatOutput(oddEvenGrid);
    }

    private static String[] createFullGrid(String[] grid) {
        final char[] allBombs = new char[grid[0].length()];
        Arrays.fill(allBombs, BOMB);
        final String row = new String(allBombs);
        final String[] fullGrid = new String[grid.length];
        Arrays.fill(fullGrid, row);
        return fullGrid;
    }

    private static String[] formatOutput(char[][] oddEvenGrid) {
        char[][] output = new char[oddEvenGrid.length][oddEvenGrid[0].length];
        for (int y = 0; y < oddEvenGrid.length; ++y) {
            final char[] oddEvenRow = oddEvenGrid[y];
            final char[] outputRow = output[y];
            for (int x = 0; x < oddEvenRow.length; ++x) {
                outputRow[x] = oddEvenRow[x] == NO_BOMB ? NO_BOMB : BOMB;
            }
        }
        return Arrays.stream(output).map(String::new).toArray(String[]::new);
    }

    public static String[] fromInput(Path inputPath) throws IOException {
        List<String> input = Files.readAllLines(inputPath, StandardCharsets.US_ASCII);
        String[] inputArguments = input.get(0).split(" ");
        final int height = Integer.parseInt(inputArguments[0]);
        final int width = Integer.parseInt(inputArguments[1]);
        final int n = Integer.parseInt(inputArguments[2]);

        assert input.size() == height + 1;
        String[] grid = input.subList(1, input.size()).toArray(new String[0]);
        assert Arrays.stream(grid).allMatch(row -> row.length() == width);
        return bomberMan(n, grid);
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("This application requires exactly one input argument, the input.");
        } else {
            Path inputPath = Paths.get(args[0]);
            String[] output = fromInput(inputPath);
        }
    }
}
