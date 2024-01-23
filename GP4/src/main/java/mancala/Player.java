package mancala;

import java.io.Serializable;

public class Player implements Serializable{
    private String playerName;
    private Store playerStore;
    private UserProfile user;
    private static final long serialVersionUID = -5345515315346338047L;

    public Player(){ 
        playerStore = new Store();
        playerName = "Player";
        user = new UserProfile();
        user.setName("Player");
    }
    public Player(final String name){
        playerStore = new Store();
        playerName = name;
        user = new UserProfile();
        user.setName(name);
    }
    /**
     * Gets the user profile attached to Player
     * @return The user profile attached to Player
     */
    public UserProfile getUser(){
        return user;
    }
    /**
     * Sets the user profile attached to Player
     * @param The user profile to attach to player
     */
    public void setUser(final UserProfile users){
        user = users;
    }
    /**
     * Gets the name attached to Player
     * @return The name attached to Player
     */
    public String getName(){
        return playerName;
    }
    /**
     * Gets the store attached to Player
     * @return The store attached to Player
     */
    public Store getStore(){
        return playerStore;
    }
    /**
     * Gets the amount of stones in the store attached to Player
     * @return The number of stones
     */
    public int getStoreCount(){
        return playerStore.getTotalStones();
    }
    /**
     * Sets the name attached to Player
     * @param name The name attached to Player
     */
    public void setName(final String name){
        playerName = name;
    }
    /* default */protected void setStore(final Store store){
        playerStore = store;
    }
    @Override
    /**
     * Creates a string representing the Player.
     * @return The string
     */
    public String toString(){
        return playerName + ";" + playerStore.getTotalStones();
    }
}
