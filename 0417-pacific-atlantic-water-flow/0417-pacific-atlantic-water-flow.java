class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // Loop through each row: 
        // Left edge connects to Pacific, Right edge connects to Atlantic
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, Integer.MIN_VALUE, i, 0);
            dfs(heights, atlantic, Integer.MIN_VALUE, i, n - 1);
        }

        // Loop through each column: 
        // Top edge connects to Pacific, Bottom edge connects to Atlantic
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, Integer.MIN_VALUE, 0, j);
            dfs(heights, atlantic, Integer.MIN_VALUE, m - 1, j);
        }

        // Find cells that can reach both oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] visited, int prevHeight, int r, int c) {
        int m = heights.length;
        int n = heights[0].length;

        // Boundary checks and validation
        if (r < 0 || r >= m || c < 0 || c >= n || visited[r][c] || heights[r][c] < prevHeight) {
            return;
        }

        // Mark current cell as reachable
        visited[r][c] = true;

        // Traverse in all 4 directions (Up, Down, Left, Right)
        dfs(heights, visited, heights[r][c], r + 1, c);
        dfs(heights, visited, heights[r][c], r - 1, c);
        dfs(heights, visited, heights[r][c], r, c + 1);
        dfs(heights, visited, heights[r][c], r, c - 1);
    }
}