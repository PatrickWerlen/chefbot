package discordjava.bot;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandHandler extends ChefBot{
    private final IMessage message;
    private final String command;
    private final IUser user;
    private String[] args;

    //enter all commands here. -> insert in switch execute() -> create function
    private enum Commands{
        help, commands
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
                default:
                    invalidCommand();
                    break;
            }
        } catch (Exception e){
            invalidCommand();
        }
    }

    private void help(){
        sendPublicMessages(this.message.getChannel(), this.message.getAuthor(), "If you want to see a list with" +
                " all available commands. Tip !commands .\nHave a nice day!");
    }

    private void commands(){
        StringBuilder sb = new StringBuilder();
        System.out.println(Commands.values().toString());
    }

    private void invalidCommand(){
        sendPublicMessages(this.message.getChannel(), this.message.getAuthor(), "There is no command: " + this.command + ".\n" +
                "If you think this command would be nice to have, don't hesitate to contact the admins.");
    }

}
