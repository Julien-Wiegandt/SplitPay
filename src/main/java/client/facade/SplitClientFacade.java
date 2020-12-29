package client.facade;

import client.ObservableClient;
import core.facade.UserFacade;
import server.SplitOriginatorMessage;
import server.facade.SplitServerFacade;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class SplitClientFacade implements Observer
{
    // Class variables ***************************************************

    /**
     * Indicates a close of the connection to server.
     */
    public static final String CONNECTION_CLOSED = "#OC:Connection closed.";

    /**
     * Indicates establishment of a connection to server.
     */
    public static final String CONNECTION_ESTABLISHED = "#OC:Connection established.";

    /**
     * The string sent to the observers when a client has connected.
     */
    public static final String JOINED_SPLIT= "#OS:Joined Split";

    /**
     * The string sent to the observers when a client has connected.
     */
    public static final String GET_SPLIT_REQUEST= "#OS:Get Split Request";

    //Instance variables **********************************************

    /**
     * The interface type variable.  It allows the implementation of
     * the display method in the client.
     */
    Object clientUI;
    ObservableClient communicationService;

    // Variable that holds de name chosen by the user when created

    String name;

    /**
     * Hook method called after the connection has been closed.
     * The default implementation does nothing. The method
     * may be overriden by subclasses to perform special processing
     * such as cleaning up and terminating, or attempting to
     * reconnect.
     */
    protected void connectionClosed() {
//        clientUI.display("Connection closed correctly");
        System.out.println("Connection closed correctly");
    }

    /**
     * Hook method called each time an exception is thrown by the
     * client's thread that is waiting for messages from the server.
     * The method may be overridden by subclasses.
     *
     * @param exception the exception raised.
     */
    protected void connectionException(Exception exception) {
//        clientUI.display("Lost connection with the server");
        System.out.println("Lost connection with the server");
        quit();
    }

    /**
     * Hook method called after a connection has been established.
     * The default implementation does nothing.
     * It may be overridden by subclasses to do anything they wish.
     */
    protected void connectionEstablished() {
//        clientUI.display("Connection established successfully");
        System.out.println("Connection established successfully");

    }



    //Constructors ****************************************************

    /**
     * Constructs an instance of the chat client.
     *
     * @param host The server to connect to.
     * @param port The port number to connect on.
     * @param clientUI The interface type variable.
     * TODO : Replace ChatIF by controller
     */

    private static SplitClientFacade instance;

    public static SplitClientFacade getInstance() throws IOException {
        if (instance == null) {
            synchronized(SplitServerFacade.class) {
                if (instance == null) {
                    instance = new SplitClientFacade("localhost",5555);
                }
            }
        }
        return instance;
    }

    private SplitClientFacade(String host, int port)
            throws IOException
    {
        this.clientUI = clientUI;
        communicationService = new ObservableClient(host, port);
        communicationService.addObserver(this);
        communicationService.openConnection();
    }


    //Instance methods ************************************************

    /**
     * This method handles all data that comes in from the server.
     *
     * @param msg The message from the server.
     */
    public void handleMessageFromServer(Object msg)
    {
        SplitOriginatorMessage msgReceived = (SplitOriginatorMessage) msg;
        System.out.println("Received message");
        switch ((String) msgReceived.getMessage()){
            case GET_SPLIT_REQUEST:
                System.out.println(msgReceived.getSplits());
                break;
            case "exception":
                System.out.println("exception");
        }

    }

    public void sendToServer(SplitOriginatorMessage message){
        try{
            communicationService.sendToServer(message);
        }
        catch(IOException e){
//            clientUI.display
//                    ("Could not send message to server.  Terminating client.");
            System.out.println("Could not send message to server.  Terminating client.");
            e.printStackTrace();

            quit();
        }
    }

    /**
     * This method terminates the client.
     */
    public void quit()
    {
        try
        {
            communicationService.closeConnection();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *
     * TODO : implement
     */
    public void joinSplit(String splitCode){

    }

    /**
     *
     */
    public void getSplits() throws IOException {
        String id = UserFacade.getUserFacade().getLoggedUser().getId();

        HashMap<String,String> arguments = new HashMap<>();
        arguments.put("userId",id);

        SplitOriginatorMessage message = new SplitOriginatorMessage(null,GET_SPLIT_REQUEST,arguments,null);

        sendToServer(message);
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        SplitOriginatorMessage msg = (SplitOriginatorMessage) arg;
        String message = (String) msg.getMessage();
        if(CONNECTION_CLOSED.contentEquals(message)){
            connectionClosed();
        }
        else if (CONNECTION_ESTABLISHED.contentEquals(message)){
            connectionEstablished();
        }
        else {
            handleMessageFromServer(msg);
        }
    }

}