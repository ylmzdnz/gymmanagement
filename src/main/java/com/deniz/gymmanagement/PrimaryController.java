package com.deniz.gymmanagement;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    public void onLessonsClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("lessons.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Gym Management");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onMembersClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("members.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Gym Management");
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void onClubsClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("clubs.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Gym Management");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
