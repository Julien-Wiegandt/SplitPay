package server.facade;

import server.exception.SplitNotFoundException;
import server.models.Participant;
import server.models.Split;
import server.utils.SplitUtilities;

import java.util.HashMap;

public class SplitServerFacade {

    private HashMap<String, Split> splits = new HashMap<>();
    private static SplitServerFacade instance = null;

    private SplitServerFacade(){

    }

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
    public String createSplit(int ownerId, String ownerNickName, Double goalAmount, String label, String splitMode) {
        String splitCode = SplitUtilities.generateSplitCode();
        Split split = new Split(splitCode, ownerId,ownerNickName,goalAmount,label,splitMode);
        splits.put(split.getSplitCode(),split);
        return splitCode;
    }

    /**
     * If not already a participant, makes a user join a split with his id and nickname else throws an exception
     * TODO: Create a custom exception and check if user already in or not before adding as participant
     * @param splitCode
     * @param participantId
     * @param participantNickname
     * @exception Exception
     */
    public void join(String splitCode, int participantId, String participantNickname) throws Exception{
        getSplitByCode(splitCode).addParticipant(participantId,participantNickname);
    }

    public Split getSplitByCode(String splitCode) throws SplitNotFoundException {
        Split split = splits.get(splitCode);
        if(split==null){
            throw new SplitNotFoundException("No split matching the splitCode found");
        } else {
            return split;
        }
    }

    public Participant getSplitParticipant(String splitCode, int participantId) throws SplitNotFoundException {
        return getSplitByCode(splitCode).getParticipantById(participantId);
    }
}