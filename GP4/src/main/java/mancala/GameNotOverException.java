package mancala;
public class GameNotOverException extends Exception {
    private static final long serialVersionUID = -6386606498972578863L;
    
    /**
     * Exception in the case of the game thinking it is over, when the game is not over yet.
     *
     */
    public GameNotOverException() {
        super();
    }
}
