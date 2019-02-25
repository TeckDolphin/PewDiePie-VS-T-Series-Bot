import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandActions{
    private DataReading reading = new DataReading();


    public void pewHelpMenu (MessageReceivedEvent event) {
        event.getChannel().sendMessage
                ("Here's the list:").queue();
        event.getChannel().sendMessage
                ("```!pewHelp - For list of commands!\n!pewSubs - For pewdiepie's current subcount! \n!pewTSeries - For T-Series current subcount! \n!pewGap - The current gap between pewdiepie and T-Series! \n!pewCredit - Who made the bot? \n!pewDiscord - The bot's official discord! \n!pewInvite - Link to invite the bot to other servers \n!pewTwitter - Pewdiepie's Current Twitter! \n!pewAbout - For more information about the bot!```").queue();
    }
    public void pewSubCount(MessageReceivedEvent event){
        reading.pewSubscriberCountUpdate();
        event.getChannel().sendMessage
                ("Pewdiepie currently has " + reading.getPewCurrentSubCount() + " subscribers!").queue();
        event.getChannel().sendMessage(
                "https://socialblade.com/youtube/user/pewdiepie/realtime").queue();
    }

    public void tSeriesSubCount(MessageReceivedEvent event){
        reading.tSeriesUpdate();
        event.getChannel().sendMessage
                ("T-Series currently has " + reading.getTSeriesSubCount()).queue();
        event.getChannel().sendMessage(
                "https://socialblade.com/youtube/user/tseries/realtime").queue();

    }

    public void pewSubGap(MessageReceivedEvent event){
        reading.tSeriesUpdate();
        reading.pewSubscriberCountUpdate();
        reading.calcSubGap();
        reading.getSubGapString(event);

    }

    public void pewCredit(MessageReceivedEvent event){

        event.getChannel().sendMessage(
                "I made this bot for the soul purpose of doing my part, I'm doing my part how about you?").queue();
        event.getChannel().sendMessage("https://www.youtube.com/channel/UCrjE_AM8RL20JtKheKJ4Q2Q?").queue();
    }

    public void pewDiscord(MessageReceivedEvent event){
        event.getChannel().sendMessage("Here's my discord link!").queue();
        event.getChannel().sendMessage("https://discord.gg/xFMfXp9").queue();

    }

    public void pewBotInvite(MessageReceivedEvent event){
        event.getChannel().sendMessage("Here's my invite link to add me to other servers!").queue();
        event.getChannel().sendMessage("https://discordapp.com/oauth2/authorize?&client_id=548622356134756394&scope=bot&permissions=3591232").queue();
    }

    public void pewTwitter(MessageReceivedEvent event){
        event.getChannel().sendMessage("Here's pewdiepie's twitter!").queue();
        event.getChannel().sendMessage("https://twitter.com/pewdiepie").queue();


    }

    public void pewWarningUnder10K(MessageReceivedEvent event){
        event.getChannel().sendMessage("@everyone PEWDIEPIE JUST HIT THE 10k SUBGAP MARK!!!").queue();
        event.getChannel().sendMessage("https://socialblade.com/youtube/user/pewdiepie/realtime").queue();


    }

    public void pewWarningUnder5K(MessageReceivedEvent event){
        event.getChannel().sendMessage("@everyone PEWDIEPIE JUST HIT THE 5k SUBGAP MARK BATTLE STATIONS EVERYONE!!!").queue();
        event.getChannel().sendMessage("https://socialblade.com/youtube/user/pewdiepie/realtime").queue();


    }

    public void aboutPewBot(MessageReceivedEvent event){
        event.getChannel().sendMessage("```I made this bot in order to keep track of pewdiepie and the 9 year old army during the current battle."
        + "\nThis bot will \"@\" everyone once or twice if the subgap between T-Series gets dangerously low"
                + "\nI'm still adding more to this bot, if you have any suggestions please join the discord!```").queue();


        pewDiscord(event);
    }




}
