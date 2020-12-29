package splitParticipationTests;

import server.exception.ParticipantNotFoundException;
import server.facade.SplitServerFacade;
import server.exception.SplitNotFoundException;
import server.models.Participant;
import server.models.Split;
import org.junit.Before;
import org.junit.Test;
import util.SplitUtilities;

import java.lang.reflect.Field;

public class BasicSplitTests {
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
        Split expectedSplit = new Split(splitCode,1,"owner0",27.3,"new year bowling","freesplit");
        System.out.println(expectedSplit);
        System.out.println(facade.getSplitByCode(splitCode));

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
        String splitCode = facade.createSplit(1,"owner0",27.3,"new year bowling","freesplit");
        Participant expectedParticipant = new Participant(1,"owner0");
        Participant returnedParticipant = facade.getSplitByCode(splitCode).getParticipantById(expectedParticipant.getId());
        System.out.println(expectedParticipant);
        System.out.println(returnedParticipant);

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
