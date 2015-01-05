package sample;


import java.util.concurrent.ScheduledFuture;

public class InterfaceUnblock implements Runnable {

    private  String ip;
    private String time;
    static ScheduledFuture<?> t;
    private int k;
    private String s;

    public InterfaceUnblock(String ip,String time,String s){
        this.ip=ip;
        this.time=time;
        this.s=s;
    }
    public void run(){
        k=0;
        RunThis();
        k++;
        if (k>0) {
            t.cancel(true);
        }
    }
    public void RunThis(){

        if(s.equals("ns")){
            Bots.observableUnBlockedip.add(new BlockedDisplay(ip,time));
        }
        else{
            Bots.observableUnBlockedSubs.add(new BlockedDisplay(Reg.Subnet(ip),time));
        }
    }
}
