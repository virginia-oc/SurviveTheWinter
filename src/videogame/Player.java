/**
 * This class manages information about each player and a method
 * to sort them regarding their scores.
 * @author virginia ojeda corona
 */

package videogame;

public class Player implements Comparable<Player>
{
    private String name;
    private long score;

    /**
     * Constructor with parameters.
     * @param name A string with the player name
     * @param score A long number with the player's score
     */
    public Player(String name, Long score)
    {
        this.name = name;
        this.score = score;
    }

    /**
     * Returns the player's name.
     * @return Player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Return the player's score.
     * @return Player's score
     */
    public long getScore() {
        return score;
    }

    /**
     * Compares the current player's score with another player's score.
     * @param otherPlayer A Player object
     * @return Returns 0 if both scores are equal,
     * an integer greater than 0 if the current player has a greater score than the other player
     * and an integer lower than 0 if the current player has a lower score than the other player
     */
    @Override
    public int compareTo(Player otherPlayer)
    {
        return -1 * Long.compare(this.score, otherPlayer.score);
    }
}
