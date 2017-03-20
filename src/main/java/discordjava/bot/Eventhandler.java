package discordjava.bot;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IChannel;
import sx.blah.discord.handle.obj.IPrivateChannel;
import sx.blah.discord.handle.obj.IVoiceChannel;
import sx.blah.discord.util.DiscordException;

import javax.xml.ws.http.HTTPException;
import java.util.List;
import java.util.Objects;

public class Eventhandler {

    @EventSubscriber
    public void onReadyEvent(ReadyEvent event){
        System.out.println("Bot ready");
    }


    @EventSubscriber
    public void onMessageEvent(MessageReceivedEvent event){
        //checks if message is a command.
        if(isCommand(event.getMessage().toString())){
            //creates CommandHandler, who will process the command
            new CommandHandler(event.getMessage(), event.getAuthor());
        }
        else return; //messages who are not commands are ignored by the bot.
    }



    /***THESE ARE ALL RANDOM TESTFUNCTIONS, WHO
     * CAN BE USED LATER TO EXECUTE COMMANDS
     * -> CommandHandler.java              ***/


    public void sendPrivateMessages(String userID, String message)
            throws DiscordException, HTTPException{
        IPrivateChannel channel = ChefBot.discordClient.getOrCreatePMChannel(ChefBot.discordClient.getUserByID(userID));
        channel.sendMessage("Hello " + ChefBot.discordClient.getUserByID(userID).getName() + "!\n" + message);
    }

    public void sendPublicMessages(String channelID, String userID, String message)
            throws DiscordException, HTTPException{
        IChannel channel = ChefBot.discordClient.getChannelByID(channelID);
        channel.sendMessage("Hello " + ChefBot.discordClient.getUserByID(userID).getName() + "!\n" + message);
    }

    public void joinVoiceChannel(){
        List<IVoiceChannel> voiceChannels = ChefBot.discordClient.getVoiceChannels();
        //just joins the first in the list
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
        System.out.println("Hello @" + client.getUserByID(userID).toString() +
                "! Your presence is: " + client.getUserByID(userID).getPresence());
    }

    public boolean isCommand(String message){
        if(message.startsWith("!")) return true;
        return false;
    }

    public void commandHandler(String message, String userID, String channelID){
        //enter all commands here and handle them further in a seperate function
        if(Objects.equals(message, new String("!commands"))) printAllCommands(channelID, userID);

        else sendPublicMessages(channelID, userID, "There is no such command. Sorry!");

    }

    public void printAllCommands(String channelID, String userID){
        sendPublicMessages(channelID, userID, "!command1 -- Command1 can do this\n" +
                "!command2 -- Command2 can do this\n" +
                "!command3 -- Command3 can do this\n");
    }

}
