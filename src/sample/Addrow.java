package sample;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Addrow {
    private StringProperty  ip;
    private  StringProperty time;
    private IntegerProperty count;

    public  Addrow(String ip,int count, String time){
        this.ip=new SimpleStringProperty(ip);
        this.count=new SimpleIntegerProperty(count);
        this.time=new SimpleStringProperty(time);
    }

    public Addrow() {
        this("",0,"");
    }

    // setters
    public void setIp(String s) {this.ip.set(s);}
    public void setTime(String s) {this.time.set(s);}
    public void setCount(int s) {this.count.set(s);}


    // getters
    public void getIp(String s) {this.ip.get();}
    public void getTime(String s) {this.time.get();}
    public void getCount(int s) {this.count.get();}

    // as properties (getters)
    public StringProperty ipProperty() {return ip;}
    public StringProperty timeProperty() {return time;}
    public IntegerProperty countProperty() {return count;}



//    @Override
//    public String toString() {
//        return   "from="+getSource()+"-src"
//                +"to="+getDestination()+"-dest"
//                +"size="+getSize()
//                +"prot="+getType().toLowerCase();
//    }
}
