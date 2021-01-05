package server;

import server.facade.SplitServerFacade;
import server.models.split.Item;

public class main {

    /**
     * main launching the server handling split management
     * @param args
     */
    public static void main(String[] args)
    {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        facade.createFreeSplit(1,"testUser",63.2,"bowling",null);
        Item pizza = new Item("margarita",4.00);
        Item drink = new Item("coke",1.50);
        Item desert = new Item("tiramisu",4.50);
        Item[] items = new Item[]{pizza, drink, desert};
        System.out.println("Free split : " + facade.createFreeSplit(2,"User 2",32.2,"Pizza",null));
        System.out.println("Item split : " + facade.createItemSplit(1,"testUser","Restaurant",items,null));


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
