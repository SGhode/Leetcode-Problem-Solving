class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int numberOfIslands = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Traverse every cell in the 2D grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If a piece of land ('1') is found, it marks a new island
                if (grid[r][c] == '1') {
                    numberOfIslands++;
                    // Trigger DFS to submerge/sink the connected landmass
                    sinkIslandDFS(grid, r, c);
                }
            }
        }
        
        return numberOfIslands;
    }
    
    private void sinkIslandDFS(char[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Base case: Check bounds and whether the current cell is water ('0')
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == '0') {
            return;
        }
        
        // Sink the current land cell by turning it to water to prevent reprocessing
        grid[r][c] = '0';
        
        // Recursively visit all 4 adjacent directions (Up, Down, Left, Right)
        sinkIslandDFS(grid, r - 1, c); // Up
        sinkIslandDFS(grid, r + 1, c); // Down
        sinkIslandDFS(grid, r, c - 1); // Left
        sinkIslandDFS(grid, r, c + 1); // Right
    }
}
