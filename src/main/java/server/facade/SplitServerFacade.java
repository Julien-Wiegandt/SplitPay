package server.facade;

import server.exception.GoalAmountExceededException;
import server.exception.ParticipantAlreadyInException;
import server.exception.ParticipantNotFoundException;
import server.exception.SplitNotFoundException;
import server.models.Participant;
import server.models.Split;
import util.SplitUtilities;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SplitServerFacade {

    private HashMap<String, Split> splits = new HashMap<>();
    private static SplitServerFacade instance = null;

    /**
     * Private constructor for Singleton pattern
     */
    private SplitServerFacade(){

    }

    /**
     * Thread-safe double lock Singleton pattern constructor
     * @return
     */
    public static SplitServerFacade getInstance() {
        if (instance == null) {
            synchronized(SplitServerFacade.class) {
                if (instance == null) {
                    instance = new SplitServerFacade();
                }
            }
        }
        return instance;
    }

    /**
     * Creates a split and adds the creator as the first participant
     * @param ownerId
     * @param ownerNickName
     * @param goalAmount
     * @param label
     * @param splitMode
     */
    public String createSplit(int ownerId, String ownerNickName, double goalAmount, String label, String splitMode) {
        String splitCode = SplitUtilities.generateCode();
        Split split = new Split(splitCode, ownerId,ownerNickName,goalAmount,label,splitMode);
        splits.put(split.getSplitCode(),split);
        return splitCode;
    }

    /**
     * If not already a participant, makes a user join a split with his id and nickname else throws an exception
     * @param splitCode
     * @param participantId
     * @param participantNickname
     * @exception SplitNotFoundException
     * @exception ParticipantAlreadyInException
     */
    public void join(String splitCode, int participantId, String participantNickname) throws SplitNotFoundException, ParticipantAlreadyInException {
        getSplitByCode(splitCode).addParticipant(participantId,participantNickname);
    }

    /**
     * Returns split identified by the splitcode
     * @param splitCode
     * @return
     * @throws SplitNotFoundException
     */
    public Split getSplitByCode(String splitCode) throws SplitNotFoundException {
        Split split = splits.get(splitCode);
        if(split==null){
            throw new SplitNotFoundException("No split matching the splitCode found");
        } else {
            return split;
        }
    }

    /**
     * Returns matching participant in a split if matching input
     * @param splitCode
     * @param participantId
     * @return
     * @throws SplitNotFoundException
     */
    public Participant getSplitParticipant(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        return getSplitByCode(splitCode).getParticipantById(participantId);
    }

    /**
     * Returns a hashmap of splits owned by the user
     * @return
     */
    public HashMap<Integer,Split> getUserSplits(int id) {
        HashMap<Integer,Split> userSplits = new HashMap<>();
        Iterator iterator = splits.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<Integer,Split> split = (Map.Entry) iterator.next();
            if(split.getValue().getOwnerId()==id){
                userSplits.put(split.getKey(),split.getValue());
            }
        }

        return userSplits;

    }

    /**
     * Returns the number of participant in a split
     * @param splitCode
     * @return
     * @throws SplitNotFoundException
     */
    public int getNumberOfSplitParticipant(String splitCode) throws SplitNotFoundException {
        return getSplitByCode(splitCode).getNumberOfParticipant();
    }

    /**
     * Changes split participant amount
     * @param splitCode
     * @param participantId
     * @param newAmount
     * @throws SplitNotFoundException
     * TODO : Check if goal amount will not be exceeded
     */
    public void changeParticipantAmount(String splitCode, int participantId, double newAmount) throws SplitNotFoundException, ParticipantNotFoundException, GoalAmountExceededException {
        getSplitByCode(splitCode).changeParticipantAmount(participantId,newAmount);
    }

    /**
     * Removes participant from split if is found
     * @param splitCode
     * @param participantId
     * @throws SplitNotFoundException
     * @throws ParticipantNotFoundException
     */
    public void removeSplitParticipant(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        getSplitByCode(splitCode).removeParticipant(participantId);
    }

    /**
     * Checks if the goal amount is reached
     * @param splitcode
     * @return
     * @throws SplitNotFoundException
     */
    public boolean isSplitGoalAmountReached(String splitcode) throws SplitNotFoundException {
        return getSplitByCode(splitcode).isGoalAmountReached();
    }

    /**
     * Switches ready status state
     */
    public void switchSplitParticipantReadyStatus(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        getSplitByCode(splitCode).switchParticipateReadyStatus(participantId);
    }

}