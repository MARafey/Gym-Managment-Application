package com.example.demo2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableUserDisplayController {

    @FXML
    private TableView<Toadd> ClinetDisplay;

    @FXML
    private TableColumn<Toadd, String> nameColumn;

    @FXML
    private TableColumn<Toadd, String> emailColumn;

    @FXML
    private TableColumn<Toadd, String> cnicColumn;

    @FXML
    private TableColumn<Toadd, String> phoneNumberColumn;

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        cnicColumn.setCellValueFactory(cellData -> cellData.getValue().cnicProperty());
        phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
    }

    public void setTableData(ObservableList<Toadd> data) {
        ClinetDisplay.setItems(data);
    }
}