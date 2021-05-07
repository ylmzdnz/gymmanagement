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

public class Lessons implements Initializable {
    @FXML
    private TableView resultList;
    @FXML
    private TextField title;
    @FXML
    private TextField ptName;
    @FXML
    private TextField price;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setup();
    }

    private void setup() {
        Connection conn = DBConnection.dbConn();
        ObservableList<Lesson> lessonObservableList = FXCollections.<Lesson>observableArrayList();

        TableColumn titleColumn = new TableColumn("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<Lesson, String>("Title"));

        TableColumn ptNameColumn = new TableColumn("PTName");
        ptNameColumn.setCellValueFactory(new PropertyValueFactory<Lesson, String>("PTName"));

        TableColumn priceColumn = new TableColumn("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<Lesson, String>("Price"));

        resultList.getColumns().clear();
        resultList.getColumns().addAll(titleColumn, ptNameColumn, priceColumn);

        try {
            String sql = "SELECT * FROM Lessons";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                lessonObservableList.add(new Lesson(
                        result.getString("Title"),
                        result.getString("PTName"),
                        result.getDouble("Price")
                ));
            }
            resultList.setItems(lessonObservableList);
            result.close();
        } catch (SQLException throwables) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void onAddLesson(ActionEvent actionEvent) throws Exception {
        Connection conn = DBConnection.dbConn();

        String sql = "INSERT INTO Lessons (Title, PTName, Price) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, title.getText());
        statement.setString(2, ptName.getText());
        statement.setDouble(3, Double.parseDouble(price.getText()));
        statement.executeUpdate();

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Success");
        a.setContentText("Lesson successfully added");
        a.setHeaderText("Added");
        a.show();

        conn.close();

        setup();
    }
}
