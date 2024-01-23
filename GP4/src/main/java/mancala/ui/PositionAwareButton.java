package ui;

public class PositionAwareButton extends javax.swing.JButton{
    private static final long serialVersionUID = 3795830357696284503L;
    private int verPos;
    private int horPos;
    public PositionAwareButton(){
        super();
    }
    public PositionAwareButton(final int vert, final int hori){
        super();
        verPos = vert;
        horPos = hori;
    }
    /**
     * gets the across position
     * @return the horizontal position
     */
    public int getAcross(){
        return horPos;
    }
    /**
     * gets the down position
     * @return the vertical position
     */
    public int getDown(){
        return verPos;
    }
    /**
     * sets the across position
     * @param val the new horizontal position
     */
    public void setAcross(final int val){
        horPos = val;
    }
    /**
     * sets the down position
     * @param val the new vertical position
     */
    public void setDown(final int val){
        verPos = val;
    }

}
