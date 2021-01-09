package server.models.split;

import core.models.StoreOwner;
import server.exception.splitException.ParticipantNotFoundException;

public class EqualSplit extends Split{
    public EqualSplit(String splitCode, int ownerId, String ownerNickName, String label, StoreOwner receiver, int numberOfParticipants) {
        super(splitCode, ownerId, ownerNickName, label, receiver);
        this.goalAmount=goalAmount;
        setSplitMode(SplitMode.EQUALSPLIT);
    }

    @Override
    public void switchParticipantReadyStatus(int participantId) throws ParticipantNotFoundException {
//        changeParticipantAmount();
        super.switchParticipantReadyStatus(participantId);
    }
}
