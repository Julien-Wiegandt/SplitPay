package util;


public class ClientServerProtocol {

    /**
     * The string sent to the observers when a client requests a split cretion.
     */
    public static final String SPLIT_CREATION_REQUEST = "#OS:Split Creation Request";

    /**
     * The string sent to the client when a split was created
     */
    public static final String SPLIT_CREATED_RESPONSE= "#OS:Split Created Response";

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

    /**
     * The string sent to the observers when a client tries to change his amount in a split.
     */
    public static final String CHANGE_AMOUNT_REQUEST= "#OS:Change Amount Request";

    /**
     * The string sent to the observers when a client tries to change his amount in a split.
     */
    public static final String UPDATED_SPLIT_STATE= "#OS:Updated Split State";

    /**
     * The string sent to the observers when a participant tries to change his ready status state.
     */
    public static final String CHANGE_READY_STATUS= "#OS:Change Ready Status";

    /**
     * The string sent to the server when a participant tries to quit a split.
     */
    public static final String QUIT_SPLIT_REQUEST= "#OS:Quit Split Request";

    /**
     * The string sent to the server when a participant quit the split successfully.
     */
    public static final String QUIT_SPLIT_SUCCESS= "#OS:Quit Split Request";

    /**
     * The string sent to the server when a participant tries to pick an item in a split.
     */
    public static final String PICK_ITEM_ATTEMPT= "#OS:Pick Item Attempt";

    /**
     * The string sent to the server when a participant tries to remove an item in a split.
     */
    public static final String REMOVE_ITEM_ATTEMPT= "#OS:Remove Item Attempt";

    /**
     * The string sent to the server when a participant a split owner tries to pay a split.
     */
    public static final String SPLIT_PAYMENT_REQUEST= "#OS:Split Payment Request";

    /**
     * The string sent to the client when a split is paid
     */
    public static final String SPLIT_PAID_RESPONSE= "#OS:Split Paid Response";
}
