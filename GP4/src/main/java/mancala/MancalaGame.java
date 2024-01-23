package mancala;
import java.io.Serializable;
import java.util.ArrayList;
public class MancalaGame implements Serializable{
    private static final long serialVersionUID = -8898677059489830536L; 
    private GameRules board;
    final private ArrayList<Player> players;
    private int turn;
    private int rules;
    private static final int PLAYERONETURN = 0;
    private static final int PLAYERTWOTURN = 1;
    private static final int AYORULESET = 2;
    private static final int KALAHRULESET = 1;


    public MancalaGame(){
        this.players = new ArrayList<>();
        this.turn = 0;
    }
    /**
     * Swaps the turn varaible
     */
    public void swapTurn(){
        if(turn == PLAYERTWOTURN){
            turn = PLAYERONETURN;
        } else {
            turn = PLAYERTWOTURN;
        }
    }
    /**
     * Sets the rule set that will be used
     * @param ruleSet 1 for kalah and 2 for ayo
     */
    public void setRules(final int ruleSet){
        rules = ruleSet;
    }
    /**
     * Which rules are being used
     * @return 1 for kalah and 2 for ayo
     */
    public int getRules(){
        return rules;
    }
    /**
     * Gets the board in MancalaGame
     * @return returns the board
     */
    public GameRules getBoard(){
        return board;
    }
    /**
     * Gets the current player based on the turn
     * @return the current player
     */
    public Player getCurrentPlayer(){ 
        return players.get(turn);
    }
    /**
     * Gets the number of stones in a pit
     * @param pitNum the pit number
     * @return returns the number of stones in the pit
     * @throws PitNotFoundException
     */
    public int getNumStones(final int pitNum) throws PitNotFoundException{
        return board.getNumStones(pitNum);
    }
    /**
     * Gets the list of players
     * @return returns the list of players
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }
    /**
     * Gets the number of stones in a store
     * @param player the player who owns the store
     * @return returns the number of stones
     * @throws NoSuchPlayerException
     */
    public int getStoreCount(final Player player) throws NoSuchPlayerException{
        if(player.equals(players.get(0)) || player.equals(players.get(1))){
            return player.getStoreCount();
        } else {
            throw new NoSuchPlayerException();
        }
    }
    /**
     * Determines the winner of the game
     * @return returns the player who won
     * @throws GameNotOverException
     */
    public Player getWinner() throws GameNotOverException{
        Player returnPlayer = null;
        if(isGameOver()){
            for(int i = 1; i<=6;i++){
                players.get(0).getStore().addStones(board.getNumStones(i));
            }
            for(int i = 7; i<=12;i++){
                players.get(1).getStore().addStones(board.getNumStones(i));
            }
            for(int i =1; i<13; i++){
                board.getDataStructure().removeStones(i);
            }
            if(players.get(0).getStore().getTotalStones() > players.get(1).getStore().getTotalStones()){
                returnPlayer = players.get(0);
            }else if(players.get(0).getStore().getTotalStones() < players.get(1).getStore().getTotalStones()){
                returnPlayer = players.get(1);
            }
        } else {
            throw new GameNotOverException();
        }
        return returnPlayer;
    }
    /**
     * Determines if the game is over
     * @return returns whether or not the game is over or not
     */
    public boolean isGameOver(){
        boolean returnBoolean;
   
            if(board.isSideEmpty(1) || board.isSideEmpty(7)){
                returnBoolean = true;
            } else {
                returnBoolean = false;
            }
        
        return returnBoolean;
    }
    /**
     * Executes a move
     * @param startPit the location of the pit chosen for the move
     * @return returns the number of stones in the whole board
     * @throws InvalidMoveException
     */
    public int move(final int startPit) throws InvalidMoveException{
        try{
            board.moveStones(startPit, turn+1);
        } catch (InvalidMoveException e){
            throw e;
        }
        int sum = 0;
        if(startPit>0 && startPit<7){
            for(int i =0; i<6;i++){
                sum += board.getNumStones(i);
            }
        }else if(startPit>6 && startPit<13){
            for(int i =7; i<13;i++){
                sum += board.getNumStones(i);
            }
        }
        return sum;
    }

    /* default */ void setBoard(final GameRules theBoard){
        board = theBoard;
    }
    /**
     * sets the current player to the paramater
     * @param player the player to be set as current
     */
    public void setCurrentPlayer(final Player player){
        if(players.get(0).equals(player)){
            turn = 1;
        } else {
            turn = 2;
        }
    }
    
    /**
     * Sets up the player and store link
     * @param onePlayer first player
     * @param twoPlayer second player
     */
    public void setPlayers(final Player onePlayer, final Player twoPlayer){
    board.registerPlayers(onePlayer, twoPlayer);
        players.add(onePlayer);
        players.add(twoPlayer);
    }
    /**
     * Creates a new game and determines the rule set
     */
    public void startNewGame(){ 
        if(rules == KALAHRULESET){
            board = new KalahRules();
        } else if (rules == AYORULESET){
            board = new AyoRules();
        } else {
            board = new KalahRules();
        }
        board.resetBoard();
    }
    @Override
    /**
     * Creates a string represneting the game
     * @return the string to return
     */
    public String toString(){
        return board.toString();
    }
}
