package mancala;

import java.io.Serializable;

public class Pit implements Countable,Serializable{
    private int stoneCount;
    private static final long serialVersionUID = -5900112831982566551L;
    
    public Pit(){
        stoneCount = 4;
    }
    @Override
    /**
     * adds a single stone to a pit.
     *
     */
    public void addStone(){
        stoneCount++;
    }
    @Override
    /**
     * gets the stone count from a pit.
     * @return The integer number of stones in the pit
     */
    public int getStoneCount(){
        return stoneCount;
    }
    @Override
    /**
     * Removes all the stones from the pit
     * @return The integer number of stones that were in the pit
     */
    public int removeStones(){
        final int temp = getStoneCount();
        stoneCount = 0;
        return temp;
    }
    @Override
    /**
     * Adds a set number of stones to a pit
     * @param numToAdd The number of stones to add to the pit
     */
    public void addStones(final int numToAdd){
        stoneCount += numToAdd;
    }
    @Override
    /**
     * interprettation of the Pit Class as a String
     * @return The string containing just the stone count
     */
    public String toString(){
        return "" +stoneCount;
    }
}
