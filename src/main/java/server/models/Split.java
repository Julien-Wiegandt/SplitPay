package server.models;

import server.exception.ParticipantNotFoundException;

import java.util.HashMap;

public class Split {

    public Split(String splitCode, int ownerId, String ownerNickName, Double goalAmount, String label, String splitMode){
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
    private Double goalAmount;
    private String splitMode;
    private int ownerId;
    private HashMap<Integer,Participant> participants = new HashMap<>();

    /**
     * True if every participants are ready
     * @return
     */
    private boolean isEveryOneReady(){
        return false;
    }

    /**
     * True if
     * @return
     */
    private boolean isGoalAmountReached(){
        return false;
    }

    /**
     * Add user to the participants list if not already in
     * TODO : check if user is in
     * @param id
     */
    public void addParticipant(int id, String nickname){
        this.participants.put(id,new Participant(id,nickname));
    }

    /**
     * Removes user from the participants list if is in
     * TODO: update the amount of the split
     * @param id
     */
    public void removeParticipant(int id){
        this.participants.remove(id);
    }

    /**
     * If participant match id returns Participant else returns null
     *
     * @param id
     * @return
     */
    public Participant getParticipantById(int id){
        return participants.get(id);
    }

    /**
     * Updates participant amount, if user not in the split throws ParticipantNotFoundException
     * @param id
     * @param amount
     *
     * @exception ParticipantNotFoundException if participant not found
     */
    public void changeAmount(int id, Double amount) throws ParticipantNotFoundException {
        if(participants.get(id)==null){
            throw new ParticipantNotFoundException("Participant not found, can't update amount");
        } else {
            participants.get(id).setAmount(amount);
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

    public Double getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(Double goalAmount) {
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
        return expired == split.expired && ownerId == split.ownerId && label.equals(split.label) && splitCode.equals(split.splitCode) && goalAmount.equals(split.goalAmount) && splitMode.equals(split.splitMode);
    }

}

