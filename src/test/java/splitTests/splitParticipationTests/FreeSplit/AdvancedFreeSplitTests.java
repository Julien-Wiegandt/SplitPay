package splitTests.splitParticipationTests.FreeSplit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.exception.splitException.GoalAmountExceededException;
import server.exception.splitException.ParticipantAlreadyInException;
import server.exception.splitException.ParticipantNotFoundException;
import server.exception.splitException.SplitNotFoundException;
import server.facade.SplitServerFacade;
import server.models.split.Participant;
import server.models.split.Split;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AdvancedFreeSplitTests {
    SplitServerFacade facade = SplitServerFacade.getInstance();

    Participant owner0;
    Participant participant1;
    Participant participant2;

    String splitCode;

    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = SplitServerFacade.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);

    }

    @Before
    public void resetSplit(){
        owner0 = new Participant(null,0,"owner0");
        participant1 = new Participant(null,1,"participant1");
        participant2 = new Participant(null,2,"participant2");
        splitCode = facade.createFreeSplit(owner0.getId(), owner0.getNickname(), 27.3,"New year bowling",null);

    }

    @Test
    public void join_NumberOfParticipantsEquals2_True() throws Exception {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,owner0.getId(),owner0.getNickname());

        Assert.assertEquals(2,facade.getNumberOfSplitParticipant(splitCode));

    }

    @Test
    public void changeParticipantAmount_AmountEquals2_True() throws Exception {

        facade.join(null,splitCode,participant2.getId(),participant2.getNickname());
        facade.changeParticipantAmount(splitCode,participant2.getId(),2);

        Assert.assertEquals(2,facade.getSplitParticipant(splitCode,participant2.getId()).getAmount(),0);

    }

    @Test
    public void changeParticipant_SplitCurrentAmountEquals12_True() throws Exception {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),2.50);
        facade.changeParticipantAmount(splitCode,participant1.getId(),12);

        Assert.assertEquals(12,facade.getSplitByCode(splitCode).getCurrentAmount(),0);

    }

    @Test
    public void changeParticipantAmount_MultipleParticipantCurrentAmountEquals13_True() throws Exception {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,participant2.getId(),participant2.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),2.50);
        facade.changeParticipantAmount(splitCode,participant2.getId(),0.8);
        facade.changeParticipantAmount(splitCode,participant1.getId(),12.2);

        Assert.assertEquals(0.8+12.2,facade.getSplitByCode(splitCode).getCurrentAmount(),0);

    }

    @Test(expected = GoalAmountExceededException.class)
    public void changeParticipantAmount_ExceedsLimit_ThrowsGoalAmountExceededException() throws Exception {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),30);

    }

    @Test
    public void isGoalAmountReached_CurrentAmountEqualsGoalAmount_True() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),27.3);

        Assert.assertTrue(facade.getSplitByCode(splitCode).isGoalAmountReached());

    }

    @Test
    public void isGoalAmountReached_CurrentAmountLessThanGoal_False() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),25.3);

        Assert.assertFalse(facade.getSplitByCode(splitCode).isGoalAmountReached());

    }

    @Test
    public void switchSplitParticipantReadyStatus_IsReady_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertTrue(facade.getSplitParticipant(splitCode, participant1.getId()).isReady());

    }

    @Test
    public void switchSplitParticipantReadyStatus_IsReady_False() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertFalse(facade.getSplitParticipant(splitCode, participant1.getId()).isReady());

    }

    @Test
    public void isEveryOneReady_NotEveryOneReady_False() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {

        facade.join(null,splitCode,owner0.getId(),owner0.getNickname());
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertFalse(facade.getSplitByCode(splitCode).isEveryOneReady());

    }

    @Test
    public void isEveryOneReady_EveryOneIsReady_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,owner0.getId(),owner0.getNickname());

        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,owner0.getId());

        Assert.assertTrue(facade.getSplitByCode(splitCode).isEveryOneReady());

    }

    @Test(expected = ParticipantNotFoundException.class)
    public void removeParticipant_isRemoved_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.removeSplitParticipant(splitCode,participant1.getId());
        facade.getSplitParticipant(splitCode,participant1.getId());

    }

    @Test
    public void removeParticipant_isCurrentAmount5_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException, GoalAmountExceededException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,owner0.getId(),owner0.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),5.34);
        facade.changeParticipantAmount(splitCode,owner0.getId(),5);

        facade.removeSplitParticipant(splitCode,participant1.getId());

        Assert.assertEquals(5,facade.getSplitByCode(splitCode).getCurrentAmount(),0);

    }

    @Test
    public void removeParticipant_isCurrentAmount0_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException, GoalAmountExceededException {

        facade.join(null,splitCode,owner0.getId(),owner0.getNickname());
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),5.34);
        facade.changeParticipantAmount(splitCode,owner0.getId(),5);

        facade.removeSplitParticipant(splitCode,participant1.getId());
        facade.removeSplitParticipant(splitCode,owner0.getId());

        Assert.assertEquals(0,facade.getSplitByCode(splitCode).getCurrentAmount(),0);

    }

    @Test
    public void getUserSplits_NoSplits_EmptyHashMap() {

        facade.createFreeSplit(owner0.getId(), owner0.getNickname(),27.3,"new year bowling",null);
        facade.createFreeSplit(participant1.getId(), participant1.getNickname(),23,"new year bowling2",null);
        facade.createFreeSplit(participant1.getId(), participant1.getNickname(),22,"new year bowling3",null);

        HashMap<String, Split> returnedSplits = facade.getUserSplits(participant2.getId());

        Assert.assertEquals(0,returnedSplits.size());

    }

    @Test
    public void getUserSplits_TwoSplits_HashMapLength2() {

        facade.createFreeSplit(owner0.getId(), owner0.getNickname(),27.3,"new year bowling",null);
        facade.createFreeSplit(participant1.getId(), participant1.getNickname(),23,"new year bowling2",null);
        facade.createFreeSplit(participant1.getId(), participant1.getNickname(),22,"new year bowling3",null);

        HashMap<String, Split> returnedSplits = facade.getUserSplits(participant1.getId());

        Assert.assertEquals(2,returnedSplits.size());

    }

    @Test
    public void isReadyForPayment_GoalAmountReachedButNotReady_False() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),27.3);

        Assert.assertFalse(facade.getSplitByCode(splitCode).isReadyForPayment());

    }

    @Test
    public void isReadyForPayment_GoalAmountReachedEveryOneReady_True() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,owner0.getId(),owner0.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),27.3);
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,owner0.getId());


        Assert.assertTrue(facade.getSplitByCode(splitCode).isReadyForPayment());

    }

}
