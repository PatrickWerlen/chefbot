package discordjava.bot;


import sx.blah.discord.handle.obj.*;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


public class CommandHandler extends ChefBot{
    private final IMessage message;
    private final String command;
    private final IUser user;
    private String[] args;

    //enter all commands here. -> insert in switch execute() -> create function
    private enum Commands{
        help, commands, who, sound
    }

    public CommandHandler(IMessage message, IUser user){
        this.message = message;
        this.user = user;
        this.command = getCommand(message);

        execute(this.command);

    }

    private String getCommand(IMessage message){
        String total = message.getContent();
        String command;
        if(total.contains(" ")){
            command = total.substring(1, total.indexOf(" "));
            getArgs(total.substring(total.indexOf(" ")));
        }else{
            command = total.substring(1);
        }
        return command;
    }

    private void getArgs(String message){
        this.args = new String[message.split(" ").length];
        this.args = message.split(" ");
    }

    private void execute(String command){
        try {
            Commands commands = Commands.valueOf(command);
            switch (commands) {
                case help:
                    help();
                    break;
                case commands:
                    commands();
                    break;
                case who:
                    who();
                    break;
                case sound:
                    sound();
                    break;
                default:
                    invalidCommand();
                    break;
            }
        } catch (Exception e){
            e.printStackTrace();
            invalidCommand();
        }
    }

    private void help(){
        sendPublicMessages(this.message.getChannel(), this.message.getAuthor(), "If you want to see a list with" +
                " all available commands. Tip !commands .\nHave a nice day!");
    }

    private void commands(){
        StringBuilder sb = new StringBuilder();
        sb.append(java.util.Arrays.asList(Commands.values()));
        sendPublicMessages(this.message.getChannel(), this.message.getAuthor(), "To enter a command enter \"!\" followed by one of the follwing keywords " + sb);
    }

    private void who(){
        sendPublicMessages(this.message.getChannel(), this.message.getAuthor(), "" + discordClient.getUserByID("161804305177903104") + " is the biggest faggot.");

    }

    private void sound()throws IOException, UnsupportedAudioFileException{
        joinVoiceChannel();
        new SoundManager("C:\\Users\\Patrick\\Dropbox\\discord_playlist\\mrsuicidesheep", this.message, true);
    }

    private void invalidCommand(){
        sendPublicMessages(this.message.getChannel(), this.message.getAuthor(), "There is no command: " + this.command + ".\n" +
                "If you think this command would be nice to have, don't hesitate to contact the admins.");
    }

}
