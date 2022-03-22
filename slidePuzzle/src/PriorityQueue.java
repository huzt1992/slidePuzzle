public class PriorityQueue<Key extends Comparable<Key>> {
    private Key[] pq;
    private int last = 0;

    public PriorityQueue(){
        // +1 because binary heap starts with index 1
        pq = (Key[]) new Comparable[1];
    }

    public boolean isEmpty(){return last==0;}

    public void enqueue(Key k){
        resize();
        pq[++last] = k;
        swim(last);
    }

    /*
    Delete and return max
     */
    public Key dequeue(){
        Key max = pq[1];
        exch(1,last);
        pq[last--]=null;
        sink(1);
        return max;
    }

    public int size(){return last;}

    public Key[] pq(){return this.pq;}

    /*
    Resize array
     */
    private void resize(){
        if(last<this.pq.length-1) return;
        Key[] newPq = (Key[]) new Comparable[this.pq.length*2];
        for(int i=0;i<pq.length;i++){
            newPq[i]=pq[i];
        }
        this.pq=newPq;
    }

    /*
    Swim : move smaller key up all the way up to its right position
     */
    private void swim(int k){
        while (k > 1 && more(k/2, k))
        {
            exch(k, k/2);
            k = k/2;
        }
    }

    /*
    Sink : move larger key all the way down to its right position
     */
    private void sink(int k){
        while(k*2<=last){
            int j= 2*k;
            // determine which child Key is larger
            if(j<last && more(j,j+1))j++;
            if(less(k,j)) break;
            exch(k,j);
            k=j;
        }
    }

    /*
    Helper function
     */
    private boolean less(int i,int j){
        return pq[i].compareTo(pq[j])<1;
    }

    /*
    Helper function
     */
    private boolean more(int i,int j){
        return pq[i].compareTo(pq[j])>1;
    }

    /*
    Helper function
     */
    private void exch(int i,int j){
        Key bridge = pq[i];
        pq[i] = pq[j];
        pq[j] = bridge;
    }

    public static void main(String[] args){
        PriorityQueue pQue = new PriorityQueue();
        System.out.println(pQue.size());
        pQue.enqueue(1);
        pQue.enqueue(3);
        pQue.enqueue(2);
        pQue.enqueue(5);
        pQue.enqueue(8);

        for(int i=1;i<pQue.size()+1;i++){
            System.out.println(pQue.pq()[i].toString());
        }

    }

}
