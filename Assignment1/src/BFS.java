import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Laura Collins (lac32) 
 * Date Started: 29th October 2013
 * @version 2.0
 * 
 * This Class is the implementation of the Breadth-First Search for the 8-Puzzle by use of Queue.
 * 
 */
public class BFS {

    private Board startBoard = new Board();
    private Board goalBoard = new Board();
    private Queue<Board> toCheck = new LinkedList<Board>();
    private ArrayList<Board> checked = new ArrayList<Board>();
    private LoadFile load;

    /**
     * Load in the Start State and the Goal State and add the Start State to the toCheck Queue
     * @param start
     * @param goal
     */
    public BFS(String start, String goal) {

        this.load = new LoadFile();

        startBoard.setGrid(load.loadPuzzles(start));

        goalBoard.setGrid(load.loadPuzzles(goal));

        toCheck.add(startBoard);
    }

    /**
     * This is the main Body of the Solving, calling upon other Methods.
     * Also prints the Output at the end.
     */
    public void BFSolve() {
        int nodes = 0;
        while (!(Arrays.deepEquals(toCheck.peek().getGrid(), goalBoard.getGrid()))) {
            moveBlank();
            nodes++;
            System.out.println(nodes);

            if (toCheck.size() == 0 && !checked.isEmpty()) {
                System.out.print("*******No Solution Found!!*******");
                System.exit(0);
            }
        }
        System.out.print("****************** \n");
        System.out.println("Start State: ");
        print(startBoard);
        System.out.print("****************** \n");
        System.out.print("Nodes explored: " + nodes + '\n');
        System.out.print("****************** \n");
        System.out.println("Number of moves to Goal State: " + toCheck.peek().getNumOfMoves());
        System.out.print("****************** \n");
        System.out.println("Last node: ");
        print(toCheck.peek());
        System.out.print("****************** \n");
        System.out.println("Goal node: ");
        print(goalBoard);
        System.out.print("****************** \n");
    }

    /**
     * This Method moves the Blank around and adds each node to the toCheck queue.
     */
    public void moveBlank() {
        Board temp = toCheck.poll();
        checked.add(temp);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (temp.getGrid()[i][j] == 0) {
                    //Move Up
                    if (i != 0) {
                        Board tempBoard = new Board();
                        copy(temp, tempBoard);
                        tempBoard.getGrid()[i][j] = tempBoard.getGrid()[i - 1][j];
                        tempBoard.getGrid()[i - 1][j] = 0;
                        tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                        toCheck.add(tempBoard);
                    }

                    //Move Down
                    if (i != 2) {
                        Board tempBoard = new Board();
                        copy(temp, tempBoard);
                        tempBoard.getGrid()[i][j] = tempBoard.getGrid()[i + 1][j];
                        tempBoard.getGrid()[i + 1][j] = 0;
                        tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                        toCheck.add(tempBoard);
                    }

                    //Move Left
                    if (j != 0) {
                        Board tempBoard = new Board();
                        copy(temp, tempBoard);
                        tempBoard.getGrid()[i][j] = tempBoard.getGrid()[i][j - 1];
                        tempBoard.getGrid()[i][j - 1] = 0;
                        tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                        toCheck.add(tempBoard);
                    }

                    //Move Right
                    if (j != 2) {
                        Board tempBoard = new Board();
                        copy(temp, tempBoard);
                        tempBoard.getGrid()[i][j] = tempBoard.getGrid()[i][j + 1];
                        tempBoard.getGrid()[i][j + 1] = 0;
                        tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                        toCheck.add(tempBoard);
                    }

                }
            }
        }
    }

    /**
     * This does a copy of a Board.
     * @param b
     * @param b1
     */
    public void copy(Board b, Board b1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                b1.getGrid()[i][j] = b.getGrid()[i][j];
            }
        }
    }

    /**
     * This outputs a printout of a Board.
     * @param b
     */
    public void print(Board b) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(b.getGrid()[i][j]);
            }
            System.out.print('\n');
        }
    }
}
