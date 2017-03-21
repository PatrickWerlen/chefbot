package discordjava.bot;

import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IUser;

import java.util.List;

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

    public boolean isCommand(String message){
        if(message.startsWith("!")) return true;
        return false;
    }

}
