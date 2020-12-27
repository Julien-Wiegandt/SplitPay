package splitParticipationTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.exception.GoalAmountExceededException;
import server.exception.ParticipantAlreadyInException;
import server.exception.ParticipantNotFoundException;
import server.exception.SplitNotFoundException;
import server.facade.SplitServerFacade;
import server.models.Participant;

import java.lang.reflect.Field;

public class AdvancedSplitTests {
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
        Participant expectedParticipant = new Participant(2,"participant1");
        facade.join(splitCode,2,expectedParticipant.getNickname());
        Assert.assertEquals(2,facade.getNumberOfSplitParticipant(splitCode));
    }

    @Test
    public void participantChangeAmount() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant = new Participant(2,"participant1");
        facade.join(splitCode,2,participant.getNickname());
        facade.changeParticipantAmount(splitCode,participant.getId(),2.50);
        Assert.assertEquals(2.50,facade.getSplitParticipant(splitCode,participant.getId()).getAmount(),0);
    }

    @Test
    public void splitAmountUpdateCorrectlyOneParticipant() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant participant1 = new Participant(2,"participant1");

        facade.join(splitCode,2,participant1.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),2.50);
        facade.changeParticipantAmount(splitCode,participant1.getId(),12.2);

        Assert.assertEquals(12.2,facade.getSplitByCode(splitCode).getCurrentAmount(),0);
    }

    @Test
    public void splitAmountUpdateCorrectlyMultipleParticipant() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant participant1 = new Participant(2,"participant1");
        Participant participant2 = new Participant(3,"participant2");

        facade.join(splitCode,participant1.getId(),participant1.getNickname());
        facade.join(splitCode,participant2.getId(),participant2.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),2.50);
        facade.changeParticipantAmount(splitCode,participant2.getId(),5.35);
        facade.changeParticipantAmount(splitCode,participant1.getId(),12.2);

        Assert.assertEquals(5.35+12.2,facade.getSplitByCode(splitCode).getCurrentAmount(),0);
    }

    @Test(expected = GoalAmountExceededException.class)
    public void splitAmountLimit() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");

        Participant participant1 = new Participant(2,"participant1");

        facade.join(splitCode,participant1.getId(),participant1.getNickname());

        facade.changeParticipantAmount(splitCode,participant1.getId(),30);
    }

    @Test
    public void isGoalAmountReached_CurrentAmountEqualsToGoal_True() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(2,"participant1");
        facade.join(splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),27.3);

        Assert.assertEquals(true,facade.getSplitByCode(splitCode).isGoalAmountReached());

    }

    @Test
    public void isGoalAmountReached_CurrentAmountLessThanGoal_False() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(2,"participant1");
        facade.join(splitCode,participant1.getId(),participant1.getNickname());
        facade.changeParticipantAmount(splitCode,participant1.getId(),25.3);

        Assert.assertEquals(false,facade.getSplitByCode(splitCode).isGoalAmountReached());

    }

    @Test
    public void changeState_Ready_true() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(2,"participant1");
        facade.join(splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertEquals(true,facade.getSplitParticipant(splitCode,participant1.getId()).isReady());

    }

    @Test
    public void changeState_False_false() throws ParticipantAlreadyInException, SplitNotFoundException, GoalAmountExceededException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(2,"participant1");
        facade.join(splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertEquals(false,facade.getSplitParticipant(splitCode,participant1.getId()).isReady());

    }

    @Test
    public void isEveryOneReady_everyOneIsNotReady_false() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(2,"participant1");
        facade.join(splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());

        Assert.assertEquals(false,facade.getSplitByCode(splitCode).isEveryOneReady());

    }

    @Test
    public void isEveryOneReady_EveryOneIsReady_true() throws ParticipantAlreadyInException, SplitNotFoundException, ParticipantNotFoundException {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant1 = new Participant(2,"participant1");
        facade.join(splitCode,participant1.getId(),participant1.getNickname());
        facade.switchSplitParticipantReadyStatus(splitCode,participant1.getId());
        facade.switchSplitParticipantReadyStatus(splitCode,1);

        Assert.assertEquals(true,facade.getSplitByCode(splitCode).isEveryOneReady());

    }

}
