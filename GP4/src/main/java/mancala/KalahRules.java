package mancala;

public class KalahRules extends GameRules{
    private static final long serialVersionUID = 6590317064585867117L;
    private static final int VALIDFORCAPTURE = 1;
    /**
     * Perform a move and return the number of stones added to the player's store in Ayo.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException{
        final MancalaDataStructure gameBoardStruct = getDataStructure(); 
        int stonesAdded = gameBoardStruct.getStoreCount(playerNum);
        if(!(playerNum == 1 && startPit>0 && startPit<=6) && !(playerNum == 2 &&startPit>6 && startPit<13)){
            throw new InvalidMoveException();
        }
        if(getNumStones(startPit) == 0){
            throw new InvalidMoveException();
        }
        gameBoardStruct.setIterator(startPit,playerNum,false);
        distributeStones(startPit);
        if(gameBoardStruct.getIteratorPos() == 6 || gameBoardStruct.getIteratorPos() == 13){
            swapGoAgain(); 
        }
        stonesAdded = gameBoardStruct.getStoreCount(playerNum) - stonesAdded;
        if(playerNum == 1 && gameBoardStruct.getIteratorPos()>=0 && gameBoardStruct.getIteratorPos()<6){
            stonesAdded += captureStones(gameBoardStruct.getIteratorPos()+1);
        } else if (playerNum == 2 && gameBoardStruct.getIteratorPos()>6 && gameBoardStruct.getIteratorPos()<13){
            stonesAdded += captureStones(gameBoardStruct.getIteratorPos());
        }
        return stonesAdded;
    }

    @Override
    /* default */int distributeStones(final int startPit){
        final MancalaDataStructure gameBoardStruct = getDataStructure();
        gameBoardStruct.setIterator(startPit,getCurrentPlayer(),false);
        final int numStones = gameBoardStruct.removeStones(startPit);
        Countable location;
        for(int i = 0; i<numStones; i++){
            location = gameBoardStruct.next();
            location.addStone();
        }
        return numStones;
    }

    @Override
    /* default */int captureStones(final int stoppingPoint){
        final MancalaDataStructure gameBoardStruct = getDataStructure();
        int stonesCaptured = 0;
        if(getNumStones(stoppingPoint) == VALIDFORCAPTURE){
            stonesCaptured += gameBoardStruct.removeStones(stoppingPoint);
            stonesCaptured += gameBoardStruct.removeStones(13-stoppingPoint);
            gameBoardStruct.addToStore(getCurrentPlayer(),stonesCaptured);
        }
        return stonesCaptured;
    }
}
