package server.models;

import server.exception.GoalAmountExceededException;
import server.exception.ParticipantAlreadyInException;
import server.exception.ParticipantNotFoundException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Split {

    public Split(String splitCode, int ownerId, String ownerNickName, double goalAmount, String label, String splitMode){
        this.splitCode=splitCode;
        this.label=label;
        this.ownerId=ownerId;
        this.goalAmount=goalAmount;
        this.splitMode=splitMode;

        // The owner is the first participant
        participants.put(ownerId,new Participant(ownerId,ownerNickName));
    }

    private String label;
    private String splitCode;
    private boolean expired = false;
    private double goalAmount;
    private String splitMode;
    private int ownerId;
    private double currentAmount = 0;

    private HashMap<Integer,Participant> participants = new HashMap<>();

    public double getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(double currentAmount) {
        this.currentAmount = currentAmount;
    }

    /**
     * True if the goal amount is reached
     * @return
     */
    public boolean isGoalAmountReached(){
        return goalAmount==currentAmount;
    }

    /**
     * Add user to the participants list if not already in
     * @param id
     */
    public void addParticipant(int id, String nickname) throws ParticipantAlreadyInException {
        Participant participant = participants.get(id);
        if (participant == null){
            this.participants.put(id,new Participant(id,nickname));
        } else {
            throw new ParticipantAlreadyInException("Participant already in, can't add twice");
        }
    }

    /**
     * Removes user from the participants list if is in
     * @param id
     */
    public void removeParticipant(int id) throws ParticipantNotFoundException {
        Participant participant = getParticipantById(id);
        if (participant!=null){
            setCurrentAmount(currentAmount - participant.getAmount());
            this.participants.remove(id);
        } else {
            throw new ParticipantNotFoundException("Couldn't remove participant, not found");
        }
    }

    /**
     * If participant match id returns Participant else returns null
     *
     * @param id
     * @return
     */
    public Participant getParticipantById(int id) throws ParticipantNotFoundException {
        if(participants.get(id)==null){
            throw new ParticipantNotFoundException("Participant not found, can't update amount");
        } else {
            return participants.get(id);
        }
    }

    /**
     * Updates participant amount, if user not in the split throws ParticipantNotFoundException
     * @param participantId
     * @param newAmount
     * TODO : use getParticipantById
     * @exception ParticipantNotFoundException if participant not found
     */
    public void changeParticipantAmount(int participantId, double newAmount) throws ParticipantNotFoundException, GoalAmountExceededException {
        if(participants.get(participantId)==null){
            throw new ParticipantNotFoundException("Participant not found, can't update amount");
        } else {
            Participant participant = participants.get(participantId);
            double oldAmount = participant.getAmount();
            double newCurrentAmount = currentAmount-oldAmount+newAmount;
            if(newCurrentAmount<=goalAmount){
                participant.setAmount(newAmount);
                setCurrentAmount(currentAmount-oldAmount+newAmount);
            } else {
                throw new GoalAmountExceededException("Can't change amount, new amount exceeds the limit");
            }

        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSplitCode() {
        return splitCode;
    }

    public void setSplitCode(String splitCode) {
        this.splitCode = splitCode;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public String getSplitMode() {
        return splitMode;
    }

    public void setSplitMode(String splitMode) {
        this.splitMode = splitMode;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public HashMap<Integer, Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(HashMap<Integer, Participant> participants) {
        this.participants = participants;
    }

    public int getNumberOfParticipant(){return participants.size();}

    @Override
    public String toString() {
        return "Split{" +
                ", label='" + label + '\'' +
                ", splitCode='" + splitCode + '\'' +
                ", expired=" + expired +
                ", goalAmount=" + goalAmount +
                ", splitMode='" + splitMode + '\'' +
                ", ownerId=" + ownerId +
                ", participants=" + participants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Split split = (Split) o;
        return expired == split.expired && ownerId == split.ownerId && label.equals(split.label) && splitCode.equals(split.splitCode) && goalAmount==split.goalAmount && splitMode.equals(split.splitMode);
    }

    /**
     * True if every participants are ready
     * @return
     */
    public boolean isEveryOneReady() {
        boolean isEveryOneReady = true;
        Iterator iterator = participants.entrySet().iterator();

        while (iterator.hasNext() && isEveryOneReady){
            Map.Entry<Integer,Participant> participant = (Map.Entry) iterator.next();
            if(!participant.getValue().isReady()){
                isEveryOneReady=false;
            }
        }

        return isEveryOneReady;

    }

    /**
     * Switches participant ready status if exists in split
     * @param participantId
     */
    public void switchParticipateReadyStatus(int participantId) throws ParticipantNotFoundException {
        Participant participant = getParticipantById(participantId);
        participant.switchReadyStatus();
    }

    /**
     * True if a split is ready for payment (goal amount reached and every participant ready) else false
     * @return
     */
    public boolean isReadyForPayment(){
        return isGoalAmountReached() && isEveryOneReady();
    }

}

