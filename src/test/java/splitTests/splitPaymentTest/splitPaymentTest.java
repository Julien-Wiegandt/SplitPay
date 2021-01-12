package splitTests.splitPaymentTest;

import core.models.StoreOwner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.models.split.Item;
import server.models.split.ItemSplit;
import server.models.split.Participant;
import util.SplitUtilities;

public class splitPaymentTest {

    ItemSplit split;
    String splitCode;

    Participant owner;
    Participant participant1;
    Participant participant3;

    Item pizza;
    Item drink;
    Item desert;

    Item[] items;

    @Before
    public void resetSplit() throws SecurityException, IllegalArgumentException {

        owner = new Participant(null,0,"owner0");

        participant1 = new Participant(null,1,"participant1");
        participant3 = new Participant(null,3,"participant3");

        pizza = new Item("margarita",4.00);
        drink = new Item("coke",1.50);
        desert = new Item("tiramisu",4.50);
        items = new Item[]{pizza, drink, desert};

        StoreOwner storeOwner = new StoreOwner("1",
                "delarte34@hotmail.com",
                "0767342312",
                "362 521 879 00034",
                "delarte34",
                "delarte34080",
                Float.parseFloat("743.67"),
                "Del arte restaurant",
                "145 Rue Alphonse Beau de Rochas, 34170 Castelnau");


        splitCode = SplitUtilities.generateCode();
        split = new ItemSplit(splitCode,participant1.getId(),participant1.getNickname(),"Bowlingstar Montpellier",items,storeOwner);


    }
    @Test
    public void paySplit_TransactionCreationWithoutError() throws Exception {

        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.addParticipant(null,participant3.getId(),participant3.getNickname());

        split.pickItem(0,participant1.getId());
        split.pickItem(1,participant1.getId());
        split.pickItem(2,participant3.getId());

        split.switchParticipantReadyStatus(participant1.getId());
        split.switchParticipantReadyStatus(participant3.getId());

        split.paySplit();
    }
}
