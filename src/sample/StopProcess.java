package sample;

public class StopProcess  {
    public static Thread thread;
    public static boolean stopOrNot=false;
    
    public static void StopProcess() throws InterruptedException {
        thread.join();
    }
}

