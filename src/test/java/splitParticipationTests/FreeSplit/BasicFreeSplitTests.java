package splitParticipationTests.FreeSplit;

import server.exception.splitException.ParticipantNotFoundException;
import server.facade.SplitServerFacade;
import server.exception.splitException.SplitNotFoundException;
import server.models.split.Participant;
import server.models.split.FreeSplit;
import org.junit.Before;
import org.junit.Test;
import util.SplitUtilities;

import java.lang.reflect.Field;

public class BasicFreeSplitTests {
    @Before
    public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = SplitServerFacade.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void getSplitByCode() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        FreeSplit expectedSplit = new FreeSplit(splitCode,1,"owner0",27.3,"new year bowling");

        if(!facade.getSplitByCode(splitCode).equals(expectedSplit)){throw new Exception("Didn't get the expected split");}
    }

    @Test(expected = SplitNotFoundException.class)
    public void getSplitByWrongCode() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode=SplitUtilities.generateCode();
        facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        facade.getSplitByCode(splitCode);
    }

    @Test
    public void getParticipantById() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();

        Participant owner = new Participant(null,1,"owner0");

        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        facade.join(null,splitCode,owner.getId(),owner.getNickname());

        Participant expectedParticipant = new Participant(null,1,"owner0");
        Participant returnedParticipant = facade.getSplitByCode(splitCode).getParticipantById(expectedParticipant.getId());

        if(!expectedParticipant.equals(returnedParticipant)){
            throw new Exception("Didn't get the expected participant");
        }
    }

    @Test(expected = ParticipantNotFoundException.class)
    public void getParticipantByWrongId() throws Exception {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant expectedParticipant = null;
        Participant returnedParticipant = facade.getSplitByCode(splitCode).getParticipantById(123456);

        if(returnedParticipant!=expectedParticipant){
            throw new Exception("Didn't get the expected participant");
        }
    }
}
