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

    @Test(expected = SplitNotFoundException.class)
    public void getSplitByCode_WrongCode_ThrowsSplitNotFoundException() throws Exception {

        String randomSplitCode = SplitUtilities.generateCode();
        facade.getSplitByCode(randomSplitCode);

    }

    @Test
    public void getParticipantById_CorrectId_ReturnsExpectedParticipant() throws Exception {

        facade.join(null,splitCode,owner0.getId(),owner0.getNickname());

        Participant returnedParticipant = facade.getSplitByCode(splitCode).getParticipantById(owner0.getId());

        if(!owner0.equals(returnedParticipant)){
            throw new Exception("Didn't get the expected participant");
        }
    }

    @Test(expected = ParticipantNotFoundException.class)
    public void getParticipantByWrongId() throws Exception {

        Participant returnedParticipant = facade.getSplitByCode(splitCode).getParticipantById(123456);

        if(returnedParticipant!= null){
            throw new Exception("Didn't get the expected participant");
        }
    }
}
