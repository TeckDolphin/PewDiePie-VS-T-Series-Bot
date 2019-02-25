import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.StatusChangeEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.client.*;
import net.dv8tion.jda.bot.*;
import javax.security.auth.login.LoginException;
import java.util.Scanner;


public class Main extends ListenerAdapter {
    int loopDetection = 0;

    public static void main(String[] args) throws LoginException{
        JDABuilder builder = new JDABuilder(AccountType.BOT);
        String token = "NTQ4NjIyMzU2MTM0NzU2Mzk0.D1ICig.vAJVVi6byaV8Nrod96sydeuWQC8";
        builder.setToken(token);
        builder.addEventListener(new Main());
        builder.buildAsync();



    }
    public void onMessageReceived (MessageReceivedEvent event){
        try{
            if(event.getAuthor().isBot()){
                loopDetection++;
                System.out.println("Loops Stopped!: " + loopDetection);

            }else if(event.getMessage().getContentRaw().contains("!pew")){
                System.out.println("Command received from " + event.getAuthor().getName() + " ID: " + event.getAuthor().getId() +": "
                        + event.getMessage().getContentDisplay());
                ResponseCommands messageCom = new ResponseCommands(event.getMessage().getContentRaw());
                messageCom.commandsList(event);
            }

        }catch(Exception e){
            System.out.println("Does not have permission, or something went wrong.");
        }











    }
}



