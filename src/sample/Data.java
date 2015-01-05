package sample;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

public class Data {

    private String time;
    private String ip;



    public Data(String ip, String time){

        this.ip=ip;
        this.time=time;
    }
    public void printData(String file){

        try{
            //System.out.println(ip+" "+time);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
            writer.write(ip+" "+time);
            writer.write("\r\n");
            writer.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}