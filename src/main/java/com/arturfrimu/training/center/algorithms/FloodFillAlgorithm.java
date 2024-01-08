package com.arturfrimu.training.center.algorithms;

public class FloodFillAlgorithm {

    // Directions: up, down, left, right
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    // Function to apply flood fill
    public static void floodFill(int[][] screen, int x, int y, int newColor) {
        int prevColor = screen[x][y];
        floodFillUtil(screen, x, y, prevColor, newColor);
    }

    // Utility function to apply flood fill
    private static void floodFillUtil(int[][] screen, int x, int y, int prevColor, int newColor) {
        // Base cases
        if (x < 0 || x >= screen.length || y < 0 || y >= screen[0].length) {
            return;
        }
        if (screen[x][y] != prevColor) {
            return;
        }

        // Replace the color at (x, y)
        screen[x][y] = newColor;

        // Recur for all adjacent pixels
        for (int i = 0; i < 4; i++) {
            floodFillUtil(screen, x + dx[i], y + dy[i], prevColor, newColor);
        }
    }

    // Function to print the screen
    public static void printScreen(int[][] screen) {
        for (int[] row : screen) {
            for (int pixel : row) {
                System.out.print(pixel + " ");
            }
            System.out.println();
        }
    }

    // Driver code
    public static void main(String[] args) {
        int[][] screen = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 0, 0, 1},
                {1, 1, 1, 1}
        };

        System.out.println("Original Screen:");
        printScreen(screen);

        int x = 2, y = 2, newColor = 3;
        floodFill(screen, x, y, newColor);

        System.out.println("\nScreen After Flood Fill:");
        printScreen(screen);
    }
}

