package mancala;

import java.io.Serializable;

public class UserProfile implements Serializable{
    private static final long serialVersionUID = -6807762626703003185L;

    private String name;
    private int ayo;
    private int kalah;
    private int wins;

    public UserProfile(){
        name = "Player";
        ayo = 0;
        kalah = 0;
        wins = 0;
    }
    public UserProfile(final String newName){
        ayo = 0;
        kalah = 0;
        wins = 0;
        name = newName;
    }
    /**
     * Gets the number of wins the user has
     * @return The number of wins
     */
    public int getWins(){
        return wins;
    }
    /**
     * Gets the number of wins in Kalah the user has
     * @return The number of wins
     */
    public int getKalah(){
        return kalah;
    }
    /**
     * Gets the number of wins in Ayo the user has
     * @return The number of wins
     */
    public int getAyo(){
        return ayo;
    }
    /**
     * Gets the name of the user
     * @return The name
     */
    public String getName(){
        return name;
    }
    /**
     * Adds one win to the amount of wins the user has
     */
    public void addWins(){
        wins++;
    }
    /**
     * Adds one win to the amount of wins the user has in Ayo
     */
    public void addAyo(){
        ayo++;
    }
    /**
     * Adds one win to the amount of wins the user has in Kalah
     */
    public void addKalah(){
        kalah++;
    }
    /**
     * Sets the name of the user
     * @param newName the name that will be used
     */
    public void setName(final String newName){
        name = newName;
    }
}