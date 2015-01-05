package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Button start;


    public TextField log;
    public TableView table;
    public TableColumn<Addrow,String> ip;
    public TableColumn<Addrow,Integer> count;
    public TableColumn<Addrow,String> time;
    public TableColumn<Addrow,String> ip1;
    public TableColumn<Addrow,Integer> count1;
    public TableColumn<Addrow,String> time1;
    public TableView table1;
    public TableView table2;
    public TableColumn<BlockedDisplay,String> ip2;
    public TableColumn<BlockedDisplay,String> time2;
    public TableView table3;
    public TableColumn<BlockedDisplay,String> ip3;
    public TableColumn<BlockedDisplay,String> time3;
    public TableView table5;
    public TableView table4;
    public TableColumn<BlockedDisplay,String> ip4;
    public TableColumn<BlockedDisplay,String> time4;
    public TableColumn<BlockedDisplay,String> ip5;
    public TableColumn<BlockedDisplay,String> time5;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                StopProcess.thread=new SleepMain(log.getText());
                StopProcess.thread.start();
            }
        });

        table.setItems(Bots.observablePackets);
        ip.setCellValueFactory(new PropertyValueFactory<Addrow, String>("ip"));
        time.setCellValueFactory(new PropertyValueFactory<Addrow, String>("time"));
        count.setCellValueFactory(new PropertyValueFactory<Addrow, Integer>("count"));

        //for subnet blocking
        table1.setItems(Bots.observableSubs);
        ip1.setCellValueFactory(new PropertyValueFactory<Addrow, String>("ip"));
        time1.setCellValueFactory(new PropertyValueFactory<Addrow, String>("time"));
        count1.setCellValueFactory(new PropertyValueFactory<Addrow, Integer>("count"));

        //for blocked IPs
        table2.setItems(Bots.observableBlockedip);
        ip2.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("ip"));
        time2.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("time"));

        //for Blocked Subnet
        table3.setItems(Bots.observableBlockedSubs);
        ip3.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("ip"));
        time3.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("time"));

        //for UnBlocked IPs
        table4.setItems(Bots.observableUnBlockedip);
        ip4.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("ip"));
        time4.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("time"));

        //for UnBlocked Subnets
        table5.setItems(Bots.observableUnBlockedSubs);
        ip5.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("ip"));
        time5.setCellValueFactory(new PropertyValueFactory<BlockedDisplay, String>("time"));

            table3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                @SuppressWarnings("rawtypes")
                ObservableList<TablePosition> cells = table3.getSelectionModel().getSelectedCells();
                for( TablePosition< BlockedDisplay, ? > cell : cells )
                {
                    String sub=Reg.regexChecker("[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}+\\.[0-9]{1,3}",Bots.observableBlockedSubs.get(cell.getRow()).getIp());
                    new RunCommand("cmd /c F:\\Languages\\BotsInt\\location_"+sub+".html");

                }

            }
        });
        table2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                @SuppressWarnings("rawtypes")
                ObservableList<TablePosition> cells1 = table2.getSelectionModel().getSelectedCells();
                for( TablePosition< BlockedDisplay, ? > cell : cells1 )
                {

                    new RunCommand("cmd /c F:\\Languages\\BotsInt\\location_"+Bots.observableBlockedip.get(cell.getRow()).getIp()+".html");

                }

            }
        });

    }
}
