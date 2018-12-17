// Program2.java by Jake Will

import java.util.ArrayList;

public class Program2 {
    public static void main(String[] args) {
        /* Check to ensure that at least one argument is passed */
        if (args.length == 0) {
            System.err.println("Error: you must enter at least 1 argument");
            return;
        }
        
        /* An ArrayList to store the arguments passed as integers 
         * rather than strings 
         */
        ArrayList<Integer> arguments = new ArrayList<>();
        
        /* Make sure all the arguments are positive integers */
        for (int i = 0; i < args.length; i++) {
            try {
                arguments.add(Integer.parseInt(args[i]));
                
                /* Make sure integers are positive */
                if (arguments.get(i) <= 0) {
                    System.err.println("Error: all arguments must be positive");
                    return;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Error: all arguments must be integers");
                return;
            }
        }
        
        /* Create the Hanoi class with the arguments list */
        Hanoi hanoi = new Hanoi(arguments);
        
        /* Display the initial state of the class and solve it */
        hanoi.display();
        hanoi.solve();
    }
}

/* A class that represents the state of our Hanoi problem -
 * it contains an array of three stacks (pegs), which in turn contain
 * an ArrayList of integers that are representative of disks
 */
class Hanoi {
    /* An array representing the three pegs of our problem */
    Stack[] pegs = {new Stack(), new Stack(), new Stack()};
    
    /* The initializer for this class requires an ArrayList of integers
     * that represent the initial starting condition for the disks
     */
    Hanoi(ArrayList<Integer> disks) {
        pegs[0].initializeDisks(disks);
    }
    
    /* Display the current state of the problem */
    public void display() {
        System.out.print("A:");
        pegs[0].print();

        System.out.println("");
        
        System.out.print("B:");
        pegs[1].print();

        System.out.println("");
        
        System.out.print("C:");
        pegs[2].print();

        System.out.println("\n");
    }
    
    /* Solve the problem (takes no arguments) */
    public void solve() {
        transfer(pegs[0].size(), 0, 1);
    }
    
    /* Transfer a given number of disks from one peg to another.
     * This method does not validate moves - that is done in the
     * move method
     */
    public void transfer(int count, int from, int to) {
        if (count == 1) {
            /* Simply move the disk if count is equal to 1 */
            move(from, to);
            
            // After the move, display the state of the problem
            display();
        } else {
            /* An integer representing the third peg which is neither 
             * being transfered from or to
             */
            int otherPeg = 0;

            while (otherPeg == from || otherPeg == to) {
                otherPeg++;
            }
            
            /* Transfer the top count - 1 pegs to the 'other peg' */
            transfer(count - 1, from, otherPeg);
            
            /* Move the bottom peg to the 'target peg' */
            move(from, to);
            
            /* After the move, display the state of the problem */
            display();
            
            /* Transfer the previously moved pegs to the 'target peg' */
            transfer(count - 1, otherPeg, to);
        }
    }
    
    /* Move a single disk from one peg to another, and also validates
     * each move - will exit if attempts to make illegal move
     */
    public void move(int from, int to) {
        /* If the target peg already has pegs on it, we want to ensure
         * that the disk we are moving has a smaller value that the top disk
         * on the target peg. If the target peg has no disks, we ignore this
         * step
         */
        if (pegs[to].size() > 0) {
            /* Compare the values of the disks and exit if it is an invalid move */
            if (pegs[from].getTopValue() > pegs[to].getTopValue()) {
                System.err.println("Error: attempted to place a larger peg "
                        + "onto a smaller peg. Please ensure that the disks are"
                        + " ordered from largest to smallest");
            }
        }
        
        /* Actually move the disk */
        pegs[to].push(pegs[from].getTopValue());
        pegs[from].pop();
    }
}

/* A class representing a peg - empty initializer, only contains
 * a list of integers that represent disks along with the
 * respective methods
 */
class Stack {
    private ArrayList<Integer> disks = new ArrayList<>();
    
    /* Initialize the array list of disks from an array list of 
     * integers - identical to a setter method
     */
    public void initializeDisks(ArrayList<Integer> disks) {
        this.disks = disks;
    }
    
    /* Returns true if disks is empty, false otherwise */
    public Boolean isEmpty() {
        return (disks.size() == 0);
    }
    
    /* Append a disk to the end of the array list of disks.
     * The validation to ensure that a move is valid is
     * performed in the move() method of the Hanoi class
     * rather than here
     */
    public void push(int disk) {
        disks.add(disk);
    }
    
    /* Remove the top disk from the array list
     * 
     * Note: for a general stack class, this method will return
     * the top value of the stack. For the purposes of this
     * program, that is not required so it is a void method
     * 
     */
    public void pop() {
        // Exit program if trying to pop disk from empty stack
        if (isEmpty()) {
            System.err.println("Error: trying to pop from an empty stack");
            System.exit(0);
        }
        
        disks.remove(disks.size() - 1);
    }
    
    /* Returns the value of the top disk in the array list of disks */
    public int getTopValue() {
        if (isEmpty()) {
            System.err.println("Error: attempting to access top "
                    + "value of empty stack");
            System.exit(0);
        }
        
        return disks.get(disks.size() - 1);
    }

    /* Method that returns the size of the disks array list */
    public int size() {
        return disks.size();
    }
    
    /* Prints out the disks in a line, each separated by a space */
    public void print() {
        for (int disk: disks) {
            System.out.print(" " + disk);
        }
    }
}