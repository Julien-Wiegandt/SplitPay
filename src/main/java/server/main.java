package server;

import core.models.StoreOwner;
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

        /**
         * Creating an item list for an itemSplit
         */
        Item pizza = new Item("margarita",4.00);
        Item drink = new Item("coke",1.50);
        Item desert = new Item("tiramisu",4.50);
        Item[] items = new Item[]{pizza, drink, desert};

        /**
         * Instantiate a store owner present in the database as the receiver of the created splits
         */
        StoreOwner storeOwner = new StoreOwner("1",
                "delarte34@hotmail.com",
                "0767342312",
                "362 521 879 00034",
                "delarte34",
                "delarte34080",
                Float.parseFloat("743.67"),
                "Del arte restaurant",
                "145 Rue Alphonse Beau de Rochas, 34170 Castelnau");


        System.out.println("Free split : " + facade.createFreeSplit(2,"User 2",32.2,"Pizza",storeOwner));
        System.out.println("Item split : " + facade.createItemSplit(1,"testUser","Restaurant",items,storeOwner));




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
