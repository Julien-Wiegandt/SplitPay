package splitParticipationTests.ItemSplit;

import org.junit.Assert;
import org.junit.Test;
import server.exception.GoalAmountExceededException;
import server.exception.ItemAlreadyPicked;
import server.exception.ParticipantAlreadyInException;
import server.exception.ParticipantNotFoundException;
import server.facade.SplitServerFacade;
import server.models.FreeSplit;
import server.models.Item;
import server.models.ItemSplit;
import server.models.Participant;
import util.SplitUtilities;

public class BasicItemSplitTests {
    @Test
    public void goalAmount_isEqualsToSumOfItemPrice_True() throws Exception {

        Participant participant1 = new Participant(null,2,"participant1");
        String splitCode = SplitUtilities.generateCode();
        Item pizza = new Item("margarita",5.90);
        Item drink = new Item("coke",1.50);
        Item desert = new Item("tiramisu",4.50);
        Item[] items = {pizza, drink, desert};
        ItemSplit split = new ItemSplit(splitCode,participant1.getId(),participant1.getNickname(),"Bowlingstar Montpellier","itemsplit",items);

        split.getGoalAmount();

        Assert.assertEquals(pizza.getPrice()+drink.getPrice()+desert.getPrice(),split.getGoalAmount(),0);
    }

    @Test
    public void pickItemUpdatesCurrentAmount_CurrentAmountEquals6_True() throws ItemAlreadyPicked, ParticipantNotFoundException, ParticipantAlreadyInException, GoalAmountExceededException {
        Participant participant1 = new Participant(null,2,"participant1");
        String splitCode = SplitUtilities.generateCode();
        Item pizza = new Item("margarita",5.90);
        Item drink = new Item("coke",1.50);
        Item desert = new Item("tiramisu",4.50);
        Item[] items = {pizza, drink, desert};
        ItemSplit split = new ItemSplit(splitCode,participant1.getId(),participant1.getNickname(),"Bowlingstar Montpellier","itemsplit",items);

        split.addParticipant(null,participant1.getId(),participant1.getNickname());

        split.pickItem(0,2);

        Assert.assertEquals(pizza.getPrice(),split.getCurrentAmount(),0);
    }
}
