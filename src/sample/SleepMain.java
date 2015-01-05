package sample;

import java.io.IOException;

public class SleepMain extends Thread {

    private  String file;

    public SleepMain(String file){
        this.file=file;
    }

    public void run(){
        new Bots(file);

    }
}
