package com.deniz.gymmanagement;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Clubs implements Initializable {
    @FXML
    private TableView resultList;
    @FXML
    private TextField name;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
    }

    private void setup() {
        Connection conn = DBConnection.dbConn();
        ObservableList<Club> clubList = FXCollections.<Club>observableArrayList();

        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("Name"));

        TableColumn addressColumn = new TableColumn("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("Address"));

        TableColumn phoneColumn = new TableColumn("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Club, String>("Phone"));

        resultList.getColumns().clear();
        resultList.getColumns().addAll(nameColumn, addressColumn, phoneColumn);

        try {
            String sql = "SELECT * FROM Clubs";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                clubList.add(new Club(
                        result.getString("Name"),
                        result.getString("Address"),
                        result.getString("Phone")
                ));
            }
            resultList.setItems(clubList);
            result.close();
        } catch (SQLException throwables) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAddClub(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();

        String sql = "INSERT INTO Clubs (Name, Address, Phone) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, name.getText());
        statement.setString(2, address.getText());
        statement.setString(3, phone.getText());
        statement.executeUpdate();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Club successfully added");
        a.setHeaderText("Added");
        a.show();

        conn.close();

        setup();
    }
}
