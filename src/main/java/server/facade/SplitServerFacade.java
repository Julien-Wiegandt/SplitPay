package server.facade;

import server.ConnectionToClient;
import server.ObservableOriginatorServer;
import server.ObservableServer;
import server.SplitOriginatorMessage;
import server.exception.GoalAmountExceededException;
import server.exception.ParticipantAlreadyInException;
import server.exception.ParticipantNotFoundException;
import server.exception.SplitNotFoundException;
import server.models.Participant;
import server.models.Split;
import util.SplitUtilities;

import java.io.IOException;
import java.util.*;

public class SplitServerFacade implements Observer {

    // CLASS VARIABLES

    /**
     * The string sent to the observers when a client requests a split cretion.
     */
    public static final String CREATION_REQUEST= "#OS:Creation Request";

    /**
     * The string sent to the client when a has requested his splits.
     */
    public static final String GET_SPLIT_REQUEST= "#OS:Get Split Request";

    /**
     * The string sent to the client when a client has successfully joined a split.
     */
    public static final String JOINED_SPLIT= "#OS:Joined Split";

    /**
     * The string sent to the client when a client has failed to join a split.
     */
    public static final String SPLIT_NOT_FOUND= "#OS:Split Not Found";

    /**
     * The string sent to the observers when a client tries to join a split.
     */
    public static final String JOIN_SPLIT_ATTEMPT= "#OS:Join Split Attempt";

    /**
     * The string sent to the observers when a client tries to join a split.
     */
    public static final String PARTICIPANT_ALREADY_IN_SPLIT= "#OS:Participant Already In Split";

    private HashMap<String, Split> splits = new HashMap<>();
    private static SplitServerFacade instance = null;

    ObservableServer communicationService;

    /**
     * Private constructor for Singleton pattern
     */
    private SplitServerFacade(int port)
    {
        communicationService = new ObservableOriginatorServer(port);
        communicationService.addObserver(this);
    }

    public void listen() throws IOException{
        communicationService.listen();
    }

    /**
     * Thread-safe double lock Singleton pattern constructor
     * @return
     */
    public static SplitServerFacade getInstance() {
        if (instance == null) {
            synchronized(SplitServerFacade.class) {
                if (instance == null) {
                    instance = new SplitServerFacade(DEFAULT_PORT);
                }
            }
        }
        return instance;
    }

    /**
     * Creates a split and adds the creator as the first participant
     * @param ownerId
     * @param ownerNickName
     * @param goalAmount
     * @param label
     * @param splitMode
     */
    public String createSplit(int ownerId, String ownerNickName, double goalAmount, String label, String splitMode) {
        String splitCode = SplitUtilities.generateCode();
        Split split = new Split(splitCode, ownerId,ownerNickName,goalAmount,label,splitMode);
        splits.put(split.getSplitCode(),split);
        return splitCode;
    }

    /**
     * If not already a participant, makes a user join a split with his id and nickname else throws an exception
     * @param splitCode
     * @param participantId
     * @param participantNickname
     * @exception SplitNotFoundException
     * @exception ParticipantAlreadyInException
     */
    public HashMap<String,Split> join(String splitCode, int participantId, String participantNickname) throws SplitNotFoundException, ParticipantAlreadyInException {
        Split split = getSplitByCode(splitCode);
        split.addParticipant(participantId,participantNickname);
        HashMap<String,Split> data = new HashMap<>();
        data.put(splitCode,split);
        return data;
    }

    /**
     * Returns split identified by the splitcode
     * @param splitCode
     * @return
     * @throws SplitNotFoundException
     */
    public Split getSplitByCode(String splitCode) throws SplitNotFoundException {
        Split split = splits.get(splitCode);
        if(split==null){
            throw new SplitNotFoundException("No split matching the splitCode found");
        } else {
            return split;
        }
    }

    /**
     * Returns matching participant in a split if matching input
     * @param splitCode
     * @param participantId
     * @return
     * @throws SplitNotFoundException
     */
    public Participant getSplitParticipant(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        return getSplitByCode(splitCode).getParticipantById(participantId);
    }

    /**
     * Returns a hashmap of splits owned by the user
     * @return
     */
    public HashMap<String,Split> getUserSplits(int id) {
        HashMap<String,Split> userSplits = new HashMap<>();
        Iterator iterator = splits.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String,Split> split = (Map.Entry) iterator.next();
            if(split.getValue().getOwnerId()==id){
                userSplits.put(split.getKey().toString(),split.getValue());
            }
        }

