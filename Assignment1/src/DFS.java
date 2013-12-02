

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 *
 * @author Laura Collins (lac32) 
 * Date Started: 18th November 2013
 * @version 1.0
 * This Class runs the Depth-First Search algorithm.
 */

public class DFS {

    private Board startBoard = new Board();
    private Board goalBoard = new Board();
    Stack<Board> toCheck = new Stack<Board>();
    private ArrayList<Board> checked = new ArrayList<Board>();
    LoadFile load;

    /**
     * Loading in the Start Board and the Goal Board and adding Start Board to the toCheck Stack.
     * @param start
     * @param goal
     */
    public DFS(String start, String goal) {

        this.load = new LoadFile();

        startBoard.setGrid(load.loadPuzzles(start));

        goalBoard.setGrid(load.loadPuzzles(goal));

        toCheck.add(startBoard);
    }

    /**
     * This is the Main solving Method, calling from other Methods to solve the Puzzle.
     * Also prints the Output at the end.
     */
    public void DFSolve() {
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
     * This Method moves the Blank around and adds each node to the toCheck stack,
     */
    public void moveBlank() {
        Board temp = new Board();
        temp = toCheck.pop();
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
                                toCheck.push(tempBoard);
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
                                toCheck.push(tempBoard);
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
                                toCheck.push(tempBoard);
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
                                toCheck.push(tempBoard);
                            }
                        }

                    }
                }
            }
        }
    }

    /**
     * This does a copy of the Board.
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
     * This outputs a printout of the Board.
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
     * This Checks to see if a node has been checked before. 
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
}