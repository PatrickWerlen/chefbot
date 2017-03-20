package discordjava.bot;

import sx.blah.discord.api.*;
import sx.blah.discord.handle.obj.*;
import sx.blah.discord.util.DiscordException;

import javax.xml.ws.http.HTTPException;
import java.util.List;


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

    public void sendPrivateMessages(IUser user, String message)
            throws DiscordException, HTTPException{
        IPrivateChannel channel = ChefBot.discordClient.getOrCreatePMChannel(ChefBot.discordClient.getUserByID(user.getID()));
        channel.sendMessage("Hello " + ChefBot.discordClient.getUserByID(user.getID()) + "!\n" + message);
    }

    public void sendPublicMessages(IChannel channel, IUser user, String message)
            throws DiscordException, HTTPException {
        channel.sendMessage("Hello " + ChefBot.discordClient.getUserByID(user.getID())+ " !\n" + message);
    }

    public void joinVoiceChannel(){
        List<IVoiceChannel> voiceChannels = ChefBot.discordClient.getVoiceChannels();
        //just joins the first in the list todo
        System.out.println(voiceChannels.get(0).getID());
        ChefBot.discordClient.getVoiceChannelByID(voiceChannels.get(0).getID()).join();
    }

    public void moveToVoiceChannel(String userID, String voiceChannelID){
        /*allows the bot to move members (who are already connected to a voicechannel)
        to another voice channel. *needs special permission for bot*/
        try {
            ChefBot.discordClient.getUserByID(userID).moveToVoiceChannel(ChefBot.discordClient.
                    getVoiceChannelByID(voiceChannelID));
        }catch(DiscordException e){
            e.printStackTrace();
        }
    }

    public void getPresence(IDiscordClient client, String userID){
        //gets the presence and the game the author is playing
        System.out.println("Hello " + client.getUserByID(userID) +
                "! Your presence is: " + client.getUserByID(userID).getPresence());
    }


}
