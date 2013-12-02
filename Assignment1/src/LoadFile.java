

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Laura Collins (lac32) 
 * Date Started: 28th October 2013
 * @version 2.0
 * 
 * This Class loads in the .txt File 
 *
 */
public class LoadFile {

    private BufferedReader input;

    /**
     * This loads in from a .txt file into a 2D array. 
     * This can then be set as whatever Board (Start, Goal etc) as needed.
     * @param filename
     * @return
     */
    public int[][] loadPuzzles(String filename) {


        int[][] tiles = new int[3][3];
        try {

            input = new BufferedReader(new FileReader("datafiles/" + filename));

            for (int x = 0; x < 3; x++) {
                String number = input.readLine();

                for (int y = 0; y < 3; y++) {
                    String[] parts = number.split(",");
                    tiles[x][y] = Integer.parseInt(parts[y]);

                }
                System.out.print("\n");
            }





        } catch (IOException e) {
            e.printStackTrace();
        }
        return tiles;

    }
}