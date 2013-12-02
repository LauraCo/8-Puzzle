

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Laura Collins (lac32) 
 * Date Started: 18th November 2013
 * @version 1.0
 * This Class runs Hamming Distance A* Search
 * 
 */
public class AStarSearch {

    private Board startBoard = new Board();
    private Board goalBoard = new Board();
    private Queue<Board> toCheck = new PriorityQueue<Board>(1, heuristicComp);
    private ArrayList<Board> checked = new ArrayList<Board>();
    private LoadFile load;

    /**
     * This loads in the Start Board and the Goal Board.
     * Also add Start Board to the toCheck Priority Queue.
     * @param start
     * @param goal
     */
    public AStarSearch(String start, String goal) {

        this.load = new LoadFile();

        startBoard.setGrid(load.loadPuzzles(start));

        goalBoard.setGrid(load.loadPuzzles(goal));

        toCheck.add(startBoard);
    }
    
    /**
     * To help generate the Heuristics needed for A* Search.
     */
    public static Comparator<Board> heuristicComp = new Comparator<Board>() {
        @Override
        public int compare(Board b1, Board b2) {
            return (int) (b1.getHeuristic() - b2.getHeuristic());
        }
    };

    /**
     * The Main Solving Method for A* Search, calls upon other Methods.
     * Also prints the Output at the end.
     */
    public void AStarSolve() {
        int nodes = 0;
        System.out.println("Iterations: ");
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
     * This Moves the Blank around and adds the nodes to the Priority Queue.
     * Also sets the Heuristic for that node.
     */
    public void moveBlank() {
        Board temp = new Board();
        temp = toCheck.poll();
        if (!(checkChecked(temp))) {
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
                            if (!(checkChecked(tempBoard))) {
                                tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                                tempBoard.setHeuristic(temp.getHeuristic() + calculateHeur());
                                toCheck.add(tempBoard);
                            }
                        }

                        //Move Down
                        if (i != 2) {
                            Board tempBoard = new Board();
                            copy(temp, tempBoard);
                            tempBoard.getGrid()[i][j] = tempBoard.getGrid()[i + 1][j];
                            tempBoard.getGrid()[i + 1][j] = 0;
                            if (!(checkChecked(tempBoard))) {
                                tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                                tempBoard.setHeuristic(temp.getHeuristic() + calculateHeur());
                                toCheck.add(tempBoard);
                            }
                        }

                        //Move Left
                        if (j != 0) {
                            Board tempBoard = new Board();
                            copy(temp, tempBoard);
                            tempBoard.getGrid()[i][j] = tempBoard.getGrid()[i][j - 1];
                            tempBoard.getGrid()[i][j - 1] = 0;
                            if (!(checkChecked(tempBoard))) {
                                tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                                tempBoard.setHeuristic(temp.getHeuristic() + calculateHeur());
                                toCheck.add(tempBoard);
                            }
                        }

                        //Move Right
                        if (j != 2) {
                            Board tempBoard = new Board();
                            copy(temp, tempBoard);
                            tempBoard.getGrid()[i][j] = tempBoard.getGrid()[i][j + 1];
                            tempBoard.getGrid()[i][j + 1] = 0;
                            if (!(checkChecked(tempBoard))) {
                                tempBoard.setNumOfMoves(temp.getNumOfMoves() + 1);
                                tempBoard.setHeuristic(temp.getHeuristic() + calculateHeur());
                                toCheck.add(tempBoard);
                            }
                        }

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

    /**
     * This Checks to see if a node has been explored already.
     * @param b
     * @return
     */
    public boolean checkChecked(Board b) {
        for (int i = 0; i < checked.size(); i++) {

            if (checked.isEmpty()) {
                return false;
            } else if (Arrays.deepEquals(b.getGrid(), checked.get(i).getGrid())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Calculates the Heuristic.
     * @return
     */
    public int calculateHeur() {

        int heurNum = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (toCheck.size() != 0 && toCheck.peek().getGrid()[i][j] == goalBoard.getGrid()[i][j]) {
                    heurNum++;
                }
            }
        }
        return heurNum;
    }
}