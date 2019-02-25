import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class ResponseCommands{
    private String command;

    public ResponseCommands(String command) {
        this.command = command.substring(0, 1).toUpperCase() + command.substring(1).toLowerCase();
    }

    public String getCommand() {
        return command;
    }

    public void commandsList(MessageReceivedEvent event){
        CommandActions comActions = new CommandActions();

        switch(getCommand()){
            case "!pewhelp":
                comActions.pewHelpMenu(event);
                break;

            case "!pewsubs":
                comActions.pewSubCount(event);
                break;

            case "!pewtseries":
                comActions.tSeriesSubCount(event);
                break;

            case "!pewgap":
                comActions.pewSubGap(event);
                break;

            case "!pewtwitter":
                comActions.pewTwitter(event);
                break;

            case "!pewcredit":
                comActions.pewCredit(event);
                break;

            case "!pewdiscord":
               comActions.pewDiscord(event);
                break;

            case "!pewinvite":
                comActions.pewBotInvite(event);
                break;

            case "!pewabout":
                comActions.aboutPewBot(event);
                break;
            default:
                event.getChannel().sendMessage("Unknown command!").queue();
                event.getChannel().sendMessage("use, `!pewHelp`").queue();
        }
    }



}
