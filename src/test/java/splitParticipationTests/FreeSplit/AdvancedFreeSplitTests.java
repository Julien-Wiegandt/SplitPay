package splitParticipationTests.FreeSplit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.exception.GoalAmountExceededException;
import server.exception.ParticipantAlreadyInException;
import server.exception.ParticipantNotFoundException;
import server.exception.SplitNotFoundException;
import server.facade.SplitServerFacade;
import server.models.Participant;
import server.models.FreeSplit;

import java.lang.reflect.Field;
import java.util.HashMap;

public class AdvancedFreeSplitTests {
    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = SplitServerFacade.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void participantJoin() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        facade.createSplit(1,"owner0",10.99,"pizza night","freesplit");

        Participant owner = new Participant(null,1,"owner0");
        Participant expectedParticipant = new Participant(null,2,"participant1");

        facade.join(null,splitCode,expectedParticipant.getId(),expectedParticipant.getNickname());
        facade.join(null,splitCode,owner.getId(),owner.getNickname());

        Assert.assertEquals(2,facade.getNumberOfSplitParticipant(splitCode));
    }

    @Test
    public void participantChangeAmount() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant = new Participant(null,2,"participant1");
        facade.join(null,splitCode,2,participant.getNickname());
        facade.changeParticipantAmount(splitCode,participant.getId(),2.50);
        Assert.assertEquals(2.50,facade.getSplitParticipant(splitCode,participant.getId()).getAmount(),0);
    }

    @Test
    public void splitAmountUpdateCorrectlyOneParticipant() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant participant1 = new Participant(null,2,"participant1");

        facade.join(null,splitCode,2,participant1.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),2.50);
        facade.changeParticipantAmount(splitCode,participant1.getId(),12.2);

        Assert.assertEquals(12.2,facade.getSplitByCode(splitCode).getCurrentAmount(),0);
    }

    @Test
    public void splitAmountUpdateCorrectlyMultipleParticipant() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant participant1 = new Participant(null,2,"participant1");
        Participant participant2 = new Participant(null,3,"participant2");

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,participant2.getId(),participant2.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),2.50);
        facade.changeParticipantAmount(splitCode,participant2.getId(),5.35);
        facade.changeParticipantAmount(splitCode,participant1.getId(),12.2);

        Assert.assertEquals(5.35+12.2,facade.getSplitByCode(splitCode).getCurrentAmount(),0);
    }

    @Test(expected = GoalAmountExceededException.class)
    public void splitAmountLimit() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant participant1 = new Participant(null,2,"participant1");

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),30);
    }

    @Test
    public void isGoalAmountReached_CurrentAmountEqualsToGoal_True() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(null,2,"participant1");
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),27.3);

        Assert.assertEquals(true,facade.getSplitByCode(splitCode).isGoalAmountReached());

    }

    @Test
    public void isGoalAmountReached_CurrentAmountLessThanGoal_False() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(null,2,"participant1");
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),25.3);

        Assert.assertEquals(false,facade.getSplitByCode(splitCode).isGoalAmountReached());

    }

    @Test
    public void changeState_Ready_true() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(null,2,"participant1");
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertEquals(true,facade.getSplitParticipant(splitCode,participant1.getId()).isReady());

    }

    @Test
    public void changeState_False_false() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(null,2,"participant1");
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertEquals(false,facade.getSplitParticipant(splitCode,participant1.getId()).isReady());

    }

    @Test
    public void isEveryOneReady_EveryOneIsNotReady_false() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant owner = new Participant(null,1,"owner0");
        Participant participant1 = new Participant(null,2,"participant1");

        facade.join(null,splitCode,owner.getId(),owner.getNickname());
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertEquals(false,facade.getSplitByCode(splitCode).isEveryOneReady());

    }

    @Test
    public void isEveryOneReady_EveryOneIsReady_true() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant owner = new Participant(null,1,"owner0");
        Participant participant1 = new Participant(null,2,"participant1");

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,owner.getId(),owner.getNickname());

        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,owner.getId());

        Assert.assertEquals(true,facade.getSplitByCode(splitCode).isEveryOneReady());

    }

    @Test(expected = ParticipantNotFoundException.class)
    public void removeParticipant_isRemoved_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(null,2,"participant1");
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.removeSplitParticipant(splitCode,participant1.getId());
        facade.getSplitParticipant(splitCode,participant1.getId());
    }

    @Test
    public void removeParticipant_isCurrentAmount5_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException, GoalAmountExceededException {
        SplitServerFacade facade = SplitServerFacade.getInstance();

        Participant participant1 = new Participant(null,2,"participant1");
        Participant owner = new Participant(null,1,"owner0");

        String splitCode = facade.createSplit(owner.getId(), owner.getNickname(),27.3,"new year bowling","freesplit");

        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,owner.getId(),owner.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),5.34);
        facade.changeParticipantAmount(splitCode,owner.getId(),5);

        facade.removeSplitParticipant(splitCode,participant1.getId());

        Assert.assertEquals(5,facade.getSplitByCode(splitCode).getCurrentAmount(),0);

    }

    @Test
    public void removeParticipant_isCurrentAmount0_True() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException, GoalAmountExceededException {
        SplitServerFacade facade = SplitServerFacade.getInstance();

        Participant participant1 = new Participant(null,2,"participant1");
        Participant owner = new Participant(null,1,"owner0");

        String splitCode = facade.createSplit(owner.getId(), owner.getNickname(),27.3,"new year bowling","freesplit");

        facade.join(null,splitCode,owner.getId(),owner.getNickname());
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),5.34);
        facade.changeParticipantAmount(splitCode,owner.getId(),5);

        facade.removeSplitParticipant(splitCode,participant1.getId());
        facade.removeSplitParticipant(splitCode,owner.getId());

        Assert.assertEquals(0,facade.getSplitByCode(splitCode).getCurrentAmount(),0);

    }

    @Test
    public void getUserSplits_NoSplits_EmptyHashMap() {
        SplitServerFacade facade = SplitServerFacade.getInstance();

        Participant participant2 = new Participant(null,2,"participant2");
        Participant participant1 = new Participant(null,1,"participant1");
        Participant owner = new Participant(null,0,"owner0");

        facade.createSplit(owner.getId(), owner.getNickname(),27.3,"new year bowling","freesplit");
        facade.createSplit(participant1.getId(), participant1.getNickname(),23,"new year bowling2","freesplit");
        facade.createSplit(participant1.getId(), participant1.getNickname(),22,"new year bowling3","freesplit");


        HashMap<String, FreeSplit> returnedSplits = facade.getUserSplits(participant2.getId());

        Assert.assertEquals(0,returnedSplits.size());
    }

    @Test
    public void getUserSplits_TwoSplits_HashMapLength2() {
        SplitServerFacade facade = SplitServerFacade.getInstance();

        Participant participant2 = new Participant(null,2,"participant2");
        Participant participant1 = new Participant(null,1,"participant1");
        Participant owner = new Participant(null,0,"owner0");

        facade.createSplit(owner.getId(), owner.getNickname(),27.3,"new year bowling","freesplit");
        facade.createSplit(participant1.getId(), participant1.getNickname(),23,"new year bowling2","freesplit");
        facade.createSplit(participant1.getId(), participant1.getNickname(),22,"new year bowling3","freesplit");

        HashMap<String, FreeSplit> returnedSplits = facade.getUserSplits(participant1.getId());

        Assert.assertEquals(2,returnedSplits.size());
    }

    @Test
    public void isReadyForPayment_GoalAmountReachedButNotReady_False() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();

        Participant participant1 = new Participant(null,1,"participant1");
        Participant owner = new Participant(null,0,"owner0");

        String splitCode = facade.createSplit(owner.getId(), owner.getNickname(),27.3,"new year bowling","freesplit");
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),27.3);

        Assert.assertEquals(false,facade.getSplitByCode(splitCode).isReadyForPayment());

    }

    @Test
    public void isReadyForPayment_GoalAmountReachedEveryOneReady_True() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();

        Participant participant1 = new Participant(null,1,"participant1");
        Participant owner = new Participant(null,0,"owner0");

        String splitCode = facade.createSplit(owner.getId(), owner.getNickname(),27.3,"new year bowling","freesplit");
        facade.join(null,splitCode,participant1.getId(),participant1.getNickname());
        facade.join(null,splitCode,owner.getId(),owner.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),27.3);
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,owner.getId());


        Assert.assertEquals(true,facade.getSplitByCode(splitCode).isReadyForPayment());

    }

}
