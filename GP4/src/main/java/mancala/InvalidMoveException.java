package mancala;
public class InvalidMoveException extends Exception {
    private static final long serialVersionUID = 3724878338534103269L;
    
    /**
     * Exception involving cases where a player tries to move the other players stones.
     *
     */
    public InvalidMoveException(){
        super();
    }
}
