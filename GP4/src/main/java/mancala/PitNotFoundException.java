package mancala;
public class PitNotFoundException extends Exception {
    private static final long serialVersionUID = -7493299591901498662L;
    
    /**
     * Exception involving cases where the pit entered does not exist.
     *
     */
    public PitNotFoundException(){
        super();
    }
}