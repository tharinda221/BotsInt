package sample;
import java.io.*; 

public class RunCommand {
    private String cmd;
    public RunCommand(String cmd) {
    this.cmd=cmd;
    try { 

        Process p=Runtime.getRuntime().exec(cmd); 
        p.waitFor(); 
        BufferedReader reader=new BufferedReader(new InputStreamReader(p.getInputStream())); 
        String line=reader.readLine(); 
        while(line!=null) { 
            System.out.println(line); 
            line=reader.readLine(); 
        } 
    } 
    catch(IOException e1) {} 
    catch(InterruptedException e2) {} 

    //System.out.println("Done");
    } 
}
