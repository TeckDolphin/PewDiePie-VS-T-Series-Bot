import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class MileStoneTimer {
    private DataReading access = new DataReading();
    private CommandActions actions = new CommandActions();

    public void mileStoneChecker(MessageReceivedEvent event){
        boolean remember = false;
        boolean remember2 = false;

        if(access.getSubGapResult() <= 10000 && access.getSubGapResult() >= 5000 && !remember){
            actions.pewWarningUnder10K(event);
            remember = true;
            remember2 = false;
        }else if(access.getSubGapResult() <= 5000 && access.getSubGapResult() >= 0 && !remember2) {
            actions.pewWarningUnder5K(event);
            remember2 = true;
            remember = false;
        }

    }

    public void timerCheck(MessageReceivedEvent event){
        access.pewSubscriberCountUpdate();
        access.tSeriesUpdate();
        access.calcSubGap();

        Thread thread = new Thread();
        int time = 300;

        try{
            for (int i = time; i >= 0; i--) {
                thread.sleep(1000);
                System.out.println(time);
            }

        }catch(Exception e){
            System.out.println("It broke lol fix it!");

        }

        timerCheck(event);
        mileStoneChecker(event);



    }

}

