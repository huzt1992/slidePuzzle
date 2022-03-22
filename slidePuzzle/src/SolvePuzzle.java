import java.util.Stack;

public class SolvePuzzle {

    public final Puzzle initial;

    private PriorityQueue<Puzzle> initialPQ = new PriorityQueue<Puzzle>();
    private PriorityQueue<Puzzle> twinPQ = new PriorityQueue<Puzzle>();

    public Stack<Puzzle> solution = new Stack<>();
    public boolean isResolvable = true;

    private int move=0;
    private Puzzle last;


    public SolvePuzzle(int[][] initialTiles){
        this.initial = new Puzzle(initialTiles);
    }

    private boolean resolvable(){

        Puzzle iniCurr = this.initial;
        Puzzle twinCurr = initial.twin();

        //Printing both initial and twin puzzle
//        System.out.println("Initial:");
//        System.out.println(iniCurr);
//        System.out.println("Twin:");
//        System.out.println(twinCurr);


        while(iniCurr.difference()!=0){
            // Get neighbours of initial puzzle and twin puzzle
            Stack<Puzzle> currNeighbours = iniCurr.neighbours();
            Stack<Puzzle> twinNeighbours = twinCurr.neighbours();

            //Adding to priorityQueue
            iniCurr = addToPqAndReturn(initialPQ,currNeighbours,iniCurr);
            twinCurr = addToPqAndReturn(twinPQ,twinNeighbours,twinCurr);

            //return false if twin reach goal first
            if(twinCurr.difference()==0) return false;
        }

        last = iniCurr;
        return true;
    }

    private Puzzle addToPqAndReturn(PriorityQueue<Puzzle> PQ,Stack<Puzzle> neighbours,Puzzle parentPz){
        for(Puzzle pz:neighbours){
            if( parentPz.parent==null || !pz.equals(parentPz.parent)) {
                pz.move = parentPz.move+1;
                pz.parent = parentPz;
                PQ.enqueue(pz);
            }
        }

        return PQ.dequeue();
    }

    public void solution(){

        if(!resolvable()) {
            this.isResolvable=false;
            System.out.println("Unresolvable Puzzle");
            return;
        }

        while(last!=null){
            solution.push(last);
            last=last.parent;
        }


        for(int i=solution.size()-2;i>=0;i--){
            System.out.println("Step "+solution.get(i).move+":");
            System.out.println(solution.get(i));
        }
    }

    public static void main(String[] args) {
        int[][] tst={{1,2,3},{4,0,6},{7,5,8}};
        int[][] tst2={{1,3,6},{4,2,0},{7,5,8}};
        int[][] tst3={{0,1,6},{4,3,2},{7,5,8}};
        int[][] tst4={{4,1,6},{0,2,3},{5,7,8}};

        SolvePuzzle solve1 = new SolvePuzzle(tst4);
        System.out.println("Initial Puzzle:");
        System.out.println(solve1.initial);
        solve1.solution();
    }

}
