/**
 * This class manages reading and writing ranking score information.
 * @author virginia ojeda corona
 * @see videogame.Player
 */
package videogame;

import java.io.*;
import java.util.*;

public class Ranking
{
    private String inputFileName = "assets/ranking.txt";
    private static List<Player> playerList = new ArrayList<>();

    /**
     * Constructor
     */
    public Ranking()
    {
        if (new File(inputFileName).exists())
        {
            loadRanking();
        }
        else
        {
            File file = new File(inputFileName);
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a list of players.
     * @return List of players
     */
    public List<Player> getPlayerList()
    {
        return playerList;
    }

    /**
     * Reads player information from a file and saves it in a list.
     */
    public void loadRanking()
    {
        try (BufferedReader inputFile = new BufferedReader(
                new FileReader(inputFileName)))
        {
            playerList.clear();
            String line = null;
            while ((line = inputFile.readLine()) != null)
            {
                playerList.add(new Player(line.split("#")[0],
                        Long.parseLong(line.split("#")[1])));
            }
        } catch (IOException e) {
            System.err.println("Error reading file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add a new player (name and score) to the list of players, sort it
     * and update the ranking file.
     * @param name Player's name
     * @param score Player's score
     */
    public void addScore(String name, long score)
    {
        playerList.add(new Player(name.trim(), score));
        Collections.sort(playerList);

        try
        {
            PrintWriter inputFile = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(inputFileName)));

            for (Player player: playerList)
            {
                inputFile.println(player.getName() + "#" + player.getScore());
            }
            inputFile.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
