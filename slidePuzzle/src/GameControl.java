import jdk.swing.interop.SwingInterOpUtils;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class GameControl {



    private static void inputRow(Scanner myInput, Integer Nth, Set<Integer> seenNum, int[][] tiles){
        System.out.println("Please input the 3 numbers in row "+Nth+": ");
        boolean requireInput = true;

        while(requireInput){
            String curr=myInput.nextLine();
            curr.replaceAll("\\s","");

            if(curr.length()!=3) {
                System.out.println("Please only input 3 number without any separate character! For example:\"3 4 5\"");
                continue;
            }
            try {
                int inputNum = Integer.parseInt(curr);
                int[] temp = new int[3];
                Set<Integer> tempSeen = new HashSet<>();

                for(int j=2;j>=0;j--){
                    int n = inputNum%10;
                    // two cases that user have to input again
                    if(seenNum.contains(n)){
                        System.out.println("You can't input the number being used in rows above!");break;
                    }
                    else if(tempSeen.contains(n)){
                        System.out.println("You can't input repeat number!");
                        break;
                    }
                    else if(n<0 || n>8){
                        System.out.println("You can't input any number smaller than 0 or larger than 8!");break;
                    }else{
                        // store n into temp array and seenNum set
                        tempSeen.add(n);
                        temp[j]=n;
                        inputNum=inputNum/10;
                        if(j==0) {
                            requireInput=false;
                            // assign temp to tiles
                            tiles[Nth-1][0]=temp[0];
                            tiles[Nth-1][1]=temp[1];
                            tiles[Nth-1][2]=temp[2];
                            seenNum.add(temp[0]);
                            seenNum.add(temp[1]);
                            seenNum.add(temp[2]);
                        }
                    }
                }
            }catch (Exception e){
                System.out.println("Please input number!");
            }
        }
    }



    private static boolean isContinue(){


        return false;
    }


    public static void main(String[] args) {

        //Game Description:
        System.out.println("Welcome to 8-puzzle game!");
        System.out.println("The 8-puzzle is a sliding puzzle that is played on a 3-by-3 grid with 8 square tiles labeled 1 through 8, plus a blank square. The goal is to rearrange the tiles so that they are in row-major order, using as few moves as possible.");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        Scanner myInput = new Scanner(System.in);

        while (true){
            System.out.println("Are you ready to play the game? Press 'y'");
            if(myInput.nextLine().equals("y"))break;
        }

        System.out.println("Provide any random 8-puzzles, this smart computer will show the resolution step by step!");

        boolean gamePlay = true;


        while(gamePlay){
            //Initiate a set to store the number use input
            Set<Integer> seenNum = new HashSet<>();
            //Initiate tiles to generate Puzzle
            int[][] tiles = new int[3][3];

            System.out.println("Please use 0 for blank site");
            inputRow(myInput,1,seenNum,tiles);
            inputRow(myInput,2,seenNum,tiles);
            inputRow(myInput,3,seenNum,tiles);

            SolvePuzzle SP = new SolvePuzzle(tiles);
            System.out.println("You puzzle is:");
            System.out.println(SP.initial);

            System.out.println("Are you ready to see resolution of this puzzle? Press 'y'");

            while (true){
                if(myInput.nextLine().equals("y")){
                    SP.solution();
                    break;
            }

            }

            while (true){
                System.out.println("Do you wish to keep playing? Press 'y' or 'n'.");
                String reply = myInput.nextLine();
                if(reply.equals("y")) break;
                else if(reply.equals("n")){
                    gamePlay=false;
                    System.out.println("Smart Computer go to sleep then!");
                    System.out.println("Bye!!!!!");
                    break;
                }
            }

        }

    }
}
