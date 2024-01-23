package mancala;

public class AyoRules extends GameRules{
    private static final long serialVersionUID = 8868613842003379656L;
    private static final int STOREONE = 6;
    @Override
    /**
     * Perform a move and return the number of stones added to the player's store in Ayo.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException{
        final MancalaDataStructure gameBoardStruct = getDataStructure();
        int stonesAdded = gameBoardStruct.getStoreCount(playerNum);
        if(!(playerNum == 1 && startPit>0 && startPit<=6) && !(playerNum == 2 && startPit>6 && startPit<13)){
            throw new InvalidMoveException();
        }
        if(getNumStones(startPit) == 0){
            throw new InvalidMoveException();
        }
        gameBoardStruct.setIterator(startPit,playerNum,true);
        distributeStones(startPit);
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
        gameBoardStruct.setIterator(startPit, getCurrentPlayer(), true);
        int numStones;
        int firstTurn = 0;
        int pitTracker = startPit;
        boolean stop = true;
        final int returnNumber = gameBoardStruct.getNumStones(pitTracker);
        while((getNumStones(pitTracker) >= 1 && firstTurn == 0 || getNumStones(pitTracker) != 1 && firstTurn > 0 ) && stop){
            numStones = gameBoardStruct.removeStones(pitTracker);
            Countable location;
            for(int i = 0; i<numStones; i++){
                location = gameBoardStruct.next();
                location.addStone();
            }
            if(gameBoardStruct.getIteratorPos() == 6 ||gameBoardStruct.getIteratorPos() == 13 ){
                stop = false;
            }
            if(gameBoardStruct.getIteratorPos()>STOREONE){
                pitTracker = gameBoardStruct.getIteratorPos();
            } else {
                pitTracker = gameBoardStruct.getIteratorPos()+1;
            }
            firstTurn++;
        }
        return returnNumber;
    }
    @Override
    /* default */int captureStones(final int stoppingPoint){
        final MancalaDataStructure gameBoardStruct = getDataStructure();
        final int captureOccuring = 1;
        int stonesCaptured;
        if(getNumStones(stoppingPoint) == captureOccuring){
            stonesCaptured = gameBoardStruct.removeStones(13-stoppingPoint);
            gameBoardStruct.addToStore(getCurrentPlayer(),stonesCaptured);
        } else {
            stonesCaptured = 0;
        }
        return stonesCaptured;
    }

}
