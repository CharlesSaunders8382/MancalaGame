package mancala;

import java.io.Serializable;

public class Store implements Countable,Serializable{
    private int stones;
    private Player owner;
    private static final long serialVersionUID = -2528464561188809818L;
    
    public Store(){
        stones = 0;
    }
    @Override
    /*
     * gets the amount of stones in the store
     * @return number of stones in the store
     */
    public int getStoneCount(){
        return stones;
    }
    @Override
    /**
     * adds an amount of stones to the store
     * @param amount number of stones to add to the store
     */
    public void addStones(final int amount){
        stones += amount;
    }
    /* default */int emptyStore(){
        final int temp = stones;
        stones = 0;
        return temp;
    }
    /**
     * Gets the owner of the store
     * @return the player who owns the store
     */
    public Player getOwner(){
        return owner;
    }
    /**
     * gets the total stones in the store
     * @return the total stones in the store
     */
    public int getTotalStones(){
        return stones;
    }
    /* default */void setOwner(final Player player){
        owner = player;
    }
    @Override
    /**
     * Adds a single stone to the store
     */
    public void addStone(){
        stones++;
    }
    @Override
    /**
     * Removed the stones from the store
     * @return the number of stones in the store before the removal
     */
    public int removeStones(){
        final int temp = getTotalStones();
        stones = 0;
        return temp;
    }
    @Override
    /**
     * Represnets the store class as a string
     * @return the string
     */
    public String toString(){
        return stones + "";
    }
}
