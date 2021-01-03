package server.facade;

import server.communication.ConnectionToClient;
import server.communication.ObservableOriginatorServer;
import server.communication.ObservableServer;
import server.communication.SplitOriginatorMessage;
import server.exception.splitException.GoalAmountExceededException;
import server.exception.splitException.ParticipantAlreadyInException;
import server.exception.splitException.ParticipantNotFoundException;
import server.exception.splitException.SplitNotFoundException;
import server.models.split.*;
import util.ClientServerProtocol;
import util.SplitUtilities;

import java.io.IOException;
import java.util.*;

public class SplitServerFacade implements Observer {

    // CLASS VARIABLES

    /**
     * The default port to listen on.
     */
    final public static int DEFAULT_PORT = 5555;

    private HashMap<String, Split> splits = new HashMap<>();
    private static SplitServerFacade instance = null;

    ObservableServer communicationService;

    /**
     * Private constructor for Singleton pattern
     */
    private SplitServerFacade()
    {
        communicationService = new ObservableOriginatorServer(DEFAULT_PORT);
        communicationService.addObserver(this);
    }

    public void listen() throws IOException{
        communicationService.listen();
    }

    /**
     * Thread-safe double lock Singleton pattern constructor
     * @return the instance of splitserverfacade
     */
    public static SplitServerFacade getInstance() {
        if (instance == null) {
            synchronized(SplitServerFacade.class) {
                if (instance == null) {
                    instance = new SplitServerFacade();
                }
            }
        }
        return instance;
    }

    /**
     * Creates a free split and sets the owner
     * @param ownerId the id of the split creator
     * @param ownerNickName the nickname of the split creator
     * @param goalAmount the goal amount of the split
     * @param label the title of the split
     * @param splitMode the split mode
     */
    public String createFreeSplit(int ownerId, String ownerNickName, double goalAmount, String label, String splitMode) {
        String splitCode = SplitUtilities.generateCode();
        FreeSplit split = new FreeSplit(splitCode, ownerId,ownerNickName,goalAmount,label);
        splits.put(split.getSplitCode(),split);
        return splitCode;
    }

    /**
     * Creates an item split and sets the owner
     * @param ownerId the id of the split creator
     * @param ownerNickName the nickname of the split creator
     * @param label the title of the split
     */
    public String createItemSplit(int ownerId, String ownerNickName, String label, Item[] items) {
        String splitCode = SplitUtilities.generateCode();
        ItemSplit split = new ItemSplit(splitCode, ownerId,ownerNickName,label,items);
        splits.put(split.getSplitCode(),split);
        return splitCode;
    }

    /**
     * If not already a participant, makes a user join a split with his id and nickname else throws an exception
     * @param splitCode the splitcode of the targeted split
     * @param participantId the id of the requester
     * @param participantNickname the nickname of the requester
     * @exception SplitNotFoundException no corresponding split found for the splitcode
     * @exception ParticipantAlreadyInException participant already in
     */
    public HashMap<String, Split> join(ConnectionToClient client, String splitCode, int participantId, String participantNickname) throws SplitNotFoundException, ParticipantAlreadyInException {
        Split split = getSplitByCode(splitCode);
        split.addParticipant(client,participantId,participantNickname);
        HashMap<String, Split> data = new HashMap<>();
        data.put(splitCode,split);
        return data;
    }

    /**
     * Returns split identified by the splitcode
     * @param splitCode the splitcode of the targeted split
     * @return the split found
     * @throws SplitNotFoundException no corresponding split found for the splitcode
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
     * @param splitCode the splitcode of the targeted split
     * @param participantId the id of the targeted participant
     * @return the matching split participant
     * @throws SplitNotFoundException no corresponding split found for the splitcode
     */
    public Participant getSplitParticipant(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        return getSplitByCode(splitCode).getParticipantById(participantId);
    }

