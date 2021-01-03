package server.models.split;

public class FreeSplit extends Split {

    public FreeSplit(String splitCode, int ownerId, String ownerNickName, double goalAmount, String label){
        super(splitCode,ownerId,ownerNickName,label);
        this.goalAmount=goalAmount;
        setSplitMode(SplitMode.FREESPLIT);
    }

}

