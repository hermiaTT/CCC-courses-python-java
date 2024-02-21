import java.util.*;
class Solutions_java{
    public static void main(String[] args){
        int c = 7;
        int[][] colors = {{0,0,1,1,0,1,0},{0,0,1,0,1,0,0}};
        System.out.println(wetWarn(c,colors));
    }
    
    // Problem J1: Deliv-e-droid
    public static int deliverScore(){
        Scanner input = new Scanner(System.in);  
        
        //get delivered packages
        int p = input.nextInt();
        //get lost packages
        int c = input.nextInt();
        //initilize total points by delivered and lost
        int f = p*50 - c*10;
        //check if won the bonus score
        if(p>c){
            f += 500;
        }
        return f;
    }
    
    // Problem J2: Chili Peppers
    public static int chiliPepper(){
        Scanner input = new Scanner(System.in);
        
        //construct key value pair for all peppers  
        Map<String, Integer> shu = new HashMap<String, Integer>();
        shu.put("Poblano", 1500);
        shu.put("Mirasol", 6000);
        shu.put("Serrano", 15500);
        shu.put("Cayenne", 40000);
        shu.put("Thai", 75000);
        shu.put("Habanero", 125000);

        //get number of pepper by user
        int n = input.nextInt();
        
        int total = 0;
        String peper = "";
        input.nextLine();
        //get n times of pepper entered by user
        for(int i=0; i<n;i++){
            peper = input.nextLine();
            //check the spiciness of input by checking the SHU map
            //add to total spiciness
            total += Integer.parseInt(shu.get(peper).toString());
        }
        return total;
    }

    //Problem J3: Special Event
    public static String eventDays(int n, String[] availability){
        String res = "";
        int curMax = 0;
        int curSum = 0;
        for(int j=0; j<5; j++){
            curSum = 0;
            for(int i=0; i<n; i++){
                if(availability[i].charAt(j) == 'Y'){
                    curSum ++;
                }
            }
            if(curSum > curMax){
                curMax = curSum;
                res = String.valueOf(j+1)+ ",";
            }
            else if(curSum == curMax){
                res += String.valueOf(j+1) + ",";
            }
        }
        return res.substring(0, res.length() - 1);
    }
    
    // Problem J4/S1: Trianglane
    public static int wetWarn(int c, int[][] colors){
        int res = 0;
        for (int i=0;i<2;i++){
            for (int j=0; j<c; j++){
                if(colors[i][j] == 1){
                    res += 2+ wetWarn_dfs(colors, i,j);
                }
            }
        }
        return res;
    }

    public static int wetWarn_dfs(int[][] colors, int i, int j){
        if(i < 0 || i >= colors.length || j < 0 || j >= colors[0].length || colors[i][j] == 0){
            return 0;
        }
        colors[i][j] = 0;
        int area = 1;
        area += wetWarn_dfs(colors, i + 1, j);
        area += wetWarn_dfs(colors, i, j + 1);
        return area;
    }

    //Problem J5: CCC Word Hunt
   public static int wordHunt(char[][] grid, String letter, int n, int m){
        int res= 0;
        for (int i =0; i< n; i++){
            for(int j=0;j<m;j++){
                if(grid[i][j] == letter.charAt(0)){
                    res += wordHunt_dfs(grid, letter, i, j, 0);
                }
            }
        }
        return res;
   }
     // Depth-First Search function
     private static int wordHunt_dfs(char[][] grid, String word, int row, int col, int index) {
        // Base case: If index reaches the length of the word, the entire word has been found
        if (index == word.length()) {
            return 1;
        }
        
        // If the current cell is out of bounds or doesn't match the current character of the word, return 0
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != word.charAt(index)) {
            return 0;
        }
        
        // Mark the current cell as visited by changing its value to a non-letter character
        char temp = grid[row][col];
        grid[row][col] = '.';
        
        // Explore adjacent cells in all directions
        int count = wordHunt_dfs(grid, word, row + 1, col, index + 1) +  // Down
                 wordHunt_dfs(grid, word, row - 1, col, index + 1) +  // Up
                 wordHunt_dfs(grid, word, row, col + 1, index + 1) +  // Right
                 wordHunt_dfs(grid, word, row, col - 1, index + 1) +  // Left
                 wordHunt_dfs(grid, word, row + 1, col + 1, index + 1) +  // Diagonal down-right
                 wordHunt_dfs(grid, word, row + 1, col - 1, index + 1) +  // Diagonal down-left
                 wordHunt_dfs(grid, word, row - 1, col + 1, index + 1) +  // Diagonal up-right
                 wordHunt_dfs(grid, word, row - 1, col - 1, index + 1);   // Diagonal up-left
        
        // Restore the original value of the current cell
        grid[row][col] = temp;
        
        return count;
    }
}