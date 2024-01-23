package mancala;

import java.io.Serializable;

/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable{
    private final MancalaDataStructure gameBoard;
    private int currentPlayer = 1; //1 or 2
    private boolean goAgain = false;
    private static final long serialVersionUID = -543085550693525930L;
    private static final int PONE = 1;
    private static final int PTWO = 2;
    

    /**
     * Constructor to initialize the game board.
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }
    public void swapCurrentPlayer(){
        if(currentPlayer == PONE){
            currentPlayer=PTWO;
        } else {
            currentPlayer = PONE;
        }
    }
    public GameRules() {
        gameBoard = new MancalaDataStructure();

    }
    public MancalaDataStructure getGameBoard(){
        return gameBoard;
    }
    public boolean isGoAgain(){
        return goAgain;
    }
    public void swapGoAgain(){
        goAgain = !goAgain;
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    public MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    /* default */boolean isSideEmpty(final int pitNum) {
        boolean isEmpty = true;
        if(pitNum>6 && pitNum<13){
            gameBoard.setIterator(7, 2, false);
            for(int i =7; i<13; i++){
                if(getNumStones(i)!=0){
                    isEmpty = false;
                }
            }
        } else {
            gameBoard.setIterator(1, 1, false);
            for(int i =1; i<7; i++){
                if(getNumStones(i)!=0){
                    isEmpty = false;
                }
            }
        }
        
        return isEmpty;
    }

    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    /* default */abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    /* default */abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        final Store store1 = new Store();
        one.setStore(store1);
        gameBoard.setStore(store1,1);
        final Store store2 = new Store();
        two.setStore(store2);
        gameBoard.setStore(store2,2);
        // this method can be implemented in the abstract class.


        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() {
        for(int i = 1; i<13;i++){
            gameBoard.removeStones(i);
        }
        gameBoard.setUpPits();
        gameBoard.emptyStores();
    }

    @Override
    public String toString() {
        return Integer.toString(getCurrentPlayer());
        // Implement toString() method logic here.
    }
}
