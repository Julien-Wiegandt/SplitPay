package splitTests.splitParticipationTests.ItemSplit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.exception.splitException.*;
import server.models.split.Item;
import server.models.split.ItemSplit;
import server.models.split.Participant;
import util.SplitUtilities;

public class ItemSplitTests {
    ItemSplit split;
    String splitCode;

    Participant owner;
    Participant participant1;
    Participant participant2;

    Item pizza;
    Item drink;
    Item desert;

    Item[] items;

    @Before
    public void resetSplit() throws SecurityException, IllegalArgumentException {
        owner = new Participant(null,0,"owner0");
        participant1 = new Participant(null,1,"participant1");
        participant2 = new Participant(null,2,"participant2");
        pizza = new Item("margarita",4.00);
        drink = new Item("coke",1.50);
        desert = new Item("tiramisu",4.50);
        items = new Item[]{pizza, drink, desert};

        splitCode = SplitUtilities.generateCode();
        split = new ItemSplit(splitCode,participant1.getId(),participant1.getNickname(),"Bowlingstar Montpellier",items,null);

    }

    @Test
    public void goalAmount_isEqualsToSumOfItemPrice_True() throws Exception {
        Assert.assertEquals(pizza.getPrice()+drink.getPrice()+desert.getPrice(),split.getGoalAmount(),0);
    }

    @Test
    public void addParticipant_CartEmpty_True() throws ParticipantAlreadyInException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        Assert.assertEquals(0,split.getParticipantCart(participant1.getId()).size());
    }

    @Test
    public void addParticipant_AmountEquals0_True() throws ParticipantAlreadyInException, ParticipantNotFoundException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        Assert.assertEquals(0,split.getParticipantById(participant1.getId()).getAmount(),0);
    }

    @Test
    public void addParticipant_FirstParticipantIsAdmin_True() throws ParticipantAlreadyInException, ParticipantNotFoundException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        Assert.assertEquals(participant1.getId(),split.getSplitAdmin(),0);
    }

    @Test
    public void addParticipant_SecondParticipantIsAdmin_False() throws ParticipantAlreadyInException, ParticipantNotFoundException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.addParticipant(null,participant2.getId(),participant2.getNickname());
        Assert.assertNotEquals(participant2.getId(),split.getSplitAdmin(),0);
    }

    @Test
    public void pickItem_IsItemInCart_True() throws ParticipantAlreadyInException, ParticipantNotFoundException, GoalAmountExceededException, ItemAlreadyPickedException, UnknownItemException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        Assert.assertEquals(pizza,split.getParticipantCart(participant1.getId()).get(0));

    }

    @Test
    public void pickItem_IsItemPicked_True() throws ParticipantAlreadyInException, ParticipantNotFoundException, GoalAmountExceededException, ItemAlreadyPickedException, UnknownItemException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        Assert.assertTrue(split.getItem(0).isPicked());
    }

    @Test
    public void pickItem_CurrentAmountEquals6_True() throws ItemAlreadyPickedException, ParticipantNotFoundException, ParticipantAlreadyInException, GoalAmountExceededException, UnknownItemException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        Assert.assertEquals(pizza.getPrice(),split.getCurrentAmount(),0);
    }

    @Test(expected = ItemAlreadyPickedException.class)
    public void pickItem_ChoosePickedItem_ItemAlreadyPickedException() throws ParticipantAlreadyInException, ParticipantNotFoundException, GoalAmountExceededException, ItemAlreadyPickedException, UnknownItemException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.addParticipant(null,participant2.getId(),participant2.getNickname());
        split.pickItem(0,participant1.getId());
        split.pickItem(0,participant2.getId());
    }

    @Test(expected = UnknownItemException.class)
    public void pickItem_ChooseUnknownItem_UnknownItemException() throws ParticipantAlreadyInException, ParticipantNotFoundException, GoalAmountExceededException, ItemAlreadyPickedException, UnknownItemException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(9,participant1.getId());
    }

    @Test
    // TODO : Test after method implementation
    public void removeItem_IsItemPicked_False() throws ParticipantAlreadyInException, ItemAlreadyPickedException, GoalAmountExceededException, UnknownItemException, ParticipantNotFoundException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        split.removeItem(0,participant1.getId());
        Assert.assertFalse(split.getItem(0).isPicked());
    }

    @Test
    public void removeItem_IsInCart_False() throws ParticipantAlreadyInException, ItemAlreadyPickedException, GoalAmountExceededException, UnknownItemException, ParticipantNotFoundException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        split.removeItem(0,participant1.getId());
        Assert.assertFalse(split.getParticipantCart(participant1.getId()).contains(split.getItem(0).getPrice()));


    }

    @Test
    public void removeItem_IsAmount4_True() throws ParticipantAlreadyInException, ItemAlreadyPickedException, GoalAmountExceededException, UnknownItemException, ParticipantNotFoundException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        split.pickItem(1,participant1.getId());
        split.pickItem(2,participant1.getId());
        split.removeItem(1,participant1.getId());
        split.removeItem(2,participant1.getId());
        Assert.assertEquals(split.getItem(0).getPrice(),split.getParticipantById(participant1.getId()).getAmount(),0);
    }

    @Test
    public void removeParticipant_IsGoalAmount4_True() throws ParticipantAlreadyInException, ItemAlreadyPickedException, GoalAmountExceededException, UnknownItemException, ParticipantNotFoundException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.addParticipant(null,participant2.getId(),participant2.getNickname());
        split.pickItem(0,participant1.getId());
        split.pickItem(1,participant2.getId());
        split.pickItem(2,participant2.getId());
        split.removeParticipant(participant2.getId());
        Assert.assertEquals(split.getItem(0).getPrice(),split.getCurrentAmount(),0);
    }

    @Test
    public void removeParticipant_IsItemPicked_False() throws ParticipantAlreadyInException, ParticipantNotFoundException, UnknownItemException, GoalAmountExceededException, ItemAlreadyPickedException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        split.removeParticipant(participant1.getId());
        Assert.assertFalse(split.getItem(0).isPicked());
    }

    @Test
    public void removeParticipant_NoAdmin_False() throws ParticipantAlreadyInException, ParticipantNotFoundException, UnknownItemException, GoalAmountExceededException, ItemAlreadyPickedException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.pickItem(0,participant1.getId());
        split.removeParticipant(participant1.getId());
        Assert.assertFalse(split.isOwned());
    }

    @Test
    public void removeParticipant_IsStillAdministratedByOther_True() throws ParticipantAlreadyInException, ParticipantNotFoundException, UnknownItemException, GoalAmountExceededException, ItemAlreadyPickedException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.addParticipant(null,participant2.getId(),participant2.getNickname());
        split.pickItem(0,participant1.getId());
        split.removeParticipant(participant1.getId());
        Assert.assertTrue(split.isOwned());
    }

    @Test
    public void removeParticipant_ChangeParticipant_Participant2Admin() throws ParticipantAlreadyInException, ParticipantNotFoundException, UnknownItemException, GoalAmountExceededException, ItemAlreadyPickedException {
        split.addParticipant(null,participant1.getId(),participant1.getNickname());
        split.addParticipant(null,participant2.getId(),participant2.getNickname());
        split.pickItem(0,participant1.getId());
        split.removeParticipant(participant1.getId());
        Assert.assertEquals(participant2.getId(),split.getSplitAdmin());
    }



}
