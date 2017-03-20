package discordjava.bot;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.handle.obj.IChannel;

import java.util.List;

public class ReadyEventListener implements IListener<ReadyEvent> {

    public void handle(ReadyEvent readyEvent) {
        //sends a message to every Channel that the bot is ready.
        List<IChannel> channels = ChefBot.discordClient.getChannels();
        for(IChannel channel : channels){
            channel.sendMessage("ChefBot is now ready");
        }
    }
}
