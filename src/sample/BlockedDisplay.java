package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BlockedDisplay {
    private StringProperty ip;
    private  StringProperty time;

    public BlockedDisplay(String ip,String time){
        this.ip=new SimpleStringProperty(ip);
        this.time=new SimpleStringProperty(time);

    }
    public BlockedDisplay() {
        this("","");
    }
    // setters
    public void setIp(String s) {this.ip.set(s);}
    public void setTime(String s) {this.time.set(s);}



    // getters
    public String getIp() {return this.ip.get();}
    public void getTime(String s) {this.time.get();}


    // as properties (getters)
    public StringProperty ipProperty() {return ip;}
    public StringProperty timeProperty() {return time;}

}
