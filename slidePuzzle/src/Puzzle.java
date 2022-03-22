import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Puzzle implements Comparable<Puzzle> {


    private final int[][] puzzle;
    private int len=0;

    public Puzzle parent = null;
    public int move=0;
    public int diff;

    /*
    Create a Puzzle from n-by-n array of tiles
     */
    public Puzzle(int[][] tiles){

        //Getting tiles length
        int n = tiles.length;

        //Initialize puzzle
        puzzle= new int[n][n];

        //Assign value of tiles to puzzle
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                puzzle[i][j]=tiles[i][j];
            }
        }

        //Assign puzzle length to len
        this.len = tiles.length;

        this.diff = this.difference();
    }

    public int compareTo(Puzzle that){
        return (this.diff+this.move)-(that.diff+that.move);
    }

    /*
    String representation of this puzzle
     */
    public String toString(){
        if(this.len==0) return null;

        StringBuilder strBld = new StringBuilder();

        //The size of puzzle
//        strBld.append("move:"+this.move+"\n");
//        strBld.append("diff:"+this.diff+"\n");

        //The puzzle
        for(int i=0;i<this.len;i++){
            for(int j=0;j<this.len;j++){
                strBld.append(this.puzzle[i][j]+"  ");
            }
            strBld.append("\n");
        }

        return strBld.toString();
    }

    /*
    The difference between a board and the goal board is the sum of the Manhattan distances (sum of the vertical and horizontal distance)
from the tiles to their goal positions.
     */
    public int difference(){


        int diff = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int value = puzzle[i][j];
                if (value != 0) {
                    int goalRow = (value - 1) / len;
                    int goalCol;
                    if (value % len == 0) goalCol = len - 1;
                    else goalCol = value % len - 1;
                    int tileDistance = Math.abs(goalRow - i) + Math.abs(goalCol - j);
                    diff += tileDistance;
                }
            }
        }

        return diff;
    }

    /*
    All neighbouring puzzles
     */
    public Stack<Puzzle> neighbours(){
        Stack<Puzzle> nei = new Stack<>();

        // find 0
        int row0=0;
        int col0=0;
        for(int i=0;i<len;i++){
            for(int j=0; j<len;j++){
                if(puzzle[i][j]==0){
                    row0=i;
                    col0=j;
                }
            }
        }

        // Creating neighbours

        // Left switch
        if(col0>0){
            int[][] leftPuzzle = puzzleDup();
            swap(leftPuzzle,row0,col0,row0,col0-1);
            Puzzle leftNei = new Puzzle(leftPuzzle);
            nei.push(leftNei);
        }

        // Top switch
        if(row0>0){
            int[][] topPuzzle = puzzleDup();
            swap(topPuzzle,row0,col0,row0-1,col0);
            Puzzle topNei = new Puzzle(topPuzzle);
            nei.push(topNei);
        }

        // Right switch
        if(col0<len-1){
            int[][] rightPuzzle = puzzleDup();
            swap(rightPuzzle,row0,col0,row0,col0+1);
            Puzzle rightNei = new Puzzle(rightPuzzle);
            nei.push(rightNei);
        }

        // Bot switch
        if(row0<len-1){
            int[][] botPuzzle = puzzleDup();
            swap(botPuzzle,row0,col0,row0+1,col0);
            Puzzle botNei = new Puzzle(botPuzzle);
            nei.push(botNei);
        }

        return nei;

    }

    /*
    A Puzzle that is obtained by exchanging any pair of tiles
    */
    public Puzzle twin(){
        // find 0
        int row0=0;
        int col0=0;
        for(int i=0;i<len;i++){
            for(int j=0; j<len;j++){
                if(puzzle[i][j]==0){
                    row0=i;
                    col0=j;
                }
            }
        }

        int[][] dup = puzzleDup();
        int rndCol = col0;
        int rndRow = row0;

        while(rndCol==col0 && rndRow==row0){
             rndCol = (int)Math.floor(Math.random()*3);
             rndRow = (int)Math.floor(Math.random()*3);
        }

        int rndCol2 = rndCol;
        int rndRow2 = rndRow;

        while((rndCol==rndCol2 && rndRow==rndRow2) || (rndCol2==col0 && rndRow2==row0)){
            rndCol2=(int)Math.floor(Math.random()*3);
            rndRow2=(int)Math.floor(Math.random()*3);
        }

        swap(dup,rndRow,rndCol,rndRow2,rndCol2);
        return new Puzzle(dup);
    }

    /*
    Is this puzzle resolved?
    */
    public boolean isResolved(){
        return difference()==0;
    }

    /*
    Is equal
    */
    public boolean equals(Puzzle cmpPuzzle){
        if(cmpPuzzle==null) return false;
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(puzzle[i][j]!= cmpPuzzle.puzzle[i][j]) return false;
            }
        }
        return true;
    }


    /*
    Helper method: copy puzzle
     */
    private int[][] puzzleDup(){
        int[][] dup = new int[len][len];
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                dup[i][j]=puzzle[i][j];
            }
        }
        return dup;
    }

    /*
    Helper method: switch tile
     */
    private void swap(int[][] p, int iRow, int iCol, int jRow, int jCol){
        int bridge = p[iRow][iCol];
        p[iRow][iCol]=p[jRow][jCol];
        p[jRow][jCol]=bridge;
    }


//    public static void main(String[] args){
//        int[][] tst={{8,1,3},{4,0,2},{7,6,5}};
//        int[][] tst2={{1,2,4},{3,5,6},{7,0,8}};
//        int[][] tst3={{5,2,3},{4,1,6},{7,8,0}};
//
//        Puzzle t1 = new Puzzle(tst);
//        Puzzle t2 = new Puzzle(tst2);
//        t2.move=5;
//        Puzzle t3 = new Puzzle(tst3);
//        t3.move=3;
//
//        Puzzle[] pzs = new Puzzle[]{t2,t1,t3};
//        Arrays.sort(pzs);
//
//        for(int i=0;i<3;i++){
//            System.out.println(pzs[i].toString());
//        }
//
//    }

}


