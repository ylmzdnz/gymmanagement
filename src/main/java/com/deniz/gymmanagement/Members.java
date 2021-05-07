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

public class Members implements Initializable {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;
    @FXML
    private TableView resultList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
    }

    private void setup() {
        Connection conn = DBConnection.dbConn();
        ObservableList<Member> memberList = FXCollections.<Member>observableArrayList();

        TableColumn firstName = new TableColumn("FirstName");
        firstName.setCellValueFactory(new PropertyValueFactory<Member, String>("FirstName"));

        TableColumn lastName = new TableColumn("LastName");
        lastName.setCellValueFactory(new PropertyValueFactory<Member, String>("LastName"));

        TableColumn address = new TableColumn("Address");
        address.setCellValueFactory(new PropertyValueFactory<Member, String>("Address"));

        TableColumn phone = new TableColumn("Phone");
        phone.setCellValueFactory(new PropertyValueFactory<Member, String>("Phone"));

        resultList.getColumns().clear();
        resultList.getColumns().addAll(firstName, lastName, address, phone);

        try {
            String sql = "SELECT * FROM Members";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                memberList.add(new Member(
                        result.getString("FirstName"),
                        result.getString("LastName"),
                        result.getString("Address"),
                        result.getString("Phone")
                ));
            }
            resultList.setItems(memberList);
            result.close();
        } catch (SQLException throwables) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void onAddMember(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();

        String sql = "INSERT INTO Members (FirstName, LastName, Address, Phone) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, firstName.getText());
        statement.setString(2, lastName.getText());
        statement.setString(3, address.getText());
        statement.setString(4, phone.getText());
        statement.executeUpdate();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Member successfully added");
        a.setHeaderText("Added");
        a.show();

        conn.close();

        setup();
    }
}
