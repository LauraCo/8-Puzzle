

/**
 *
 * @author Laura Collins (lac32) 
 * Date Started: 28th October 2013
 * @version 2.0
 * This is the Main Class which is called when run on the Command Line/Terminal.
 */
public class Solve {

    /**
     * This runs the Entire Program. It calls the 4 different AI Search Techniques.
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args[2].equals("bfs")) {
            BFS e = new BFS(args[0], args[1]);
            System.out.println("Breadth-First Search");
            e.BFSolve();
        } else if (args[2].equals("dfs")) {
            DFS d = new DFS(args[0], args[1]);
            System.out.println("Depth-First Search");
            d.DFSolve();
        } else if (args[2].equals("astar1")) {
            AStarSearch a = new AStarSearch(args[0], args[1]);
            System.out.println("Hamming Distance");
            a.AStarSolve();
        } else if (args[2].equals("astar2")) {
            AStarSearch a1 = new AStarSearch(args[0], args[1]);
            System.out.println("Manhattan Distance");
            a1.AStarSolve();
        }

    }
}
