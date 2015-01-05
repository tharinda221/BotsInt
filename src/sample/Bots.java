package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.ArrayList;

public class Bots {

    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    static ScheduledFuture<?> t;
    public static ObservableList<Addrow> observablePackets = FXCollections.observableArrayList();
    public static ObservableList<Addrow> observableSubs = FXCollections.observableArrayList();
    public static ObservableList<BlockedDisplay> observableBlockedip = FXCollections.observableArrayList();
    public static ObservableList<BlockedDisplay> observableBlockedSubs = FXCollections.observableArrayList();
    public static ObservableList<BlockedDisplay> observableUnBlockedip = FXCollections.observableArrayList();
    public static ObservableList<BlockedDisplay> observableUnBlockedSubs = FXCollections.observableArrayList();

    public static HashMap<String, Node> IPs = new HashMap<String, Node>();
    public static HashMap<String, Node> SubIPs = new HashMap<String, Node>();
    public static ArrayList<String> Submask = new ArrayList<String>();
    public  String ip;
    public  String time;
    public int k;

    AutomatedTelnetClient telnet = new AutomatedTelnetClient();

    private String file;

    public Bots(String file){
        this.file=file;
        RunBots(file);
    }

    public void RunBots(String fileName) {
        Scanner in = new Scanner(System.in);
        //String fileName = in.nextLine();

        try {
            @SuppressWarnings("unchecked")
            FileReader fileRd = new FileReader(fileName);
            BufferedReader bufferRd = new BufferedReader(fileRd);
            int k = 0;
            String line;

            while(true){
                k=0;
                line = bufferRd.readLine();

                if (line == null) {
                    //Node.PrintNode(IPs);
                    //Node.PrintNode(SubIPs);
                    //break;
                }
                else{
                    if (Reg.stringChecker(".*error: PAM: .*", line)) {

                        String ip=Reg.regexChecker("[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}",line);
                        String time=Reg.regexChecker("[0-9]{1,2}+\\:[0-9]{1,2}+\\:[0-9]{1,2}",line);
                        //gets Ip Address and its failed time by searching log file


                        String subipl=telnet.AutomatedTelnet("route-server.ip-plus.net", "rviews", "rviews", ip);
                        //System.out.println(subip);
                        if(Node.subNetIn(Submask, subipl) && !Node.searchNode(IPs,ip)  ){
                            if(Node.searchNode(SubIPs, subipl)){
                                Node.UpdateNode(SubIPs, subipl, 1);
                                k=Node.searchKey(SubIPs,subipl);
                                observableSubs.add(new Addrow(subipl,k,time));
                            }
                            else{
                                SubIPs.put(subipl, new Node(subipl, 2, time));
                                observableSubs.add(new Addrow(subipl,1,time));
                                observableSubs.add(new Addrow(subipl,2,time));
                            }
                        }

                        if(Node.searchNode(IPs, ip)){
                            Node.UpdateNode(IPs, ip, 1);
                            k=Node.searchKey(IPs,ip);
                            observablePackets.add(new Addrow(ip,k,time));

                        }
                        else{
                            IPs.put(ip, new Node(ip, 1, time));
                            observablePackets.add(new Addrow(ip,1,time));
                            Submask.add(subipl);
                        }




                        if(Node.SelectIpAdd(IPs, ip,2)){

                            Node.writeIPS(ip,time,".\\Blocked.txt");
                            new BlockIP(ip,"ns",ip);
                            observableBlockedip.add(new BlockedDisplay(ip,time));
                            t = executor.schedule(new UNBlockIP(ip,"ns"), 10, SECONDS);
                            t = executor.schedule(new InterfaceUnblock(ip,time,"ns"), 5, SECONDS);
                            IPs.remove(ip);

                            Submask.remove(subipl);

                        }
                        if(Node.SelectIpAdd(SubIPs, subipl,5)){

                            Node.writeIPS(subipl,time,".\\BlockedSubs.txt");
                            new BlockIP(ip,"s",subipl);
                            String b=subipl;
                            observableBlockedSubs.add(new BlockedDisplay(b,time));
                            t = executor.schedule(new UNBlockIP(subipl,"s"), 2, SECONDS);
                            t = executor.schedule(new InterfaceUnblock(subipl,time,"s"), 2, SECONDS);
                            SubIPs.remove(subipl);
                        }
                    }
                }

            }

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
