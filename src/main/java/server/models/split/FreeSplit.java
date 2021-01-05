package server.models.split;

import core.models.StoreOwner;

public class FreeSplit extends Split {

    public FreeSplit(String splitCode, int ownerId, String ownerNickName, double goalAmount, String label, StoreOwner receiver){
        super(splitCode,ownerId,ownerNickName,label,receiver);
        this.goalAmount=goalAmount;
        setSplitMode(SplitMode.FREESPLIT);
    }

}

