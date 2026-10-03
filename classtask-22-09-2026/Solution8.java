import java.util.*;

class Solution8 {
    // number of islands
    public static void dfs(int i,int j, int[][] grid){
        if(i<0 || 0>j || i>=grid.length || j>=grid[0].length || grid[i][j]==0) return;
        grid[i][j]=0;
        dfs(i+1,j,grid);
        dfs(i-1,j,grid);
        dfs(i,j+1,grid);
        dfs(i,j-1,grid);
    }
    public static int numIslands(int [][] grid) {
        int c=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    dfs(i,j,grid);
                    c++;
                }
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n= scan.nextInt();
        int m =scan.nextInt();
        int [][] grid = new int[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                grid[i][j]= scan.nextInt();
            }
        }

        System.out.println("Number of islands: " + numIslands(grid));
    }

}