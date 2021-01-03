package server;

import server.facade.SplitServerFacade;

public class main {

    /**
     * main launching the server handling split management
     * @param args
     */
    public static void main(String[] args)
    {
        SplitServerFacade facade = SplitServerFacade.getInstance();
        facade.createFreeSplit(1,"testUser",63.2,"bowling","freesplit");
        System.out.println(facade.createFreeSplit(2,"User 2",32.2,"Pizza","freesplit"));

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