    /**
     * Returns a hashmap of splits owned by the user
     * @return the splits of the user formatted in a hashmap with it's splitcode
     */
    public HashMap<String, Split> getUserSplits(int id) {
        HashMap<String, Split> userSplits = new HashMap<>();
        Iterator iterator = splits.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, Split> split = (Map.Entry) iterator.next();
            if(split.getValue().getOwnerId()==id){
                userSplits.put(split.getKey(),split.getValue());
            }
        }
        return userSplits;
    }

    /**
     * Returns a hashmap of splits owned by the user
     * @return the split formatted in a hashmap with it's splitcode
     */
    public HashMap<String, Split> getHashSplit(String splitCode) throws SplitNotFoundException {
        HashMap<String, Split> returnedSplit = new HashMap<>();
        Iterator iterator = splits.entrySet().iterator();

        while (iterator.hasNext()){
            Map.Entry<String, Split> split = (Map.Entry) iterator.next();
            if(split.getKey().equals(splitCode)){
                returnedSplit.put(split.getKey(),split.getValue());
            }
        }

        if(returnedSplit.size()==0){
            throw new SplitNotFoundException("Did not find the split");
        }
        return returnedSplit;
    }

    /**
     * Returns the number of participant in a split
     * @param splitCode the splitcode of the targeted split
     * @return the number of participant in the split
     * @throws SplitNotFoundException no corresponding split found for the splitcode
     */
    public int getNumberOfSplitParticipant(String splitCode) throws SplitNotFoundException {
        return getSplitByCode(splitCode).getNumberOfParticipant();
    }

    /**
     * Changes split participant amount
     * @param splitCode the splitcode of the targeted split
     * @param participantId the id of the targeted participant
     * @param newAmount the new amount of the participant
     * @throws SplitNotFoundException no corresponding split found for the splitcode
     */
    public Split changeParticipantAmount(String splitCode, int participantId, double newAmount) throws SplitNotFoundException, ParticipantNotFoundException, GoalAmountExceededException {
        splits.get(splitCode).changeParticipantAmount(participantId,newAmount);
        return splits.get(splitCode);
    }

    /**
     * Removes participant from split if is found
     * @param splitCode the splitcode of the targeted split
     * @param participantId the id of the targeted participant
     * @throws SplitNotFoundException no corresponding split found for the splitcode
     * @throws ParticipantNotFoundException no corresponding participant found for the id in the split
     */
    public void removeSplitParticipant(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        getSplitByCode(splitCode).removeParticipant(participantId);
    }

    /**
     * Checks if the goal amount is reached
     * @param splitcode the splitcode of the targeted split
     * @return true if the goal amount is reached else false
     * @throws SplitNotFoundException no corresponding split found for the splitcode
     */
    public boolean isSplitGoalAmountReached(String splitcode) throws SplitNotFoundException {
        return getSplitByCode(splitcode).isGoalAmountReached();
    }

    /**
     * Switches ready status state
     */
    public void switchSplitParticipantReadyStatus(String splitCode, int participantId) throws SplitNotFoundException, ParticipantNotFoundException {
        getSplitByCode(splitCode).switchParticipantReadyStatus(participantId);
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
        System.out.println(msg);

        int userId;
        String splitCode;

        switch (message.getMessage()){
            // TODO : test
            case ClientServerProtocol.CREATION_REQUEST:
                int ownerId = Integer.getInteger(((SplitOriginatorMessage) msg).getArgument("ownerId"));
                String ownerNickname = ((SplitOriginatorMessage) msg).getArgument("ownerNickname");
                Double goalAmount = Double.parseDouble(((SplitOriginatorMessage) msg).getArgument("goalAmount"));
                String label = ((SplitOriginatorMessage) msg).getArgument("label");
                String splitMode = ((SplitOriginatorMessage) msg).getArgument("splitMode");
                splitCode = createFreeSplit(ownerId,ownerNickname,goalAmount,label,splitMode);
                try {
                    HashMap<Integer, FreeSplit> split = new HashMap<>();
                    //split.put(splitCode,getSplitByCode(splitCode));
                    getSplitByCode(splitCode);
                    // TODO : reimplement after changing type to string
                } catch (SplitNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    client.sendToClient(new SplitOriginatorMessage(null, ClientServerProtocol.JOINED_SPLIT, null, null));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ClientServerProtocol.GET_SPLIT_REQUEST:
                userId = Integer.parseInt(message.getArguments().get("userId"));
                HashMap<String, Split> userSplits = getUserSplits(userId);
                try {
                    client.sendToClient(new SplitOriginatorMessage(null, ClientServerProtocol.GET_SPLIT_REQUEST,null, userSplits));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case ClientServerProtocol.JOIN_SPLIT_ATTEMPT:
                System.out.println("Join split attempt");
                userId = Integer.parseInt(message.getArguments().get("userId"));
                String nickName = message.getArgument("nickName");
                splitCode = message.getArgument("splitCode");
                try {
                    HashMap<String, Split> data = join(client,splitCode,userId,nickName);
                    try {
                        System.out.println("Success : Joined split");
                        client.sendToClient(new SplitOriginatorMessage(null,ClientServerProtocol.JOINED_SPLIT,null,data));
                        sendToParticipantUpdate(splitCode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (SplitNotFoundException e) {
                    try {
                        System.out.println("Error : Split not found");
                        client.sendToClient(new SplitOriginatorMessage(null,ClientServerProtocol.SPLIT_NOT_FOUND,null,null));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } catch (ParticipantAlreadyInException e) {
                    System.out.println("Error : Participant already in");
                    try {
                        client.sendToClient(new SplitOriginatorMessage(null,ClientServerProtocol.PARTICIPANT_ALREADY_IN_SPLIT,null,null));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
                break;
            case ClientServerProtocol.CHANGE_AMOUNT_REQUEST:
                System.out.println("Change amount request");
                userId = Integer.parseInt(message.getArguments().get("userId"));
                splitCode = message.getArgument("splitCode");
                double newAmount = Double.parseDouble(message.getArgument("newAmount"));
                try {
                    changeParticipantAmount(splitCode,userId,newAmount);
                    sendToParticipantUpdate(splitCode);
                    // TODO : Implement error handling
                } catch (SplitNotFoundException | ParticipantNotFoundException | GoalAmountExceededException e) {
                    e.printStackTrace();
                }
                break;
            case ClientServerProtocol.CHANGE_READY_STATUS:
                userId = Integer.parseInt(message.getArguments().get("userId"));
                splitCode = message.getArgument("splitCode");
                try {
                    switchSplitParticipantReadyStatus(splitCode,userId);
                    HashMap<String, Split> data = getHashSplit(splitCode);
                    try {
                        client.sendToClient(new SplitOriginatorMessage(null,ClientServerProtocol.UPDATED_SPLIT_STATE,null,data));
                        sendToParticipantUpdate(splitCode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (SplitNotFoundException | ParticipantNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            case ClientServerProtocol.QUIT_SPLIT_REQUEST:
                userId = Integer.parseInt(message.getArguments().get("userId"));
                splitCode = message.getArgument("splitCode");
                try {
                    removeSplitParticipant(splitCode,userId);
                    client.sendToClient(new SplitOriginatorMessage(null,ClientServerProtocol.QUIT_SPLIT_SUCCESS,null,null));
                    sendToParticipantUpdate(splitCode);
                } catch (SplitNotFoundException | ParticipantNotFoundException | IOException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    /**
     * Method which is called to send an update of the split state to participants
     * @param splitCode the splitcode of the targeted split
     */
    private void sendToParticipantUpdate(String splitCode){
        Split split;

        try {
            /* Create the message object */
            HashMap<String, Split> hashSplit = getHashSplit(splitCode);
            SplitOriginatorMessage update = new SplitOriginatorMessage(null,ClientServerProtocol.UPDATED_SPLIT_STATE,null,hashSplit);

            /* Get split participants */
            split = getSplitByCode(splitCode);
            HashMap<Integer,Participant> participants = split.getParticipants();

            /* Send the message to every participants */
            for (Map.Entry<Integer, Participant> integerParticipantEntry : participants.entrySet()) {
                Participant participant = integerParticipantEntry.getValue();
                try {
                    participant.getClientConnection().sendToClient(update);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (SplitNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Receives signals from the server
     * @param o the observable notifier
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

}