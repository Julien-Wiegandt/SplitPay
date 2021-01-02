package server.models;

import server.ConnectionToClient;
import server.exception.GoalAmountExceededException;
import server.exception.ItemAlreadyPicked;
import server.exception.ParticipantAlreadyInException;
import server.exception.ParticipantNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemSplit extends Split{

    private List<Item> items = new ArrayList<>();
    private HashMap<Integer,List<Item>> participantsCart = new HashMap<>();

    public ItemSplit(String splitCode, int ownerId, String ownerNickName, String label, String splitMode,Item[] items){
        super(splitCode,ownerId,ownerNickName,label,splitMode);
        double totalAmount = 0;
        for (int i = 0; i < items.length ; i++) {
            System.out.println(items[i].getPrice());
            this.items.add(items[i]);
            totalAmount+= items[i].getPrice();
        }
        System.out.println(totalAmount);
        this.goalAmount=totalAmount;
    }

    public Item getItem(int itemId){
        return items.get(itemId);
    }

    // TODO : handle concurrency
    public void pickItem(int itemId, int participantId) throws ItemAlreadyPicked, ParticipantNotFoundException, GoalAmountExceededException {
        Item item = getItem(itemId);
        if(item.isPicked()){
            throw new ItemAlreadyPicked("This item is already picked by someone");
        } else {
            item.setPicked(true);
        }
        addItemToCart(item,participantId);
        updateParticipantAmount(participantId,item.getPrice());
    }

//    TODO : implement
    public void removeItem(int itemId, int participantId){}

    public void removeParticipant(int id) throws ParticipantNotFoundException {
        Participant participant = getParticipantById(id);
        participant.setAmount(0);
        super.removeParticipant(id);
    }

    private void removeParticipantPickedItems(int id){
        getParticipantCart(id).forEach((item -> {
            item.setPicked(false);
        }));

    }

    private void removeParticipantCart(int id){
        participantsCart.remove(id);
    }

    private void updateParticipantAmount(int participantId, double addedAmount) throws ParticipantNotFoundException, GoalAmountExceededException {
        double participantCurrentAmount = getParticipantById(participantId).getAmount();
        changeParticipantAmount(participantId,participantCurrentAmount+addedAmount);

    }

    private void addItemToCart(Item item, int participantId){
        getParticipantCart(participantId).add(item);
    }

    private List<Item> getParticipantCart(int participantId){
        return participantsCart.get(participantId);
    }

    /**
     * Same behavior as Split but adds an empty cart for the participant
     * @param client
     * @param id
     * @param nickname
     * @throws ParticipantAlreadyInException
     */
    @Override
    public void addParticipant(ConnectionToClient client, int id, String nickname) throws ParticipantAlreadyInException {
        super.addParticipant(client, id, nickname);
        participantsCart.put(id,new ArrayList<>());
    }
}
