

/**
 *
 * @author Laura Collins lac32
 * Date Started: 6th November 2013
 * @version 2.0
 * 
 * This Class controls the Puzzle and it's Tiles, setting each Tile to Values etc.
 * 
 */

public class Board {

    private int[][] grid = new int[3][3];
    private int numOfMoves = 0;
    private int heuristic = 0;

    /**
     * Sets the Grid Values.
     * @param gridIn
     */
    public void setGrid(int[][] gridIn) {
        grid = gridIn;
    }

    /**
     * Returns the Grid Values
     * @return
     */
    public int[][] getGrid() {
        return grid;
    }

    /**
     * This is an Integer to return the amount of Moves a Tile does until it reaches it's Goal State.
     * @param a
     */
    public void setNumOfMoves(int a) {
        numOfMoves = a;
    }

    /**
     * This returns the number of Moves a Tile does between Start State and Goal State.
     * @return
     */
    public int getNumOfMoves() {
        return numOfMoves;
    }

    /**
     * This sets the Heuristic for use within A*Searches.
     * @param a
     */
    public void setHeuristic(int a) {
        heuristic = a;
    }

    /**
     * This returns the Heuristic integer for A*Searches.
     * @return
     */
    public int getHeuristic() {
        return heuristic;
    }
}