        return userSplits;

    }

    /**
     * Returns the number of participant in a split
     * @param splitCode
     * @return
     * @throws SplitNotFoundException
     */
    public int getNumberOfSplitParticipant(String splitCode) throws SplitNotFoundException {
        return getSplitByCode(splitCode).getNumberOfParticipant();
    }

    /**
     * Changes split participant amount
     * @param splitCode
     * @param participantId
     * @param newAmount
     * @throws SplitNotFoundException
     */
    public void changeParticipantAmount(String splitCode, int participantId, double newAmount) throws SplitNotFoundException, ParticipantNotFoundException, GoalAmountExceededException {
        getSplitByCode(splitCode).changeParticipantAmount(participantId,newAmount);
    }

    /**
     * Removes participant from split if is found
     * @param splitCode
     * @param participantId
     * @throws SplitNotFoundException
     * @throws ParticipantNotFoundException
     */
    public void removeSplitParticipant(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        getSplitByCode(splitCode).removeParticipant(participantId);
    }

    /**
     * Checks if the goal amount is reached
     * @param splitcode
     * @return
     * @throws SplitNotFoundException
     */
    public boolean isSplitGoalAmountReached(String splitcode) throws SplitNotFoundException {
        return getSplitByCode(splitcode).isGoalAmountReached();
    }

    /**
     * Switches ready status state
     */
    public void switchSplitParticipantReadyStatus(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        getSplitByCode(splitCode).switchParticipateReadyStatus(participantId);
    }

    /**
     * This method handles any messages received from the client.
     *
     * @param msg The message received from the client.
     * @param client The connection from which the message originated.
     *               TODO : handle try catches correctly
     */
    public void handleMessageFromClient(Object msg, ConnectionToClient client) {
        SplitOriginatorMessage message = (SplitOriginatorMessage) msg;

        int userId;
        String splitCode;

        switch (message.getMessage()){
            // TODO : test
            case CREATION_REQUEST:
                int ownerId = Integer.getInteger(((SplitOriginatorMessage) msg).getArgument("ownerId"));
                String ownerNickname = ((SplitOriginatorMessage) msg).getArgument("ownerNickname");
                Double goalAmount = Double.parseDouble(((SplitOriginatorMessage) msg).getArgument("goalAmount"));
                String label = ((SplitOriginatorMessage) msg).getArgument("label");
                String splitMode = ((SplitOriginatorMessage) msg).getArgument("splitMode");
                splitCode = createSplit(ownerId,ownerNickname,goalAmount,label,splitMode);
                try {
                    HashMap<Integer,Split> split = new HashMap<>();
                    //split.put(splitCode,getSplitByCode(splitCode));
                    getSplitByCode(splitCode);
                    // TODO : reimplement after changing type to string
                } catch (SplitNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    client.sendToClient(new SplitOriginatorMessage(null, JOINED_SPLIT, null, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case GET_SPLIT_REQUEST:
                userId = Integer.parseInt(message.getArguments().get("userId"));
                HashMap<String,Split> userSplits = getUserSplits(userId);
                try {
                    client.sendToClient(new SplitOriginatorMessage(null, GET_SPLIT_REQUEST,null, userSplits));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case JOIN_SPLIT_ATTEMPT:
                System.out.println("Join split attempt");
                userId = Integer.parseInt(message.getArguments().get("userId"));
                String nickName = message.getArgument("nickName");
                splitCode = message.getArgument("splitCode");
                try {
                    HashMap<String,Split> data = join(splitCode,userId,nickName);
                    // TODO : return split hash
                    try {
                        System.out.println("Success : Joined split");
                        client.sendToClient(new SplitOriginatorMessage(null,JOINED_SPLIT,null,data));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (SplitNotFoundException e) {
                    try {
                        System.out.println("Error : Split not found");
                        // TODO : handle client side
                        client.sendToClient(new SplitOriginatorMessage(null,SPLIT_NOT_FOUND,null,null));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } catch (ParticipantAlreadyInException e) {
                    System.out.println("Error : Participant already in");
                    // TODO : handle client side
                    try {
                        client.sendToClient(new SplitOriginatorMessage(null,PARTICIPANT_ALREADY_IN_SPLIT,null,null));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
                break;

        }
    }

    /**
     * Receives signals from the server
     * @param o
     * @param arg
     * TODO : implement cases
     */
    @Override
    public void update(Observable o, Object arg) {
        SplitOriginatorMessage msg= ((SplitOriginatorMessage)arg);
        String message = msg.getMessage();

        if (ObservableOriginatorServer.SERVER_STARTED.equals(message)) {
            System.out.println("Server started");
        } else if (ObservableOriginatorServer.SERVER_STOPPED.equals(message)) {
            System.out.println("Server stopped");
        } else if (ObservableOriginatorServer.CLIENT_CONNECTED.equals(message)) {
            System.out.println("Client connected");
        } else if (ObservableOriginatorServer.CLIENT_DISCONNECTED.equals(message)) {
            System.out.println("Client disconnected");
        } else if (ObservableOriginatorServer.CLIENT_EXCEPTION.equals(message)) {
            System.out.println("Client exception");
        } else if (ObservableOriginatorServer.SERVER_CLOSED.equals(message)) {
            System.out.println("Server closed");
        } else {
            this.handleMessageFromClient(msg,msg.getOriginator());
        }

    }

    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;

    public static void main(String[] args)
    {

        int port = 0; //Port to listen on

        try
        {
            port = Integer.parseInt(args[0]); //Get port from command line
        }
        catch(Throwable t)
        {
            port = DEFAULT_PORT; //Set port to 5555
        }

        SplitServerFacade facade = new SplitServerFacade(port);
        facade.createSplit(1,"testUser",63.2,"bowling","freesplit");
        System.out.println(facade.createSplit(2,"User 2",32.2,"Pizza","freesplit"));

        try
        {
            facade.listen();
            //Start listening for connections
        }
        catch (Exception ex)
        {
            System.out.println("ERROR - Could not listen for clients!");
        }
    }


}