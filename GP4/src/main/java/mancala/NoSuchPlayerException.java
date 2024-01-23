package mancala;
public class NoSuchPlayerException extends Exception {
    private static final long serialVersionUID = -9048534461650095974L;
    
    /**
     * Exception involving cases where a player entered doesn't exist.
     *
     */
    public NoSuchPlayerException(){
        super();
    }
}
