package discordjava.bot;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.handle.obj.IUser;

public class CommandHandler {
    private final IMessage message;
    private final String command;
    private final IUser user;
    private String[] args;

    //enter all commands here. -> insert in switch execute() -> create function
    private enum Commands{
        help, hello, faggot, commands
    }

    public CommandHandler(IMessage message, IUser user){
        this.message = message;
        this.user = user;
        this.command = getCommand(message);

        execute(this.command);

    }

    private void execute(String command){
        try {
            Commands commands = Commands.valueOf(command);
            switch (commands) {
                case help:
                    System.out.println("help");
                    break;
                case commands:
                    System.out.println("commands");
                    break;
                case faggot:
                    System.out.println("faggot");
                    break;
                case hello:
                    System.out.println("hello");
                    break;
                default:
                    System.out.println("command not valid");
                    break;
            }
        } catch (Exception e){
            System.out.println("command not valid");
        }
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

}
