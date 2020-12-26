package splitParticipationTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
        Assert.assertEquals(facade.getNumberOfSplitParticipant(splitCode),2);
    }

    @Test
    public void participantChangeAmount() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant participant = new Participant(2,"participant1");
        facade.join(splitCode,2,participant.getNickname());
        facade.changeParticipantAmount(splitCode,participant.getId(),2.50);
        Assert.assertEquals(facade.getSplitParticipant(splitCode,participant.getId()).getAmount(), 2.50,0);
    }
}
