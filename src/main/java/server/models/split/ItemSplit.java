package server.models.split;

import core.models.StoreOwner;
import server.communication.ConnectionToClient;
import server.exception.splitException.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemSplit extends Split{

    private final List<Item> items = new ArrayList<>();
    private final HashMap<Integer,List<Item>> participantsCart = new HashMap<>();
    private boolean isOwned = false;

    /**
     * Overrides Split constructor and computes
     * the goal amount with the items provided
     * @param splitCode
     * @param ownerId
     * @param ownerNickName
     * @param label
     * @param items
     */
    public ItemSplit(String splitCode, int ownerId, String ownerNickName, String label, Item[] items, StoreOwner receiver){
        super(splitCode,ownerId,ownerNickName,label,receiver);
        double totalAmount = 0;
        for (int i = 0; i < items.length ; i++) {
            this.items.add(items[i]);
            totalAmount+= items[i].getPrice();
        }
        this.goalAmount=totalAmount;
        setSplitMode(SplitMode.ITEMSPLIT);
    }



    /**
     * TODO : javadoc
     * @param itemId
     * @return
     */
    public Item getItem(int itemId) throws UnknownItemException {
        try{
            return items.get(itemId);
        } catch(IndexOutOfBoundsException e){
            throw new UnknownItemException("Error : Unknown item requested");
        }
    }

    public List<Item> getItems(){
        return items;
    }

    /**
     * Picks the n`th item for the participant and puts it in his cart
     * @param itemId
     * @param participantId
     * @throws ItemAlreadyPickedException
     * @throws ParticipantNotFoundException
     * @throws GoalAmountExceededException
     */
    // TODO : handle concurrency
    // TODO : check if item id in the list
    // TODO : check if user is in split
    public void pickItem(int itemId, int participantId) throws ItemAlreadyPickedException, ParticipantNotFoundException, GoalAmountExceededException, UnknownItemException {
        Item item = getItem(itemId);
        if(item.isPicked()){
            throw new ItemAlreadyPickedException("This item is already picked by someone");
        } else {
            item.setPicked(true);
        }
        addItemToCart(item,participantId);
        updateParticipantAmount(participantId,item.getPrice());
    }

    /**
     * Removes an item from the user
     * @param itemId
     * @param participantId
     * @throws UnknownItemException
     * @throws GoalAmountExceededException
     * @throws ParticipantNotFoundException
     */
    public void removeItem(int itemId, int participantId) throws UnknownItemException, GoalAmountExceededException, ParticipantNotFoundException {
        Item item = getItem(itemId);
        item.setPicked(false);
        getParticipantCart(participantId).remove(item);
        updateParticipantAmount(participantId,-item.getPrice());
    }

    /**
     * Removes a participant from the split
     * set the items he picked to available
     * deletes his cart
     * sets another random participant to be the owner and if there is no one sets the split as not owned until someone joins
     * @param id
     * @throws ParticipantNotFoundException
     */
    public void removeParticipant(int id) throws ParticipantNotFoundException {
        super.removeParticipant(id);
        removeParticipantPickedItems(id);
        removeParticipantCart(id);
        if(isParticipantAdmin(id)){
            if(getParticipants().size()!=0){
                setSplitAdmin(getParticipants().entrySet().stream().findFirst().get().getKey());
                setOwned(true);
            } else {
                setOwned(false);
            }
        }

    }

    /**
     * Same behavior as Split but adds an empty cart for the participant and first participant to join becomes the owner
     * @param client
     * @param id
     * @param nickname
     * @throws ParticipantAlreadyInException
     */
    @Override
    public void addParticipant(ConnectionToClient client, int id, String nickname) throws ParticipantAlreadyInException {
        super.addParticipant(client, id, nickname);
        participantsCart.put(id,new ArrayList<>());

        if(!isOwned()){
            setSplitAdmin(id);
            setOwned(true);
        }
    }

    /**
     * Free items picked by the participant
     * @param id
     */
    private void removeParticipantPickedItems(int id){
        getParticipantCart(id).forEach((item -> {
            item.setPicked(false);
        }));
    }

    /**
     * Deletes the participant cart
     * @param id
     */
    private void removeParticipantCart(int id){
        participantsCart.remove(id);
    }

    /**
     * Update the participant amount
     * @param participantId
     * @param addedAmount
     * @throws ParticipantNotFoundException
     * @throws GoalAmountExceededException
     */
    private void updateParticipantAmount(int participantId, double addedAmount) throws ParticipantNotFoundException, GoalAmountExceededException {
        double participantCurrentAmount = getParticipantById(participantId).getAmount();
        changeParticipantAmount(participantId,participantCurrentAmount+addedAmount);

    }

    /**
     * Adds an item to the participant cart
     * @param item
     * @param participantId
     */
    private void addItemToCart(Item item, int participantId){
        getParticipantCart(participantId).add(item);
    }

    /**
     * Retrieve the participant cart
     * @param participantId
     * @return
     */
    public List<Item> getParticipantCart(int participantId){
        return participantsCart.get(participantId);
    }

    public HashMap<Integer, List<Item>> getParticipantsCart() {
        return participantsCart;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    public boolean isParticipantAdmin(int id){
        return id == getSplitAdmin();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
