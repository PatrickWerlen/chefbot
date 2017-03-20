package discordjava.bot;

import sx.blah.discord.api.*;
import sx.blah.discord.util.DiscordException;


public class ChefBot{
    public static IDiscordClient discordClient;


    public static void main( String[] args ){
        discordClient = getClient("MjkyMjM4MzQ5MDIzMDUxNzc2.C61Hyg.8pk5ogBb9zpV_8aVYdCzJLedLO8");
        discordClient.getDispatcher().registerListener(new Eventhandler());
        discordClient.getDispatcher().registerListener(new ReadyEventListener());
    }

    public static IDiscordClient getClient(String token) throws DiscordException{
        return new ClientBuilder().withToken(token).login();
    }



}
