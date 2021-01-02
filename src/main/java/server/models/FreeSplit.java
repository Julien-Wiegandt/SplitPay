package server.models;

public class FreeSplit extends Split {

    public FreeSplit(String splitCode, int ownerId, String ownerNickName, double goalAmount, String label, String splitMode){
        super(splitCode,ownerId,ownerNickName,label,splitMode);
        this.goalAmount=goalAmount;
    }

}

