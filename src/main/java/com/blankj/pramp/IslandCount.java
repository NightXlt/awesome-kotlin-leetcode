package com.blankj.pramp;

public class IslandCount {

    static int row, col;

    public static int numIslandsDFS(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;
        row = grid.length;
        col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') continue;
                count++;
                grid[i][j] = '0';
                dfs(grid, i, j);
            }
        }
        return count;
    }

    static int[][] dirs = {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };

    public static void dfs(char[][] grid, int r, int c) {
        for (int[] dir : dirs) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            if (newRow >= 0 && newRow < row && newCol >= 0 && newCol < col && grid[newRow][newCol] == '1') {
                grid[newRow][newCol] = '0';
                dfs(grid, newRow, newCol);
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println(numIslandsDFS(grid)); // Output: 3
    }

}
